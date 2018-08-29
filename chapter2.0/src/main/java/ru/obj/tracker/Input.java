package ru.obj.tracker;

import java.util.Scanner;
import java.util.Scanner.*;
public class Input {
    public String ask(String question){
        Scanner scanner= new Scanner(System.in);
        System.out.println(question);
        return scanner.next();
    }

}
