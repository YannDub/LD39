package fr.sorax.game.entities;

import fr.sorax.game.gfx.Art;
import fr.sorax.game.gfx.Screen;
import fr.sorax.game.physics.Box;
import fr.sorax.game.worlds.Level;

public class EntityBattery extends Entity {

	public EntityBattery(Level level) {
		super(level);
		this.box = new Box(this.x - 8, this.y - 8, 8, 8);
	}

	@Override
	public void render(Screen screen) {
		screen.drawBitmap(x, y, Art.tiles[0][15]);
	}
	
	public void update() {
		super.update();
		this.x = this.box.getX();
		this.y = this.box.getY();
	}
	
	public void heal(EntityPlayer player, int life) {
		player.addLife(life);
	}
} 
