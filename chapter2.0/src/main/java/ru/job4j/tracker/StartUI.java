package ru.job4j.tracker;

public class StartUI {

    private static final String ADD = "0";
    private static final String SHOWALL = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDBYID = "4";
    private static final String FINDBYNAME = "5";
    private static final String EXIT = "6";

    public final Tracker tracker;

    private final Input input;

    /**
     * Еонструктор класса пользовательского интерфейса
     *
     * @param input
     * @param tracker
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void showMenu() {
        System.out.println("Меню:");
        System.out.println("Для добавления новой записи нажмите 0");
        System.out.println("Для просмотра всех записей нажмите 1");
        System.out.println("Для редактирования записи нажмите 2");
        System.out.println("Для удаления записи нажмите 3");
        System.out.println("Для поиска записи по id нажмите 4");
        System.out.println("Для поиска записи по наимеованию нажмите 5");
        System.out.println("Для выхода нажмите 6");
    }

    private void createItem() {
        System.out.println("_________________Добавление новой заявки________________");
        String name = this.input.ask("Ввкдите наименование заявки");
        String desc = this.input.ask("Ввкдите описание заявки");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("id зарегистрированной заявки - " + item.getId());
    }

    public void showAll() {
        Item[] show = this.tracker.findAll();
        if (show.length > 0) {
            System.out.println("Найдено " + show.length + " записей:");
            for (Item item : show) {
                System.out.println("Наименование заявки - " + item.getName() + ", id заявки - " + item.getId());
            }
        } else {
            System.out.println("Нет записей для отображения");
        }
    }

    public void edit() {
        System.out.println("Сейчас будет замена записи");
        String id = this.input.ask("Введите для id для замены");
        String name = this.input.ask("Введите новое имя запими");
        String desc = this.input.ask("Введите новое описание записи");
        if (this.tracker.replace(id, new Item(name, desc))) {
            System.out.println("Запись заменена");
        } else {
            System.out.println("облом при попытке замены");
        }
    }

    public void delete() {
        String id = this.input.ask("Введите id Записи для удаления");
        if (this.tracker.delete(id)) {
            System.out.println("запись успешно удалена");
        } else {
            System.out.println("удалить не смогла! А жаль");
        }
    }

    public void findById() {
        String id = this.input.ask("Id записи, пожалуйста");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println("Найдена запись с наименованием" + item.getName());
        } else {
            System.out.println("Запись не найдена");
        }
    }

    public void findByName() {
        String name = this.input.ask("Id наименование записи, пожалуйста");
        Item[] item = this.tracker.findByName(name);
        if (item.length > 0) {
            System.out.println("Найдено " + item.length + " записей с наименованием " + name);
        } else {
            System.out.println("Запись не найдена");
        }
    }

    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню:");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (this.SHOWALL.equals(answer)) {
                this.showAll();
            } else if (this.EDIT.equals(answer)) {
                this.edit();
            } else if (this.DELETE.equals(answer)) {
                this.delete();
            } else if (this.FINDBYID.equals(answer)) {
                this.findById();
            } else if (this.FINDBYNAME.equals(answer)) {
                this.findByName();
            } else if (this.EXIT.equals(answer)) {
                exit = true;
            } else {
                System.out.println("Ошибка ввода данных");
            }
        }
    }

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
