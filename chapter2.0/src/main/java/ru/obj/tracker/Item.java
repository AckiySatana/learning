package ru.obj.tracker;

import java.util.Date;

public class Item {
    /**
     * Объявляем переменнве:
     * id   - идентификатор
     * name - наименование
     * desc - описание заявки
     * comments - массив комментариев
     */
    private String id,name,desc;
    private long created;
    String[] comments;

    /**
     * метод создания заявки
     */
    Item(String name,String desc,long create) {
        this.name=name;
        this.desc=desc;
        this.created=create;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getId(){
        return this.id;
    }

    public String getName() {
        if (this.getId() != null) {
            return this.name;
        }
        return null;
    }

}
