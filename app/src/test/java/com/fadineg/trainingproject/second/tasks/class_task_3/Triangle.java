package com.fadineg.trainingproject.second.tasks.class_task_3;

import android.graphics.Point;

import java.text.DecimalFormat;
import java.util.Scanner;

/*
      III
      Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов,
      вычисления площади, периметра и точки пересечения медиан.
      Описать свойства для получения состояния объекта.
     */

public class Triangle {
    private Point xy1;
    private Point xy2;
    private Point xy3;
    private DecimalFormat df;

    private Triangle(Point xy1, Point xy2, Point xy3) {
        this.xy1 = xy1;
        this.xy2 = xy2;
        this.xy3 = xy3;
        DecimalFormat df = new DecimalFormat("#.#");
    }

    public static void main(String[] args) {
        Point xy1 = new Point();
        Point xy2 = new Point();
        Point xy3 = new Point();
        Scanner in = new Scanner(System.in);
        System.out.println("Введите x1, y1");
        xy1.x = in.nextInt();
        xy1.y = in.nextInt();

        System.out.println("Введите x2, y2");
        xy2.x = in.nextInt();
        xy2.y = in.nextInt();

        System.out.println("Введите x3, y3");
        xy3.x = in.nextInt();
        xy3.y = in.nextInt();

        Triangle triangle = new Triangle(xy1,xy2,xy3);
        triangle.side();
        triangle.median();
    }


    private void side() {
        double a = Math.sqrt((xy1.x - xy2.x) * (xy1.x - xy2.x) + (xy1.y - xy2.y) * (xy1.y - xy2.y));
        double b = Math.sqrt((xy1.x - xy3.x) * (xy1.x - xy3.x) + (xy1.y - xy3.y) * (xy1.y - xy3.y));
        double c = Math.sqrt((xy2.x - xy3.x) * (xy2.x - xy3.x) + (xy2.y - xy3.y) * (xy2.y - xy3.y));
        if (a + b <= c || a + c <= b || b + c <= a) {
            System.out.println("Треугольник не существует");
            System.exit(0);
        }
        else {
            System.out.println("Стороны трегульника: a = " + df.format(a) + " ; b = " + df.format(b) + " ; c = " + df.format(c));
            perimeter(a, b, c);
            area(a, b, c);
        }
    }

    private void perimeter(double a, double b, double c) {
        double perimeter = a + b + c;
        System.out.println("Периметр: " + df.format(perimeter));
    }

    private void area(double a, double b, double c) {
        double p = (a + b + c) / 2.0;
        double square = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        System.out.println("Площадь: " + df.format(square));
    }

    private void median() {
        double m1 = (xy1.x + xy2.x + xy3.x) / 3.0;
        double m2 = (xy1.y + xy2.y + xy3.y) / 3.0;
        System.out.println("Точка пересечения: " + df.format(m1) + " ; " + df.format(m2));
    }

}
