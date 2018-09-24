package ru.job4j.shape;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;

/**
 * Класс тестирования реализации интерфейса Shape в классах Square и Triangle
 */
public class PaintTest {
    /**
     * sep - переменная, разделитель. Используется во для сокращения записи во всез методах класса
     * PrintStream содержит дефолтный вывод в консоль
     * ByteArrayOutputStream буфер для результата
     */
    private String sep = System.lineSeparator();
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * метод, выполняемый перед телом метода проверки - меняет дефолтный вывод на буфер
     */
    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    /**
     * метод, выполняемый после тела метода проверки - меняет дефолтный вывод на консоль
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    /**
     * метод проверки формирования строки, содержащей форму квадрата
     */
    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        assertThat(
                square.draw(),
                is(
                        new StringBuilder()
                                .append("*********" + sep)
                                .append("*       *" + sep)
                                .append("*       *" + sep)
                                .append("*       *" + sep)
                                .append("*********")
                                .toString()
                )
        );
    }

    /**
     * метод проверки формирования строки, содержащей форму треугольника
     */
    @Test
    public void whenDrawTrinangle() {
        Triangle tri = new Triangle();
        assertThat(
                tri.draw(),
                is(
                        new StringBuilder()
                                .append("   *   " + sep)
                                .append("  ***  " + sep)
                                .append(" ***** " + sep)
                                .append("*******")
                                .toString()
                )
        );
    }

    /**
     * метод проверки формирования строки, содержащей форму квадрата с выводом в буфер
     */
    @Test
    public void whenDrawSquareTwo() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("*********" + sep)
                                .append("*       *" + sep)
                                .append("*       *" + sep)
                                .append("*       *" + sep)
                                .append("*********" + sep)
                                .toString()
                )
        );
        System.setOut(stdout);
    }

    /**
     * метод проверки формирования строки, содержащей форму треугольника с выводом в буфер
     */
    @Test
    public void whenDrawTriangleTwo() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("   *   " + sep)
                                .append("  ***  " + sep)
                                .append(" ***** " + sep)
                                .append("*******" + sep)
                                .toString()
                )
        );
        System.setOut(stdout);
    }
}