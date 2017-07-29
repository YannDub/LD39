package fr.sorax.game.worlds.tiles;

import fr.sorax.game.gfx.Art;
import fr.sorax.game.gfx.Screen;
import fr.sorax.game.worlds.Level;

public class CollidedTile extends Tile {

	public CollidedTile(int color) {
		super(color);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		Tile l = level.getTile(x - 1, y);
		Tile r = level.getTile(x + 1, y);
		Tile u = level.getTile(x, y - 1);
		Tile d = level.getTile(x, y + 1);
		
		if(u == Tile.wall && d == Tile.wall)
			screen.drawTile(x, y, Art.tiles[0][2]);
		else if(l == Tile.wall && r == Tile.wall)
			screen.drawTile(x, y, Art.tiles[1][1]);
		else if(d == Tile.wall && r == Tile.wall)
			screen.drawTile(x, y, Art.tiles[0][1]);
		else if(l == Tile.wall && u == Tile.wall)
			screen.drawTile(x, y, Art.tiles[2][3]);
		else if(r == Tile.wall && u == Tile.wall)
			screen.drawTile(x, y, Art.tiles[0][3]);
		else
			screen.drawTile(x, y, Art.tiles[2][1]);
	}

}
