package ru.job4j.shape;

/**
 * Класс отображения формы квадрата, реализующий интерфейс Shape
 */
public class Square implements Shape {
    /**
     * Метод возвращает стоку, составляющую квадрат
     *
     * @return
     */
    public String draw() {
        return new StringBuilder()
                .append("*********" + System.lineSeparator())
                .append("*       *" + System.lineSeparator())
                .append("*       *" + System.lineSeparator())
                .append("*       *" + System.lineSeparator())
                .append("*********").toString();
    }
}
