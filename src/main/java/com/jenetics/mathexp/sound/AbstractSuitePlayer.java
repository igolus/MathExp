package com.jenetics.mathexp.sound;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.Synthesizer;

public abstract class AbstractSuitePlayer {

	
	
	public AbstractSuitePlayer(Synthesizer synthesizer, int bank, int preset) {
		super();
		this.channel = synthesizer.getChannels()[NB_CHANNEL++];
		this.channel.programChange(bank, preset);
		// TODO Auto-generated constructor stub
	}

	private MidiChannel channel;
	private static int NB_CHANNEL=0;


	public MidiChannel getChannel() {
		return channel;
	}


	public abstract int playNext();

	public void playNote(int note, int velocity) {
		channel.noteOn(note, velocity);
	}
	
	public void stopNote(int note, int velocity) {
		channel.noteOff(note, velocity);
	}

}
