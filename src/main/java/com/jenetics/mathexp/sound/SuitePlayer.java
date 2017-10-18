package com.jenetics.mathexp.sound;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.Synthesizer;

import com.jenetics.mathexp.math.BigDecimalMath;
import com.jenetics.mathexp.math.BigIntegerSuite;

import math.suite.SuiteFactory;

public class SuitePlayer extends AbstractSuitePlayer {
	private static final int OFFSET = 45;
	private BigIntegerSuite suite = null;
	private List<BigInteger> fillSuite;


	private int index = 0;
	private int octaveStart;
	private int nbOctave;

	private static BigDecimal bdPI =
			new BigDecimal("3.1415926535897932384626433832795028841971693993751058209");
	private static BigDecimal twoBdPI =
			bdPI.multiply(new BigDecimal("2"));

	public SuitePlayer(BigIntegerSuite suite, Synthesizer synthesizer, int bank, int preset, int octaveStart, int nbOctave) {
		super(synthesizer, bank, preset);

		this.suite = suite;
		this.octaveStart = octaveStart;
		this.nbOctave = nbOctave;
		this.fillSuite = suite.fillSuite();
	}

	@Override
	public int playNext() {
		
		double progression = Math.pow(2, (double)1/(double)12);
		
		if (index < fillSuite.size()) {
			BigInteger bigInteger = fillSuite.get(index++);
			BigDecimal divide = BigDecimalMath.divideRound(bigInteger, twoBdPI);
			BigDecimal remainder = divide.subtract(new BigDecimal(divide.toBigInteger()));
			System.out.println("divide:" + divide);
			System.out.println("remainder:" + remainder);
			double noteIndexDouble = 1 / ((double)1/(double)8 + (3 * remainder.doubleValue()) / 4);
			
			int noteToPlay = Double.valueOf(Math.log(noteIndexDouble) / Math.log(progression)).intValue();
			System.out.println("noteToPlay:" + noteToPlay);

			return noteToPlay + OFFSET;	
		}
		return -1;
	}	


}
