package ui;

import Model.DefenseGame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private String OVER = "You have lost all health \nGame Over";
    private DefenseGame game;

    public GamePanel(DefenseGame game) {
        setPreferredSize(new Dimension(DefenseGame.WIDTH, DefenseGame.HEIGHT));
        setBackground(Color.GRAY);
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

        if (game.isGameOver()) {
            gameOver(g);
        }


    }

    // MODIFIES: game
    // EFFECTS: draw the game onto the Graphics object g
    private void drawGame(Graphics g) {
        game.draw(g);
    }

    // MODIFIES: g
    // EFFECTS: draw "game over" instruction onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        putAtCenter(OVER, g, fm, DefenseGame.HEIGHT / 2);
        g.setColor(saved);
    }

    private void putAtCenter(String s, Graphics g, FontMetrics fm, int yPos) {
        int width = fm.stringWidth(s);
        g.drawString(s, (DefenseGame.WIDTH - width) / 2, yPos);
    }
}
