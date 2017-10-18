package com.jenetics.mathexp.math.complex.fonction;

import org.apache.commons.math3.complex.Complex;

import math.suite.complex.ComplexFonction;

public class F implements ComplexFonction {

	@Override
	public Complex getValue(Complex c, int maxIter, Double maxAbs) {
		return ComplexContant.EC.pow(Complex.I.multiply(c));
	}
}
