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

    // Rook Mobility Logic
    public boolean canMove(int targetFile, int targetRank) {
        if (isWithinBoard(targetFile,targetRank) && !isSameSquare(targetFile, targetRank)) {
            // Rook can move horizontally and vertically
            if (targetFile == preFile || targetRank == preRank) {
                if (isValidSquare(targetFile,targetRank) && !pieceIsOnLine(targetFile, targetRank)) {
                    return true;
                }
            }
        }
        return false;
    }
}
