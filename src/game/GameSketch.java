package game;

import org.json.JSONException;
import org.json.JSONObject;

import core.C4Events;
import core.SocketManager;
import core.dataclasses.GameState;
import core.dataclasses.InvalidData;
import core.dataclasses.TurnData;
import core.listeners.GameListener;
import io.socket.client.Ack;
import processing.core.PApplet;

public class GameSketch extends PApplet implements GameListener {

    SocketManager socketManager;

    public GameSketch() {
        super();

        socketManager = SocketManager.getSocketManager();
        socketManager.setGameListener(this);

    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {

    }

    @Override
    public void mousePressed() {
        // just for testing
        try {
            JSONObject data = new JSONObject();
            data.put("columnIndex", 1);
            socketManager.getSocket().emit(C4Events.PLAYER_MOVEMENT, data, new Ack() {
                @Override
                public void call(Object... args) {
                    TurnData turnData = new TurnData((JSONObject) args[0]);
                    System.out.println(turnData.getTimeConsumed());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }

    @Override
    public void onPlayerTurn() {
        System.out.println("MI turno");
    }

    @Override
    public void onTurnLost(TurnData turnData) {
        System.out.println("Perdi mi turno");
    }

    @Override
    public void onUpdateState(GameState gameState) {
        System.out.println("m: ");
        System.out.println(gameState.getM());
    }

    @Override
    public void onPlayerWon(GameState gameState) {
        
    }

    @Override
    public void onInvalidPlay(InvalidData invalidData) {
        System.out.println(invalidData.getErrorMessage());
    }
}
