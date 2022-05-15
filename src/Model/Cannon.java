package Model;

import java.awt.*;

public class Cannon extends Block {

    public int SPEED = 2;
    public static final int COST = 200;

    private int SIZE_X = 80;
    private int SIZE_Y = 70;

    private Color mainCOLOR;
    public Color unavailableColor;

    private Color currentColor;

    public Cannon() {
        super("Cannon");
        mainCOLOR = new Color(0xB72B353E, true);
        unavailableColor = new Color(0xFF0808);
        RANGE = 200;
        DAMAGE = 2;
        currentColor = mainCOLOR;


    }




    @Override
    public void draw(Graphics g) {
        Color savedColor = g.getColor();
        g.setColor(currentColor);
        g.fillOval(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
        g.setColor(savedColor);
    }

    @Override
    public void setAsUnavailable() {
        currentColor = unavailableColor;
    }

    @Override
    public void setAsAvailable() {
        currentColor = mainCOLOR;
    }
}
