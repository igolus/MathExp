package math.suite;

import com.jenetics.mathexp.math.BigIntegerSuite;

/**
 * Created by igolus on 23/09/2017.
 */
public class SuiteFactory {
    public static BigIntegerSuite getPrimeSuite(int limit) {
        return new PrimeListSuite(limit);
    }
    public static BigIntegerSuite getSimpleSuite(int limit) {
        return new SimpleIntListSuite(limit, 1);
    }
    
    public static BigIntegerSuite getSimpleSuite(int limit, int mul) {
        return new SimpleIntListSuite(limit, mul);
    }

    public static BigIntegerSuite getFoboSuite(int limit) {
        return new FibonnacitListSuite(limit);
    }
	public static BigIntegerSuite getN2Suite(int limit) {
		return new N2Suite(limit);
	}
	public static BigIntegerSuite getPiSuite(int limit) {
		return new PiSuite(limit);
	}
	public static BigIntegerSuite getSinSuite(int limit) {
		return new PiSuite(limit);
	}
}
