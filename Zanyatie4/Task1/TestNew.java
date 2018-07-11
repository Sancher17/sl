package Zanyatie4.Task1;

public class TestNew {

    private Facade facade = new Facade();    

    public static void main(String[] args){
    
        facade.printCompletedOrdersNew();
        facade.printCompletedOrdersSortedByDateOfPeriod(GregorianCalendar startDate, GregorianCalendar endDate);
        facade.printCompletedOrdersSortedByPriceOfPeriod(GregorianCalendar startDate, GregorianCalendar endDate)
        
        
        facade.printBooks();
        facade.addOrder(int bookId);
    
    
    }
}