package glitched.front.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import glitched.engine.renderer.Renderer;
import glitched.front.abstracts.AbstractObject;

public class ObjectManager {
	List<AbstractObject> objects = new ArrayList<>();
	
	public void update() {
		objects = objects.stream()
				.map(x -> {x.update(); return x;})
				.collect(Collectors.toList());
	}
	
	public void render(Renderer renderer) {
		objects = objects.stream()
				.map(x -> {x.render(renderer); return x;})
				.collect(Collectors.toList());
	}
	
	public void instanceCreate(AbstractObject obj) {objects.add(obj);}
	public void instanceDestroy(AbstractObject obj) {objects.remove(obj);}
	public void instanceDestroyAll() {objects.clear();}
}
