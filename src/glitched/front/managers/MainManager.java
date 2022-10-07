package glitched.front.managers;

import glitched.engine.renderer.Renderer;
import glitched.front.Sprite;

public class MainManager {
	ObjectManager objManager;
	
	public MainManager() {
		objManager = new ObjectManager();
	}
	
	public void initialize() {
		objManager.instanceCreate(new Sprite());
	}
	
	public void update(MainContainer mc, float dt) {
		objManager.update();
		//System.out.println(mc.fps+"");
	}
	
	public void render(MainContainer mc, Renderer renderer) {
		objManager.render(renderer);
	}
}
