package fr.sorax.game.worlds.tiles;

import fr.sorax.game.gfx.Art;
import fr.sorax.game.gfx.Screen;
import fr.sorax.game.worlds.Level;

public class EndTile extends Tile {
	
	public EndTile() {
		super(0xffff00ff);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		screen.drawTile(x, y, Art.tiles[1][0]);
	}
}
