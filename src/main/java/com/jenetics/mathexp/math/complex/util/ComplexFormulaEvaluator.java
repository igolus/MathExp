package com.jenetics.mathexp.math.complex.util;

import static com.jenetics.mathexp.util.ComplexEvalException.ComplexEvalExceptionType.MISSING_CLOSING_BRACKET;
import static com.jenetics.mathexp.util.ComplexEvalException.ComplexEvalExceptionType.MISSING_COMMA;
import static com.jenetics.mathexp.util.ComplexEvalException.ComplexEvalExceptionType.MISSING_OPEN_BRACKET;
import static com.jenetics.mathexp.util.ComplexEvalException.ComplexEvalExceptionType.PREVIOUS_SHOULD_BE_COMPLEX;
import static com.jenetics.mathexp.util.ComplexEvalException.ComplexEvalExceptionType.WRONG_NUMERIC_FORMAT;
import static com.jenetics.mathexp.util.ComplexEvalException.ComplexEvalExceptionType.INVALID_CHAR;

import java.util.logging.Logger;

import org.apache.commons.math3.complex.Complex;

import com.jenetics.mathexp.util.ChainedList;
import com.jenetics.mathexp.util.ComplexEvalException;

public class ComplexFormulaEvaluator {
	
	static Logger log = Logger.getLogger(ComplexFormulaEvaluator.class.getName());
	
	
	
	
	/**
	 * Create a complex Formula that can be reused afterward without having to parse 
	 * again the formula
	 * @param expr
	 * @return
	 * @throws ComplexEvalException
	 */
	public static ComplexFormula getFormula(String expr) throws ComplexEvalException {
		//(C(1,0) + C(0,1))
		ChainedList<ChainElement> chain = null; 
		char[] exprChars = expr.toCharArray();
		for (int i = 0; i < exprChars.length; i++) {
			char singleChar = exprChars[i];
			if (singleChar == ' ') {
				continue;
			}
			if (singleChar == 'z') {
				manageVariable(chain, exprChars, i);
				i++;
				continue;
				
			}
			if (singleChar == 'C') {
				int closingIndex = manageComplexValue(expr, chain, exprChars, i, singleChar);
				i+=closingIndex;
				continue;
			}
			else if (singleChar == '+') {
				manageOperator(chain, ComplexOperators.ADD);
			}
			else if (singleChar == '-') {
				manageOperator(chain, ComplexOperators.REMOVE);
			}
			else if (singleChar == '*') {
				manageOperator(chain, ComplexOperators.MUL);
			}
			else if (singleChar == '(') {
				//manageOperator(chain, ComplexOperators.MUL);
			}
			else {
				throw new ComplexEvalException(INVALID_CHAR, Character.toString(singleChar));
			}
			chain = chain.getNext();
		}
		return new ComplexFormulaImpl(chain.getInitial());
	}

	private static void manageOperator(ChainedList<ChainElement> chain, ComplexOperators complexOperators ) throws ComplexEvalException {
		if (chain == null) {
			throw new ComplexEvalException(INVALID_CHAR, "formula cannot start with operator");
		}
		if (!chain.getValue().isComplex() && !chain.getValue().isVariable()) {
			throw new ComplexEvalException(INVALID_CHAR, "previous should variable or complex");
		}
		addToChain(chain, new ChainElement(complexOperators));
	}

	private static int manageComplexValue(String expr, ChainedList<ChainElement> chain, char[] exprChars, int i,
			char singleChar) throws ComplexEvalException {
		String remaining = expr.substring(i);
		if (singleChar != '(' || i == exprChars.length - 1) {
			throw new ComplexEvalException(MISSING_OPEN_BRACKET, "inside C(...) definition");
		}
		int closingIndex = remaining.indexOf(')');
		if (closingIndex == -1) {
			throw new ComplexEvalException(MISSING_CLOSING_BRACKET, "inside C(...) definition");
		}
		String complex = remaining.substring(1, closingIndex + 1);
		ChainElement chainElem = new ChainElement(getComplexFromValue(complex));
		
		addToChain(chain, chainElem);
		return closingIndex;
	}

	private static void manageVariable(ChainedList<ChainElement> chain, char[] exprChars, int i)
			throws ComplexEvalException {
		if (i < exprChars.length) {
			char variableName = exprChars[i+1]; 
			try {
				int variableNumber = Integer.parseInt(String.valueOf(variableName));
				if (variableNumber >= 0) {
					ChainElement chainElem = new ChainElement(variableNumber);
					addToChain(chain, chainElem);
				}
				else {
					throw new ComplexEvalException(INVALID_CHAR, "value after z should numeric greater or equals to 0");
				}
			}
			catch (NumberFormatException e) {
				throw new ComplexEvalException(INVALID_CHAR, "value after z should numeric");
			}

		}
		else {
			throw new ComplexEvalException(INVALID_CHAR, "value after z should numeric");
		}
	}
	
	/**
	 * Add to chain, create if null
	 * @param chain
	 * @param chainElem
	 */
	private static void addToChain(ChainedList<ChainElement> chain, ChainElement chainElem) {
		if (chain == null) {
			chain = new ChainedList<ChainElement>(chainElem);
		}
		else {
			chain.addFollowing(chainElem);
		}
		
	}


	/**
	 * Get the complex value from a String like C(0,1)
	 * The two values inside the parenthesis are double values.
	 * @param complex
	 * @return
	 * @throws ComplexEvalException
	 */
	private static Complex getComplexFromValue(String complex) throws ComplexEvalException {
		String leftValue = complex.substring(1);
		if (leftValue.indexOf(",") == -1) {
			throw new ComplexEvalException(MISSING_COMMA, "missing , in complex expression ex C(1,0)");
		}
		leftValue = leftValue.substring(0, leftValue.indexOf(","));		
		String rightValue = complex.substring(complex.indexOf(",") + 1, complex.length() - 1);
		
		Complex c = null;
		try {
			double re = Double.parseDouble(leftValue);
			double im = Double.parseDouble(rightValue);
			c = new Complex(re, im);
		}
		catch (NumberFormatException e) {
			throw new ComplexEvalException(WRONG_NUMERIC_FORMAT, "wrong numeric format in C expression ex C(1,0)");
		}
		return c;
	}
}
