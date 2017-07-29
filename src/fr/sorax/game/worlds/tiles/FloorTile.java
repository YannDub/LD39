package fr.sorax.game.worlds.tiles;

import fr.sorax.game.gfx.Art;
import fr.sorax.game.gfx.Screen;
import fr.sorax.game.worlds.Level;

public class FloorTile extends Tile {

	public FloorTile() {
		super(0xff000000, 0xffff0000, 0xff00ff00, 0xff0000ff, 0xffffff00);
	}
	
	@Override
	public void render(Screen screen, Level level, int x, int y) {
		screen.drawTile(x, y, Art.tiles[0][0]);
	}

}
