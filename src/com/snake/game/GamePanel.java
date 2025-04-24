package com.snake.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private final GameState gameState;

    public GamePanel() {
        gameState = new GameState();
        setPreferredSize(new Dimension(
                GameState.GRID_WIDTH * GameState.CELL_SIZE,
                GameState.GRID_HEIGHT * GameState.CELL_SIZE
        ));
        setBackground(Color.BLACK);
        setFocusable(true);        // 必须设置焦点！！！！
        requestFocusInWindow();    // 主动请求焦点

        // 键盘事件监听（修复版）
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Direction current = gameState.getCurrentDirection();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (current != Direction.DOWN)  // 禁止反向移动
                            gameState.setCurrentDirection(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        if (current != Direction.UP)
                            gameState.setCurrentDirection(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        if (current != Direction.RIGHT)
                            gameState.setCurrentDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (current != Direction.LEFT)
                            gameState.setCurrentDirection(Direction.RIGHT);
                        break;
                }
            }
        });

        // 游戏循环（200ms更新一次）
        Timer gameTimer = new Timer(200, e -> {
            gameState.move();
            repaint();
        });
        gameTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 绘制蛇身
        g.setColor(Color.GREEN);
        for (int[] segment : gameState.getSnakeBody()) {
            g.fillRect(
                    segment[0] * GameState.CELL_SIZE,
                    segment[1] * GameState.CELL_SIZE,
                    GameState.CELL_SIZE - 1,
                    GameState.CELL_SIZE - 1
            );
        }

        // 绘制食物
        g.setColor(Color.RED);
        g.fillOval(
                gameState.getFood()[0] * GameState.CELL_SIZE,
                gameState.getFood()[1] * GameState.CELL_SIZE,
                GameState.CELL_SIZE - 1,
                GameState.CELL_SIZE - 1
        );

        // 显示分数
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + gameState.getScore(), 10, 20);
    }
}