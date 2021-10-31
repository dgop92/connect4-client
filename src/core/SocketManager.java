package core;

import java.net.URI;

import org.json.JSONObject;

import core.dataclasses.LobbyStatus;
import core.dataclasses.TurnData;
import core.listeners.ConnectionListener;
import core.listeners.GameListener;
import core.listeners.LobbyListener;
import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketManager {

    private static SocketManager socketManager = null;
    private Socket socket;
    public static final String serverUrl = "ws://localhost:8080";
    private ConnectionListener connectionListener;
    private LobbyListener lobbyListener;
    private GameListener gameListener;
    private LobbyStatus currentLobbyStatus;
    // private TurnData currentTurnData;

    private SocketManager() {

    }

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
            socket.connect();
            initConnectionListeners();
            initLobbyListeners();
            initGameListeners();
        }
    }

    private void initConnectionListeners() {
        socket.on(Socket.EVENT_CONNECT, (Object... args) -> {
            if (connectionListener != null)
                this.connectionListener.onConnection();
        });
        socket.on(Socket.EVENT_DISCONNECT, (Object... args) -> {
            if (connectionListener != null)
                this.connectionListener.onDisconnection();
        });
        socket.on(Socket.EVENT_CONNECT_ERROR, (Object... args) -> {
            if (connectionListener != null)
                this.connectionListener.onConnectionError();
        });
    }

    private void initLobbyListeners() {
        socket.on(C4Events.JOIN_LOBBY, (Object... args) -> {
            if (lobbyListener != null) {
                currentLobbyStatus = new LobbyStatus((JSONObject) args[0]);
                lobbyListener.onNewPlayer(currentLobbyStatus);
            }
        });
        socket.on(C4Events.LEAVE_LOBBY, (Object... args) -> {
            if (lobbyListener != null) {
                currentLobbyStatus = new LobbyStatus((JSONObject) args[0]);
                lobbyListener.onPlayerLeave(currentLobbyStatus);
            }
        });
        socket.on(C4Events.GAME_STARTED, (Object... args) -> {
            if (lobbyListener != null)
                lobbyListener.onGameStarted();
        });
    }

    private void initGameListeners() {
        socket.on(C4Events.PLAYER_TURN, (Object... args) -> {
            if (gameListener != null) {
                gameListener.onPlayerTurn();
            }
        });
        socket.on(C4Events.TURN_LOST, (Object... args) -> {
            if (gameListener != null) {
                gameListener.onTurnLost(new TurnData((JSONObject) args[0]));
            }
        });
    }

    public void setConnectionListener(ConnectionListener connectionListener) {
        this.connectionListener = connectionListener;
    }

    public void setLobbyListener(LobbyListener lobbyListener) {
        this.lobbyListener = lobbyListener;
        // if java is slower than the websocket
        if (currentLobbyStatus != null && lobbyListener != null){
            lobbyListener.onNewPlayer(currentLobbyStatus);
        }
    }

    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
    }

    public Socket getSocket() {
        return socket;
    }

    public LobbyStatus getCurrentLobbyStatus() {
        return currentLobbyStatus;
    }

}
