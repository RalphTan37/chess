package piece;

import main.GamePanel;

public class King extends Piece{
    // King Constructor
    public King(int color, int file, int rank) {
        super(color, file, rank);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/wK");
        } else {
            image = getImage("/piece/bK");
        }
    }

    // King Mobility Logic
    public boolean canMove(int targetFile, int targetRank) {
        if(isWithinBoard(targetFile, targetRank)) {
            // King can move horizontally, vertically, and diagonally one square
            if (Math.abs(targetFile - preFile) + Math.abs(targetRank - preRank) == 1 || Math.abs(targetFile - preFile) * Math.abs(targetRank - preRank) == 1) {
                if (isValidSquare(targetFile, targetRank)) return true;
            }
        }
        return false;
    }
}
