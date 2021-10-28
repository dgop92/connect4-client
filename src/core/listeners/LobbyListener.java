package core.listeners;

import core.dataclasses.LobbyStatus;

public interface LobbyListener {
    
    public void onNewPlayer(LobbyStatus lobbyStatus);
    
    public void onPlayerLeave(LobbyStatus lobbyStatus);

    public void onGameStarted();
}
