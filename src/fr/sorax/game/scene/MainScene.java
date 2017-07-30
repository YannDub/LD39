package fr.sorax.game.scene;

import java.awt.event.KeyEvent;

import fr.sorax.game.Game;
import fr.sorax.game.gfx.Art;
import fr.sorax.game.gfx.Screen;

public class MainScene implements Scene {

	@Override
	public void render(Screen screen) {
		screen.drawImage(Game.WIDTH / 8 - Art.title.getWidth() / 2, Game.HEIGHT / 8 - Art.title.getHeight() / 2 - 30, Art.title);
		screen.drawImage(Game.WIDTH / 8 - Art.enter.getWidth() / 2, Game.HEIGHT / 8 - Art.enter.getHeight() / 2, Art.enter);
		screen.drawImage(-20, Game.HEIGHT / 4 - Art.move.getHeight() / 2 - 10, Art.move);
	}

	@Override
	public void update() {
		if(Game.INPUT.isKeyDown(KeyEvent.VK_ENTER))
			Game.goToScene(new GameScene());
	}

}
