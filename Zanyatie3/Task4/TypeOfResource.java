package Zanyatie3.Task4;

public abstract class TypeOfResource {

    private int id;
    private String name;
    private boolean state;

    public TypeOfResource(int id, String name, boolean state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }
}
