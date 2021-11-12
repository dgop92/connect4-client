package core;

import java.net.URI;

import org.json.JSONException;
import org.json.JSONObject;
import core.dataclasses.GameState;
import core.dataclasses.InGamePlayer;
import core.dataclasses.InvalidData;
import core.dataclasses.LobbyStatus;
import core.dataclasses.TickData;
import core.listeners.ConnectionListener;
import core.listeners.GameListener;
import core.listeners.LobbyListener;
import core.listeners.MainMenuListener;
import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketManager {

    private static SocketManager socketManager = null;
    private Socket socket;
    public static final String serverUrl = "ws://localhost:8080";
    private ConnectionListener connectionListener;
    private LobbyListener lobbyListener;
    private MainMenuListener mainMenuListener;
    private GameListener gameListener;
    private LobbyStatus currentLobbyStatus;
    private GameState currentGameState;

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    private SocketManager() {

    }

    public static SocketManager getSocketManager() {
        if (socketManager == null) {
            socketManager = new SocketManager();
        }
        return socketManager;
    }

    public void initSocket(String username, String roomName, int n, int m) {
        if (socket == null) {
            String query = String.format(
                "username=%s&roomName=%s&n=%d&m=%d", 
                username, 
                roomName,
                n,
                m
            );

            URI uri = URI.create(serverUrl);
            IO.Options options = IO.Options.builder().setQuery(query).build();
            socket = IO.socket(uri, options);
            socket.connect();
            initConnectionListeners();
            initMainMenuListeners();
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

    private void initMainMenuListeners() {
        socket.on(C4Events.MOVE_TO_LOBBY, (Object... args) -> {
            if (mainMenuListener != null)
                mainMenuListener.moveToLobby();
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
                try {
                    gameListener.onPlayerTurn(new InGamePlayer((JSONObject) args[0]));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        socket.on(C4Events.TURN_LOST, (Object... args) -> {
            if (gameListener != null) {
                try {
                    gameListener.onTurnLost(new InGamePlayer((JSONObject) args[0]));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        socket.on(C4Events.UPDATE_GAME, (Object... args) -> {
            if (gameListener != null) {
                currentGameState = new GameState((JSONObject) args[0]);
                gameListener.onUpdateState(currentGameState);
            }
        });
        socket.on(C4Events.PLAYER_WON, (Object... args) -> {
            if (gameListener != null) {
                currentGameState = new GameState((JSONObject) args[0]);
                gameListener.onPlayerWon(currentGameState);
            }
        });
        socket.on(C4Events.INVALID_PLAY, (Object... args) -> {
            if (gameListener != null) {
                gameListener.onInvalidPlay(new InvalidData((JSONObject) args[0]));
            }
        });
        socket.on(C4Events.TURN_TICK, (Object... args) -> {
            if (gameListener != null) {
                gameListener.onTurnTick(new TickData((JSONObject) args[0]));
            }
        });
    }

    public void setMainMenuListener(MainMenuListener mainMenuListener) {
        this.mainMenuListener = mainMenuListener;
    }

    public void setConnectionListener(ConnectionListener connectionListener) {
        this.connectionListener = connectionListener;
    }

    public void setLobbyListener(LobbyListener lobbyListener) {
        this.lobbyListener = lobbyListener;
        // if java is slower than the websocket
        if (currentLobbyStatus != null && lobbyListener != null) {
            lobbyListener.onNewPlayer(currentLobbyStatus);
        }
    }

    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
        if (currentGameState != null && gameListener != null) {
            gameListener.onUpdateState(currentGameState);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public LobbyStatus getCurrentLobbyStatus() {
        return currentLobbyStatus;
    }

}
