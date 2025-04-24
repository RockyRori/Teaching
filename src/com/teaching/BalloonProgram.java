package com.teaching;

import java.util.Scanner;

public class BalloonProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Hi there. What is your name? ");
        String name = scanner.nextLine();

        System.out.print("Hi, " + name + ". Do you like balloons? True or false? ");
        boolean likesBalloons = scanner.nextBoolean();

        System.out.print("How many balloons would you like today? ");
        int balloons = scanner.nextInt();

        // 增加3个气球
        balloons += 3;
        System.out.println("Okay! I'll give you 3 more which makes " + balloons + "!");

        // 使用复合赋值运算符除以2
        double finalBalloons = balloons / 2.0;
        System.out.println("Oh no! The balloons got chopped! I'm sorry. You now only have "
                + finalBalloons + " balloons.");

        scanner.close();
    }
}