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
public class PiSuite extends BigIntegerSuite {
	private static final int WINDOW_SIZE = 30;
	private int limit = 10000;
	private static File piFile = new File("C:\\Java\\NaturalFractalBridge\\pi.txt");
	private int LIMIT_LINES = 10000;

	private List<BigInteger> items;

	public PiSuite(int limit) {
		this.limit = limit;
	}

	@Override
	public List<BigInteger> fillSuite() {
		List<BigInteger> all = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(piFile));
			String piLong = in.readLine();

			for (int i = 0; i < piLong.length() - WINDOW_SIZE &&  i < limit; i++) {
				BigInteger bigInteger = new BigInteger(piLong.substring(i, i + WINDOW_SIZE));
				all.add(bigInteger);
			}

			return all;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
