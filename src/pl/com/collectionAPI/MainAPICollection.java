package pl.com.collectionAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

public class MainAPICollection {

	public static void main(String[] args) {
		
		Person p1 = new Person("Alice", 34);
		Person p2 = new Person("Ben", 23);
		Person p3 = new Person("Richard", 47);
		Person p4 = new Person("Bob", 18);
		Person p5 = new Person("Max", 52);
		Person p6 = new Person("David", 28);
		
//		List<Person> people = new ArrayList(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
//		people.removeIf(person -> person.getAge() < 30);
//		people.replaceAll(person -> new Person(person.getName().toUpperCase(), person.getAge()));
//		people.sort(Comparator.comparing(Person::getAge).reversed());
//		
//		people.forEach(System.out::println);
		
		City newyork = new City("New York");
		City shanghai = new City("Shanghai");
		City paris = new City("Paris");
		
		Map<City, List<Person>> map1 = new HashMap<>();
		//map.putIfAbsent(paris, new ArrayList<>());
		//map.get(paris).add(p1);
		map1.computeIfAbsent(paris, city -> new ArrayList<>()).add(p1);
		map1.computeIfAbsent(newyork, city -> new ArrayList<>()).add(p2);
		map1.computeIfAbsent(newyork, city -> new ArrayList<>()).add(p3);
		
		Map<City, List<Person>> map2 = new HashMap<>();
		map2.computeIfAbsent(paris, city -> new ArrayList<>()).add(p4);
		map2.computeIfAbsent(shanghai, city -> new ArrayList<>()).add(p5);
		map2.computeIfAbsent(shanghai, city -> new ArrayList<>()).add(p6);
		
		System.out.println("Map 1");
		map1.forEach((city, people)->System.out.println(city + " : " +people));
		System.out.println("Map 2");
		map2.forEach((city, people)->System.out.println(city + " : " +people));
		
		map2.forEach(
				(city, people) -> {
					map1.merge(
							city, people,
							(existingPeople, newPeople) -> {
								existingPeople.addAll(newPeople);
								return existingPeople;
							});
				}
		);
				
	}

}
