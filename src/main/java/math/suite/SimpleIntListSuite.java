package math.suite;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.jenetics.mathexp.math.BigIntegerSuite;

/**
 * Created by igolus on 23/09/2017.
 */
public class SimpleIntListSuite extends BigIntegerSuite {
    private int limit = 10000;

    private List<BigInteger> items;

	private BigInteger mul;

    public SimpleIntListSuite(int limit, int mul) {
        this.limit = limit;
        this.mul  = BigInteger.valueOf(mul);
    }

    @Override
    public List<BigInteger> fillSuite() {
        List<BigInteger> all = new ArrayList<>();
        for (long i = 0; i < limit; i++) {
            all.add(BigInteger.valueOf(i+1).multiply(mul));
        }
        return all;
    }
}
