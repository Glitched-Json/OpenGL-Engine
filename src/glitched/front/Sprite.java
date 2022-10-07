package glitched.front;

import glitched.engine.renderer.Renderer;
import glitched.front.abstracts.AbstractObject;

public class Sprite extends AbstractObject{
	
	public Sprite() {
		/*(setVertices(new float[]{-0.5f, -0.5f, 0f, 1f, 0f, 0f, 1f, 1f, 0f,
								-0.5f,  0.5f, 0f, 0f, 1f, 0f, 1f, 0f, 1f,
								 0.5f,  0.5f, 0f, 1f, 0f, 1f, 1f, 1f, 1f,
								 0.5f, -0.5f, 0f, 1f, 1f, 0f, 1f, 0f, 0f
		});*/
		setVertices(new float[] {-0.5f, -0.5f, 0f, -0.5f, 0.5f, 0f, 0.5f, 0.5f, 0f, 0.5f, -0.5f, 0f});
		setIndices(new int[]{0, 1, 2, 2, 3, 0});
		vao.build(vertices, indices);
	}
	
	public void update() {
		transform.getRotation().y+=2f;
	}
	
	public void render(Renderer renderer) {
		renderer.renderVao(vao, transform);
	}
}
