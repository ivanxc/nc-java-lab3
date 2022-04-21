package com.ivanxc.netcracker.lab.сhapter3;

public class StudentTester {
    public static void test() {
        Student student = new Student("Ivan");
        System.out.println("Student name: \t" + student.getName());
        System.out.println("Student total score: \t" + student.getTotalScore());
        System.out.println("Student average score: \t" + student.getAverageScore());
        System.out.println("====================");
        student.addQuiz(60);
        System.out.println("Student total score: \t" + student.getTotalScore());
        System.out.println("Student average score: \t" + student.getAverageScore());
        System.out.println("====================");
        student.addQuiz(80);
        System.out.println("Student total score: \t" + student.getTotalScore());
        System.out.println("Student average score: \t" + student.getAverageScore());
        System.out.println("====================");
        student.addQuiz(100);
        System.out.println("Student total score: \t" + student.getTotalScore());
        System.out.println("Student average score: \t" + student.getAverageScore());
    }
}
