package piece;

import main.GamePanel;

public class King extends Piece{
    // King Constructor
    public King(int color, int file, int rank) {
        super(color, file, rank);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/wK.png");
        } else {
            image = getImage("/piece/bK.png");
        }
    }
}
