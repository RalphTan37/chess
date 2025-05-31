package piece;

import main.GamePanel;

public class Pawn extends Piece{
    // Pawn Constructor
    public Pawn(int color, int file, int rank) {
        super(color, file, rank);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/wP.png");
        } else {
            image = getImage("/piece/bP.png");
        }
    }
}
