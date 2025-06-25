package piece;

import main.GamePanel;

public class Knight extends Piece{
    //Knight Constructor
    public Knight(int color, int file, int rank) {
        super(color, file, rank);

        if (color == GamePanel.WHITE){
            image = getImage("/piece/wN");
        } else {
            image = getImage("/piece/bN");
        }
    }
}
