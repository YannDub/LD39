package fr.sorax.game.scene;

import java.awt.event.KeyEvent;

import fr.sorax.game.Game;
import fr.sorax.game.audio.Sound;
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
		Sound.music.loop();
	}
	
	@Override
	public void render(Screen screen) {
		this.level.render(screen);
		screen.drawPart(10, 10, player.getLife(), player.getMaxLife(), Art.lifeFull, Art.lifeEmpty);
		if(player.getLife() == 0)
			screen.drawImage(Game.WIDTH / 8 - Art.gameOver.getWidth() / 2, 0, Art.gameOver);
	}

	@Override
	public void update() {
		level.update();
		if(Game.INPUT.isKeyDown(KeyEvent.VK_ESCAPE))
			System.exit(0);
		if(player.getLife() == 0 && Game.INPUT.isKeyDown(KeyEvent.VK_ENTER))
			Game.goToScene(new GameScene());
	}

}
