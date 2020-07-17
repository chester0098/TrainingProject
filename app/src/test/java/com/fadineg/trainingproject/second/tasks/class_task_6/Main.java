package com.fadineg.trainingproject.second.tasks.class_task_6;

/*
      VI
      Задача на взаимодействие между классами. Разработать систему «Вступительные экзамены».
      Абитуриент регистрируется на Факультет, сдает Экзамены. Преподаватель выставляет Оценку.
      Система подсчитывает средний бал и определяет Абитуриента, зачисленного в учебное заведение.
     */

public class Main {
    public static void main(String[] args) {
        General general = new General();

        general.examAbiturients();
        general.countEverage();
        general.makeRating();
        general.whoEnroll();
        general.showAllTheAbiturients();
    }
}
