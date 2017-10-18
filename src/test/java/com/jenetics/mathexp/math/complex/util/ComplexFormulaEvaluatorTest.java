package com.jenetics.mathexp.math.complex.util;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.math3.complex.Complex;
import org.junit.Test;

import com.jenetics.mathexp.util.ComplexEvalException;
import com.jenetics.mathexp.util.ComplexRuntimeException;
import com.jenetics.mathexp.util.ComplexEvalException.ComplexEvalExceptionType;

import static com.jenetics.mathexp.util.ComplexEvalException.ComplexEvalExceptionType.MISSING_CLOSING_BRACKET;
import static com.jenetics.mathexp.util.ComplexEvalException.ComplexEvalExceptionType.MISSING_COMMA;
import static com.jenetics.mathexp.util.ComplexEvalException.ComplexEvalExceptionType.MISSING_OPEN_BRACKET;
import static com.jenetics.mathexp.util.ComplexEvalException.ComplexEvalExceptionType.PREVIOUS_SHOULD_BE_COMPLEX;
import static com.jenetics.mathexp.util.ComplexEvalException.ComplexEvalExceptionType.WRONG_NUMERIC_FORMAT;

public class ComplexFormulaEvaluatorTest {

	@Test
	public void testSimpleC() {
		try {
			ComplexFormula formula = ComplexFormulaEvaluator.getFormula("C(0,1) + C(1,0)");
			Complex result = formula.compute(null);
			assertTrue("C(0,1) + C(1,0) should be C(1,1)", result.equals(new Complex(1, 1)));
		} catch (ComplexRuntimeException | ComplexEvalException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFormulaParsingIvalidFisrt() {
		ComplexEvalException complexEvalExceptionExpected = null;	
		try {
			ComplexFormulaEvaluator.getFormula("FOO");
			fail("ComplexEvalException should be thrown");
		} catch (ComplexEvalException e ) {
			complexEvalExceptionExpected = e;
			assertTrue("Message should be ", e.getComplexEvalExceptionType() == (ComplexEvalExceptionType.INVALID_CHAR));
		}
	}



}
