package fr.sorax.game.entities;

import java.awt.event.KeyEvent;

import fr.sorax.game.Game;
import fr.sorax.game.audio.Sound;
import fr.sorax.game.gfx.Art;
import fr.sorax.game.gfx.Screen;
import fr.sorax.game.scene.WinScene;
import fr.sorax.game.utils.Constants;
import fr.sorax.game.worlds.Level;
import fr.sorax.game.worlds.tiles.Tile;

public class EntityPlayer extends Entity {
	
	private int life;
	private int maxLife;
	
	public EntityPlayer(Level level, int x, int y) {
		super(level, x, y);
	}
	
	public EntityPlayer(Level level) {
		super(level);
	}
	
	public void init() {
		super.init();
		this.life = 50;
		this.maxLife = 100;
	}
	
	public void render(Screen screen) {
		screen.drawBitmap(x, y, Art.player[frame][direction]);
	}

	@Override
	public void update() {
		super.update();
		
		if(this.life > 0) {
			if(level.getTile(x / Constants.TILESIZE, (y / Constants.TILESIZE) + 2) == Tile.destructable && Game.INPUT.isKeyDown(KeyEvent.VK_SPACE)) {
				level.setTile(Tile.floor, x / Constants.TILESIZE, (y / Constants.TILESIZE) + 2);
				level.removeMask(x / Constants.TILESIZE, (y / Constants.TILESIZE) + 2);
				this.removeLife(10);
				Sound.destroy.play();
			} else if(level.getTile(x / Constants.TILESIZE, y / Constants.TILESIZE) == Tile.destructable && Game.INPUT.isKeyDown(KeyEvent.VK_SPACE)) {
				level.setTile(Tile.floor, x / Constants.TILESIZE, y / Constants.TILESIZE);
				level.removeMask(x / Constants.TILESIZE, y / Constants.TILESIZE);
				Sound.destroy.play();
				this.removeLife(10);
			} else if(level.getTile(x / Constants.TILESIZE + 4, y / Constants.TILESIZE + 1) == Tile.destructable && Game.INPUT.isKeyDown(KeyEvent.VK_SPACE)) {
				level.setTile(Tile.floor, x / Constants.TILESIZE + 4, y / Constants.TILESIZE + 1);
				level.removeMask(x / Constants.TILESIZE + 4, y / Constants.TILESIZE + 1);
				this.removeLife(10);
				Sound.destroy.play();
			} else if(level.getTile(x / Constants.TILESIZE + 1, y / Constants.TILESIZE + 1) == Tile.destructable && Game.INPUT.isKeyDown(KeyEvent.VK_SPACE)) {
				level.setTile(Tile.floor, x / Constants.TILESIZE + 1, y / Constants.TILESIZE + 1);
				level.removeMask(x / Constants.TILESIZE + 1, y / Constants.TILESIZE + 1);
				this.removeLife(10);
				Sound.destroy.play();
			}
			
			if(level.getTile(x / Constants.TILESIZE, y / Constants.TILESIZE + 1) == Tile.end) {
				Game.goToScene(new WinScene());
				return;
			}

			if(Game.INPUT.isKeyDown(KeyEvent.VK_UP)) {
				this.direction = 2;
				this.move(0, -1);
			}
			if(Game.INPUT.isKeyDown(KeyEvent.VK_DOWN)) {
				this.direction = 0;
				this.move(0, 1);
			}
			if(Game.INPUT.isKeyDown(KeyEvent.VK_LEFT)) {
				this.direction = 1;
				this.move(-1, 0);
			}
			if(Game.INPUT.isKeyDown(KeyEvent.VK_RIGHT)) {
				this.direction = 3;
				this.move(1, 0);
			}
			
			if(this.tick % 180 == 0)
				this.removeLife(1);
			
		}
	}
	
	public void setLife(int life) {
		this.life = life;
	}
	
	public void addLife(int life) {
		this.life += life;
		if(this.life > this.maxLife) this.life = this.maxLife;
	}
	
	public void removeLife(int life) {
		this.life -= life;
		if(this.life < 0) this.life = 0;
	}
	
	public int getLife() {
		return this.life;
	}
	
	public int getMaxLife() {
		return this.maxLife;
	}

}
