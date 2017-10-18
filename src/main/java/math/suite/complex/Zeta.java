package math.suite.complex;

import org.apache.commons.math3.complex.Complex;

public class Zeta implements ComplexFonction {

	@Override
	public Complex getValue(Complex c, int maxIter, Double maxAbs) {
		Complex seed = new Complex(1, 0);
     	int iter = 0;
		for (iter = 2; iter < maxIter && (  maxAbs == null || seed.abs() < maxAbs); iter++) {
     		seed = seed.add(Complex.ONE.divide(  new Complex(iter).pow(c)));
     	}
		return seed;
	}

}
