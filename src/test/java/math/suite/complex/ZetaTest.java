package math.suite.complex;

import static org.junit.Assert.*;

import org.apache.commons.math3.complex.Complex;
import org.junit.Test;

public class ZetaTest {
	@Test 
	public void testZeta(){
		Complex c = new Complex(0.5, 14.13472514);
		Zeta z = new Zeta();
		Complex res = z.getValue(c, 100000, null);
		System.out.println("res:" + res);
		
	}
}
