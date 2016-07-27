package pl.com.lambdas;

import java.util.Arrays;
import java.util.List;

public class ReductionExample {

	public static void main(String[] args) {
		
		List<Integer> list = Arrays.asList(10, 10, 10);
		Integer red = 
		list.stream()
		//.reduce(0, (i1, i2) -> i1 + i2);
		.reduce(0, Integer::max);
		System.out.println("red = "+red);
		
	}

}
