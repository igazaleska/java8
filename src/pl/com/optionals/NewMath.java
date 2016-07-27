package pl.com.optionals;

import java.util.Optional;
import java.util.stream.Stream;

public class NewMath {

	public static Optional<Double> inv(Double d) {
		// TODO Auto-generated method stub
		return  d != 0d ? Optional.of(1d/d): Optional.empty();
	}

	public static Optional<Double> sqrt(Double d) {
		// TODO Auto-generated method stub
		return  d > 0d ? Optional.of(Math.sqrt(d)): Optional.empty();
	}

}
