package fr.sorax.game.scene;

import fr.sorax.game.gfx.Screen;

public interface Scene {
	
	public void render(Screen screen);
	
	public void update();
}
