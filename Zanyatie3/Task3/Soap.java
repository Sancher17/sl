package Zanyatie3.Task3;

public class Soap extends Goods {

    private int quantity;

    public Soap() {
        super(1, 2, "Soap");
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
