package piece;

import main.GamePanel;

public class Rook extends Piece{
    //Rook Constructor
    public Rook(int color, int file, int rank) {
        super(color, file, rank);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/wR");
        } else {
            image = getImage("/piece/bR");
        }
    }
}
