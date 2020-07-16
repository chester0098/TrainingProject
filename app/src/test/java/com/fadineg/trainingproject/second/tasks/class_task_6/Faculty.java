package com.fadineg.trainingproject.second.tasks.class_task_6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Faculty {
    Scanner in = new Scanner(System.in);
    public List<Student> abiturient;

    public Faculty() {
        abiturient = new ArrayList<>();
    }

    public void addToTheFaculty() {
        Student student = new Student();
        System.out.println("Введите ваше имя: ");
        student.setName(in.nextLine());
        System.out.println("Введите вашу фамилию: ");
        student.setFamily(in.nextLine());
        System.out.println("Введите номер группы: ");
        student.setGroup(in.nextLine());
        abiturient.add(student);
    }



}