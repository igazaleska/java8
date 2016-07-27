package pl.com.lambdas;

public class Predicates {
	
	public static void main(String[] args){
		Predicate<String> p1 = s -> s.length() < 20;
		Predicate<String> p2 = s -> s.length() > 10;
		
		Predicate<String> p3 = p1.and(p2);
		
	}
}
