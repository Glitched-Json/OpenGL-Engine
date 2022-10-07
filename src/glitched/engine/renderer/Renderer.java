package glitched.engine.renderer;

import org.lwjgl.opengl.GL11;

import glitched.engine.entity.Camera;
import glitched.engine.entity.Transform;
import glitched.engine.models.Vao;
import glitched.engine.utilities.Matrix4f;
import glitched.engine.utilities.MatrixUtils;

public class Renderer {
	
	private SimpleShader shader;
	private MatrixUtils utils;
	private Camera camera;
	private float FOV = 90f, NEAR = 0.1f, FAR = 1000f;
	
	public Renderer() {
		shader = new SimpleShader();
		utils = new MatrixUtils();
		camera = new Camera();
	}
	
	private void prepare() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(0.05f, 0.05f, 0.05f, 1f);
	}
	
	public void renderVao(Vao vao, Transform transform) {
		prepare();
		shader.useProgram();
		Matrix4f transformation = utils.createTransformation(transform);
		shader.getTransformation().loadMatrix(transformation);
		Matrix4f view = utils.createCameraMatrix(camera);
		shader.getView().loadMatrix(view);
		Matrix4f projection = utils.createProjectionMatrix(FOV, NEAR, FAR);
		shader.projection.loadMatrix(projection);
		
		vao.render();
		shader.stopProgram();
	}
}
