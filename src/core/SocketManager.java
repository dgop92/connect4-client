package core;

import java.net.URI;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketManager {

    private static SocketManager socketManager = null;
    private Socket socket;
    public static final String serverUrl = "ws://localhost:8080";

    public static SocketManager getSocketManager() {
        if (socketManager == null) {
            socketManager = new SocketManager();
        }
        return socketManager;
    }

    public void initSocket(String username, String roomName) {
        if (socket == null) {
            String query = String.format("username=%s&roomName=%s", username, roomName);

            URI uri = URI.create(serverUrl);
            IO.Options options = IO.Options.builder().setQuery(query).build();
            socket = IO.socket(uri, options);
        }
    }

    public void startConnection() {
        socket.connect();
    }

    public Socket getSocket() {
        return socket;
    }
}
