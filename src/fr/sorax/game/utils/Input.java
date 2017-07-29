package fr.sorax.game.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

	private boolean keys[];
	
	public Input() {
		this.keys = new boolean[65465];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		this.keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isKeyDown(int keyCode) {
		return this.keys[keyCode];
	}
}
