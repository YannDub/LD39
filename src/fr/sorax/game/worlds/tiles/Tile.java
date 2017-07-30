package fr.sorax.game.worlds.tiles;

import java.util.HashMap;

import fr.sorax.game.gfx.Screen;
import fr.sorax.game.utils.Constants;
import fr.sorax.game.worlds.Level;

public class Tile {
	
	public static final Tile wall = new CollidedTile(0xffffffff);
	public static final Tile destructable = new DestructableTile();
	public static final Tile floor = new FloorTile();
	
	public static final HashMap<Integer, Tile> tiles = new HashMap<Integer, Tile>() {
		private static final long serialVersionUID = 1L;

	{
		for(int c : wall.getColor())
			put(c, wall);
		
		for(int c : floor.color)
			put(c, floor);
		
		for(int c : destructable.color)
			put(c, destructable);
	}
	};
	
	private int[] color;
	
	public Tile(int... color) {
		this.color = color;
	}
	
	public int[] getColor() {
		return this.color;
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		screen.draw(x * Constants.TILESIZE, y * Constants.TILESIZE, Constants.TILESIZE, Constants.TILESIZE, color[0]);
	}
}
