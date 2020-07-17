package com.fadineg.trainingproject.second.tasks.class_task_1;

import java.util.Scanner;
/*
      I
      Создать класс с двумя переменными. Добавить функцию вывода на экран
      и функцию изменения этих переменных. Добавить функцию, которая находит
      сумму значений этих переменных, и функцию которая находит наибольшее
      значение из этих двух переменных.
     */

class FirstTask {
    private int a;
    private int b;
    private Scanner in;
    public FirstTask(){
        a = 5;
        b = 10;
        in = new Scanner(System.in);
    }

    public static void main(String[] args) {
        FirstTask first = new FirstTask();
        first.menu();
    }

    void menu() {
        System.out.println();
        System.out.println("Введите номер:" + '\n'
                + "1.Вывести на экран" + '\n'
                + "2.Изменить" + '\n'
                + "3.Сложить" + '\n'
                + "4.Найти максимальное" + '\n'
                + "5.Завершить программу" + '\n');
        int idMenu = in.nextInt();
        switch (idMenu) {
            case 1:
                view();
                break;
            case 2:
                scan();
                break;
            case 3:
                addition();
                break;
            case 4:
                maximum();
                break;
            case 5:
                System.exit(0);
                break;
        }
    }

    void view() {
        System.out.println("Переменная 1: " + a);
        System.out.println("Переменная 2: " + b);
        menu();
    }

    void scan() {
        System.out.println("Введите первое значение");
        a = in.nextInt();
        System.out.println("Введите второе значение");
        b = in.nextInt();
        System.out.println("Значения успешно изменены");
        menu();
    }

    void addition() {
        int sum = a + b;
        System.out.println("Сумма " + a + " и " + b + " = " + sum);
        menu();
    }

    void maximum() {
        int max = Math.max(a, b);
        System.out.println("Наибольшее значение: " + max);
        menu();
    }
}
