package ru.obj.shape;

import org.junit.Test;
import ru.job4j.shape.Paint;
import ru.job4j.shape.Square;
import ru.job4j.shape.Triangle;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PaintTest {
    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        assertThat(
                square.draw(),
                is(
                        new StringBuilder()
                                .append("*********\n")
                                .append("*       *\n")
                                .append("*       *\n")
                                .append("*       *\n")
                                .append("*********")
                                .toString()
                )
        );
    }

    public void whenDrawTrinangle() {
        Triangle tri = new Triangle();
        assertThat(
                tri.draw(),
                is(
                        new StringBuilder()
                                .append("   *   \n")
                                .append("  ***  \n")
                                .append(" ***** \n")
                                .append("*******")
                                .toString()
                )
        );
    }
        @Test
        public void whenDrawSquareTwo() {
            // получаем ссылку на стандартный вывод в консоль.
            PrintStream stdout = System.out;
            // Создаем буфур для хранения вывода.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //Заменяем стандартный вывод на вывод в пямять для тестирования.
            System.setOut(new PrintStream(out));
            // выполняем действия пишушиее в консоль.
            new Paint().draw(new Square());
            // проверяем результат вычисления
            assertThat(
                    new String(out.toByteArray()),
                    is(
                            new StringBuilder()
                                    .append("*********\n")
                                    .append("*       *\n")
                                    .append("*       *\n")
                                    .append("*       *\n")
                                    .append("*********\r\n")
                                    .toString()
                    )
            );
            // возвращаем обратно стандартный вывод в консоль.
            System.setOut(stdout);
        }

    @Test
    public void whenDrawTriangleTwo() {
        // получаем ссылку на стандартный вывод в консоль.
        PrintStream stdout = System.out;
        // Создаем буфер для хранения вывода.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //Заменяем стандартный вывод на вывод в пямять для тестирования.
        System.setOut(new PrintStream(out));
        // выполняем действия пишушиее в консоль.
        new Paint().draw(new Triangle());
        // проверяем результат вычисления
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("   *   \n")
                                .append("  ***  \n")
                                .append(" ***** \n")
                                .append("*******\r\n")
                                .toString()
                )
        );
        // возвращаем обратно стандартный вывод в консоль.
        System.setOut(stdout);
    }
}