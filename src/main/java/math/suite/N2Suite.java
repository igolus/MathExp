package math.suite;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.jenetics.mathexp.math.BigIntegerSuite;

/**
 * Created by igolus on 23/09/2017.
 */
public class N2Suite extends BigIntegerSuite {
    private int limit = 10000;

    private List<BigInteger> items;

    public N2Suite(int limit) {
        this.limit = limit;
    }

    @Override
    public List<BigInteger> fillSuite() {
        List<BigInteger> all = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            all.add( BigInteger.valueOf(2).pow(i+2));
        }
        return all;
    }
}
