package ru.job4j.shape;

/**
 * Класс Triangle (Треугольник) реализует интерфейс Shape (форма)
 */
public class Triangle implements Shape {
    /**
     * Метод draw() возвращает строку, рисующую треугольник
     *
     * @return
     */
    @Override
    public String draw() {
        return new StringBuilder()
                .append("   *   " + System.lineSeparator())
                .append("  ***  " + System.lineSeparator())
                .append(" ***** " + System.lineSeparator())
                .append("*******")
                .toString();
    }
}
