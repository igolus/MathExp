package com.jenetics.mathexp.math.complex.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.math3.complex.Complex;

import com.jenetics.mathexp.util.ComplexRuntimeException;

@FunctionalInterface
public interface ComplexFormula {
	/**
	 * Compute the results, Complex args are z1, z2, etc.. coming from the formula
	 * @param values
	 * @return
	 * @throws ComplexRuntimeException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Complex compute(Complex... values) throws ComplexRuntimeException;
}
