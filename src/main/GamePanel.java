package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    final int FPS = 60;
    Thread gameThread;

    // GamePanel Constructor
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GRAY);
    }

    private void update() {

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
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
