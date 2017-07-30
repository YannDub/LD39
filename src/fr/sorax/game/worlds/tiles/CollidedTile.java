package fr.sorax.game.worlds.tiles;

import fr.sorax.game.gfx.Art;
import fr.sorax.game.gfx.Screen;
import fr.sorax.game.worlds.Level;

public class CollidedTile extends Tile {

	public CollidedTile(int color) {
		super(color);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		this.autoTile(screen, level, x, y, 1, 2);
	}
	
	protected void autoTile(Screen screen, Level level, int x, int y, int xTile, int yTile) {
		Tile l = level.getTile(x - 1, y);
		Tile r = level.getTile(x + 1, y);
		Tile u = level.getTile(x, y - 1);
		Tile d = level.getTile(x, y + 1);
		
		if(u instanceof CollidedTile && d instanceof CollidedTile)
			screen.drawTile(x, y, Art.tiles[xTile - 1][yTile]);
		else if(l instanceof CollidedTile && r instanceof CollidedTile)
			screen.drawTile(x, y, Art.tiles[xTile][yTile - 1]);
		else if(d instanceof CollidedTile && r instanceof CollidedTile)
			screen.drawTile(x, y, Art.tiles[xTile - 1][yTile - 1]);
		else if(l instanceof CollidedTile && u instanceof CollidedTile)
			screen.drawTile(x, y, Art.tiles[xTile + 1][yTile + 1]);
		else if(r instanceof CollidedTile && u instanceof CollidedTile)
			screen.drawTile(x, y, Art.tiles[xTile - 1][yTile + 1]);
		else
			screen.drawTile(x, y, Art.tiles[xTile + 1][yTile - 1]);
	}

}
