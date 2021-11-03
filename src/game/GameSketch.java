package game;

import org.json.JSONException;
import org.json.JSONObject;

import core.C4Events;
import core.SocketManager;
import core.dataclasses.TurnData;
import core.listeners.GameListener;
import core.models.PlayBoard;
import io.socket.client.Ack;
import processing.core.PApplet;
import processing.core.PImage;

public class GameSketch extends PApplet implements GameListener {

    SocketManager socketManager;
//    int [] colorP1 = new int[3];
//    int [] colorP2 = new int[3];

     int[] colorP1 = {250, 0, 0};
     int[] colorP2 = {0, 0, 250};
    String[] names = new String[2];
    int filas, columnas;
    boolean[][] putTheToken;

    public GameSketch() {
        super();

        socketManager = SocketManager.getSocketManager();
        socketManager.setGameListener(this);

//        this.colorP1 = getColorByString(color1);
//        this.colorP2 = getColorByString(color2); 

        //for test
      

    }

    

    //int colorP1[] = new int[3];
    //int colorP2[] = new int[3];
    PImage board;

    @Override
    public void settings() {
        size(800, 600);

        // To test the places and look
        names[0] = "Player one";
        names[1] = "Player two";

        board = this.loadImage("interfaces/images/textura-tipo-madera-roble.jpg");
        
//        putTheToken = new boolean[filas][columnas];
//
//        for (int i = 0; i < filas; i++) {
//            for (int j = 0; j < columnas; j++) {
//                putTheToken[i][j] = false;
//            }
//        }

    }

    @Override
    public void draw() {
        background(0);

        // upper section 
        setHeader();

        // board 
        setBoard(10, 10, 1);
       
        setFooter();
        
    }

    @Override
    public void mousePressed() {

    }

    int tokenSelectedX = 0, tokenSelectedY = 0;

    @Override
    public void keyPressed() {
        if (keyCode == UP) {

            if (tokenSelectedY != 0) {
                tokenSelectedY--;
            }

        }

        if (keyCode == DOWN) {
            if (tokenSelectedY != filas - 1) {
                tokenSelectedY++;
            }
        }

        if (keyCode == LEFT) {
            if (tokenSelectedX != 0) {
                tokenSelectedX--;
            }

        }
        if (keyCode == RIGHT) {
            if (tokenSelectedX != columnas - 1) {
                tokenSelectedX++;
            }

        }

        if (keyCode == ENTER) {

            //putTheToken[tokenSelectedY][tokenSelectedX] = true;
            
            //         just for testing
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

    }

    public void run() {
        String[] processingArgs = {this.getClass().getName()};
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

    public void setHeader() {
        noStroke();

        // grid with the color and username
        // player one
        fill(colorP1[0], colorP1[1], colorP1[2]);
        rect(0, 0, 400, 50);

        // username place
        fill(250);
        textSize(28);
        text(names[0], 200 - textWidth(names[0]) / 2, 35);

        // player two
        fill(colorP2[0], colorP2[1], colorP2[2]);
        rect(401, 0, 800, 50);

        // username place
        fill(250);
        textSize(28);
        text(names[1], 600 - textWidth(names[1]) / 2, 35);

    }

    int tokenRadius;

    public void setBoard(int n, int m, int playerNumber) {
        filas = n;
        columnas = m;
        PlayBoard playBoard = new PlayBoard();
        playBoard.calculateTokenSeparation(n, m);

        System.out.println(playBoard.tokenSeparationX + " " + playBoard.tokenSeparationY);

        fill(0);

        // 4x4 as minimin size
        image(board, 100, 100, 600, 350);
        //initial value
        tokenRadius = playBoard.tokenRadius;
        float acumX = 0, acumY = 0;
//    float separationX =  ; 

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {

                //marking the selected token
                if (tokenSelectedX == j && tokenSelectedY == i) {
                    System.out.println(i + " " + j);
                    stroke(250);

                } else {
                    noStroke();
                }

                //paint the token
//                if(putTheToken[i][j]) {
//                    
//                        switch(playerNumber){
//                            case 1:
//                                fill(colorP1[0], colorP1[1], colorP1[2]);
//                                break;
//                            case 2:
//                                 fill(colorP2[0], colorP2[1], colorP2[2]);
//                                break;
//                        }
//                    
//                    }
                //draw the token
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
//        fill(250, 0, 0);
//        circle(width / 2, 500, tokenRadius);

    }
    
    // value to tests
    String time = "1:00";
    
    public void setFooter(){
        fill(250);
        rect(width/2 - 50, 600 - 30, 100, 30);
        fill(0);
        textSize(20);
        text(time, width/2 - textWidth(time)/2, 600-10);
    }
    

    public int[] getColorByString(String color) {

        int[] colorF;

        if (color.equalsIgnoreCase("Red")) {

            int[] colorV = {250, 0, 0};
            colorF = colorV;
        } else if (color.equalsIgnoreCase("Green")) {

            int[] colorV = {0, 250, 0};
            colorF = colorV;
        } else if (color.equalsIgnoreCase("Blue")) {
            int[] colorV = {0, 0, 250};
            colorF = colorV;
        } else if (color.equalsIgnoreCase("Orange")) {
            int[] colorV = {239, 127, 26};
            colorF = colorV;
        } else {
            int[] colorV = {0, 0, 0};
            colorF = colorV;

        }

        return colorF;

    }

}
