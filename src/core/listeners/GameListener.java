package core.listeners;

import core.dataclasses.GameState;
import core.dataclasses.InvalidData;
import core.dataclasses.TickData;
import core.dataclasses.TurnData;

public interface GameListener {
    
    public void onPlayerTurn();

    public void onTurnLost(TurnData turnData);

    public void onUpdateState(GameState gameState);
    
    public void onPlayerWon(GameState gameState);
    
    public void onInvalidPlay(InvalidData invalidData);

    public void onTurnTick(TickData tickData);

    // public void onPlayedTurn(TurnData turnData);
}
