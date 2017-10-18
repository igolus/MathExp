package math.suite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.jenetics.mathexp.math.BigIntegerSuite;

/**
 * Created by igolus on 23/09/2017.
 */
public class PrimeListSuite extends BigIntegerSuite {
    private static File primeListFile = new File("C:\\Java\\NaturalFractalBridge\\primeList.txt");
    private int LIMIT_LINES = 10000;

    private List<BigDecimal> items;

    public PrimeListSuite(int limit) {
        this.LIMIT_LINES = limit;
    }

    @Override
    public List<BigInteger> fillSuite() {
        List<BigInteger> allPrime = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(primeListFile));
            String str;
            int index = 0;
            while ((str = in.readLine()) != null && (index< LIMIT_LINES || LIMIT_LINES == 0)) {
                String[] prime = str.split("\\s+");
                for (int i = 1; i < prime.length; i++) {
                    String primeS = prime[i];
                    allPrime.add(new BigInteger(primeS));
                }
                index ++;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allPrime;
    }
}
