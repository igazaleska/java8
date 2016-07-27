package pl.com.lambdas;

import java.util.Objects;

public interface Predicate<T> {
	
	boolean test(T t);
	
	default Predicate<T> and (Predicate<? super T> other) {
		Objects.requireNonNull(other);
		return (t) -> test(t) && other.test(t);
	}
}
