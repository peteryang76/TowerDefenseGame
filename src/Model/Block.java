package Model;

import java.awt.*;

public abstract class Block {

    public int SPAWN_X = 100;
    public int SPAWN_Y = 100;
    public int RANGE;
    public int DAMAGE;


    private String type;
    private int x_location;
    private int y_location;

    public Block(String type) {
        this.type = type;
        x_location = SPAWN_X;
        y_location = SPAWN_Y;

    }

    // EFFECTS: return type of the block
    public String getType() {
        return type;
    }

    public int getX() {
        return x_location;
    }

    public int getY() {
        return y_location;
    }

    public void setX(int x) {
        x_location = x;
    }

    public void setY(int y) {
        y_location = y;
    }

    public abstract void draw(Graphics g);

    public abstract void setAsUnavailable();

    public abstract void setAsAvailable();
}
