package core.models;

import java.util.ArrayList;

public class PlayBoard {

    //atributes
    public static final int height = 350;
    public static final int width = 600;
    public static final float borderSeparation = 10;
    public float tokenSeparationX;
    public float tokenSeparationY;
    public int tokenRadius;

    public ArrayList<Token> tokens = new ArrayList<Token>();

    // methods
    // constructor 
    public PlayBoard() {
     tokenRadius = 50;
    }

    public void calculateTokenSeparation(int n, int m) {
          
        float tokenSeparationX, tokenSeparationY;
        
        tokenSeparationX = (width - 20 - tokenRadius*m) / (m-1);
        tokenSeparationY = (height - 20 - tokenRadius*n) / (n-1);

        this.tokenSeparationX = tokenSeparationX;
        this.tokenSeparationY = tokenSeparationY;
        
        if(tokenSeparationY <= 5 && tokenRadius != 0){
            
            tokenRadius -=  10;
            calculateTokenSeparation(n, m); 
        }
        
        
        
    }

}
