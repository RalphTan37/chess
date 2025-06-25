package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import piece.Piece;
import piece.Pawn;
import piece.Knight;
import piece.Rook;
import piece.Bishop;
import piece.Queen;
import piece.King;

public class GamePanel extends JPanel implements Runnable{
    public static final int WIDTH = 1125;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();

    // Chess Piece
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();

    // Color
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;

    // GamePanel Constructor
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GRAY);
        setPieces();
        copyPieces(pieces, simPieces);
    }

    public void setPieces() {
        // White Chess Pieces
        pieces.add(new Pawn(WHITE, 0, 6));
        pieces.add(new Pawn(WHITE, 1, 6));
        pieces.add(new Pawn(WHITE, 2, 6));
        pieces.add(new Pawn(WHITE, 3, 6));
        pieces.add(new Pawn(WHITE, 4, 6));
        pieces.add(new Pawn(WHITE, 5, 6));
        pieces.add(new Pawn(WHITE, 6, 6));
        pieces.add(new Pawn(WHITE, 7, 6));
        pieces.add(new Rook(WHITE, 0,7));
        pieces.add(new Rook(WHITE, 7,7));
        pieces.add(new Knight(WHITE, 1,7));
        pieces.add(new Knight(WHITE, 6,7));
        pieces.add(new Bishop(WHITE, 2,7));
        pieces.add(new Bishop(WHITE, 5,7));
        pieces.add(new Queen(WHITE,3,7));
        pieces.add(new King(WHITE,4,7));

        // Black Chess Pieces
        pieces.add(new Pawn(BLACK, 0, 1));
        pieces.add(new Pawn(BLACK, 1, 1));
        pieces.add(new Pawn(BLACK, 2, 1));
        pieces.add(new Pawn(BLACK, 3, 1));
        pieces.add(new Pawn(BLACK, 4, 1));
        pieces.add(new Pawn(BLACK, 5, 1));
        pieces.add(new Pawn(BLACK, 6, 1));
        pieces.add(new Pawn(BLACK, 7, 1));
        pieces.add(new Rook(BLACK, 0,0));
        pieces.add(new Rook(BLACK, 7,0));
        pieces.add(new Knight(BLACK, 1,0));
        pieces.add(new Knight(BLACK, 6,0));
        pieces.add(new Bishop(BLACK, 2,0));
        pieces.add(new Bishop(BLACK, 5,0));
        pieces.add(new Queen(BLACK,3,0));
        pieces.add(new King(BLACK,4,0));
    }

    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target) {
        target.clear();
        for(int i = 0; i < source.size(); i++) {
            target.add(source.get(i));
        }
    }

    private void update() {

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;

        // Draw Board
        board.draw(graphics2D);

        // Create Chess Pieces
        for(Piece piece : simPieces) {
            piece.draw(graphics2D);
        }
    }

    @Override
    public void run() {
        // Game Loop - processes run continuously while game is running
        double drawInterval = (double) 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;

            // Executed every 1/60 of a second
            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    // Instantiate Thread
    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start(); // Calls run()
    }
}
