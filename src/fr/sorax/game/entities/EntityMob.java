package fr.sorax.game.entities;

import java.awt.Container;

import fr.sorax.game.entities.ai.Rail;
import fr.sorax.game.gfx.Art;
import fr.sorax.game.gfx.Screen;
import fr.sorax.game.physics.AABB;
import fr.sorax.game.physics.Box;
import fr.sorax.game.utils.Constants;
import fr.sorax.game.worlds.Level;

public class EntityMob extends Entity {

	protected Rail currentRail;
	protected boolean targetNextRail;
	protected Box visionBox;
	
	public EntityMob(Level level) {
		super(level);
	}
	
	public EntityMob(Level level, int x, int y) {
		super(level, x, y);
	}
	
	public void init() {
		super.init();
		this.targetNextRail = true;
	}

	@Override
	public void render(Screen screen) {
		screen.drawBitmap(x, y, Art.player[4 + frame - 1][direction]);
	}

	@Override
	public void update() {
		super.update();
		int xTarget, yTarget;
		
		if(currentRail.getNextRail() != null && this.targetNextRail) {
			xTarget = currentRail.getNextRail().getWorldX();
			yTarget = currentRail.getNextRail().getWorldY();
		} else if(currentRail.getPredRail() != null) {
			this.targetNextRail = false;
			xTarget = currentRail.getPredRail().getWorldX();
			yTarget = currentRail.getPredRail().getWorldY();
		} else {
			targetNextRail = true;
			xTarget = x;
			yTarget = y;
		}
		
		if(x != xTarget) {
			int dir = (int)Math.signum(xTarget - x);
			this.move(dir, 0);
			this.direction = dir == -1 ? 1 : 3;
		}
		if(y != yTarget) {
			int dir = (int)Math.signum(yTarget - y);
			this.move(0, dir);
			this.direction = dir == -1 ? 2 : 0;
		}
		
		switch(this.direction) {
		case 0:
			this.visionBox = new Box(x - Constants.TILESIZE, y + Constants.TILESIZE, 3 * Constants.TILESIZE, 3 * Constants.TILESIZE);
			break;
		case 1:
			this.visionBox = new Box(x + Constants.TILESIZE, y - Constants.TILESIZE, 3 * Constants.TILESIZE, 3 * Constants.TILESIZE);
			break;
		case 2:
			this.visionBox = new Box(x - Constants.TILESIZE, y - 3 * Constants.TILESIZE, 3 * Constants.TILESIZE, 3 * Constants.TILESIZE);
			break;
		case 3:
			this.visionBox = new Box(x - 3 * Constants.TILESIZE, y - Constants.TILESIZE, 3 * Constants.TILESIZE, 3 * Constants.TILESIZE);
			break;
		}
		
		if(x == xTarget && y == yTarget) 
			this.currentRail = this.targetNextRail ? this.currentRail.getNextRail() : this.currentRail.getPredRail();
			
		if(this.detectPlayer()) {
			if(Constants.random.nextFloat() <= 0.2) this.level.getPlayer().removeLife(1);
		}
	}
	
	private boolean detectPlayer() {
		if(new AABB(this.level.getPlayer().getBox(), this.visionBox).intersect())
			return true;
		
		return false;
	}
	
	public void setCurrentRail(Rail rail) {
		this.currentRail = rail;
	}
	
	public Rail getCurrentRail() {
		return this.currentRail;
	}

}
