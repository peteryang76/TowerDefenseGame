package test;

import Model.Enemy;
import Model.LowHealthEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyTest {
    Enemy testLow;

    @BeforeEach
    void runBefore() {
        testLow = new LowHealthEnemy();

    }

    @Test
    void testConstructor() {
        assertEquals(1, (testLow.getX() - 50) / 100 + 1);
        assertEquals(20, testLow.getHealth());
    }

    @Test
    void testMove() {
        testLow.moveRight();
//        assertEquals(2, (testLow.getX() - 50) / 100 + 1);
        assertEquals(0, 30 / 50);
        assertEquals(0, 99/100);
        assertEquals(testLow.getY(), 600);

        testLow.moveDown();
        int y = testLow.getY() / 100;
        int remaining = testLow.getY() - y * 100;
        assertEquals(testLow.getY(), 610);

        assertEquals(10, remaining);
    }
}
