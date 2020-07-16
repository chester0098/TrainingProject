package com.fadineg.trainingproject.second.tasks;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see CollectionsBlockTest.
 */
public class CollectionsBlock<T extends Comparable> {

    /**
     * Даны два упорядоченных по убыванию списка.
     * Объедините их в новый упорядоченный по убыванию список.
     * Исходные данные не проверяются на упорядоченность в рамках данного задания
     *
     * @param firstList  первый упорядоченный по убыванию список
     * @param secondList второй упорядоченный по убыванию список
     * @return объединенный упорядоченный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask0(List<T> firstList, List<T> secondList) {

        try {
            List<T> finalList = new ArrayList<>(firstList);
            finalList.addAll(secondList);
            Collections.sort(finalList, Collections.reverseOrder());
            return finalList;
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }

    }

    /**
     * Дан список. После каждого элемента добавьте предшествующую ему часть списка.
     *
     * @param inputList с исходными данными
     * @return измененный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask1(List<T> inputList) {
        try {
            List<T> outputList = new ArrayList<>();
            for (int i = 0; i < inputList.size(); i++) {
                outputList.add(inputList.get(i));
                outputList.addAll(inputList.subList(0, i));
            }
            return outputList;
        } catch (NullPointerException e) {
            throw new  NullPointerException();
        }

    }

    /**
     * Даны два списка. Определите, совпадают ли множества их элементов.
     *
     * @param firstList  первый список элементов
     * @param secondList второй список элементов
     * @return <tt>true</tt> если множества списков совпадают
     * @throws NullPointerException если один из параметров null
     */
    public boolean collectionTask2(List<T> firstList, List<T> secondList) {


        try {
            List<T> newFirst = new ArrayList<>(firstList);
            List<T> newSecond = new ArrayList<>(secondList);
            newFirst.removeAll(secondList);
            newSecond.removeAll(firstList);
            return newFirst.size() == 0 & newSecond.size() == 0;
        }
        catch (NullPointerException e){
            throw  new NullPointerException();
        }



    }

    /**
     * Создать список из заданного количества элементов.
     * Выполнить циклический сдвиг этого списка на N элементов вправо или влево.
     * Если N > 0 циклический сдвиг вправо.
     * Если N < 0 циклический сдвиг влево.
     *
     * @param inputList список, для которого выполняется циклический сдвиг влево
     * @param n         количество шагов циклического сдвига N
     * @return список inputList после циклического сдвига
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask3(List<T> inputList, int n) {

        try {
            Collections.rotate(inputList, n);
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
        return inputList;
    }

    /**
     * Элементы списка хранят слова предложения.
     * Замените каждое вхождение слова A на B.
     *
     * @param inputList список со словами предложения и пробелами для разделения слов
     * @param a         слово, которое нужно заменить
     * @param b         слово, на которое нужно заменить
     * @return список после замены каждого вхождения слова A на слово В
     * @throws NullPointerException если один из параметров null
     */


    public List<String> collectionTask4(List<String> inputList, @NonNull String a,
                                        @NonNull String b) {
        int i;
        try {
            while( (i = inputList.indexOf(a)) > -1) {
                inputList.set(i, b);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
        return inputList;
    }
    }
    /*
  Задание подразумевает создание класса(ов) для выполнения задачи.
  Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
  курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
  Упорядочите студентов по курсу, причем студенты одного курса располагались
  в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
  Определите самого старшего студента и самого младшего студентов.
  Для каждой группы найдите лучшего с точки зрения успеваемости студента.
 */
    //Задание находится в папке collections_task_last

