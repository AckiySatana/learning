package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {
    Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.println(question);
        return scanner.next();
    }

    public int ask(String question, int[] range) {
        boolean check = false;
        int key = Integer.valueOf(ask(question));
        for (int value : range
                ) {
            if (value == key) {
                check = true;
                break;
            }
        }
        if (!check) {
            throw new MenuOutException("Вне диопаона меню");
        }
        return key;
    }
}
