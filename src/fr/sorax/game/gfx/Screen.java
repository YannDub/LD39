package fr.sorax.game.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import fr.sorax.game.utils.Constants;
import fr.sorax.game.worlds.tiles.Tile;

public class Screen extends Bitmap{
	
	private BufferedImage image;
	private int xOffset = 0, yOffset = 0;
	
	public Screen(int width, int height) {
		super(width, height);
	
		this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = -xOffset;
		this.yOffset = -yOffset;
	}
	
	public void drawBitmap(int x, int y, Bitmap bitmap) {
		super.drawBitmap(x + xOffset, y + yOffset, bitmap);
	}
	
	public void drawTile(int x, int y, Bitmap bitmap) {
		int xTile = x * Constants.TILESIZE;
		int yTile = y * Constants.TILESIZE;
		
		this.drawBitmap(xTile, yTile, bitmap);
	}
	
	public void drawPart(int x, int y, float current, float max, Bitmap b1, Bitmap b2) {
		int w = (int) (b1.width * current / max);
		
		for(int j = 0; j < b1.height; j++) {
			int yPos = y + j;
			for(int i = 0; i < w; i++) {
				if(b1.pixels[i + j * b1.width] != 0xffFF00DC)
					this.pixels[(x + i) + yPos * width] = b1.pixels[i + j * b1.width];
			}
			for(int i = w; i < b2.width; i++) {
				if(b2.pixels[i + j * b2.width] != 0xffFF00DC)
					this.pixels[(x + i) + yPos * width] = b2.pixels[i + j * b2.width];
			}
		}
	}
	
	public BufferedImage getImage() {
		return this.image;
	}

}
