package ui;

import Model.DefenseGame;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    private static final String SCORE_TXT = "Score: ";
    private static final String GOLD_TXT = "Gold: ";
    private static final String HEALTH_TXT = "Health: ";
    private static final int WIDTH = 200;
    private static final int HEIGHT = 30;

    private DefenseGame game;
    private JLabel scoreLabel;
    private JLabel goldLabel;
    private JLabel healthLabel;

    public ScorePanel(DefenseGame game) {
        this.game = game;
        setBackground(new Color(0x0BF384));
        scoreLabel = new JLabel(SCORE_TXT + game.getScore());
        scoreLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        goldLabel = new JLabel(GOLD_TXT + game.getGold());
        goldLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        healthLabel = new JLabel(HEALTH_TXT + game.getHealth());
        healthLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(scoreLabel);
        add(Box.createHorizontalStrut(10));
        add(goldLabel);
        add(Box.createHorizontalStrut(10));
        add(healthLabel);
    }

    public void update() {
        scoreLabel.setText(SCORE_TXT + game.getScore());
        goldLabel.setText(GOLD_TXT + game.getGold());
        healthLabel.setText(HEALTH_TXT + game.getHealth());
    }
}
