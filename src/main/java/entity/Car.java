package entity;

public class Car {

	private long id;
	private long ownerId;
	private String name;
	private String model;

	public Car() {

	}

	public Car(long id, String name, String model, int ownerId) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.ownerId = ownerId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
