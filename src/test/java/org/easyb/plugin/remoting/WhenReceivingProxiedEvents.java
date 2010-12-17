package org.easyb.plugin.remoting;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import groovy.lang.Binding;
import org.easyb.BehaviorStep;
import org.easyb.StoryContext;
import org.easyb.domain.Story;
import org.easyb.domain.GroovyShellConfiguration;
import org.easyb.listener.ExecutionListener;
import org.easyb.result.Result;
import org.easyb.util.BehaviorStepType;

import static org.easymock.EasyMock.*;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WhenReceivingProxiedEvents {
  private RemoteExecutionListener listener;
  private ObjectOutputStream outputStream;

  class RemotingExecutionListenerAdapter implements RemotingExecutionListener {
    public RemotableBehaviorStep step;
    public RemotableBehavior behavior;
    public String describe;
    public Result result;
    public boolean stop = false;
    public boolean complete = false;

    public void startStep(RemotableBehaviorStep data) {
      this.step = data;
    }

    public void startBehavior(RemotableBehavior data) {
      this.behavior = data;
    }

    public void describeStep(String data) {
      this.describe = data;
    }

    public void gotResult(Result data) {
      this.result = data;
    }

    public void stopStep() {
      stop = true;
    }

    public void stopBehavior(RemotableBehaviorStep step, RemotableBehavior data) {
      this.step = step;
      this.behavior = data;
    }

    public void completeTesting() {
      complete = true;
    }
  }

  @Before
  public void setUp() throws Exception {
    listener = new RemoteExecutionListener();
    listener.start();

    Socket socket = new Socket(InetAddress.getLocalHost(), listener.getPort());
    outputStream = new ObjectOutputStream(socket.getOutputStream());
  }

  @After
  public void tearDown() throws IOException {
    listener.stop();

    RemotingExecutionListener receiver = createMock(RemotingExecutionListener.class);
    receiver.completeTesting();
    expectLastCall();
    replay(receiver);

    sendEventAndVerifyReceipt(new Event(EventType.COMPLETE_TESTING), receiver);

    outputStream.close();
  }

  @Test
  public void shouldReceiveStartBehaviorEvent() throws IOException {
    Story story = new Story(new GroovyShellConfiguration(), "Transferring funds", null);

    RemotingExecutionListenerAdapter adapter = new RemotingExecutionListenerAdapter();

    sendEventAndVerifyReceipt(new Event(EventType.START_BEHAVIOR, story), adapter);

    assertThat(adapter.behavior).isNotNull();
    assertThat(adapter.behavior.getPhrase()).isEqualTo("Transferring funds");
  }

  @Test
  public void shouldReceiveStartStepEvent() throws IOException {
    BehaviorStep step = new BehaviorStep(BehaviorStepType.STORY, "Transferring funds");

    RemotingExecutionListenerAdapter adapter = new RemotingExecutionListenerAdapter();

    sendEventAndVerifyReceipt(new Event(EventType.START_STEP, step), adapter);

    assertThat(adapter.step.getStepType()).isEqualTo(BehaviorStepType.STORY);
    assertThat(adapter.step.getName()).isEqualTo("Transferring funds");
  }

  @Test
  public void scenarioShouldBeTaggedWithWhereCounterPrefix() throws IOException {
    BehaviorStep step = new BehaviorStep(BehaviorStepType.SCENARIO, "Transferring funds");
    StoryContext ctx = new StoryContext((Binding)null);
    ctx.setExampleData(new Object());
    step.setStoryContext(ctx); // this ensures it detects that we have the RemotableBehaviorStep detect we may have multiple steps

    RemotingExecutionListenerAdapter adapter = new RemotingExecutionListenerAdapter();

    sendEventAndVerifyReceipt(new Event(EventType.START_STEP, step), adapter);

    assertThat(adapter.step.getStepType()).isEqualTo(BehaviorStepType.SCENARIO);
    assertThat(adapter.step.getName()).isEqualTo("(run 1) Transferring funds");

    sendEventAndVerifyReceipt(new Event(EventType.START_STEP, step), adapter);
    assertThat(adapter.step.getStepType()).isEqualTo(BehaviorStepType.SCENARIO);
    assertThat(adapter.step.getName()).isEqualTo("(run 2) Transferring funds");
  }

  @Test
  public void scenarioTaggedByWhereCounterDetectsContextChange() throws IOException {
    scenarioShouldBeTaggedWithWhereCounterPrefix();
    scenarioShouldBeTaggedWithWhereCounterPrefix();
  }

  @Test
  public void shouldReceiveDescribeStepEvent() throws IOException {
    RemotingExecutionListenerAdapter adapter = new RemotingExecutionListenerAdapter();

    sendEventAndVerifyReceipt(new Event(EventType.DESCRIBE_STEP, "Description"), adapter);

    assertThat(adapter.describe).isEqualTo("Description");
  }

  @Test
  public void shouldReceiveGotResultEvent() throws IOException {
    Result result = new Result(Result.SUCCEEDED);

    RemotingExecutionListenerAdapter adapter = new RemotingExecutionListenerAdapter();

    sendEventAndVerifyReceipt(new Event(EventType.GOT_RESULT, result), adapter);

    assertThat(adapter.result).isEqualTo(result);
  }

  @Test
  public void shouldReceiveStopStepEvent() throws IOException {
    RemotingExecutionListenerAdapter adapter = new RemotingExecutionListenerAdapter();

    sendEventAndVerifyReceipt(new Event(EventType.STOP_STEP), adapter);

    assertThat(adapter.stop).isTrue();
  }

  @Test
  public void shouldReceiveStopBehaviorEvent() throws IOException {
    Story story = new Story(new GroovyShellConfiguration(), "Transferring funds2", null);

    RemotingExecutionListenerAdapter adapter = new RemotingExecutionListenerAdapter();

    sendEventAndVerifyReceipt(new Event(EventType.STOP_BEHAVIOR, story), adapter);

    assertThat(adapter.behavior.getPhrase()).isEqualTo("Transferring funds2");
  }

  @Test
  public void shouldReceiveMultipleSubsequentEvents() throws IOException {
    shouldReceiveStartBehaviorEvent();
    shouldReceiveStopBehaviorEvent();
  }

  private void sendEventAndVerifyReceipt(Event event, RemotingExecutionListener receiver) throws IOException {
    listener.setReceiver(receiver);
    writeEventToSocket(event);
  }

  private void writeEventToSocket(Event event) throws IOException {
    outputStream.writeObject(event);

    // TODO Find a better way to wait for the listener to do its work
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
