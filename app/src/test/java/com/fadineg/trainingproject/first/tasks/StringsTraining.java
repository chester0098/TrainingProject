package com.fadineg.trainingproject.first.tasks;

import java.util.ArrayList;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see StringsTrainingTest.
 */
public class StringsTraining {

    /**
     * Метод по созданию строки,
     * состоящей из нечетных символов
     * входной строки в том же порядке
     *
     * @param text строка для выборки
     * @return новая строка из нечетных
     * элементов строки text
     */
    public String getOddCharacterString(String text) {
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < text.length(); i++){
            int x = Integer.parseInt(String.valueOf(text.charAt(i)));
            if (x%2!=0){
                newString.append(x);
            }
        }
        return newString.toString();
    }

    /**
     * Метод для определения количества
     * символов, идентичных последнему
     * в данной строке
     *
     * @param text строка для выборки
     * @return массив с номерами символов,
     * идентичных последнему. Если таких нет,
     * вернуть пустой массив
     */
    public int[] getArrayLastSymbol(String text) {

        int[] resultMass;
        if (text.equals("")) {
            resultMass = new int[0];
            return resultMass;
        }

        ArrayList<Integer> mass = new ArrayList<>();
        char x = text.charAt(text.length()-1);
        for (int i = 0; i < text.length()-1; i++){
            if (text.charAt(i) == x){
                mass.add(i);
            }
        }

        resultMass = new int[mass.size()];
        for (int i = 0; i<mass.size();i++){
            resultMass[i] = mass.get(i);
        }
        return resultMass;
    }

    /**
     * Метод по получению количества
     * цифр в строке
     *
     * @param text строка для выборки
     * @return количество цифр в строке
     */
    public int getNumbersCount(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    /**
     * Дан текст. Заменить все цифры
     * соответствующими словами.
     *
     * @param text текст для поиска и замены
     * @return текст, где цифры заменены словами
     */
    public String replaceAllNumbers(String text) {
        String[] x = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        for (int i = 0; i < text.length(); i++){
            if (Character.isDigit(text.charAt(i))){
                int b = Integer.parseInt(String.valueOf(text.charAt(i)));
                text = text.replace(String.valueOf(text.charAt(i)), x[b]);
            }
        }
        return text;
    }

    /**
     * Метод должен заменить заглавные буквы
     * на прописные, а прописные на заглавные
     *
     * @param text строка для изменения
     * @return измененная строка
     */
    public String capitalReverse(String text) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < text.length(); i++){
            char x = text.charAt(i);
            stringBuilder.append(Character.isLowerCase(x)? Character.toUpperCase(x):Character.toLowerCase(x));
        }

        return stringBuilder.toString();
    }
}