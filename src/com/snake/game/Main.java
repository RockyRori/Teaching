package com.snake.game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("贪吃蛇大作战");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GamePanel());
        frame.pack();
        frame.setLocationRelativeTo(null); // 窗口居中
        frame.setVisible(true);
        frame.setResizable(false);        // 禁止调整窗口大小
    }
}