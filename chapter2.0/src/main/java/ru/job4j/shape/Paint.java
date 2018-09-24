package ru.job4j.shape;

/**
 * Класс Paint используется для рисования форм использую интерфейс shape
 */
public class Paint {
    /**
     * Метод выводит на печать форму определенную входящим параметром
     *
     * @param shape - интерфейс, входящий параметр
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }
}
