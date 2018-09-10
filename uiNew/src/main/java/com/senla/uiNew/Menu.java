package com.senla.uiNew;

import com.senla.util.Printer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Menu {

    private final String name;

    private LinkedHashMap<String, Runnable> actionsMap = new LinkedHashMap<>();

    Menu(String name) {
        this.name = name;
    }

    void action(String name, Runnable action) {
        actionsMap.put(name, action);
    }

    String generateText() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":\n");

        List<String> actionNames = new ArrayList<>(actionsMap.keySet());
        int count = 0;
        for (String actionName : actionNames) {
            sb.append(String.format("  %d: %s%n", count++, actionName));
        }
        return sb.toString();
    }

    void executeAction(int actionNumber) {
        if (actionNumber >= 0 & actionNumber <= actionsMap.size()) {
            List<Runnable> actions = new ArrayList<>(actionsMap.values());
            actions.get(actionNumber).run();
        } else {
            Printer.println("Нет меню с номером: " + actionNumber);
            Printer.println("Выбирите другое существующее меню");
        }
    }


}
