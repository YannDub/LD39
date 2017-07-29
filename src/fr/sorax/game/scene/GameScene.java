package fr.sorax.game.scene;

import fr.sorax.game.entities.EntityPlayer;
import fr.sorax.game.gfx.Art;
import fr.sorax.game.gfx.Screen;
import fr.sorax.game.worlds.Level;

public class GameScene implements Scene {

	private Level level;
	private EntityPlayer player;
	
	public GameScene() {
		this.level = new Level("test");
		this.player = level.getPlayer();
	}
	
	@Override
	public void render(Screen screen) {
		this.level.render(screen);
		screen.drawPart(10, 10, player.getLife(), player.getMaxLife(), Art.lifeFull, Art.lifeEmpty);
	}

	@Override
	public void update() {
		level.update();
	}

}
