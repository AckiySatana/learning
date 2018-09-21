package ru.job4j.tracker;
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
    public Item(String name,String desc) {
        this.name=name;
        this.desc=desc;
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
