package math.suite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.jenetics.mathexp.math.BigIntegerSuite;

/**
 * Created by igolus on 23/09/2017.
 */
public class SinSuite extends BigIntegerSuite {
	private int limit = 10000;

	private List<BigInteger> items;

	public SinSuite(int limit) {
		this.limit = limit;
	}

	@Override
	public List<BigInteger> fillSuite() {
		List<BigInteger> all = new ArrayList<>();
		try {
			for (int i = 0; i < limit; i++) {
				int value = Double.valueOf(Math.sin((double)i) * (double)10).intValue();
				all.add(BigInteger.valueOf(value));
			}

			return all;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
