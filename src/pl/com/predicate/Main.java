package pl.com.predicate;

public class Main {

	public static void main(String[] args) {
		
		Predicate<String> p1 = s -> s.length() < 20;
		Predicate<String> p2 = s -> s.length() > 5;
		
		boolean b = p1.test("Hello");
		System.out.println("Hello is shorter than 20 chars: "+b);
		
		Predicate<String> p3 = p1.and(p2);
		System.out.println("p3 for yes: "+p3.test("yes"));
		System.out.println("p3 for good morning "+p3.test("Good morning"));
		System.out.println("p3 for good morning gentelmen: "+p3.test("Good morning gentlemen"));

		Predicate<String> p4 = p1.or(p2);
		System.out.println("p4 for yes: "+p4.test("yes"));
		System.out.println("p4 for good morning "+p4.test("Good morning"));
		System.out.println("p4 for good morning gentelmen: "+p4.test("Good morning gentlemen"));
		
		Predicate<String> p5 = Predicate.isEqualsTo("Yes");
		System.out.println("p5 for yes: "+p5.test("Yes"));
		System.out.println("p5 for no: "+p5.test("No"));
		
		Predicate<Integer> p6 = Predicate.isEqualsTo(1);
		
	}

}
