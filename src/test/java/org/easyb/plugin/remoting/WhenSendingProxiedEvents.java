package org.easyb.plugin.remoting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static junit.framework.Assert.assertEquals;
import org.disco.easyb.BehaviorStep;
import org.disco.easyb.domain.Specification;
import org.disco.easyb.domain.Story;
import org.disco.easyb.listener.ExecutionListener;
import org.disco.easyb.result.Result;
import org.disco.easyb.util.BehaviorStepType;
import static org.easyb.plugin.remoting.EventType.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

public class WhenSendingProxiedEvents {
    private MockReceiver mockReceiver;
    private ExecutionListener remoteListener;

    @Before
    public void setUp() throws IOException {
        mockReceiver = new MockReceiver();
        remoteListener = new ExecutionListenerProxy(mockReceiver.getPort());
    }

    @After
    public void tearDown() {
        remoteListener.completeTesting();
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

    @Test
    public void shouldSendMultipleSubsequentEventes() throws IOException {
        shouldSendStartBehaviorEvent();
        shouldSendStopBehaviorEvent();
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
                Event eventToReturn = event;
                event = null;
                return eventToReturn;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        Socket socket = null;
        try {
            socket = server.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            do {
                event = (Event) inputStream.readObject();
            } while (event.getType() != EventType.COMPLETE_TESTING);
        } catch (IOException e) {
            // Expect IOException to be thrown when remote port closes
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
