package pl.com.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

import javax.swing.event.ListSelectionEvent;

import pl.com.collectionAPI.Person;

public class AgeAverageUsingLambda {

	public static void main(String args[]){
		
		Person p1 = new Person("Alice", 34);
		Person p2 = new Person("Ben", 23);
		Person p3 = new Person("Richard", 47);
		Person p4 = new Person("Bob", 18);
		Person p5 = new Person("Max", 52);
		Person p6 = new Person("David", 28);
		
		List<Person> people = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6));
		double average = people.stream()
				.mapToDouble(p -> p.getAge())
				.filter(age -> age > 20)
				.average().getAsDouble();
		
//		people.stream()
//				.map(person -> person.getAge())
//				.filter(age -> age > 20)
//				.mapToInt(i -> i)
//				.average();
		
		people.stream()
		.mapToInt(person -> person.getAge())
		.filter(age -> age > 20)
		.average(); 
		
		
		System.out.println("average: "+average);
		
	}

}
			
			
			
						
