package test;

import Model.Block;
import Model.Map;
import Model.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {
    private Map map;
    private Block path;
    @BeforeEach
    void runBefore() {
        map = new Map();
        path = new Path();
    }

    @Test
    void testConstructor() {
        assertEquals("Path", map.getBlock(0, 0).getType());
    }
}
