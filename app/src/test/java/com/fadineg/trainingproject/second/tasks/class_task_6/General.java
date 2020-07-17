package com.fadineg.trainingproject.second.tasks.class_task_6;

import java.util.Comparator;
import java.util.Scanner;

public class General {
    //Поступит только половина
    public static final double cf = 0.5;
    Scanner in;
    Faculty faculty;

    public General() {
        faculty = new Faculty();
        in = new Scanner(System.in);
    }

    public void examAbiturients() {
        int id;
        do {
            System.out.println("Добавить нового абитуриента?" + '\n' + "1 да, 0 нет");
            id = in.nextInt();
            if (id == 1) {
                faculty.addToTheFaculty();
            } else id = 0;
        } while (id == 1);

        exam();

    }

    public void exam() {
        if (faculty.abiturient.size() == 0){
            System.out.println("Список абитуриентов пуст");
            System.exit(0);
        }
        in = new Scanner(System.in);
        int exMath;
        int exPh;
        int exRus;
        System.out.println('\n' + "Выставите оценки");
        for (Student s : faculty.abiturient) {

            System.out.println(s.getName() + " " + s.getFamily() + ": ");

            System.out.println("Оценка за экзамен по математике: ");
            exMath = in.nextInt();

            System.out.println("Оценка за экзамен по физике: ");
            exPh = in.nextInt();

            System.out.println("Оценка за экзамен по русскому языку: ");
            exRus = in.nextInt();

            s.setExamMath(exMath);
            s.setExamPhysics(exPh);
            s.setExamRussianLanguage(exRus);
        }

    }

    public void countEverage() {
        int tempEverage;
        for (Student s : faculty.abiturient) {
            tempEverage = (s.getExamMath() + s.getExamPhysics() + s.getExamRussianLanguage()) / 3;
            s.setAverageRating(tempEverage);
        }
    }

    public void makeRating() {
        faculty.abiturient.sort(Comparator.comparing(Student::getAverageRating).reversed());
    }

    public void whoEnroll() {
        Student student;
        double possibleStudents = faculty.abiturient.size() * cf;
        System.out.println("Эти студенты зачислены:");
        for (int i = 0; i < possibleStudents; i++) {
            student = (Student) faculty.abiturient.get(i);
            System.out.println('\n' + "Имя: " + student.getName());
            System.out.println("Фамилия: " + student.getFamily());
            System.out.println("Номер группы: " + student.getGroup());
            System.out.println("Средняя оценка: " + student.getAverageRating());
        }
    }

    public void showAllTheAbiturients() {
        int i = 0;
        System.out.println('\n' + "Все абитуриенты:");
        for (Student student : faculty.abiturient) {
            student = (Student) faculty.abiturient.get(i);
            System.out.println( '\n' + "Имя: " + student.getName());
            System.out.println("Фамилия: " + student.getFamily());
            System.out.println("Номер группы: " + student.getGroup());
            System.out.println("Средняя оценка: " + student.getAverageRating());
            i++;
        }
    }


}