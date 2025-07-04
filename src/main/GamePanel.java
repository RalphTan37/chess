package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.AlphaComposite;

import javax.swing.JPanel;

import piece.Piece;
import piece.Pawn;
import piece.Knight;
import piece.Rook;
import piece.Bishop;
import piece.Queen;
import piece.King;

public class GamePanel extends JPanel implements Runnable{
    // Settings
    public static final int WIDTH = 1125;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();

    // Chess Piece
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();
    Piece activePiece;

    // Color
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;

    // Booleans
    boolean canMove;
    boolean validSquare;

    // GamePanel Constructor
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GRAY);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);
        setPieces();
        copyPieces(pieces, simPieces);
    }

    // Creates chess pieces for white and black
    public void setPieces() {
        // White Chess Pieces
        pieces.add(new Pawn(WHITE, 0, 6));  // White a2-Pawn
        pieces.add(new Pawn(WHITE, 1, 6));  // White b2-Pawn
        pieces.add(new Pawn(WHITE, 2, 6));  // White c2-Pawn
        pieces.add(new Pawn(WHITE, 3, 6));  // White d2-Pawn
        pieces.add(new Pawn(WHITE, 4, 6));  // White e2-Pawn
        pieces.add(new Pawn(WHITE, 5, 6));  // White f2-Pawn
        pieces.add(new Pawn(WHITE, 6, 6));  // White g2-Pawn
        pieces.add(new Pawn(WHITE, 7, 6));  // White h2-Pawn
        pieces.add(new Rook(WHITE, 0,7));   // White a1-Rook
        pieces.add(new Rook(WHITE, 7,7));   // White h1-Rook
        pieces.add(new Knight(WHITE, 1,7)); // White b1-Knight
        pieces.add(new Knight(WHITE, 6,7)); // White g1-Knight
        pieces.add(new Bishop(WHITE, 2,7)); // White c1-Bishop
        pieces.add(new Bishop(WHITE, 5,7)); // White f1-Bishop
        pieces.add(new Queen(WHITE,3,7));   // White d1-Queen
        pieces.add(new King(WHITE,4,7));    // White e1-King

        // Black Chess Pieces
        pieces.add(new Pawn(BLACK, 0, 1));  // Black a7-Pawn
        pieces.add(new Pawn(BLACK, 1, 1));  // Black b7-Pawn
        pieces.add(new Pawn(BLACK, 2, 1));  // Black c7-Pawn
        pieces.add(new Pawn(BLACK, 3, 1));  // Black d7-Pawn
        pieces.add(new Pawn(BLACK, 4, 1));  // Black e7-Pawn
        pieces.add(new Pawn(BLACK, 5, 1));  // Black f7-Pawn
        pieces.add(new Pawn(BLACK, 6, 1));  // Black g7-Pawn
        pieces.add(new Pawn(BLACK, 7, 1));  // Black h7-Pawn
        pieces.add(new Rook(BLACK, 0,0));   // Black a8-Rook
        pieces.add(new Rook(BLACK, 7,0));   // Black h8-Rook
        pieces.add(new Knight(BLACK, 1,0)); // Black b8-Knight
        pieces.add(new Knight(BLACK, 6,0)); // Black g8-Knight
        pieces.add(new Bishop(BLACK, 2,0)); // Black c8-Bishop
        pieces.add(new Bishop(BLACK, 5,0)); // Black f8-Bishop
        pieces.add(new Queen(BLACK,3,0));   // Black d8-Queen
        pieces.add(new King(BLACK,4,0));    // Black e8-King
    }

    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target) {
        target.clear();
        for(int i = 0; i < source.size(); i++) {
            target.add(source.get(i));
        }
    }

    private void update() {
        // When mouse button is pressed
        if (mouse.pressed) {
            // If the current chess piece is null, check if it can be picked up
            if (activePiece == null) {
                for (Piece piece : simPieces) {
                    // If the mouse is on the chess piece, set is as the active chess piece
                    if (piece.color == currentColor && piece.file == mouse.x/Board.SQUARE_SIZE && piece.rank == mouse.y/Board.SQUARE_SIZE) {
                        activePiece = piece;
                    } else if (piece.color != currentColor && piece.file == mouse.x/Board.SQUARE_SIZE && piece.rank == mouse.y/Board.SQUARE_SIZE) {
                        activePiece = piece;
                    }
                }
            } else {
                // If the current chess piece is being held, simulate the move
                simulate();
            }
        }

        // When mouse button is released
        if (!mouse.pressed){
            if (activePiece != null) {
                if (validSquare) {
                    // Updates the list of chess pieces when a chess piece has been captured
                    copyPieces(simPieces, pieces);
                    activePiece.updatePosition();
                } else {
                    // Not a legal move, reset position
                    activePiece.resetPosition();
                    activePiece = null;
                }
            }
        }
    }

    private void simulate() {
        canMove = false;
        validSquare = false;

        // Restores chess piece loop per loop
        copyPieces(pieces, simPieces);

        // If a chess piece is being held, position is updated
        activePiece.letter = mouse.x - Board.HALF_SQUARE_SIZE;
        activePiece.num = mouse.y - Board.HALF_SQUARE_SIZE;
        activePiece.file = activePiece.getFile(activePiece.letter);
        activePiece.rank = activePiece.getRank(activePiece.num);

        // Checks if the chess piece hovering over square is legal
        if (activePiece.canMove(activePiece.file, activePiece.rank)) {
            canMove = true;
            if (activePiece.targetedPiece != null) simPieces.remove(activePiece.targetedPiece.getIndex());
            validSquare = true;
        }
    }

    // Paint logic for Graphics
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;

        // Draw Board
        board.draw(graphics2D);

        // Create Chess Pieces
        for(Piece piece : simPieces) {
            piece.draw(graphics2D);
        }

        // Highlights the square the active chess piece is hovering over
        if (activePiece != null) {
            if (canMove) {
                graphics2D.setColor(Color.WHITE);
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                graphics2D.fillRect(activePiece.file*Board.SQUARE_SIZE, activePiece.rank*Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE);
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }

            // Draws active chess piece to prevent it from being hidden by the board and colored square
            activePiece.draw(graphics2D);
        }
    }

    // Runs Chess Game
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
