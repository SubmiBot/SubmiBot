package p2.submibot.resources;

public class Assignment {

	private String id;
	private String name;

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
