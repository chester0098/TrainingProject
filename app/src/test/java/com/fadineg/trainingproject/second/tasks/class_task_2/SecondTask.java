package com.fadineg.trainingproject.second.tasks.class_task_2;

import java.util.*;

/*
      II
      Создать класс, содержащий динамический массив и количество элементов в нем.
      Добавить конструктор, который выделяет память под заданное количество элементов.
      Добавить методы, позволяющие заполнять массив случайными числами,
      переставлять в данном массиве элементы в случайном порядке, находить количество
      различных элементов в массиве, выводить массив на экран.
     */

public class SecondTask {
    private List<Integer> list;
    private int x;

    private SecondTask(int x) {
        this.x = x;
        list = new ArrayList<>(x);
    }

    public static void main(String[] args) {
        System.out.println("Введите количество элементов массива");
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        SecondTask secondTask = new SecondTask(x);
        secondTask.randomSet();
        secondTask.mix();
        secondTask.uniqueElements();
    }

    private void randomSet() {
        for (int i = 0; i < x; i++) {
            list.add((int) Math.round(( Math.random() * 200) - 100));
        }
        System.out.println("Случайно заполненный массив" + '\n' + list);
    }

    private void mix() {
        for (int i = x - 1; i >= 1; i--) {
            int j = (int) (Math.random() * x);
            int temp = list.get(j);
            list.set(j, list.get(i));
            list.set(i, temp);
        }
        System.out.println("Случайно перемешанный массив" + '\n' + list);
    }

    private void uniqueElements() {
        Set<Integer> s = new LinkedHashSet<>(list);
        System.out.println("В массиве " + s.size() + " уникальных элементов");
        System.out.println(s);
    }
}
