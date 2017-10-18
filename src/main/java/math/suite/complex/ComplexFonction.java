package math.suite.complex;

import org.apache.commons.math3.complex.Complex;

public interface ComplexFonction {
	public Complex getValue(Complex c, int maxIter, Double maxAbs);
}
