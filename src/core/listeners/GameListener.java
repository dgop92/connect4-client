package core.listeners;

import core.dataclasses.GameState;
import core.dataclasses.InGamePlayer;
import core.dataclasses.InvalidData;
import core.dataclasses.TickData;

public interface GameListener {
    
    public void onPlayerTurn(InGamePlayer player);

    public void onTurnLost(InGamePlayer player);

    public void onUpdateState(GameState gameState);
    
    public void onPlayerWon(GameState gameState);
    
    public void onInvalidPlay(InvalidData invalidData);

    public void onTurnTick(TickData tickData);

    // public void onPlayedTurn(TurnData turnData);
}
