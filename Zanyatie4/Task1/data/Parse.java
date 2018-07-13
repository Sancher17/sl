
public abstract class Parse {

    public GregorianCalendar parseDate(String date) {
        String[] dates = date.split("\\.");
        int year = Integer.parseInt(dates[2]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[0]);
        return new GregorianCalendar(year, month, day);
    }

    public double parsePrice(String price) {
        return Double.parseDouble(price);
    }

    public boolean parseBoolean(String isAvailable) {
        return Boolean.parseBoolean(isAvailable);
    }
    
    public int checkNullRow(Object[] obj){
        int count = 0;
        
        if(obj instanseOf Book){
            for (Book book : obj) {
                if (book != null) {
                    count++;
                }
            }
            
        }else if(obj instanseOf Order){
        for (Order order : obj) {
                if (order != null) {
                    count++;
                }
            }
        }else if(obj instanseOf Request){
         for (Request request : obj) {
                if (request != null) {
                    count++;
                }
            }
        }
        return count;
    }
}