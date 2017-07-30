package fr.sorax.game.gfx;

import java.util.Arrays;

public class Bitmap {
	
	protected int width, height;
	protected int[] pixels;
	
	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
	}
	
	public void fill(int color) {
		Arrays.fill(this.pixels, color);
	}
	
	public void draw(int xO, int yO, int w, int h, int color) {
		for(int j = 0; j < h; j++) {
			for(int i = 0; i < w; i++) {
				int x = xO + i;
				int y = yO + j;
				
				if(x < 0) continue;
				if(y < 0) break;
				if(x >= width) break;
				if(y >= height) break;
				
				this.pixels[x + y * width] = color;
			}
		}
	}
	
	public void drawBitmap(int xO, int yO, Bitmap bitmap) {
		for(int j = 0; j < bitmap.height; j++) {
			for(int i = 0; i < bitmap.width; i++) {
				int x = xO + i;
				int y = yO + j;
				
				if(x < 0) continue;
				if(y < 0) break;
				if(x >= width / 2 + 10) break;
				if(y >= height / 2 + 10) break;
				
				if(bitmap.pixels[i + j * bitmap.width] != 0xffFF00DC)
					this.pixels[x + y * width] = bitmap.pixels[i + j * bitmap.width];
			}
		}
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
}
