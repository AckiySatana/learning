package ru.job4j.tracker;

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
}