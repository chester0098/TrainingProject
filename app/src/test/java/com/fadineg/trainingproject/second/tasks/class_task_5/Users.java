package com.fadineg.trainingproject.second.tasks.class_task_5;

import com.fadineg.trainingproject.second.tasks.class_task_5.Subscriber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
      V
      Класс Абонент: Идентификационный номер, Фамилия, Имя, Отчество, Адрес,
      Номер кредитной карточки, Дебет, Кредит, Время междугородных и городских переговоров;
      Конструктор; Методы: установка значений атрибутов, получение значений атрибутов,
      вывод информации. Создать массив объектов данного класса.
      Вывести сведения относительно абонентов, у которых время городских переговоров
      превышает заданное.  Сведения относительно абонентов, которые пользовались
      междугородной связью. Список абонентов в алфавитном порядке.
     */

public class Users {
    private List<Subscriber> subscribers;
    private Scanner in;

    private Users(){
        subscribers = new ArrayList<>();
        in = new Scanner(System.in);
    }

    public static void main(String[] args) {

        Users users = new Users();

        users.subscribers.add(new Subscriber(2342, "Евгений", "Геннадьевич", "Фадин",
                "Попова 2", 123456, 0, 6, 3, 5));

        users.subscribers.add(new Subscriber(6456, "Илья", "Петрович", "Осин",
                "Энгельса 7", 82633, 6, 2, 23, 7));

        users.subscribers.add(new Subscriber(6456, "Роман", "Александрович", "Романов",
                "Сурская 4", 3453453, 1, 25, 0, 34));

        users.subscribers.add(new Subscriber(2344, "Екатерина", "Романовна", "Вилкова",
                "Коммунистическая 123", 76345, 4, 75, 45, 65));

        users.subscribers.add(new Subscriber(8678, "Виктор", "Максимович", "Ошкин",
                "Серадзская 23", 64557, 7, 43, 0, 45));


        //Вывести сведения относительно абонентов, у которых время городских переговоров превышает заданное.
        System.out.println("Введите значение городских вызовов:");
        int n = users.in.nextInt();
        users.cityCallMore(users.subscribers.stream()
                .filter(subscriber -> subscriber.getCityCall() > n)
                .collect(Collectors.toList()));

        //Сведения относительно абонентов, которые пользовались междугородной связью.
        users.usedCityCall(users.subscribers.stream()
                .filter(subscriber -> subscriber.getLongDistanceCall() > 0)
                .collect(Collectors.toList()));

        //Список абонентов в алфавитном порядке.
        users.sortUsers();

        users.menu();
    }

    private void menu() {
        System.out.println();
        System.out.println("Введите номер:" + '\n'
                + "1.Показать атрибуты абонентов" + '\n'
                + "2.Изменить атрибуты абонентов" + '\n'
                + "3.Показать данные всех абонентов" + '\n'
                + "4.Завершить программу");
        int menuId = in.nextInt();
        switch (menuId) {
            case 1:
                showAttribute();
                break;
            case 2:
                attributeChange();
                break;
            case 3:
                for (int i = 0; i < subscribers.size(); i++) {
                    System.out.println("id:" + i + " " + subscribers.get(i).toString());
                }
                menu();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Введите верный номер пункта");
                menu();
        }
    }

    private void showAttribute() {
        for (int i = 0; i < subscribers.size(); i++) {
            System.out.println("id:" + i + " " + subscribers.get(i).getFamily()
                    + " " + subscribers.get(i).getName()
                    + " " + subscribers.get(i).getSecondName());
        }
        System.out.println('\n' + "Введите id позователя");
        int userId = in.nextInt();
        if (userId > subscribers.size() - 1) {
            System.out.println("В базе только " + subscribers.size() + " пользователей");
            showAttribute();
        }
        System.out.println(subscribers.get(userId).toString());
        menu();
    }

    private void attributeChange() {
        int y;
        String x;
        for (int i = 0; i < subscribers.size(); i++) {
            System.out.println("id:" + i + " " + subscribers.get(i).getFamily()
                    + " " + subscribers.get(i).getName()
                    + " " + subscribers.get(i).getSecondName());
        }
        System.out.println("Введите id абонента, данные которого выхотите изменить:");
        int userId = in.nextInt();
        if (userId > subscribers.size() - 1) {
            System.out.println("В базе только " + subscribers.size() + " пользователей");
            attributeChange();
        }
        System.out.println("Что вы хотите изменить:" + '\n'
                + "1.Адрес" + '\n'
                + "2.Номер кредитной карты" + '\n'
                + "3.Дебет" + '\n'
                + "4.Кредит" + '\n'
                + "5.Время междугородних вызовов" + '\n'
                + "6.Время городских вызовов" + '\n');

        int idAttribute = in.nextInt();
        in.nextLine();
        switch (idAttribute) {
            case 1:
                System.out.println("Введите новый адрес:");
                x = in.nextLine();
                subscribers.get(userId).setAddress(x);
                break;
            case 2:
                System.out.println("Введите новый номер кредитной карты:");
                y = in.nextInt();
                subscribers.get(userId).setNumberCreditCard(y);
                break;
            case 3:
                System.out.println("Введите новое значение дебета:");
                y = in.nextInt();
                subscribers.get(userId).setDebit(y);
                break;
            case 4:
                System.out.println("Введите новое значение кредита:");
                y = in.nextInt();
                subscribers.get(userId).setCredit(y);
                break;
            case 5:
                System.out.println("Введите новое время междугородних вызовов:");
                y = in.nextInt();
                subscribers.get(userId).setLongDistanceCall(y);
                break;
            case 6:
                System.out.println("Введите новое время городских вызовов:");
                y = in.nextInt();
                subscribers.get(userId).setLongDistanceCall(y);
                break;
            case 7:
                menu();
                break;
            default:
                System.out.println("Неверное значение");
        }
        menu();
    }

    private void cityCallMore(List<Subscriber> list) {
        System.out.println('\n' + "У данных абонентов кол-во городских вызовов больше введенного значения:");
        for (Subscriber s : list) {
            System.out.println(s.getFamily() + " " + s.getName() + " " + s.getSecondName());
        }
    }

    private void usedCityCall(List<Subscriber> list) {
        System.out.println('\n' + "Данные абоненты пользовались межгородом:");
        for (Subscriber s : list) {
            System.out.println(s.getFamily() + " " + s.getName() + " " + s.getSecondName());
        }
    }

    private void sortUsers() {
        System.out.println('\n' + "Сортированный список абонентов:");
        List<Subscriber> sortList = new ArrayList<>(subscribers);
        sortList.sort(Comparator.comparing(Subscriber::getFamily).thenComparing(Subscriber::getName).thenComparing(Subscriber::getSecondName));
        for (Subscriber s : sortList) {
            System.out.println(s.getFamily() + " " + s.getName() + " " + s.getSecondName());
        }
    }
}
