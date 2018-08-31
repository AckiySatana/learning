package ru.obj.tracker;

import java.util.Scanner;
import java.util.Scanner.*;
public class ConsoleInput implements Input{
    Scanner scanner = new Scanner(System.in);
    public String ask(String question) {
        System.out.println(question);
        return scanner.next();
    }
}
