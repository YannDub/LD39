package fr.sorax.game.physics;

public class AABB {
	
	private Box b1, b2;
	
	public AABB(Box b1, Box b2) {
		this.b1 = b1;
		this.b2 = b2;
	}
	
	public boolean intersect() {
		int x1 = b1.getX();
		int y1 = b1.getY();
		int x2 = b1.getWidth() + x1;
		int y2 = b1.getHeight() + y1;
		
		int xx1 = b2.getX();
		int yy1 = b2.getY();
		int xx2 = b2.getWidth() + xx1;
		int yy2 = b2.getHeight() + yy1;
		
		return (x1 >= xx1 && x1 <= xx2 || x2 >= xx1 && x2 <= xx2) && (y1 >= yy1 && y1 <= yy2 || y2 >= yy1 && y2 <= yy2);
	}
}