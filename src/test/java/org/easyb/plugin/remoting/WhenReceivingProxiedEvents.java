package org.easyb.plugin.remoting;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.easyb.BehaviorStep;
import org.easyb.domain.Story;
import org.easyb.domain.GroovyShellConfiguration;
import org.easyb.listener.ExecutionListener;
import org.easyb.result.Result;
import org.easyb.util.BehaviorStepType;
import static org.easymock.EasyMock.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WhenReceivingProxiedEvents {
    private RemoteExecutionListener listener;
    private ObjectOutputStream outputStream;

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

        ExecutionListener receiver = createMock(ExecutionListener.class);
        receiver.completeTesting();
        expectLastCall();
        replay(receiver);

        sendEventAndVerifyReceipt(new Event(EventType.COMPLETE_TESTING, null), receiver);

        outputStream.close();
    }

    @Test
    public void shouldReceiveStartBehaviorEvent() throws IOException {
        Story story = new Story(new GroovyShellConfiguration(), "Transferring funds", null);

        ExecutionListener receiver = createMock(ExecutionListener.class);
        receiver.startBehavior(story);
        expectLastCall();
        replay(receiver);

        sendEventAndVerifyReceipt(new Event(EventType.START_BEHAVIOR, story), receiver);
    }

    @Test
    public void shouldReceiveStartStepEvent() throws IOException {
        BehaviorStep step = new BehaviorStep(BehaviorStepType.STORY, "Transferring funds");

        ExecutionListener receiver = createMock(ExecutionListener.class);
        receiver.startStep(step);
        expectLastCall();
        replay(receiver);

        sendEventAndVerifyReceipt(new Event(EventType.START_STEP, step), receiver);
    }

    @Test
    public void shouldReceiveDescribeStepEvent() throws IOException {
        ExecutionListener receiver = createMock(ExecutionListener.class);
        receiver.describeStep("Description");
        expectLastCall();
        replay(receiver);

        sendEventAndVerifyReceipt(new Event(EventType.DESCRIBE_STEP, "Description"), receiver);
    }

    @Test
    public void shouldReceiveGotResultEvent() throws IOException {
        Result result = new Result(Result.SUCCEEDED);

        ExecutionListener receiver = createMock(ExecutionListener.class);
        receiver.gotResult(result);
        expectLastCall();
        replay(receiver);

        sendEventAndVerifyReceipt(new Event(EventType.GOT_RESULT, result), receiver);
    }

    @Test
    public void shouldReceiveStopStepEvent() throws IOException {
        ExecutionListener receiver = createMock(ExecutionListener.class);
        receiver.stopStep();
        expectLastCall();
        replay(receiver);

        sendEventAndVerifyReceipt(new Event(EventType.STOP_STEP, null), receiver);
    }

    @Test
    public void shouldReceiveStopBehaviorEvent() throws IOException {
        Story story = new Story(new GroovyShellConfiguration(), "Transferring funds", null);

        ExecutionListener receiver = createMock(ExecutionListener.class);
        receiver.stopBehavior(null, story);
        expectLastCall();
        replay(receiver);

        sendEventAndVerifyReceipt(new Event(EventType.STOP_BEHAVIOR, story), receiver);
    }

    @Test
    public void shouldReceiveMultipleSubsequentEvents() throws IOException {
        shouldReceiveStartBehaviorEvent();
        shouldReceiveStopBehaviorEvent();
    }

    private void sendEventAndVerifyReceipt(Event event, ExecutionListener receiver) throws IOException {
        listener.setReceiver(receiver);
        writeEventToSocket(event);
        verify(receiver);
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
