package Zanyatie3.Task3;

public class Storehouse {

    private static final int MAX_VOLUME_OF_STOREHOUSE = 200;
    private int grossVolumeOfGoods = 0;
    private int grossWeightOfGoods = 0;

    public void printFreeVolumeInStorehouse() {
        System.out.println("Свободное место на складе - " + (MAX_VOLUME_OF_STOREHOUSE - grossVolumeOfGoods));
    }

    public void printGrossWeightOffGoodsInStorehouse() {
        System.out.println("Общий вес товаров на складе - " + grossWeightOfGoods);
    }

    public void addGoodsToStorehouse(Goods goods, int quantity) {
        int currentVolume = quantity * goods.getVolume();
        System.out.println("Добавили "+ goods.getName() + " на склад - " + quantity);
        int currentWeight = weightOfGoods(goods, quantity);
        grossVolumeOfGoods += currentVolume;
        grossWeightOfGoods += currentWeight;
    }

    private int weightOfGoods(Goods goods, int quantity) {
        int weight = goods.getWeight() * quantity;
        System.out.println("Вес " + goods.getName() + " - " + weight);
        return weight;
    }
}
