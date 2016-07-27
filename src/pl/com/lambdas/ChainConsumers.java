package pl.com.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ChainConsumers {

	public static void main(String args[]) {
		List<String> strings = 
				Arrays.asList("one", "two", "three", "four", "five");
		
		List<String> result = new ArrayList<>();

		//Consumer<String> c1 = s -> System.out.println(s);
		Consumer<String> c1 = System.out::println;
		// Consumer<String> c2 = s -> result.add(s);
		Consumer<String> c2 = result::add;

		strings.forEach(c1.andThen(c2));
		System.out.println("size of result = " + result.size());
	}
}
