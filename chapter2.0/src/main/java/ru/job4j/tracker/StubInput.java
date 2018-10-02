package ru.job4j.tracker;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class StubInput implements Input {
    private final String[] value;
    private  int position;

    public StubInput(final String[] value){
        this.value=value;
    }
    public String ask(String question){
        return this.value[position++];
    }
    public int ask(String question,int[] range){
        boolean check = false;
        int key = Integer.valueOf(ask(question));
        for (int value : range
                ) {
            if (value == key) {
                check = true;
                break;
            }
        }
        if (check) {
            return key;
        } else {
            throw new MenuOutException("Вне диопаона меню");
        }
    }
}
