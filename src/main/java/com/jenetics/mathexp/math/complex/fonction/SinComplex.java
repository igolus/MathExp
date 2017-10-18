package com.jenetics.mathexp.math.complex.fonction;

import org.apache.commons.math3.complex.Complex;

import math.suite.complex.ComplexFonction;

public class SinComplex implements ComplexFonction {

	@Override
	public Complex getValue(Complex c, int maxIter, Double maxAbs) {
		return new Complex(c.getImaginary(), Math.cos(c.getImaginary()));
	}
}

