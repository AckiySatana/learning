package ru.job4j.tracker;

/**
 * Метод range возвращает массив количкства элементлв
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];

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
        this.actions[0] = new AddItem();
        this.actions[1] = new showAll();
        this.actions[2] = new edit();
        this.actions[3] = new delete();
        this.actions[4] = new findById();
        this.actions[5] = new findByName();
        this.actions[6] = new exit(ui);
    }

    private class AddItem implements UserAction {
        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Ввкдите наименование заявки");
            String desc = input.ask("Ввкдите описание заявки");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("id зарегистрированной заявки - " + item.getId());
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Добавьте новую запись");
        }
    }

    private class showAll implements UserAction {
        public int key() {
            return 1;
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

        public String info() {
            return String.format("%s. %s", this.key(), "Просмотр всех записей");
        }
    }

    private class edit implements UserAction {
        public int key() {
            return 2;
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

        public String info() {
            return String.format("%s. %s", this.key(), "Редактирование записи");
        }
    }

    private class delete implements UserAction {
        public int key() {
            return 3;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id Записи для удаления");
            if (tracker.delete(id)) {
                System.out.println("запись успешно удалена");
            } else {
                System.out.println("удалить не смогла! А жаль");
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Удаление записи");
        }
    }

    private class findById implements UserAction {
        public int key() {
            return 4;
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

        public String info() {
            return String.format("%s. %s", this.key(), "Поиск записи по id");
        }
    }

    private class findByName implements UserAction {
        public int key() {
            return 5;
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

        public String info() {
            return String.format("%s. %s", this.key(), "Поиск записи по имени");
        }
    }

    private class exit implements UserAction {
        private final  StartUI ui;

        exit(StartUI ui) {
            this.ui = ui;
        }

        public int key() {
            return 6;
        }

        public void execute(Input input, Tracker tracker) {
            this.ui.notWorking();
            System.out.println("Хорошего дня");
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Выход из программы");
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
