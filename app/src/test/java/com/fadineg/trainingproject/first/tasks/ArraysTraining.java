package com.fadineg.trainingproject.first.tasks;


/**
 * Набор тренингов по работе с массивами в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ArraysTrainingTest.
 */
public class ArraysTraining {

    /**
     * Метод должен сортировать входящий массив
     * по возрастранию пузырьковым методом
     *
     * @param valuesArray массив для сортировки
     * @return отсортированный массив
     */
    public int[] sort(int[] valuesArray) {

        for (int i = 0; i < valuesArray.length; i++)
            for (int x = 0; x < valuesArray.length - 1; x++)
                if (valuesArray[x] > valuesArray[x + 1]){
                    int temp = valuesArray[x];
                    valuesArray[x] = valuesArray[x + 1];
                    valuesArray[x + 1] = temp;
                }
        return valuesArray;
    }

    /**
     * Метод должен возвращать максимальное
     * значение из введенных. Если входящие числа
     * отсутствуют - вернуть 0
     *
     * @param values входящие числа
     * @return максимальное число или 0
     */
    public int maxValue(int... values) {
        if (values.length == 0) {
            return 0;
        } else {
            int max = values[0];
            for (int value : values) {
                if (value>max) {
                    max = value;
                }
            }
            return max;
        }
    }

    /**
     * Переставить элементы массива
     * в обратном порядке
     *
     * @param array массив для преобразования
     * @return входящий массив в обратном порядке
     */
    public int[] reverse(int[] array) {
        int[] reverse = new int[array.length];
        int x = 0;

        for (int i = array.length-1; i>=0;i--){
            reverse[x] = array[i];
            x++;
        }

        return reverse;
    }

    /**
     * Метод должен вернуть массив,
     * состоящий из чисел Фибоначчи
     *
     * @param numbersCount количество чисел Фибоначчи,
     *                     требуемое в исходящем массиве.
     *                     Если numbersCount < 1, исходный
     *                     массив должен быть пуст.
     * @return массив из чисел Фибоначчи
     */
    public int[] fibonacciNumbers(int numbersCount) {
        int[] fib;

        if (numbersCount<=0){
            fib = new int[0];
            return  fib;
        }

        fib = new int[numbersCount];
        if (numbersCount == 1) {
            fib[0] = 0;
            }
        else if (numbersCount == 2){
            fib[0] = 0;
            fib[1] = 1;
        }
        else {
            fib[0] = 0;
            fib[1] = 1;
            for (int i = 2; i<numbersCount; i++){
                fib[i] = fib[i-1] + fib[i-2];
            }
        }
        return fib;
    }

    /**
     * В данном массиве найти максимальное
     * количество одинаковых элементов.
     *
     * @param array массив для выборки
     * @return количество максимально встречающихся
     * элементов
     */
    public int maxCountSymbol(int[] array) {
        int[] count = new int[10];
        int maxVal=0;

        for (int value : array) {
            ++count[value];
        }

        for (int value : count) {
            if (value > maxVal) {
                maxVal = value;
            }
        }

        return maxVal;
    }
}