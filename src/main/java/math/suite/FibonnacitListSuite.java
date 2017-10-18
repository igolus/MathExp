package math.suite;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.jenetics.mathexp.math.BigIntegerSuite;

/**
 * Created by igolus on 23/09/2017.
 */
public class FibonnacitListSuite extends BigIntegerSuite {
    private int limit = 10000;

    private List<BigDecimal> items;

    public FibonnacitListSuite(int limit) {
        this.limit = limit;
    }

    @Override
    public List<BigInteger> fillSuite() {
        List<BigInteger> allItem = new ArrayList<>();
        allItem.add(BigInteger.valueOf(1));
        allItem.add(BigInteger.valueOf(2));
        for (int i = 2; i < limit; i++) {
            allItem.add(allItem.get(i-1).add(allItem.get(i-2)));
        }
        return allItem;
    }
}
