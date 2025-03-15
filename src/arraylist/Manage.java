package arraylist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Manage {

	public static void main(String[] args) throws IOException {
		List<Person> personList = new ArrayList<Person>();
		FileWriter writer = new FileWriter(new File("person.txt"));
		
		Person p1 = new Person(1, "Hai", 23, "nam");
		Person p2 = new Person(7, "Thu", 43, "nu");
		Person p3 = new Person(4, "Duyen", 19, "nu");
		Person p4 = new Person(2, "Thang", 28, "nam");
		Person p5 = new Person(5, "Dung", 31, "nam");
		
		personList.add(p1);
		personList.add(p2);
		personList.add(p3);
		personList.add(p4);
		personList.add(p5);
		
		personList.sort(new PersonComparator());
		System.out.println(personList);
		writer.write("*Sort by Person ID*\n" + personList.toString() + "\n");
		
		personList.sort(new PersonComparator("name"));
		System.out.println(personList);
		writer.write("*Sort by Person Name*\n" + personList.toString() + "\n");
		
		personList.sort(new PersonComparator("age"));
		System.out.println(personList);
		writer.write("*Sort by Person Age*\n" + personList.toString() + "\n");
		
		personList.sort(new PersonComparator("gender"));
		System.out.println(personList);
		writer.write("*Sort by Person Gender*\n" + personList.toString());
		
		writer.close();
	}

}
