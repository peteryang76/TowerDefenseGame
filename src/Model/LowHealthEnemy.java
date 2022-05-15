package Model;

import java.awt.*;

public class LowHealthEnemy extends Enemy {
    private Color MAX_HEALTH_COLOR = new Color(0x22E0E0);
    private Color HALF_HEALTH_COLOR = new Color(0xFAFA01);
    private Color LOW_HEALTH_COLOR = new Color(0xEF0606);
    private static final int SIZE_X = 50;
    private static final int SIZE_Y = 40;

    public LowHealthEnemy() {
        super();
        SPEED = 5;
        MAX_HEALTH = 6;
        REWARDS = 10;
        health = MAX_HEALTH;
        SCORE = 100;

    }

    public void setCOLOR(Color color) {
        MAX_HEALTH_COLOR = color;
    }

    @Override
    public void draw(Graphics g) {
        Color savedColor = g.getColor();
        if (health == MAX_HEALTH) {
            g.setColor(MAX_HEALTH_COLOR);
        } else if (health > health / 2) {
            g.setColor(HALF_HEALTH_COLOR);
        } else if (health <= health / 2) {
            g.setColor(LOW_HEALTH_COLOR);
        }
        g.fillRect(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
        g.setColor(savedColor);
    }

}
