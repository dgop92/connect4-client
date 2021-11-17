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
import inevaup.dialogs.InfoDialog;
import interfaces.MainMenu;
import io.socket.client.Ack;
import java.util.ArrayList;
import javax.swing.JFrame;
import processing.awt.PSurfaceAWT.SmoothCanvas;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PSurface;

public class GameSketch extends PApplet implements GameListener {

    SocketManager socketManager;
    int tokenSelectedX = 0, tokenRadius;;
    GameState curreGameState;
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
            setFooter();

        }

    }

    @Override
    public void mousePressed() {

    }

    @Override
    public void keyPressed() {

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

    public void run() {
        String[] processingArgs = {this.getClass().getName()};
        PApplet.runSketch(processingArgs, this);
    }

    @Override
    public void onPlayerTurn(InGamePlayer player) {
        currentPlayer = player;

    }

    String playerTL;

    @Override
    public void onTurnLost(InGamePlayer player) {
        gameMessage = String.format("%s perdió el turno", player.getUsername());

    }

    @Override
    public void onUpdateState(GameState gameState) {
        this.curreGameState = gameState;
    }

    @Override
    public void onTurnTick(TickData tickData) {
        time = tickData.getTime();
    }



    @Override
    public void onPlayerWon(GameState gameState) {
        this.curreGameState = gameState;
        gameMessage = String.format("%s ganó la partida", currentPlayer.getUsername());
        InfoDialog infoDialog = new InfoDialog(
                null,
                "Partida Terminada",
                currentPlayer.getUsername() + " ha ganado la partida <br>"
                + "El tiempo acumulado por " + gameState.getPlayers().get(0).
                        getUsername() + " es " + gameState.getPlayers().get(0).
                        getCumulativeTime() + "<br>El tiempo Acumulado por "
                + gameState.getPlayers().get(1).getUsername() + " es "
                + gameState.getPlayers().get(1).getCumulativeTime() + "<br>" + "El jugador"
                + " más rapido fue " + determinateFastestPlayer(gameState.getPlayers()).getUsername(),
                InfoDialog.TypeInfoDialog.INFO_DIALOG
        );
        infoDialog.setVisible(true);

        if (!infoDialog.isShowing()) {

            this.socketManager.disconnectAndReset();

            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
            mainMenu.setBounds(500, 0, 800, 600);

            this.dispose();
            PSurface surface = this.getSurface();
            SmoothCanvas smoothCanvas = (SmoothCanvas) surface.getNative();
            JFrame frame = (JFrame) smoothCanvas.getFrame();
            frame.dispose();

        }

    }

    @Override
    public void onTiedGame(GameState gameState) {
        this.curreGameState = gameState;
        gameMessage = "Juego empatado";
        InfoDialog infoDialog = new InfoDialog(
                null,
                "Partida empatada",
                "El tiempo acumulado por " + gameState.getPlayers().get(0).
                        getUsername() + " es " + gameState.getPlayers().get(0).
                        getCumulativeTime() + "<br>El tiempo Acumulado por "
                + gameState.getPlayers().get(1).getUsername() + " es "
                + gameState.getPlayers().get(1).getCumulativeTime() + "<br>" + "El jugador"
                + " más rapido fue " + determinateFastestPlayer(gameState.getPlayers()).getUsername(),
                InfoDialog.TypeInfoDialog.INFO_DIALOG
        );
        infoDialog.setVisible(true);

        if (!infoDialog.isShowing()) {

            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
            mainMenu.setBounds(500, 0, 800, 600);

            this.dispose();
            PSurface surface = this.getSurface();
            SmoothCanvas smoothCanvas = (SmoothCanvas) surface.getNative();
            JFrame frame = (JFrame) smoothCanvas.getFrame();
            frame.dispose();

        }

    }

    @Override
    public void onInvalidPlay(InvalidData invalidData) {
        InfoDialog infoDialog = new InfoDialog(
                null,
                "Jugada invalida",
                "La columna que seleccionó ya está llena, intente con otra columna",
                InfoDialog.TypeInfoDialog.ERROR_DIALOG
        );
        infoDialog.setVisible(true);

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
        String timeText = String.format("%d / 30", time);
        text(timeText, width / 2 - textWidth(timeText) / 2, 600 - 10);
    }

    private void drawPlayersData() {

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

            int[] colorV = {204, 0, 0};
            colorF = colorV;
        } else if (color.equalsIgnoreCase("Green")) {
            int[] colorV = {0, 204, 0};
            colorF = colorV;
        } else if (color.equalsIgnoreCase("Blue")) {
            int[] colorV = {0, 0, 204};
            colorF = colorV;
        } else if (color.equalsIgnoreCase("Orange")) {
            int[] colorV = {255, 102, 0};
            colorF = colorV;
        } else {
            int[] colorV = {0, 0, 0};
            colorF = colorV;

        }

        return colorF;

    }

    private class TurnDataCallBack implements Ack {

        @Override
        public void call(Object... args) {
            currentTurnData = new TurnData((JSONObject) args[0]);
        }

    }

    private InGamePlayer determinateFastestPlayer(ArrayList<InGamePlayer> players) {
        InGamePlayer fastestPlayer;
        if (players.get(0).getCumulativeTime() < players.get(1).getCumulativeTime()) {

            fastestPlayer = players.get(0);

        } else {
            fastestPlayer = players.get(1);

        }
        return fastestPlayer;
    }

}
