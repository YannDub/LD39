package fr.sorax.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Art {
	
	public static final Bitmap lifeEmpty = load("/gui/lifeEmpty.png");
	public static final Bitmap lifeFull = load("/gui/lifeFull.png");
	public static final Bitmap gameOver = load("/gui/gameOver.png");
	public static final Bitmap title = load("/gui/title.png");
	public static final Bitmap enter = load("/gui/enter.png");
	public static final Bitmap move = load("/gui/move.png");
	public static final Bitmap win = load("/gui/win.png");
	
	public static final Bitmap[][] player = cut("/entities/player.png", 16, 16);
	public static final Bitmap[][] tiles = cut("/levels/tiles.png", 16, 16);
	
	private static Bitmap load(String path) {
		try {
			BufferedImage image = ImageIO.read(Art.class.getResourceAsStream(path));
			int width = image.getWidth();
			int height = image.getHeight();
			Bitmap result = new Bitmap(width, height);
			image.getRGB(0, 0, width, height, result.pixels, 0, width);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static Bitmap[][] cut(String path, int xSize, int ySize) {
		try {
			BufferedImage image = ImageIO.read(Art.class.getResourceAsStream(path));
			int w = image.getWidth();
			int h = image.getHeight();
			int xTile = w / xSize;
			int yTile = h / ySize;
			
			Bitmap[][] results = new Bitmap[xTile][yTile];
			for(int i = 0; i < xTile; i++) {
				for(int j = 0; j < yTile; j++) {
					Bitmap bitmap = new Bitmap(xSize, ySize);
					image.getRGB(i * xSize, j * ySize, xSize, ySize, bitmap.pixels, 0, xSize);
					results[i][j] = bitmap;
				}
			}
			
			return results;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
