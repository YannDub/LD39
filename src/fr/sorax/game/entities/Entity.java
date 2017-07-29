package fr.sorax.game.entities;

import fr.sorax.game.gfx.Screen;
import fr.sorax.game.physics.AABB;
import fr.sorax.game.physics.Box;
import fr.sorax.game.utils.Constants;
import fr.sorax.game.worlds.Level;

public abstract class Entity {
	
	protected int x, y;
	protected Level level;
	protected Box box;
	protected int direction;
	protected int frame;
	protected int tick;
	
	public Entity(Level level, int x, int y) {
		this.level = level;
		this.x = x;
		this.y = y;
		this.init();
	}
	
	public Entity(Level level) {
		this(level, 0, 0);
	}
	
	protected void init() {
		this.box = new Box(this.x, this.y, 10, 8);
		this.direction = 0;
		this.frame = 1;
	}
	
	public void translate(int xO, int yO) {
		this.box.translate(xO, yO);
	}
	
	public void setLocalPosition(int x, int y) {
		this.box.setPosition(x, y);
	}
	
	public void setWorldPosition(int x, int y) {
		this.setLocalPosition(x * Constants.TILESIZE, y * Constants.TILESIZE);
	}
	
	public Box getBox() {
		return this.box;
	}
	
	public abstract void render(Screen screen);
	
	public void move(int x, int y) {
		this.translate(x, y);
		
		for(Box b : level.getMask()) {
			AABB aabb = new AABB(box, b);
			if(aabb.intersect()) {
				this.translate(-x, -y);
			}
		}
		
		switch(tick % 60) {
		case 0:
		case 40:
			frame = 2;
			break;
		case 10:
		case 30:
			frame = 1;
			break;
		case 20:
		case 60:
			frame = 0;
			break;
		}
	}
	
	public void update() {
		this.x = this.box.getX() - 3;
		this.y = this.box.getY() - 8;
		this.tick++;
		if(tick > 10000) tick = 0;
	}
}
