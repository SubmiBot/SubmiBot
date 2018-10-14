package p2.submibot.resources;

public class Assignment {

	private String id;
	private String name;
	private Boolean is_quiz_assignment;
	private Boolean locked_for_user;
	
	public String getId() {
		return this.id;
	}
	
	public Boolean isAvailable() {
		return !(this.locked_for_user || this.is_quiz_assignment);
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
