package piece;

import main.GamePanel;

public class Bishop extends Piece{
    // Bishop Constructor
    public Bishop(int color, int file, int rank) {
        super(color, file, rank);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/wB");
        } else {
            image = getImage("/piece/bB");
        }
    }
}
