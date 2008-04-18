package org.easyb.plugin.remoting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.disco.easyb.listener.ExecutionListener;
import static org.easyb.plugin.remoting.RemoteUtils.safeClose;

/**
 * Listens for remote events to sent over a socket and forwards then to an execution listener
 */
public class RemoteExecutionListener implements Runnable {
    private ExecutionListener receiver;
    private ServerSocket serverSocket;

    public void start() {
        new Thread(this).start();
        verifyListenerThreadStarted();
    }

    public void run() {
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(0);
            socket = serverSocket.accept();

            Event event;
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            do {
                event = (Event) inputStream.readObject();
                if (receiver != null) {
                    event.fire(receiver);
                }
            } while (event.getType() != EventType.COMPLETE_TESTING);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            safeClose(socket);
        }
    }

    private void verifyListenerThreadStarted() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (serverSocket != null) {
                return;
            }
        }
        throw new RuntimeException("Unable to verify that listener thread started");
    }

    public void setReceiver(ExecutionListener receiver) {
        this.receiver = receiver;
    }

    public void stop() {
        safeClose(serverSocket);
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }
}
