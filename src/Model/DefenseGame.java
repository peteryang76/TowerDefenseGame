package Model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class DefenseGame {

    public static int WIDTH = 1200;
    public static int HEIGHT = 1200;
    public int INITIAL_GOLD = 1000;
    public int MAX_HEALTH = 10;
    public int INITIAL_SCORE = 0;
    public int DOWN = 1;
    public int TIME_INTERVAL = 5000;

    private int gold;
    private boolean gameOver;
    private Map map;
    private List<Enemy> enemyArmy;
    private List<Block> towers;
    private int enemySpawnTimer;
    private int towersAttackTimer;
    private int health;
    private int score;
    private int direction;
    private boolean canAddTower;
    private boolean canMoveTower;

    public DefenseGame() {
        initialize();

    }

    private void initialize() {
        gameOver = false;
        gold = INITIAL_GOLD;
        map = new Map();
        enemyArmy = new ArrayList<Enemy>();
        towers = new ArrayList<Block>();
        enemySpawnTimer = 0;
        towersAttackTimer = 0;
        health = MAX_HEALTH;
        score = INITIAL_SCORE;
        direction = 0;
        canAddTower = true;
        canMoveTower = false;
    }

    // MODIFIES: this
    // EFFECTS: update enemy
    public void update() {
        moveEnemy();
        checkGameOver();
        spawnNewEnemy();
        checkHealth();
        checkAttack();

    }

    // MODIFIES: this
    // EFFECTS: move enemy to the next location
    public void moveEnemy() {
        for (Enemy enemy: enemyArmy) {
            int x = (enemy.getX() - 99) / 100;
            int y = (enemy.getY() + 99) / 100 - 1;
            int right = (enemy.getX() - 99) / 100 + 1;
            int up = (enemy.getY() + 99) / 100 - 2;
            int times = enemy.getY() / 100;
            int remaining = enemy.getY() - times * 100;
            if (direction == DOWN && remaining != 0) {
                enemy.moveDown();
            } else if (map.getBlock(right, y).getType() == ("Path")) {
                enemy.moveRight();
                direction = 0;
            } else if (map.getBlock(x, up).getType() == ("Path")) {
                    enemy.moveUp();
                } else {
                    enemy.moveDown();
                    direction = DOWN;
            }
        }
    }


    // MODIFIES: enemyArmy
    // EFFECTS: spawn new enemy based on time past
    public void spawnNewEnemy() {
        enemySpawnTimer += 20;
        if (enemySpawnTimer == TIME_INTERVAL) {
            Enemy newEnemy = new LowHealthEnemy();
            enemyArmy.add(newEnemy);
            enemySpawnTimer = 0;
        }
    }

    public void checkAttack() {
        towersAttackTimer += 20;
        if (towersAttackTimer == 1000) {
            for (Block tower : towers) {
                int x_Center = tower.getX() * 100 + 50;
                int y_Center = tower.getY() * 100 + 50;
                int range = tower.RANGE;
                int damage = tower.DAMAGE;
                for (Enemy enemy : enemyArmy) {
                    int x_Location_Center = enemy.getX() + 50;
                    int y_Location_Center = enemy.getY() + 50;
                    int dx = Math.abs(x_Location_Center - x_Center);
                    int dy = Math.abs(y_Location_Center - y_Center);
                    double distance = Math.sqrt(dx ^ 2 + dy ^ 2);
                    if (distance <= range) {
                        enemy.deductHealth(damage);
                    }

                }
            }
            towersAttackTimer = 0;
        }
    }


    // MODIFIES: this
    // EFFECTS: if HEALTH <= 0, gameOver is true; false otherwise
    private void checkGameOver() {
        if (health <= 0) {
            gameOver = true;
        }
    }

    public void draw(Graphics g) {
        for (Enemy next: enemyArmy) {
            next.draw(g);
        }
        for (Block next: towers) {
            next.draw(g);
        }

    }

    // EFFECTS: return gameOver
    public boolean isGameOver() {
        return gameOver;
    }

    // MODIFIES: this
    // EFFECTS: place the cannon and choose location
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_A) {
            int cost = Cannon.COST;
            if (canAddTower && gold >= cost) {
                Cannon newCannon = new Cannon();
                towers.add(newCannon);
                canAddTower = false;
                canMoveTower = true;
                gold -= cost;
            }
        } else if (keyCode == KeyEvent.VK_LEFT) {
            checkAndMove(-100, 0);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            checkAndMove(100, 0);
        } else if (keyCode == KeyEvent.VK_UP) {
            checkAndMove(0, -100);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            checkAndMove(0, 100);
        } else if (keyCode == KeyEvent.VK_ENTER) {
            if (canMoveTower) {
                int size = towers.size() - 1;
                int x = towers.get(size).getX() / 100 - 1;
                int y = towers.get(size).getY() / 100 - 1;

                if (checkAvailability(x, y)) {
                    map.placeACannon(x, y);
                    canMoveTower = false;
                    canAddTower = true;
                } else {
                    towers.get(size).setAsUnavailable();
                }
            }
        }
    }

    private void checkAndMove(int dx, int dy) {
        if (canMoveTower) {
            int size = towers.size() - 1;
            int y = towers.get(size).getY() + dy;
            int x = towers.get(size).getX() + dx;
            if (y <= 100) {
                y = 100;
            } else if (y >= 1100) {
                y = 1100;
            }
            if (x <= 100) {
                x = 100;
            } else if (x >= 1100) {
                x = 1100;
            }
            towers.get(size).setX(x);
            towers.get(size).setY(y);
            if (checkAvailability(x / 100 - 1, y / 100 - 1)) {
                towers.get(size).setAsAvailable();
            } else {
                towers.get(size).setAsUnavailable();
            }
        }
    }

    public void checkHealth() {
        List<Enemy> toBeRemoved = new ArrayList<>();
        int healthDeducted = 0;
        for (Enemy next: enemyArmy) {
            if (next.getX() >= 1050) {
                toBeRemoved.add(next);
                healthDeducted++;
            }
            if (next.getHealth() <= 0) {
                toBeRemoved.add(next);
                int reward = next.getRewards();
                gold += reward;
                int rewardScore = next.getScore();
                score += rewardScore;
            }
        }
        enemyArmy.removeAll(toBeRemoved);
        health -= healthDeducted;
    }

    public boolean checkAvailability(int x, int y) {
        return map.getBlock(x, y).getType() == "Path" && !map.checkEntry(x, y);
    }

    public int getScore() {
        return score;
    }

    public int getGold() {
        return gold;
    }

    public int getHealth() {
        return health;
    }
}
