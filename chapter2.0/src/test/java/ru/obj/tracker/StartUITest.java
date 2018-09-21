package ru.obj.tracker;

import org.junit.Test;
import ru.job4j.tracker.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

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
        Input input = new StubInput(new String[]{"0", "test name", "desc", "3",tracker.findAll()[0].getId(), "6"});
        new StartUI(input,tracker).init();
        assertThat(tracker.findAll()[0].getName(),is("test name"));
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("delete", "desc of delete"));
        Input input = new StubInput(new String[]{"0", "test name", "desc", "0", "test name", "desc2", "6"});
        new StartUI(input,tracker).init();
        assertThat(tracker.findByName("test name").length,is(2));
    }
}