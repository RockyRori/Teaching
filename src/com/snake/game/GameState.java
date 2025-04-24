package com.snake.game;

import java.util.LinkedList;

// 游戏状态管理（蛇身、食物、分数）
public class GameState {
    public static final int CELL_SIZE = 20;       // 网格大小
    public static final int GRID_WIDTH = 30;      // 网格宽度（单位：格）
    public static final int GRID_HEIGHT = 20;     // 网格高度

    private LinkedList<int[]> snakeBody;          // 蛇身坐标（链表结构）
    private int[] food;                           // 食物坐标
    private Direction currentDirection;           // 当前方向
    private int score;                            // 得分

    public GameState() {
        resetGame();
    }

    // 初始化游戏状态
    public void resetGame() {
        snakeBody = new LinkedList<>();
        snakeBody.add(new int[]{5, 5});           // 初始蛇头位置
        currentDirection = Direction.RIGHT;       // 初始方向向右
        score = 0;
        generateFood();
    }

    // 生成新食物（避免生成在蛇身上）
    private void generateFood() {
        food = new int[]{
                (int) (Math.random() * GRID_WIDTH),
                (int) (Math.random() * GRID_HEIGHT)
        };
        // 如果食物生成在蛇身上，重新生成
        for (int[] segment : snakeBody) {
            if (segment[0] == food[0] && segment[1] == food[1]) {
                generateFood();
                break;
            }
        }
    }

    // 移动蛇（核心逻辑）
    public void move() {
        int[] head = snakeBody.getFirst().clone();
        switch (currentDirection) {
            case UP:
                head[1]--;
                break;
            case DOWN:
                head[1]++;
                break;
            case LEFT:
                head[0]--;
                break;
            case RIGHT:
                head[0]++;
                break;
        }

        // 检测碰撞
        if (isCollision(head)) {
            resetGame();
            return;
        }

        snakeBody.addFirst(head);
        // 吃到食物
        if (head[0] == food[0] && head[1] == food[1]) {
            score += 10;
            generateFood();
        } else {
            snakeBody.removeLast();  // 没吃到食物时移除尾部
        }
    }

    // 碰撞检测（撞墙或自身）
    private boolean isCollision(int[] head) {
        // 撞墙检测
        if (head[0] < 0 || head[0] >= GRID_WIDTH ||
                head[1] < 0 || head[1] >= GRID_HEIGHT) {
            return true;
        }
        // 撞自身检测
        for (int[] segment : snakeBody) {
            if (head[0] == segment[0] && head[1] == segment[1]) {
                return true;
            }
        }
        return false;
    }

    // Getter方法
    public LinkedList<int[]> getSnakeBody() {
        return snakeBody;
    }

    public int[] getFood() {
        return food;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction dir) {
        currentDirection = dir;
    }

    public int getScore() {
        return score;
    }
}