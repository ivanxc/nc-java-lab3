package com.ivanxc.netcracker.lab.сhapter3;

public class Student {
    private String name;
    private int totalQuizScore;
    private int numberOfQuizzes;
    private double averageQuizScore;

    public Student(String name) {
        this.name = name;
    }

    public void addQuiz(int score) {
        totalQuizScore += score;
        numberOfQuizzes++;
        averageQuizScore = (double)totalQuizScore / numberOfQuizzes;
    }

    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return totalQuizScore;
    }

    public double getAverageScore() {
        return averageQuizScore;
    }
}
