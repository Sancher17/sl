package Zanyatie3.Task3;

public class TestProgram {

    public static void main(String[] args) {

        Goods milk = new Milk();
        Goods soap = new Soap();

        Storehouse storehouse = new Storehouse();

        storehouse.addGoodsToStorehouse(milk, 31);
        storehouse.addGoodsToStorehouse(soap, 35);

        storehouse.printGrossWeightOffGoodsInStorehouse();
        storehouse.printFreeVolumeInStorehouse();

        System.out.println("\nЕще есть свободное место\nДобавим еще товар\n");

        storehouse.addGoodsToStorehouse(milk, 12);
        storehouse.printFreeVolumeInStorehouse();
        storehouse.printGrossWeightOffGoodsInStorehouse();
    }
}
