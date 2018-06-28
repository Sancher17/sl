package Zanyatie3.Task3;

public abstract class Goods {

  private final int weight;
  private final int volume;
  private final String name;
  private int quantity;

    public Goods(int weight, int volume, String name) {
        this.weight = weight;
        this.volume = volume;
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public int getVolume() {
        return volume;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
