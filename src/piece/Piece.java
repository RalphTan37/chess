package piece;

import main.Board;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;


// Superclass for all chess pieces
public class Piece {
    public BufferedImage image;
    public int letter, num;
    public int file, rank, preFile, preRank;
    public int color;
    public Piece targetedPiece;

    // Chess Piece Constructor
    public Piece(int color, int file, int rank) {
        // Initialize instance variables
        this.color = color;
        this.file = file;
        this.rank = rank;

        letter = getLetter(file);
        num = getNum(rank);

        preFile = file;
        preRank = rank;
    }

    // Gets images from the Resource Root
    public BufferedImage getImage(String imagePath) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /***** Getters & Setters *****/

    public int getLetter(int file) {
        return file * Board.SQUARE_SIZE;
    }

    public int getNum(int rank) {
        return rank * Board.SQUARE_SIZE;
    }

    public int getFile(int x) {
        return (x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }

    public int getRank(int y) {
        return (y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }

    public int getIndex() {
        for (int i = 0; i < GamePanel.simPieces.size(); i++) {
           if (GamePanel.simPieces.get(i) == this) return i;
        }
        return 0;
    }

    // Updates Chess Position
    public void updatePosition() {
        letter = getLetter(file);
        num = getNum(rank);
        preFile = getFile(letter);
        preRank = getRank(num);
    }

    // Resets Chess Position
    public void resetPosition() {
        file = preFile;
        rank = preRank;
        letter = getLetter(file);
        num = getNum(rank);
    }

    // Checks if chess piece can move
    public boolean canMove(int targetFile, int targetRank) {
        return false;
    }

    // Checks if square is on the chess board
    public boolean isWithinBoard(int targetFile, int targetRank) {
        if (targetFile >= 0 && targetFile <= 7 && targetRank >= 0 && targetRank <= 7) {
            return true;
        }
        return false;
    }

    // Returns chess pieces that can be captured
    public Piece canCapture(int targetFile, int targetRank) {
        for (Piece piece : GamePanel.simPieces) {
            if (piece.file == targetFile && piece.rank == targetRank && piece != this) return piece;
        }
        return null;
    }

    // Checks if square is valid
    public boolean isValidSquare(int targetFile, int targetRank) {
        targetedPiece = canCapture(targetFile, targetRank);
        if (targetedPiece == null) return true; // Available Square
        else {                                  // Occupied Square
            if (targetedPiece.color != this.color) return true; // If opposing piece, it can be captured
            else targetedPiece = null;
        }
        return false;
    }

    // Draw Chess Piece Method
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image, letter, num, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }
}
