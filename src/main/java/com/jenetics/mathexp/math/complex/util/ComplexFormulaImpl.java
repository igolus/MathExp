package com.jenetics.mathexp.math.complex.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.math3.complex.Complex;

import com.jenetics.mathexp.util.ChainedList;
import com.jenetics.mathexp.util.ComplexRuntimeException;

public class ComplexFormulaImpl implements ComplexFormula {

	private static final String ERROR_IN_REFLECTION_CALL = "Error in reflection call";
	private ChainedList<ChainElement> chain;
	
	public ComplexFormulaImpl(ChainedList<ChainElement> chain) {
		super();
		this.chain = chain;
	}
	
	
	@Override
	public Complex compute(Complex... values) throws ComplexRuntimeException {
		ChainedList<ChainElement> actualChain = chain;
		if (!actualChain.getValue().isComplex()) {
			throw new ComplexRuntimeException("First element of chain should be a complex");
		}
		Complex actualComplex = actualChain.getValue().getComplex();
		while (actualChain.getNext() != null) {
			actualChain = actualChain.getNext();
			if (!actualChain.getValue().isOperator()) {
				throw new ComplexRuntimeException("element of chain should be an operator");
			}
			ComplexOperators complexOperator = actualChain.getValue().getComplexOperator();
			Method methodToProcess = ComplexMethodProvider.getComplexMethd(complexOperator);
			
			actualChain = actualChain.getNext();
			if (!actualChain.getValue().isComplex()) {
				throw new ComplexRuntimeException("element of chain should be a complex");
			}
			Complex complexArg = actualChain.getValue().getComplex();
			
			try {
				actualComplex = (Complex) methodToProcess.invoke(actualComplex, complexArg);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new ComplexRuntimeException(ERROR_IN_REFLECTION_CALL, e);
			} 
		}
		return actualComplex;
	}

}
