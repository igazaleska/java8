package pl.com.filters;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class MainReduction {
	
	private static int reduce(
			List<Integer> values, int valueIfEmpty, BinaryOperator<Integer> reduction){
			
			int result = valueIfEmpty;
			for (int value : values){
				result = reduction.apply(result, value);
			}
			return result;
	}

	public static void main(String[] args) {
		
		List<Integer> ints = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		List<Integer> ints1 = Arrays.asList(0, 1, 2, 3, 4);
		List<Integer> ints2 = Arrays.asList(5, 6, 7, 8, 9);
		
		BinaryOperator<Integer> op = (i1, i2) -> i1 + i2; //associative
		//BinaryOperator<Integer> op = (i1, i2) -> Integer.max(i1, 12); //associative max has no identity element, poprawny dla wartosci >= 0
		//BinaryOperator<Integer> op = (i1, i2) -> (i1 + i2)*(i1 + i2); //non-associative reduction3 result is invalid
		//BinaryOperator<Integer> op = (i1, i2) -> (i1 + i2)/2; //non-associative
		
		int reduction = reduce(ints, 0, op);
		
		//Parallel simulation
		int reduction1 = reduce(ints1, 0, op); 
		int reduction2 = reduce(ints2, 0, op);
		int reduction3 = reduce(Arrays.asList(reduction1, reduction2), 0, op);
		
		System.out.println("Reduction: "+reduction);
		System.out.println("Reduction: "+reduction3);
	}

	

}
