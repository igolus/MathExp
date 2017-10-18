package com.jenetics.mathexp.sound;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.sound.midi.MidiChannel;

import com.jenetics.mathexp.math.BigDecimalMath;

public interface ISuitePlayer {

	void stopNote(int note, int velocity);

	void playNote(int note, int velocity);

	int playNext();

	MidiChannel getChannel();

}
