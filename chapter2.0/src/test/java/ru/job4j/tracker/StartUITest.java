package ru.job4j.tracker;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Класс тестиования работы трекера с эмуляцией пользовательского
 * PrintStream - поле содержит дефолтный вывод в консоль
 * ByteArrayOutputStream - буфер для результата
 * loadOutput()     - метод, выполняемый до тестового кейса, переводит вывод исформации в буфкр
 * backOutput()     - метод, выполняемый после тестового кейса, переводит вывод исформации в кончоль
 * addItemTest()    - метод проверки записи в трекер
 * whenGetCount()   - метод проверки возврата массива записей трекера
 * whenUpdateThenSameName() - метод проверки обновления записи трекера
 * whenDeleteThanGood()     - метож проверки корректного удаления записи по id
 * whenFindByName()         - метод проверки поиска записей по имени
 */
public class StartUITest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    String sep = System.lineSeparator();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void addItemTest() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenGetCount() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "0", "test name2", "desc2", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(2));
    }

    @Test
    public void whenUpdateThenSameName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("first", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "second", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("second"));
    }

    @Test
    public void whenDeleteThanGood() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("delete", "desc of delete"));
        Input input = new StubInput(new String[]{"0", "test name", "desc", "3", tracker.findAll()[0].getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("delete", "desc of delete"));
        Input input = new StubInput(new String[]{"0", "test name", "desc", "0", "test name", "desc2", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("test name").length, is(2));
    }

    /**
     * Тесты через вывод в консоль
     */
    @Test

    public void whenCheckConsoleOutput() {

        Input input = new StubInput(new String[]{"6"});
        new StartUI(input, new Tracker()).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                // .append(System.lineSeparator())
                .append("Меню:").append(sep)
                .append("Для добавления новой записи нажмите 0").append(sep)
                .append("Для просмотра всех записей нажмите 1").append(sep)
                .append("Для редактирования записи нажмите 2").append(sep)
                .append("Для удаления записи нажмите 3").append(sep)
                .append("Для поиска записи по id нажмите 4").append(sep)
                .append("Для поиска записи по наимеованию нажмите 5").append(sep)
                .append("Для выхода нажмите 6")
                .append(System.lineSeparator())
                .toString()));
    }

    @Test
    public void whenCheckConsoleOutputAddObject() {
        Input input = new StubInput(new String[]{"0", "name4", "desc4", "6"});
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("Меню:").append(sep)
                .append("Для добавления новой записи нажмите 0").append(sep)
                .append("Для просмотра всех записей нажмите 1").append(sep)
                .append("Для редактирования записи нажмите 2").append(sep)
                .append("Для удаления записи нажмите 3").append(sep)
                .append("Для поиска записи по id нажмите 4").append(sep)
                .append("Для поиска записи по наимеованию нажмите 5").append(sep)
                .append("Для выхода нажмите 6")
                .append(sep)
                .append("_________________Добавление новой заявки________________").append(sep)
                .append("id зарегистрированной заявки - ")
                .append(tracker.findAll()[0].getId()).append(sep)
                .append("Меню:").append(sep)
                .append("Для добавления новой записи нажмите 0").append(sep)
                .append("Для просмотра всех записей нажмите 1").append(sep)
                .append("Для редактирования записи нажмите 2").append(sep)
                .append("Для удаления записи нажмите 3").append(sep)
                .append("Для поиска записи по id нажмите 4").append(sep)
                .append("Для поиска записи по наимеованию нажмите 5").append(sep)
                .append("Для выхода нажмите 6")
                .append(sep)
                .toString()));
    }
}