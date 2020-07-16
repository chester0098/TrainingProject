package com.fadineg.trainingproject.second.tasks;

import android.graphics.Point;

public interface PracticalTask {
    /*
      I
      Написать простое лямбда-выражение в переменной myClosure,
      лямбда-выражение должно выводить в консоль фразу "I love Java". Вызвать это лямбда-выражение.
      Далее написать функцию, которая будет запускать заданное лямбда-выражение заданное количество раз.
      Объявить функцию так: public void repeatTask (int times, Runnable task).
      Функция должна запускать times раз лямбда-выражение task .
      Используйте эту функцию для печати "I love Java" 10 раз.
     */
    public class Main {

        public static void main(String[] args) {
            Runnable myClosure = () -> {
                System.out.println("I love Java");
            };
            Main main = new Main();
            main.repeatTask(10, myClosure);
        }

        public void repeatTask(int times, Runnable task) {
            for (int i = 0; i < times; i++) {
                task.run();
            }
        }

    }

    /*
      II
      Условия: есть начальная позиция на двумерной плоскости,
      можно осуществлять последовательность шагов по четырем направлениям up, down, left, right.
      Размерность каждого шага равна 1.
      Задание:
      1.Создать enum Directions с возможными направлениями движения

      2.Создать метод, принимающий координаты и одно из направлений и возвращающий координату
      после перехода по направлению

      3.Создать метод, осуществляющий несколько переходов от первоначальной координаты и
      выводящий координату после каждого перехода. Для этого внутри метода следует определить
      переменную location с начальными координатами (0,0) и массив направлений,
      содержащий элементы [up, up, left, down, left, down, down, right, right, down, right], и програмно
      вычислить какие будут координаты у переменной location после выполнения этой последовательности шагов.
      Для вычисленеия результата каждого перемещения следует использовать созданный ранее метод перемещения
      на один шаг.
     */

    public enum Directions {
        up,
        down,
        left,
        right;

        public static void main(String[] args) {

            Point location = new Point();

            Directions arg = down;
            arg.shift(location, arg);
            System.out.println(location.x + "," + location.y);

            location.x = 0;
            location.y = 0;

            Directions[] list = {up, up, left, down, left, down, down, right, right, down, right};
            for (Directions d : list) {
                arg.shift(location, d);
            }
            System.out.println(location.x + "," + location.y);
        }

        public Point shift(Point p, Directions arg) {
            switch (arg) {
                case up:
                    p.y += 1;
                    break;
                case down:
                    p.y -= 1;
                    break;
                case left:
                    p.x -= 1;
                    break;
                case right:
                    p.x += 1;
                    break;
            }
            return p;
        }
    }


    /*
      III
      Создать интерфейс Shape с двумя методами perimeter и area,
      выводящими периметр и площадь фигуры соответственно,
      после чего реализовать и использовать для вывода периметра и площади следующие классы,
      реализующие интерфейс Shape:
      1.Rectangle - прямоугольник с двумя свойствами: ширина и длина
      2.Square - квадрат с одним свойством: длина стороны
      3.Circle - круг с одним свойством: диаметр круг
     */

    interface Shape {
        public float area();

        public float perimeter();
    }

    public class Figures {
        public static void main(String[] args) {
            Rectangle rectangle = new Rectangle(5, 4);
            System.out.println(rectangle);

            Square square = new Square(8);
            System.out.println(square);

            Circle circle = new Circle(10);
            System.out.println(circle);
        }
    }

    class Rectangle implements Shape {
        private float a, b;

        public Rectangle(float a, float b) {
            this.a = a;
            this.b = b;
        }

        public float area() {
            return a * b;
        }

        public float perimeter() {
            return (a + b) * 2;
        }

        @Override
        public String toString() {
            return "Прямоугольник" + " периметр: " + perimeter() + " площадь: " + area();
        }

    }

    class Square implements Shape {
        private float a;

        public Square(float a) {
            this.a = a;
        }

        public float area() {
            return a * a;
        }

        public float perimeter() {
            return 4 * a;
        }

        @Override
        public String toString() {
            return "Квадрат" + " периметр: " + perimeter() + " площадь: " + area();
        }

    }


    class Circle implements Shape {

        private float r;

        public Circle(float r) {
            this.r = r;
        }

        public float area() {
            return (float) (Math.PI * r * r);
        }

        public float perimeter() {
            return (float) (2 * Math.PI * r);
        }

        @Override
        public String toString() {
            return "Круг " + " периметр: " + perimeter() + " площадь: " + area();
        }

    }
}
