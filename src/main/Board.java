package main;

import java.awt.*;

public class Board {
    final int MAX_FILE = 8;
    final int MAX_RANK = 8;
    public static final int SQUARE_SIZE = 100;
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE/2;

    // Creates the chessboard
    public void draw(Graphics2D graphics2D) {
        int c = 0;
        for(int rank = 0; rank < MAX_RANK; rank++ ){
            for(int file = 0; file < MAX_FILE; file++){
                if(c == 0) {
                    graphics2D.setColor(new Color(210,165,125));
                    c = 1;
                } else {
                    graphics2D.setColor(new Color(175,115,70));
                    c = 0;
                }
                graphics2D.fillRect(file * SQUARE_SIZE,rank * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
            if(c == 0) {
                c = 1;
            } else {
                c = 0;
            }
        }
    }
}
