package piece;

import main.Board;

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

    public int getLetter(int file) {
        return file * Board.SQUARE_SIZE;
    }

    public int getNum(int rank) {
        return rank * Board.SQUARE_SIZE;
    }

    // Draw Chess Piece Method
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image, letter, num, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }
}
