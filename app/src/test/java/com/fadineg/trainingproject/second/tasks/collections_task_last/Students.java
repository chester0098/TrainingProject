package com.fadineg.trainingproject.second.tasks.collections_task_last;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
  Задание подразумевает создание класса(ов) для выполнения задачи.
  Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
  курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
  Упорядочите студентов по курсу, причем студенты одного курса располагались
  в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
  Определите самого старшего студента и самого младшего студентов.
  Для каждой группы найдите лучшего с точки зрения успеваемости студента.
 */
class Students {

    public void main(String[] args) {
        List<StudentsModel> studentsModelList = new ArrayList<>();

        studentsModelList.add(new StudentsModel("Евгений", "Геннадьевич", "Фадин",
                1997, 2, 202, 4, 4, 3, 5, 5));
        studentsModelList.add(new StudentsModel("Илья", "Николаевич", "Кашин",
                1996, 3, 302, 3, 4, 5, 4, 3));
        studentsModelList.add(new StudentsModel("Лев", "Алексеевич", "Резанов",
                1997, 2, 202, 5, 3, 5, 5, 5));
        studentsModelList.add(new StudentsModel("Григорий", "Петрович", "Алешкин",
                1996, 3, 303, 5, 4, 5, 4, 5));
        studentsModelList.add(new StudentsModel("Геннадий", "Евгеньевич", "Сотов",
                1998, 1, 102, 5, 3, 5, 2, 5));
        studentsModelList.add(new StudentsModel("Алексей", "Александрович", "Лазарев",
                1998, 1, 102, 5, 5, 5, 5, 5));
        studentsModelList.add(new StudentsModel("Максим", "Евгеньевич", "Носов",
                1998, 1, 102, 5, 4, 3, 5, 5));
        studentsModelList.add(new StudentsModel("Костя", "Александрович", "Лазарев",
                1998, 1, 102, 5, 4, 3, 5, 5));
        Students students = new Students();
        students.sortSt(studentsModelList);
        students.averageMark(studentsModelList);
        students.minAndMaxAge(studentsModelList);
        students.mostSuccessful(studentsModelList);

    }

    //Упорядочите студентов по курсу, причем студенты одного курса располагались в алфавитном порядке.
    public void sortSt(List<StudentsModel> list) {
        list.sort(Comparator.comparing(StudentsModel::getCourse)
                .thenComparing(StudentsModel::getFamily)
                .thenComparing(StudentsModel::getName));
        for (StudentsModel s : list) {
            System.out.println(s.getCourse() + " " + s.getFamily() + " " + s.getName());
        }
    }

    //Найдите средний балл каждой группы по каждому предмету.
    public void averageMark(List<StudentsModel> list) {
        List<StudentsModel> averageList = new ArrayList<>();
        Set<Integer> group = new LinkedHashSet<>();
        int sr1, sr2, sr3, sr4, sr5;
        //Определяем список групп
        for (StudentsModel s : list) {
            group.add(s.getGroupNumber());
        }

        for (Integer i : group) {
            sr1 = 0;
            sr2 = 0;
            sr3 = 0;
            sr4 = 0;
            sr5 = 0;
            averageList.clear();
            averageList.addAll(list.stream()
                    .filter(l -> l.getGroupNumber() == i)
                    .collect(Collectors.toList()));
            for (StudentsModel s : averageList) {
                sr1 += s.getP1();
                sr2 += s.getP2();
                sr3 += s.getP3();
                sr4 += s.getP4();
                sr5 += s.getP5();
            }
            System.out.println('\n' + "Группа номер: " + i);
            System.out.println("Средний балл по 1 предмету: " + sr1 / averageList.size());
            System.out.println("Средний балл по 2 предмету: " + sr2 / averageList.size());
            System.out.println("Средний балл по 3 предмету: " + sr3 / averageList.size());
            System.out.println("Средний балл по 4 предмету: " + sr4 / averageList.size());
            System.out.println("Средний балл по 5 предмету: " + sr5 / averageList.size() + '\n');
        }

    }

    //Определите самого старшего студента и самого младшего студентов.
    public void minAndMaxAge(List<StudentsModel> list) {
        list.sort(Comparator.comparing(StudentsModel::getYearOfBirth));
        System.out.println('\n' + "Самый старший студент: " + list.get(0).getFamily() + " " + list.get(0).getName());
        System.out.println("Самый младший студент: " + list.get(list.size() - 1).getFamily() + " " + list.get(list.size() - 1).getName());
    }

    //Для каждой группы найдите лучшего с точки зрения успеваемости студента.
    public void mostSuccessful(List<StudentsModel> list) {
        Set<Integer> group = new LinkedHashSet<>();
        List<StudentsModel> averageList = new ArrayList<>();
        StudentsModel student = null;
        int sum;
        //Определяем список групп
        for (StudentsModel s : list) {
            group.add(s.getGroupNumber());
        }
        for (Integer i : group) {
            sum = 0;

            averageList.clear();
            averageList.addAll(list.stream()
                    .filter(l -> l.getGroupNumber() == i)
                    .collect(Collectors.toList()));
            for (StudentsModel s : averageList) {
                if (s.getP1() + s.getP2() + s.getP3() + s.getP4() + s.getP5() > sum) {
                    sum = s.getP1() + s.getP2() + s.getP3() + s.getP4() + s.getP5();
                    student = s;
                }
            }
            System.out.println('\n' + "Самый успевающий ученик из группы " + i + ":");
            System.out.println(student.getFamily() + " " + student.getName());
        }
    }

}
