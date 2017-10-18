package com.jenetics.mathexp.sound;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.Synthesizer;

public class Simple10IntPlayer extends AbstractSuitePlayer {
	
	private int INDEX = 0;
	private static final int WINDOW_SIZE = 30;
	private static final int OTCAVE = 6;
	private int limit = 10000;
	private static File piFile = new File("C:\\Java\\NaturalFractalBridge\\pi.txt");
	private int LIMIT_LINES = 10000;

	private List<BigInteger> items;
	private List<Integer> listNotes;

	public Simple10IntPlayer(int limit, Synthesizer synthesizer, int bank, int preset) {
		super(synthesizer, bank, preset);
		this.limit = limit;
		this.listNotes = fillSuite();
	}

	public List<Integer> fillSuite() {
		List<Integer> all = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(piFile));
			String piLong = in.readLine();

			for (int i = 0; i < piLong.length() - 1 &&  i < limit; i++) {
				int note = Integer.parseInt(piLong.substring(i, i + 1));
				all.add(note);
			}

			return all;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return all;
	}
	
	
	
	
	

	@Override
	public int playNext() {
		// TODO Auto-generated method stub
		if (INDEX < listNotes.size()) {
			return listNotes.get(INDEX++) + 12 * OTCAVE;
		}
		else {
			return -1;
		}
		
	}


}
