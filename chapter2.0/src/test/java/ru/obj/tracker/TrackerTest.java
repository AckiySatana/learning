package ru.obj.tracker;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class TrackerTest {

    @org.junit.Test
    public void add() {
    }

    @org.junit.Test
    public void whenReplaceNameThenReturnNewName() {
       Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    public void whenAddNewItemThenTrackerHasSameOne() {
        Tracker treack = new Tracker();
        Item item = new Item("test1", "testDescr", 123L);
        treack.add(item);
        assertThat(treack.getItem(0), is(item));
    }

    public void whenDelete() {
        Tracker treack = new Tracker();
        Item item = new Item("test1", "testDescr", 123L);
        treack.add(item);
        Item item1 = new Item("test1", "testDescr", 123L);
        treack.add(item1);
        Item item2 = new Item("test2", "testDescr", 123L);
        treack.add(item2);
        treack.delete(item1.getId());
        assertThat(treack.findAll().length, is(2));
    }

    @Test
    public void whenGetCount(){
        Tracker tracker = new Tracker();
        Item item1 = new Item("test2", "testDescription", 123L);
        tracker.add(item1);
        Item item2 = new Item("test2", "testDescription", 123L);
        tracker.add(item2);
        Item item3 = new Item("test3", "testDescription", 123L);
        tracker.add(item3);
        assertThat(tracker.findAll().length, is(3));
    }

    @Test

    public void whenFindName() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test2", "testDescription", 123L);
        tracker.add(item1);
        System.out.println(item1.getName());
        Item item2 = new Item("test2", "testDescription", 123L);
        tracker.add(item2);
        Item item3 = new Item("test3", "testDescription", 123L);
        tracker.add(item3);
        Item[] it = tracker.findByName("test2");
        assertThat(it.length, is(2));
        assertThat(it[0].getId(),is(item1.getId()));
        assertThat(it[1].getId(),is(item2.getId()));
    }

    @Test
    public void whenFindById() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test2", "testDescription", 123L);
        tracker.add(item1);
        System.out.println(item1.getName());
        Item item2 = new Item("test2", "testDescription", 123L);
        tracker.add(item2);
        Item item3 = new Item("test3", "testDescription", 123L);
        tracker.add(item3);
        assertThat(tracker.findById(item1.getId()),is(item1));
    }

}