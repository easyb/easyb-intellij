package org.easyb.plugin.remoting;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class RemoteUtils {
    public static void safeClose(ServerSocket socket) {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void safeClose(Socket socket) {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void safeWriteObject(Object object, ObjectOutputStream outputStream) {
        try {
            outputStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
