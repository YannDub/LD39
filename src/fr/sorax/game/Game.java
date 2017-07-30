package fr.sorax.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import fr.sorax.game.gfx.Screen;
import fr.sorax.game.scene.MainScene;
import fr.sorax.game.scene.Scene;
import fr.sorax.game.utils.Input;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1080;
	public static final int HEIGHT = WIDTH * 9 / 16;
	public static final int SCALE = 2;
	public static final Input INPUT = new Input();
	
	public static final String TITLE = "Battery";
	
	private JFrame window;
	private Thread thread;
	private BufferStrategy bs;
	
	private boolean running;
	
	private Screen screen;
	private static Scene scene;
	
	public Game() {
		this.screen = new Screen(WIDTH / SCALE, HEIGHT / SCALE);
		scene = new MainScene();
	}
	
	private void init() {
		Dimension dim = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(dim);
		this.setMaximumSize(dim);
		this.setMinimumSize(dim);
		
		window = new JFrame(TITLE);
		window.add(this);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
		
		this.addKeyListener(INPUT);
	}
	
	public synchronized void start() {
		this.thread = new Thread(this, "Screen");
		this.thread.start();
		this.running = true;
	}
	
	public synchronized void stop() {
		if(!running) return;
		running = false;
		
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.init();
		
		double ns = 1000000000.0 / 60.0;
		long lastTime = System.nanoTime();
		double delta = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				this.update();
				delta -= 1;
			}
			
			this.render();
		}
		
		System.exit(0);
	}
	
	private void render() {
		this.bs = this.getBufferStrategy();
		if(this.bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		this.screen.fill(0xff000000);
		scene.render(screen);
		
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, WIDTH / SCALE, HEIGHT / SCALE);
		
		g.drawImage(this.screen.getImage(), 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
		bs.show();
	}
	
	private void update() {
		scene.update();
	}
	
	public static void goToScene(Scene scene) {
		Game.scene = scene;
	}
	
	public static void main(String[] args) {
		new Game().start();
	}
}
