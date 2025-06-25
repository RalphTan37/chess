package piece;

import main.GamePanel;

public class Queen extends Piece{
    // Queen Constructor
    public Queen(int color, int file, int rank) {
        super(color, file, rank);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/wQ");
        } else {
            image = getImage("/piece/bQ");
        }
    }
}
