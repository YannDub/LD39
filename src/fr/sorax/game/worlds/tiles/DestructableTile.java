package fr.sorax.game.worlds.tiles;

import fr.sorax.game.gfx.Screen;
import fr.sorax.game.worlds.Level;

public class DestructableTile extends CollidedTile {

	public DestructableTile() {
		super(0xff00ffff);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		this.autoTile(screen, level, x, y, 4, 2);
	}
}
