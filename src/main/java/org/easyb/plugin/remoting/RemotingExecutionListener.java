package org.easyb.plugin.remoting;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.disco.easyb.BehaviorStep;
import org.disco.easyb.domain.Behavior;
import org.disco.easyb.listener.ExecutionListener;
import org.disco.easyb.result.Result;
import static org.easyb.plugin.remoting.EventType.START_BEHAVIOR;
import static org.easyb.plugin.remoting.EventType.START_STEP;
import static org.easyb.plugin.remoting.EventType.DESCRIBE_STEP;
import static org.easyb.plugin.remoting.EventType.GOT_RESULT;
import static org.easyb.plugin.remoting.EventType.STOP_STEP;
import static org.easyb.plugin.remoting.EventType.STOP_BEHAVIOR;

public class RemotingExecutionListener implements ExecutionListener {
    private Socket socket;
    private ObjectOutputStream outputStream;

    public RemotingExecutionListener(int port) throws IOException {
        socket = new Socket(InetAddress.getLocalHost(), port);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    public void startBehavior(Behavior behavior) {
        safeWriteObject(new Event(START_BEHAVIOR, behavior));
    }

    public void startStep(BehaviorStep behaviorStep) {
        safeWriteObject(new Event(START_STEP, behaviorStep));
    }

    public void describeStep(String description) {
        safeWriteObject(new Event(DESCRIBE_STEP, description));
    }

    public void gotResult(Result result) {
        safeWriteObject(new Event(GOT_RESULT, result));
    }

    public void stopStep() {
        safeWriteObject(new Event(STOP_STEP, null));
    }

    public void stopBehavior(Behavior behavior) {
        safeWriteObject(new Event(STOP_BEHAVIOR, behavior));
    }

    public void completeTesting() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void safeWriteObject(Object object) {
        try {
            outputStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
