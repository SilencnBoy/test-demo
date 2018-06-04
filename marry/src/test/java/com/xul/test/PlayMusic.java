package com.xul.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class PlayMusic extends Thread {
	
	private static String FileName = "C:\\WINDOWS\\Media\\onestop.mid";
	private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	private Sequence seq = null;

	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
				if (isTimeToPlay())
					LoadAndPlay();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public PlayMusic() {
	}

	public void LoadAndPlay() {
		try {
			this.seq = MidiSystem.getSequence(new File(FileName));
			Sequencer localSequencer = MidiSystem.getSequencer();
			localSequencer.open();
			localSequencer.setSequence(seq);
			localSequencer.start();
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	private boolean isTimeToPlay() {
	 return format.format(new Date()).endsWith("00:00"); //format.format(new Date()).endsWith("30:00"); //��Сʱ��ʱ 
	 } 
	 
	 public static void main(String[] args) { 
		 PlayMusic timeClock = new PlayMusic(); timeClock.start(); 
	} 
	  
}
