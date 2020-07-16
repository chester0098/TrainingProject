package com.fadineg.trainingproject.second.tasks.class_task_7;

import java.util.Scanner;

public class General {
    private Scanner in;
    private Orders orders;

    public General() {
        in = new Scanner(System.in);
        orders = new Orders();
    }

    public void whoAreYou() {
        System.out.println('\n' + "Кто вы?" + '\n'
                + "1.Продавец" + '\n'
                + "2.Покупатель" + '\n'
                + "3.Выйти из программы");
        int who = in.nextInt();
        switch (who) {
            case 1:
                sellerMenu();
                break;
            case 2:
                orders.makeOrders();
                whoAreYou();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Введите верное значение");
                whoAreYou();
                break;
        }
    }

    public void sellerMenu() {
        System.out.println('\n' + "1.Показать список всех заказов" + '\n'
                + "2.Добавить покупателя в черный список" + '\n'
                + "3.Показать все товары в продаже" + '\n'
                + "4.Добавить новый товар в продажу" + '\n'
                + "5.Вернуться в начальное меню");
        int idMenu = in.nextInt();
        switch (idMenu) {
            case 1:
                showOrders();
                sellerMenu();
                break;
            case 2:
                addToBlackList();
                sellerMenu();
                break;
            case 3:
                showProductList();
                sellerMenu();
                break;
            case 4:
                orders.addNewProduct();
                sellerMenu();
                break;
            case 5:
                whoAreYou();
                break;
            default:
                System.out.println("Введите верное значение");
                sellerMenu();
                break;
        }
    }

    public void showOrders() {
        int i = 0;
        for (Buyer b : orders.orders) {
            System.out.println('\n' + "id:" + i + " " + b.toString());
            i++;
        }
    }
    public void showProductList(){
        int i = 0;
        for (Product p : orders.productList) {
            System.out.println("id:" + i + " " + p.toString());
            i++;
        }
    }

    public void addToBlackList() {
        int i = 0;
        for (Buyer b : orders.orders) {
            System.out.println('\n' + "id:" + i + " " + b.toString());
            i++;
        }
        System.out.println('\n' + "Введите id пользователя, которого хотите добавить в черный список:");
        i = in.nextInt();
        if (i < 0 || i > orders.orders.size()) {
            System.out.println("Пользователя с таким id не существует");
            addToBlackList();
        }
        orders.orders.get(i).setBlackList(true);

        sellerMenu();
    }


}
