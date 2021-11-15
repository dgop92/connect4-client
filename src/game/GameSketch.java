package game;

import org.json.JSONException;
import org.json.JSONObject;

import core.C4Events;
import core.SocketManager;
import core.dataclasses.CellData;
import core.dataclasses.GameState;
import core.dataclasses.InGamePlayer;
import core.dataclasses.InvalidData;
import core.dataclasses.TickData;
import core.dataclasses.TurnData;
import core.listeners.GameListener;
import core.models.PlayBoard;
import io.socket.client.Ack;
import processing.core.PApplet;
import processing.core.PImage;

public class GameSketch extends PApplet implements GameListener {

    SocketManager socketManager;

    GameState curreGameState;
    boolean on = true;
    boolean showName = true, showPTL = false, showInvalidP = false, showGameOver = false;
    int showNumber, start1, start2, start3, start4;
    PImage board;
    InGamePlayer currentPlayer;
    TurnData currentTurnData;
    String gameMessage = "---";

    public GameSketch() {
        super();

        socketManager = SocketManager.getSocketManager();
        socketManager.setGameListener(this);

    }

    @Override
    public void settings() {
        size(800, 600);

        board = this.loadImage("interfaces/images/textura-tipo-madera-roble.jpg");

    }

    @Override
    public void draw() {
        background(0);

        if (curreGameState != null) {
            setHeader();
            setBoard();
    
            /* if (showName && millis() - start1 <= 5000) {
    
                if (showPTL && millis() - start2 > 2500) {
                    showMessage(0, actualPlayer);
    
                }
    
                if (!showPTL)
                    showMessage(0, actualPlayer);
    
            }
    
            if (showPTL && millis() - start2 <= 2500) {
                showMessage(1, playerTL);
            }
    
            if (showInvalidP && millis() - start3 <= 5000) {
                showMessage(2, "");
            } */
    
            setFooter();
    
            /* if (showGameOver) {
                showMessage(3, you);
            } */
        }

    }

    @Override
    public void mousePressed() {

    }

    int tokenSelectedX = 0;

    @Override
    public void keyPressed() {
        if (on) {
            if (keyCode == LEFT) {
                if (tokenSelectedX != 0) {
                    tokenSelectedX--;
                }

            }
            if (keyCode == RIGHT) {
                if (tokenSelectedX != curreGameState.getM() - 1) {
                    tokenSelectedX++;
                }
            }

            if (keyCode == ENTER) {

                // putTheToken[tokenSelectedY][tokenSelectedX] = true;
                try {
                    JSONObject data = new JSONObject();
                    data.put("columnIndex", tokenSelectedX);
                    TurnDataCallBack turnDataCallBack = new TurnDataCallBack();
                    socketManager.getSocket().emit(
                        C4Events.PLAYER_MOVEMENT, 
                        data, 
                        turnDataCallBack
                    );

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void run() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }

    @Override
    public void onPlayerTurn(InGamePlayer player) {
        currentPlayer = player;
        showName = true;
        start1 = millis();
    }

    String playerTL;

    @Override
    public void onTurnLost(InGamePlayer player) {
        playerTL = player.getUsername();

        start2 = millis();
        showPTL = true;

        gameMessage = String.format("%s perdio el turno", player.getUsername());

    }

    @Override
    public void onUpdateState(GameState gameState) {
        this.curreGameState = gameState;
    }

    @Override
    public void onTurnTick(TickData tickData) {
        time = tickData.getTime();
    }

    int totalTimeP1, totalTimeP2;

    @Override
    public void onPlayerWon(GameState gameState) {
        this.curreGameState = gameState;

        totalTimeP1 = gameState.getPlayers().get(0).getCumulativeTime();
        totalTimeP2 = gameState.getPlayers().get(1).getCumulativeTime();

        showGameOver = true;

        gameMessage = String.format("%s ganó la partida", currentPlayer.getUsername());
    }

    @Override
    public void onInvalidPlay(InvalidData invalidData) {
        start3 = millis();
        showInvalidP = true;
        showName = false;
        showPTL = false;

    }

    private void setHeader() {
        noStroke();

        InGamePlayer player1 = curreGameState.getPlayers().get(0);
        InGamePlayer player2 = curreGameState.getPlayers().get(1);

        int[] colorPlayer1 = getColorByString(player1.getColorAsString());
        int[] colorPlayer2 = getColorByString(player2.getColorAsString());
        // grid with the color and username
        // player one
        fill(colorPlayer1[0], colorPlayer1[1], colorPlayer1[2]);
        rect(0, 0, 400, 50);

        // username place
        fill(250);
        textSize(28);
        text(player1.getUsername(), 200 - textWidth(player1.getUsername()) / 2, 35);

        // player two
        fill(colorPlayer2[0], colorPlayer2[1], colorPlayer2[2]);
        rect(401, 0, 800, 50);

        // username place
        fill(250);
        textSize(28);
        text(player2.getUsername(), 600 - textWidth(player2.getUsername()) / 2, 35);

    }

    int tokenRadius;

    private void setBoard() {

        int filas = curreGameState.getN();
        int columnas = curreGameState.getM();
        CellData[][] table = curreGameState.getConnect4Table();

        PlayBoard playBoard = new PlayBoard();
        playBoard.calculateTokenSeparation(filas, columnas);

        fill(0);

        // 4x4 as minimin size
        image(board, 100, 100, 600, 350);
        // initial value
        tokenRadius = playBoard.tokenRadius;
        float acumX = 0, acumY = 0;
        // float separationX = ;

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                // marking the selected token
                if (tokenSelectedX == j) {
                    stroke(250);
                } else {
                    noStroke();
                }

                if (table[i][j] != null) {

                    int[] colorOnTable = getColorByString(
                        table[i][j].getColorAsString()
                    );

                    fill(colorOnTable[0], colorOnTable[1], colorOnTable[2]);

                } else {
                    fill(0);
                }

                // draw the token
                if (j == 0) {
                    circle(110 + tokenRadius / 2 + acumX, 110 + tokenRadius / 2 + acumY, tokenRadius);
                } else {
                    circle(110 + tokenRadius / 2 + acumX, 110 + tokenRadius / 2 + acumY, tokenRadius);
                }

                acumX += (tokenRadius + playBoard.tokenSeparationX);

            }

            acumX = 0;
            acumY += (tokenRadius + playBoard.tokenSeparationY);

        }
        noStroke();

    }

    int time;

    private void setFooter() {

        drawPlayersData();
        
        rect(width / 2 - 50, 600 - 30, 100, 30);
        fill(0);
        textSize(20);
        text(
            String.valueOf(time), 
            width / 2 - textWidth(String.valueOf(time)) / 2, 
            600 - 10
        );
    }

    private void drawPlayersData(){

        int initX = 50;
        int initY = 500;
        int yGap = 30;
        int xGap = 200;

        textSize(16);
        fill(250);
        text(String.format("Turno de: %s", currentPlayer.getUsername()), initX, initY);
        if (currentTurnData != null) {
            text(
                String.format(
                    "Tiempo Gastado: %d", 
                    currentTurnData.getTimeConsumed()
                ), 
                initX, initY + yGap
            );
        }

        InGamePlayer player1 = curreGameState.getPlayers().get(0);
        InGamePlayer player2 = curreGameState.getPlayers().get(1);

        text(
            String.format(
                "Tiempo acumulado por %s: %d", 
                player1.getUsername(), 
                player1.getCumulativeTime()
            ),
            initX + xGap, initY
        );
        text(
            String.format(
                "Tiempo acumulado por %s: %d", 
                player2.getUsername(), 
                player2.getCumulativeTime()
            ),
            initX + xGap, initY + yGap
        );

        text("Avisos:", initX + (3 * xGap) - 60, initY);
        text(gameMessage, initX + (3 * xGap) - 60, initY + yGap);


    }

    public int[] getColorByString(String color) {

        int[] colorF;

        if (color.equalsIgnoreCase("Red")) {

            int[] colorV = { 250, 0, 0 };
            colorF = colorV;
        } else if (color.equalsIgnoreCase("Green")) {
            int[] colorV = { 0, 250, 0 };
            colorF = colorV;
        } else if (color.equalsIgnoreCase("Blue")) {
            int[] colorV = { 0, 0, 250 };
            colorF = colorV;
        } else if (color.equalsIgnoreCase("Orange")) {
            int[] colorV = { 239, 127, 26 };
            colorF = colorV;
        } else {
            int[] colorV = { 0, 0, 0 };
            colorF = colorV;

        }

        return colorF;

    }

    private class TurnDataCallBack implements Ack{

        @Override
        public void call(Object... args) {
            currentTurnData = new TurnData((JSONObject) args[0]);
        }
        
    }


    /* private void showMessage(int type, String name) {
        String messageT;

        switch (type) {

        // Onturn
        case 0:
            fill(250);
            // rect(0, 500, 800, 20);
            textSize(20);
            messageT = "Es el turno de " + name;
            text(messageT, width / 2 - textWidth(messageT) / 2, 510);
            break;

        // LostTurn
        case 1:
            fill(250);
            // rect(0, 500, 800, 20);
            textSize(20);
            messageT = name + " perdió el turno";
            text(messageT, width / 2 - textWidth(messageT) / 2, 510);

            break;

        // invalidPlay
        case 2:
            fill(250);
            // rect(0, 500, 800, 20);
            textSize(20);
            messageT = "Jugada invalida, posición ya ocupada";
            text(messageT, width / 2 - textWidth(messageT) / 2, 510);

            break;

        // Victory
        case 3:
            fill(250);
            on = false;
            // rect(0, 500, 800, 20);
            textSize(28);
            text("Victoria", width / 2 - textWidth("Victoria") / 2, 100);
            textSize(20);

            // show the winner
            messageT = "El ganador es: " + name;
            text(messageT, width / 2 - textWidth(messageT) / 2, 200);

            // show the stats
                // show the stats 
            // show the stats
            String time1 = "Tiempo usado por " + names[0] + ": " + totalTimeP1,
                    time2 = "Tiempo usado por " + names[1] + ": " + totalTimeP2,
                    fastestT = "El jugador más rapido de la partida fue: ",
                    totalT = String.valueOf(totalTimeP1 + totalTimeP2);
            if (totalTimeP1 > totalTimeP2) {
                fastestT += names[0];

            } else {
                fastestT += names[1];
            }

            text(totalT, width / 2 - textWidth(totalT) / 2, 220);
            text(time1, width / 2 - textWidth(time1) / 2, 240);
            text(time2, width / 2 - textWidth(time2) / 2, 260);
            text(fastestT, width / 2 - textWidth(fastestT) / 2, 280);

            break;

        }

    } */

}
