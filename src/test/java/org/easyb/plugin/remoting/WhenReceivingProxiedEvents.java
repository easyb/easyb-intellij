package org.easyb.plugin.remoting;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.disco.easyb.BehaviorStep;
import org.disco.easyb.domain.Story;
import org.disco.easyb.listener.ExecutionListener;
import org.disco.easyb.util.BehaviorStepType;
import static org.easymock.EasyMock.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class WhenReceivingProxiedEvents {
    private RemoteExecutionListener listener;

    @Before
    public void setUp() {
        listener = new RemoteExecutionListener();
        listener.start();
    }

    @After
    public void tearDown() {
        listener.stop();
    }

    @Test
    public void shouldSendStartBehaviorEvent() throws IOException {
        Story story = new Story("Transferring funds", null);

        ExecutionListener receiver = createMock(ExecutionListener.class);
        receiver.startBehavior(story);
        expectLastCall();
        replay(receiver);

        sendEventAndVerifyReceipt(new Event(EventType.START_BEHAVIOR, story), receiver);
    }

    @Test
    public void shouldSendStartStepEvent() throws IOException {
        BehaviorStep step = new BehaviorStep(BehaviorStepType.STORY, "Transferring funds");

        ExecutionListener receiver = createMock(ExecutionListener.class);
        receiver.startStep(step);
        expectLastCall();
        replay(receiver);

        sendEventAndVerifyReceipt(new Event(EventType.START_STEP, step), receiver);
    }

    @Test
    @Ignore
    public void shouldSendDescribeStepEvent() throws IOException {
    }

    @Test
    @Ignore
    public void shouldSendGotResultEvent() throws IOException {
    }

    @Test
    @Ignore
    public void shouldSendStopStepEvent() throws IOException {
    }

    @Test
    @Ignore
    public void shouldSendStopBehaviorEvent() throws IOException {
    }

    @Test
    @Ignore
    public void shouldSendMultipleSubsequentEvents() throws IOException {
    }

    private void sendEventAndVerifyReceipt(Event event, ExecutionListener receiver) throws IOException {
        listener.setReceiver(receiver);
        writeEventToSocket(event);
        verify(receiver);
    }

    private void writeEventToSocket(Event event) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), listener.getPort());
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(event);
        outputStream.close();
    }
}
