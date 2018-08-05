package menus;

import util.Printer;

public class MenuExit extends Menu {


    MenuExit() {
        super("EXIT");
        finalizationProgram();
        Printer.println("Программа завершена !!!\nВсе данные сохранены в файлы ");
    }


    private void finalizationProgram(){
        getEBookShop().writeBookToFile();
        getEBookShop().writeOrderToFile();
        getEBookShop().writeRequestToFile();

        // TODO: 03.08.2018 закрыть поток Scanner
    }

    @Override
    public void createMenu() {

    }

    @Override
    public void printMenu() {

    }
}
