package com.jenetics.mathexp.math.complex.util;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.apache.commons.math3.complex.Complex;

public class ComplexMethodProvider {
	
	static Logger log = Logger.getLogger(ComplexMethodProvider.class.getName());
	
	public static Method getComplexMethd(ComplexOperators operator) {
		Method methodToRet = null;
		try {
			if (operator == ComplexOperators.ADD) {
				return Complex.class.getMethod("add", Complex.class);
			}
		} catch (NoSuchMethodException e) {
			log.severe("No able to find the methods");
		} catch (SecurityException e) {
			log.severe("Security error");
		}
		return methodToRet;
	}
}
