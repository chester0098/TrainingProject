package com.fadineg.trainingproject.first.tasks;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Набор тренингов по работе с примитивными типами java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 */
public class ElementaryTraining {

    /**
     * Метод должен возвращать среднее значение
     * для введенных параметров
     *
     * @param firstValue  первый элемент
     * @param secondValue второй элемент
     * @return среднее значение для введенных чисел
     */
    public double averageValue(int firstValue, int secondValue) {

        return (double) (firstValue + secondValue) / 2;
    }

    /**
     * Пользователь вводит три числа.
     * Произвести манипуляции и вернуть сумму новых чисел
     *
     * @param firstValue  увеличить в два раза
     * @param secondValue уменьшить на три
     * @param thirdValue  возвести в квадрат
     * @return сумма новых трех чисел
     */
    public int complicatedAmount(int firstValue, int secondValue, int thirdValue) {

        return (firstValue * 2) + (secondValue - 3) + (thirdValue * thirdValue);
    }

    /**
     * Метод должен поменять значение в соответствии с условием.
     * Если значение больше 3, то увеличить
     * на 10, иначе уменьшить на 10.
     *
     * @param value число для изменения
     * @return новое значение
     */
    public int changeValue(int value) {
        if (value > 3) {
            value += 10;
        } else value -= 10;

        return value;
    }

    /**
     * Метод должен менять местами первую
     * и последнюю цифру числа.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10, вернуть
     * то же число
     *
     * @param value число для перестановки
     * @return новое число
     */
    public int swapNumbers(int value) {
        if (value < 10 && value > -10 || value > 99999) return value;

        ArrayList<Integer> nums = new ArrayList<>();
        while (value != 0) {
            nums.add(value % 10);
            value /= 10;
        }
        int x = nums.get(0);
        nums.set(0, nums.get(nums.size() - 1));
        nums.set(nums.size() - 1, x);

        x = 1;
        for (int i = 0; i < nums.size(); i++) {
            value += nums.get(i) * x;
            x *= 10;
        }

        return value;
    }

    /**
     * Изменить значение четных цифр числа на ноль.
     * Счет начинать с единицы.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10 вернуть
     * то же число.
     *
     * @param value число для изменения
     * @return новое число
     */
    public int zeroEvenNumber(int value) {
        if (value < 10 && value > -10 || value > 99999) return value;

        ArrayList<Integer> nums = new ArrayList<>();
        while (value != 0) {
            nums.add(value % 10);
            value /= 10;
        }

        int x = 1;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) % 2 == 0 & i != nums.size() - 1)
                nums.set(i, 0);
            value += nums.get(i) * x;
            x *= 10;
        }
        return value;
    }
}