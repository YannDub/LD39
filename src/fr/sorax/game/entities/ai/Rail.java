package fr.sorax.game.entities.ai;

import fr.sorax.game.utils.Constants;

public class Rail {
	
	protected int x, y;
	protected Rail nextRail;
	protected Rail predRail;
	
	public Rail(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setNextRail(Rail rail) {
		this.nextRail = rail;
	}
	
	public Rail getNextRail() {
		return this.nextRail;
	}
	
	public void setPredRail(Rail rail) {
		this.predRail = rail;
	}
	
	public Rail getPredRail() {
		return this.predRail;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWorldX() {
		return this.x * Constants.TILESIZE;
	}
	
	public int getWorldY() {
		return this.y * Constants.TILESIZE;
	}
	
	public static boolean goodColor(int color) {
		return color == 0xffff0000 || color == 0xff00ff00;
	}
}
