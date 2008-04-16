package org.easyb.plugin.remoting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.disco.easyb.domain.Story;
import org.disco.easyb.domain.Specification;
import org.disco.easyb.listener.ExecutionListener;
import org.disco.easyb.BehaviorStep;
import org.disco.easyb.result.Result;
import org.disco.easyb.util.BehaviorStepType;
import org.junit.Test;
import org.junit.Before;
import static org.easyb.plugin.remoting.EventType.START_BEHAVIOR;
import static org.easyb.plugin.remoting.EventType.START_STEP;
import static org.easyb.plugin.remoting.EventType.DESCRIBE_STEP;
import static org.easyb.plugin.remoting.EventType.GOT_RESULT;
import static org.easyb.plugin.remoting.EventType.STOP_STEP;
import static org.easyb.plugin.remoting.EventType.STOP_BEHAVIOR;
import static junit.framework.Assert.assertEquals;

public class WhenRemotingEvents {
    private MockReceiver mockReceiver;
    private ExecutionListener remoteListener;

    @Before
    public void setUp() throws IOException {
        mockReceiver = new MockReceiver();
        remoteListener = new RemotingExecutionListener(mockReceiver.getPort());
    }

    @Test
    public void shouldSendStartBehaviorEvent() throws IOException {
        Story story = new Story("Transferring funds", null);
        remoteListener.startBehavior(story);
        assertEquals(new Event(START_BEHAVIOR, story), mockReceiver.getEvent());
    }

    @Test
    public void shouldSendStartStepEvent() throws IOException {
        BehaviorStep step = new BehaviorStep(BehaviorStepType.STORY, "Transferring funds");
        remoteListener.startStep(step);
        assertEquals(new Event(START_STEP, step), mockReceiver.getEvent());
    }

    @Test
    public void shouldSendDescribeStepEvent() throws IOException {
        remoteListener.describeStep("description");
        assertEquals(new Event(DESCRIBE_STEP, "description"), mockReceiver.getEvent());
    }

    @Test
    public void shouldSendGotResultEvent() throws IOException {
        Result result = new Result(Result.SUCCEEDED);
        remoteListener.gotResult(result);
        assertEquals(new Event(GOT_RESULT, result), mockReceiver.getEvent());
    }

    @Test
    public void shouldSendStopStepEvent() throws IOException {
        remoteListener.stopStep();
        assertEquals(new Event(STOP_STEP, null), mockReceiver.getEvent());
    }

    @Test
    public void shouldSendStopBehaviorEvent() throws IOException {
        Specification spec = new Specification("should do something", null);
        remoteListener.stopBehavior(spec);
        assertEquals(new Event(STOP_BEHAVIOR, spec), mockReceiver.getEvent());
    }
}

class MockReceiver implements Runnable {
    private ServerSocket server;
    private Event event;

    MockReceiver() throws IOException {
        server = new ServerSocket(0);

        new Thread(this).start();
    }

    public int getPort() {
        return server.getLocalPort();
    }

    public Event getEvent() {
        for (int i = 0; i < 10; i++) {
            if (event != null) {
                return event;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void run() {
        Socket socket = null;
        try {
            socket = server.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            event = (Event) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            safeClose(socket);
        }
    }

    private void safeClose(Socket socket) {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
