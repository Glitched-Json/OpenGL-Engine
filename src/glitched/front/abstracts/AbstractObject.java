package glitched.front.abstracts;

import glitched.engine.entity.Transform;
import glitched.engine.models.Vao;
import glitched.engine.renderer.Renderer;
import glitched.engine.utilities.Vector3f;
import lombok.Setter;

@Setter
public abstract class AbstractObject {
	protected float[] vertices = {};
	protected int[] indices = {};
	protected Transform transform = new Transform(new Vector3f(0f, 0f, 0f), new Vector3f(0, 0, 0), new Vector3f(1f, 1f, 1f));
	protected Vao vao = new Vao();
	
	public void update() {}
	public void render(Renderer renderer) {}
	
	public AbstractObject setPosition(Vector3f pos) {transform.setPosition(pos); return this;}
	public AbstractObject setRotation(Vector3f rotation) {transform.setRotation(rotation); return this;}
	public AbstractObject setScale(Vector3f scale) {transform.setScale(scale); return this;}
}
