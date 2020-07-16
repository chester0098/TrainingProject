package com.fadineg.trainingproject.second.tasks.class_task_4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/*
      IV
      Составить описание класса для представления времени.
      Предусмотреть возможности установки времени и изменения его отдельных полей
      (час, минута, секунда) с проверкой допустимости вводимых значений.
      В случае недопустимых значений полей выбрасываются исключения.
      Создать методы изменения времени на заданное количество часов, минут и секунд.
     */

public class Time {
    private Calendar cal;
    private DateFormat dateFormat;

    private Time(){
        cal = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    public static void main(String[] args) throws InvalidDateException {
        Time time = new Time();
        time.menu();
    }

    private void menu() throws InvalidDateException {
        int hour, minute, second;
        System.out.println("Введите номер:" + '\n'
                + "1.Вывести текущее время на экран" + '\n'
                + "2.Изменить часы" + '\n'
                + "3.Изменить минуты" + '\n'
                + "4.Изменить секунды" + '\n'
                + "5.Изменение времени полностью" + '\n'
                + "6.Завершить программу" + '\n');
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        switch (i) {
            case 1:
                currentTime();
                break;
            case 2:
                System.out.println("Введите час");
                hour = in.nextInt();
                changeHour(hour);
                break;
            case 3:
                System.out.println("Введите минуты");
                minute = in.nextInt();
                changeMinute(minute);
                break;
            case 4:
                System.out.println("Введите секунды");
                second = in.nextInt();
                changeSecond(second);
                break;
            case 5:
                System.out.println("Введите час");
                hour = in.nextInt();
                System.out.println("Введите минуты");
                minute = in.nextInt();
                System.out.println("Введите секунды");
                second = in.nextInt();
                changeFullTime(hour, minute, second);
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Введите значение от 1 до 6");
                menu();
                break;
        }
    }

    private void currentTime() throws InvalidDateException {
        System.out.println("Текущее время : " + dateFormat.format(cal.getTime()));
        menu();
    }

    private void changeHour(int hour) throws InvalidDateException {
        if (hour < 0 || hour > 23) throw new InvalidDateException("Неверное значение часа", hour);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        System.out.println("Час изменен : " + dateFormat.format(cal.getTime()));
        menu();
    }

    private void changeMinute(int minute) throws InvalidDateException {
        if (minute < 0 || minute > 59) throw new InvalidDateException("Неверное значение минут", minute);
        cal.set(Calendar.MINUTE, minute);
        System.out.println("Минуты изменены : " + dateFormat.format(cal.getTime()));
        menu();
    }

    private void changeSecond(int second) throws InvalidDateException {
        if (second < 0 || second > 59) throw new InvalidDateException("Неверное значение секунд", second);
        cal.set(Calendar.SECOND, second);
        System.out.println("Секунды изменены : " + dateFormat.format(cal.getTime()));
        menu();
    }

    private void changeFullTime(int hour, int minute, int second) throws InvalidDateException {
        if (hour < 0 || hour > 23) throw new InvalidDateException("Неверное значение часа", hour);
        if (minute < 0 || minute > 59) throw new InvalidDateException("Неверное значение минут", minute);
        if (second < 0 || second > 59) throw new InvalidDateException("Неверное значение секунд", second);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        System.out.println("Время изменено : " + dateFormat.format(cal.getTime()));
        menu();
    }
}

class InvalidDateException extends Exception {

    private int num;
    public int getNumber() {
        return num;
    }

    public InvalidDateException(String message, int num) {

        super(message);
        this.num = num;
    }
}
