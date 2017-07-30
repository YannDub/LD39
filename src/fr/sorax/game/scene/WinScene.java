package fr.sorax.game.scene;

import fr.sorax.game.Game;
import fr.sorax.game.gfx.Art;
import fr.sorax.game.gfx.Screen;

public class WinScene implements Scene {

	@Override
	public void render(Screen screen) {
		screen.drawImage(Game.WIDTH / 8 - Art.win.getWidth() / 2, Game.HEIGHT / 8 - Art.win.getHeight() / 2, Art.win);
	}

	@Override
	public void update() {
		
	}

}
