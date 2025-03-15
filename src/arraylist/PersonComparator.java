package arraylist;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
	private String sortBy;

	public PersonComparator() {
		super();
		this.sortBy = "id";
	}

	public PersonComparator(String sortBy) {
		super();
		this.sortBy = sortBy;
	}

	@Override
	public int compare(Person p1, Person p2) {
		switch (sortBy) {
		
		case "name": {
			return p1.getName().compareTo(p2.getName());
		}
		case "gender": {
			return p1.getGender().compareTo(p2.getGender());
		}
		case "age": {
			if (p1.getAge() == p2.getAge()) return 0;
			if (p1.getAge() > p2.getAge()) 
				return 1;
			else {
				return -1;
			}
		}
		default:
			if (p1.getId() == p2.getId()) return 0;
			if (p1.getId() > p2.getId()) return 1;
			
			return -1;
		}
	}

}
