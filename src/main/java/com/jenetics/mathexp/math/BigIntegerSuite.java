package com.jenetics.mathexp.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by igolus on 23/09/2017.
 */
public abstract class BigIntegerSuite implements IBigIntegerSuite {
    private List<BigInteger> items;
    private int counter = 0;

    public abstract List<BigInteger> fillSuite();

    public BigInteger next() {
        if (items == null) {
            items = fillSuite();
        }
        if (counter > items.size()) {
            return null;
        }
        return items.get(counter++);
    }
}
