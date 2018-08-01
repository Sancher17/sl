package menus;

public class MenuExit extends Menu {


    MenuExit() {
        super("EXIT");
        finalizationProgram();
        getPrinter().println("Программа завершена !!!\nВсе данные сохранены в файлы ");
    }


    private void finalizationProgram(){
        getEBookShop().writeBookToFile();
        getEBookShop().writeOrderToFile();
        getEBookShop().writeRequestToFile();
    }

    @Override
    public void createMenu() {

    }

    @Override
    public void printMenu() {

    }
}
