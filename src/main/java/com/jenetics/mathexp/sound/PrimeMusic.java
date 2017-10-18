package com.jenetics.mathexp.sound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import math.suite.SuiteFactory;

public class PrimeMusic {

	private static final int PLAY_TIME = 60;

	private static final int VELOCITY = 100;

	public static void main(String[] args) {
		Synthesizer synthesizer;
		try {
			synthesizer = MidiSystem.getSynthesizer();
			synthesizer.open();
//			Instrument[] instruments = synthesizer.getDefaultSoundbank().getInstruments();
//			for (Instrument i : instruments)
//				System.out.println(i);
			List<AbstractSuitePlayer> suitePlayerList = getSuitePlayerList(synthesizer);
			List<Integer> listNotes = new LinkedList<>();

			boolean doit = true;
			int index = 0;
			while (doit) {
				listNotes.clear();
				
				for (Iterator iterator = suitePlayerList.iterator(); iterator.hasNext();) {
					AbstractSuitePlayer suitePlayer = (AbstractSuitePlayer) iterator.next();
					int note = suitePlayer.playNext();
					suitePlayer.getChannel().noteOn( note, VELOCITY);
					doit = (note != -1);
					if (doit) {
						suitePlayer.getChannel().noteOn( note, VELOCITY);
						System.out.println(suitePlayer.getChannel() + " " + note);
						listNotes.add(note);
						Thread.sleep(PLAY_TIME);
					}
				}

				int noteIndex = 0;
				for (Iterator iterator = suitePlayerList.iterator(); iterator.hasNext();) {
					AbstractSuitePlayer suitePlayer = (AbstractSuitePlayer) iterator.next();
					if (doit) {
						Integer notePlayed = listNotes.get(noteIndex++);
						suitePlayer.getChannel().noteOff( notePlayed, VELOCITY);
					}
				}
				Thread.sleep( Double.valueOf(Math.cos((double)index) * 50 + 100).intValue() );
			}
		} catch (MidiUnavailableException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static List<AbstractSuitePlayer> getSuitePlayerList(Synthesizer synthesizer) {
		List<AbstractSuitePlayer> ret = new ArrayList<>();
		ret.add(new SuitePlayer( SuiteFactory.getPiSuite(1000), synthesizer, 0, 0, 3, 8));
		ret.add(new SuitePlayer(SuiteFactory.getPrimeSuite(1000), synthesizer, 0, 4, 2, 3));
		return ret;
	}
}
