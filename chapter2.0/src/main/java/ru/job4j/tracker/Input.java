package ru.job4j.tracker;

public interface Input {
    public String ask(String question);
    public int ask(String question,int[] range);
}
