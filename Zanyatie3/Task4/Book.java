package Zanyatie3.Task4;

import java.util.Objects;

public class Book extends TypeOfResource {

	private int id;
	private String name;
	private boolean state;
	private TypeOfCover cover;

	public Book(int id, String name, boolean state) {
		super(id, name, state);
		this.id = id;
		this.name = name;
		this.state = state;
	}

    public TypeOfCover getCover() {
        return cover;
    }

    public void setCover(TypeOfCover cover) {
        this.cover = cover;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", cover=" + cover +
                '}';
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return id == book.id &&
				Objects.equals(name, book.name);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name);
	}
}