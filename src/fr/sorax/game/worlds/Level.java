package fr.sorax.game.worlds;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import fr.sorax.game.Game;
import fr.sorax.game.entities.Entity;
import fr.sorax.game.entities.EntityBattery;
import fr.sorax.game.entities.EntityMob;
import fr.sorax.game.entities.EntityPlayer;
import fr.sorax.game.entities.ai.Rail;
import fr.sorax.game.gfx.Screen;
import fr.sorax.game.physics.AABB;
import fr.sorax.game.physics.Box;
import fr.sorax.game.utils.Constants;
import fr.sorax.game.worlds.tiles.CollidedTile;
import fr.sorax.game.worlds.tiles.Tile;

public class Level {
	
	private int width, height;
	private Tile[] tiles;
	private List<Entity> entities;
	private EntityPlayer player;
	private List<Box> mask;
	
	public Level(int width, int height) {
		this.init();
		this.width = width;
		this.height = height;
		this.tiles = new Tile[width * height];
	}
	
	public Level(String levelName) {
		this.init();

		try {
			BufferedImage image = ImageIO.read(Level.class.getResource("/levels/" + levelName + ".png"));
			this.width = image.getWidth();
			this.height = image.getHeight();
			this.tiles = new Tile[width * height];
			
			for(int x = 0; x < width; x++) {
				for(int y = 0; y < height; y++) {
					int pixel = image.getRGB(x, y);
					
					if(pixel == 0xff0000ff)
						this.player.setWorldPosition(x, y);
					
					if(pixel == 0xff00ff00) {
						EntityMob mob = new EntityMob(this);
						this.addEntity(mob);
						mob.setWorldPosition(x, y);
						mob.setCurrentRail(this.generateRail(image, x, y, null));
					}
					
					if(pixel == 0xffffff00) {
						EntityBattery battery = new EntityBattery(this);
						this.addEntity(battery);
						battery.setWorldPosition(x, y);
					}
					
					if(Tile.tiles.containsKey(pixel))
						this.tiles[x + y * width] = Tile.tiles.get(pixel);
					else
						this.tiles[x + y * width] = new Tile(pixel);
					
					if(this.tiles[x + y * width] instanceof CollidedTile) {
						Box b = new Box(x * Constants.TILESIZE, y * Constants.TILESIZE, Constants.TILESIZE, Constants.TILESIZE);
						this.mask.add(b);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("/levels/" + levelName + ".png is not a good level");
		}
	}
	
	protected void init() {
		this.entities = new LinkedList<Entity>();
		this.player = new EntityPlayer(this);
		this.addEntity(player);
		this.mask = new ArrayList<Box>();
	}
	
	protected Rail generateRail(BufferedImage image, int x, int y, Rail predRail) {
		Rail rail = new Rail(x, y);
		int startX = x;
		int startY = y;
		int nextX = -1;
		int nextY = -1;
		
		if(predRail != null) {
			startX = predRail.getX();
			startY = predRail.getY();
		}
		
		if(Rail.goodColor(image.getRGB(x, y + 1)) && !(x == startX && y + 1 == startY)) {
			nextX = x;
			nextY = y + 1;
		}
		else if(Rail.goodColor(image.getRGB(x, y - 1)) && !(x == startX && y - 1 == startY)) {
			nextX = x;
			nextY = y - 1;
		}
		else if(Rail.goodColor(image.getRGB(x + 1, y)) && !(x + 1 == startX && y == startY)) {
			nextX = x + 1;
			nextY = y;
		}
		else if(Rail.goodColor(image.getRGB(x - 1, y)) && !(x - 1 == startX && y == startY)) {
			nextX = x - 1;
			nextY = y;
		}
		
		if(nextX != -1 && nextY != -1) 
			rail.setNextRail(this.generateRail(image, nextX, nextY, rail));
		
		rail.setPredRail(predRail);
		
		return rail;
	}
	
	public void render(Screen screen) {
		int xOff = this.player.getBox().getX() - Game.WIDTH / 8;
		int yOff = this.player.getBox().getY() - Game.HEIGHT / 8;
		
		screen.setOffset(xOff, yOff);
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				tiles[i + j * width].render(screen, this, i, j);
			}
		}
		
		for(Entity e : entities) {
			e.render(screen);
		}
	}
	
	public void update() {
		List<Entity> removedList = new LinkedList<Entity>();
		
		for(Entity e : entities) {
			e.update();
			if(new AABB(e.getBox(), player.getBox()).intersect()) {
				if(e instanceof EntityMob) {					
					this.player.setLife(0);
				} else if(e instanceof EntityBattery) {
					((EntityBattery) e).heal(player, 10 + Constants.random.nextInt(20));
					removedList.add(e);
				}
			}
		}
		
		this.removeEntities(removedList);
	}
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
	}
	
	public void removeEntities(List<Entity> entities) {
		this.entities.removeAll(entities);
	}
	
	public List<Box> getMask() {
		return this.mask;
	}
	
	public void removeMask(int x, int y) {
		for(Box b : this.mask) {
			if(b.getX() == x * Constants.TILESIZE && b.getY() == y * Constants.TILESIZE) {
				this.mask.remove(b);
				return;
			}
		}
	}
	
	public void setTile(Tile tile, int x, int y) {
		this.tiles[x + y * width] = tile;
	}
	
	public Tile getTile(int x, int y) {
		return this.tiles[x + y * width];
	}
	
	public EntityPlayer getPlayer() {
		return this.player;
	}
}
