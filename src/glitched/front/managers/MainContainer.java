package glitched.front.managers;

import glitched.engine.renderer.Renderer;
import glitched.engine.renderer.Window;

public class MainContainer implements Runnable {
	Window window;
	Renderer renderer;
	MainManager manager;
	private final double UPDATE_CAP = 1./60.;
	private final double E = 1000000000.;
	public int fps;
	private Thread thread;
	
	public void initialize() {
		manager = new MainManager();
		window = new Window(1280, 720, "Title Here");
		renderer = new Renderer();
		thread = new Thread(this);
		manager.initialize();
		thread.run();
	}
	
	public void run() {
		boolean render = false;
		double firstTime=0, lastTime=System.nanoTime()/E, passedTime=0, unprocessedTime=0, frameTime = 0;
		int frames = 0;
		
		while (!window.shouldClose()) {
			render = false;
			firstTime = System.nanoTime()/E;
			passedTime = firstTime-lastTime;
			lastTime = firstTime;
			unprocessedTime+=passedTime;
			frameTime+=passedTime;
			frames++;
			
			while (unprocessedTime>=UPDATE_CAP) {
				unprocessedTime-=UPDATE_CAP;
				render=true;
				manager.update(this, (float)UPDATE_CAP);
				if (frameTime>=1.) {
					frameTime=0;
					fps=frames;
					frames=0;
				}
			}
			
			if (render) {
				manager.render(this, renderer);
				window.updateWindow();
				render=false;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		window.closeWindow();
	}
	
	public static void main(String[] args) {
		MainContainer manager = new MainContainer();
		manager.initialize();
	}
}
