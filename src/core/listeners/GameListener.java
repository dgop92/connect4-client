package core.listeners;

import core.dataclasses.TurnData;

public interface GameListener {
    
    public void onPlayerTurn();

    public void onTurnLost(TurnData turnData);

    // public void onPlayedTurn(TurnData turnData);
}
