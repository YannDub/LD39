package fr.sorax.game.audio;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	
	public static final Sound battery = new Sound("/sounds/battery.wav");
	public static final Sound death = new Sound("/sounds/death.wav");
	public static final Sound destroy = new Sound("/sounds/destroy.wav");
	public static final Sound music = new Sound("/sounds/music.wav");
	
	private Clip clip;
	
	private Sound(String name) {
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource(name));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = 
					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f); // Reduce volume by 10 decibels.
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		new Thread() {
			public void run() {
				clip.start();
			}
		}.start();
	}
	
	public void loop() {
		new Thread() {
			public void run() {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}.start();
	}
}
