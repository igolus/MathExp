package com.jenetics.mathexp.math.complex.util;

import org.apache.commons.math3.complex.Complex;

import com.jenetics.mathexp.util.ChainedList;

public class ChainElement {
	
	private Object selectedObject = null;
	private Complex complex = null;
	private ComplexOperators complexOperator;
	private int variable = -1;
	private ChainedList<ChainElement> chain = null;
	
	
	public ChainElement(Complex complex) {
		super();
		this.complex = complex;
	}
	
	public ChainElement(ComplexOperators complexOperator) {
		super();
		this.complexOperator = complexOperator;
	}
	
	public ChainElement(int variable) {
		super();
		this.variable = variable;
	}
	
	
	public ChainElement(ChainedList<ChainElement> chain) {
		super();
		this.chain = chain;
	}
	

	public boolean isChain() {
		return chain != null;
	}
	
	public boolean isComplex() {
		return complex != null;
	}
	
	public boolean isOperator() {
		return complexOperator != null;
	}
	
	public boolean isVariable() {
		return variable != -1;
	}

	public Complex getComplex() {
		return complex;
	}

	public ComplexOperators getComplexOperator() {
		return complexOperator;
	}

	public ChainedList<ChainElement> getChain() {
		return chain;
	}

	public int getVariable() {
		return variable;
	}
}
