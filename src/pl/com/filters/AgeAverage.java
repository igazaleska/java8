package pl.com.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.com.collectionAPI.Person;

public class AgeAverage {

	public static void main(String[] args){
		Person p1 = new Person("Alice", 34);
		Person p2 = new Person("Ben", 23);
		Person p3 = new Person("Richard", 47);
		Person p4 = new Person("Bob", 18);
		Person p5 = new Person("Max", 52);
		Person p6 = new Person("David", 28);
		
		List<Person> people = new ArrayList(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
		int sum = 0;
		int count = 0;
		for (Person p : people){
			if (p.getAge() > 20){
				sum += p.getAge();
				count++;
			}
		}
		int average = 0;
		if (count > 0){
			average = sum/count;
		}
	
		System.out.println("The average of the age above 20 is: "+average);
	}
}
