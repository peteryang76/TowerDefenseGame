package Model;

import java.awt.*;

public abstract class Enemy {

    protected int SPEED;
    protected int MAX_HEALTH;
    protected int REWARDS;
    protected int SPAWN_X = 100;
    protected int SPAWN_Y = 600;
    public int SCORE;

    private int xLocation;
    private int yLocation;
    protected int health;

    public Enemy() {
        xLocation = SPAWN_X;
        yLocation = SPAWN_Y;
        health = MAX_HEALTH;
    }

    // MODIFIES: this
    // EFFECTS: move enemy to the right for a speed unit
    public void moveRight() {
        xLocation += SPEED;
    }

    // MODIFIES: this
    // EFFECTS: move enemy to the bottom for a speed unit
    public void moveDown() {
        yLocation += SPEED;
    }

    // MODIFIES: this
    // EFFECTS: move enemy to the top for a speed unit
    public void moveUp() {
        yLocation -= SPEED;
    }


    // EFFECTS: return enemy's current x location
    public int getX() {
        return xLocation;
    }

    // EFFECTS: return enemy's current y location
    public int getY() {
        return yLocation;
    }

    public void setX(int x) {
        xLocation = x;
    }

    public void setY(int y) {
        yLocation = y;
    }

    public abstract  void draw(Graphics g);

    public void deductHealth(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public int getRewards() {
        return REWARDS;
    }

    public int getScore() {
        return SCORE;
    }

}
