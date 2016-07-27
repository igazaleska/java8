package pl.com.collectoradvance;

import java.util.Objects;

public class Actor {
	
	private String firstName;
	private String lastName;
	
	public Actor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}


	
	@Override
	public String toString() {
		return "Actor[ name=" + firstName + " "+lastName+"]";
	}



	@Override
	public int hashCode() {
		int hash = 7;
		hash = hash * 67 +Objects.hashCode(this.lastName);
		hash = hash * 67 + Objects.hashCode(this.firstName);
		return hash;
	}



	@Override
	public boolean equals(Object obj) {
		
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Actor other = (Actor) obj;
		if(!Objects.equals(this.lastName, other.lastName)){
			return false;
		}
		return Objects.equals(this.firstName, other.firstName);
	}
}
