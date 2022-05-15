package Model;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<List<Block>> grid;

    public int WIDTH = 11;
    public int HEIGHT = 11;

    public Map() {
        Block emptyBlock = new Path();
        grid = new ArrayList<>();
        for (int x = 0; x < WIDTH; x++) {
            grid.add(new ArrayList<>());
            for (int y = 0; y < HEIGHT; y++) {
                grid.get(x).add(emptyBlock);
            }
        }
    }

    // EFFECTS: return the block in the x, y coordinate;
    public Block getBlock(int x, int y) {
        return grid.get(x).get(y);
    }

    // REQUIRES: the x, y block is a path
    // MODIFIES: this
    // EFFECTS: place a cannon in the x, y coordinate
    public void placeACannon(int x, int y) {
        if (checkEntry(x, y)) {

        } else {
            Block cannon = new Cannon();
            grid.get(x).set(y, cannon);
        }
    }

    // REQUIRES: there is a cannon at x, y block
    // MODIFIES: this
    // EFFECTS: delete the cannon at the x, y coordinate
    public void deleteACannon(int x, int y) {

    }

    // EFFECTS: return ture if the x, y coordinate is the entry;
    //                 false otherwise;
    public boolean checkEntry(int x, int y) {
        return x == 0 && y == 5;
    }


}
