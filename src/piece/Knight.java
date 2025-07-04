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

    // Knight Mobility Logic
    public boolean canMove(int targetFile, int targetRank) {
        if (isWithinBoard(targetFile, targetRank)) {
            // Knight can move two squares vertically then one square horizontally (1:2) and/or two squares horizontally then one square vertically (2:1)
            if (Math.abs(targetFile - preFile) * Math.abs(targetRank - preRank) == 2) {
                if (isValidSquare(targetFile, targetRank)) return true;
            }
        }
        return false;
    }
}
