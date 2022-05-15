package test;

import Model.Enemy;
import Model.LowHealthEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DefenseGameTest {

    private List<Enemy> enemyList;
    private LowHealthEnemy enemy0;

    @BeforeEach
    void runBefore() {
        enemyList = new ArrayList<>();
        enemy0 = new LowHealthEnemy();
        enemyList.add(enemy0);
    }

    @Test
    void testMove() {
        enemy0.setX(1050);


    }
}
