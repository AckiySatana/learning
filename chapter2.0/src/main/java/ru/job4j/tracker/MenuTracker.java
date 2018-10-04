package ru.job4j.tracker;

/**
 * Метод range возвращает массив количкства элементлв
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];
    private int position = 0;

    public int[] getRange() {
        int[] range = new int[this.actions.length];
        for (int i = 0; i < range.length; i++) {
            range[i] = i;
        }
        return range;
    }

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions(StartUI ui) {
        // скорее всего тут будет запуск меню
        this.actions[position++] = new AddItem(0, "Добавьте новую запись");
        this.actions[position++] = new showAll(1, "Просмотр всех записей");
        this.actions[position++] = new edit(2, "Редактирование записи");
        this.actions[position++] = new delete(3, "Удаление записи");
        this.actions[position++] = new findById(4, "Поиск записи по id");
        this.actions[position++] = new findByName(5, "Поиск записи по имени");
        this.actions[position++] = new exit(6, "Выход из программы",ui);
    }

    private class AddItem extends BaseAction {

        AddItem(int key, String info) {
            super(key, info);
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Ввкдите наименование заявки");
            String desc = input.ask("Ввкдите описание заявки");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("id зарегистрированной заявки - " + item.getId());
        }
    }

    private class showAll extends BaseAction {
        showAll(int key, String info) {
            super(key, info);
        }

        public void execute(Input input, Tracker tracker) {
            Item[] show = tracker.findAll();
            if (show.length > 0) {
                System.out.println("Найдено " + show.length + " записей:");
                for (Item item : show) {
                    System.out.println("Наименование заявки - " + item.getName() + ", id заявки - " + item.getId());
                }
            } else {
                System.out.println("Нет записей для отображения");
            }
        }
    }

    private class edit extends BaseAction {
        edit(int key, String info) {
            super(key, info);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("Сейчас будет замена записи");
            String id = input.ask("Введите для id для замены");
            String name = input.ask("Введите новое имя запими");
            String desc = input.ask("Введите новое описание записи");
            if (tracker.replace(id, new Item(name, desc))) {
                System.out.println("Запись заменена");
            } else {
                System.out.println("Ошибка при попытке замены");
            }
        }
    }

    private class delete extends BaseAction {
        delete(int key, String info) {
            super(key, info);
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id Записи для удаления");
            if (tracker.delete(id)) {
                System.out.println("запись успешно удалена");
            } else {
                System.out.println("удалить не смогла! А жаль");
            }
        }
    }

    private class findById extends BaseAction {
        findById(int key, String info) {
            super(key, info);
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Id записи, пожалуйста");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("Найдена запись с наименованием" + item.getName());
            } else {
                System.out.println("Запись не найдена");
            }
        }
    }

    private class findByName extends BaseAction {
        findByName(int key, String info) {
            super(key, info);
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Id наименование записи, пожалуйста");
            Item[] item = tracker.findByName(name);
            if (item.length > 0) {
                System.out.println("Найдено " + item.length + " записей с наименованием " + name);
            } else {
                System.out.println("Запись не найдена");
            }
        }
    }

    private class exit extends BaseAction {
        private final StartUI ui;
        exit(int key,String info,StartUI ui) {
            super(key,info);
            this.ui = ui;
        }
        /**
         * процедура прерывания работы программы
         */
        public void execute(Input input, Tracker tracker) {
            this.ui.notWorking();
            System.out.println("Хорошего дня");
        }
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

}
