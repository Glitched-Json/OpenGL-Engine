package glitched.engine.renderer.shaders.uniforms;

import org.lwjgl.opengl.GL20;

import lombok.Getter;

public class Uniform {
	private String variable;
	@Getter
	private int sharedLocation;
	
	public Uniform(String variable) {
		this.variable = variable;
	}
	
	public void getUniformLocation(int programID) {
		sharedLocation = GL20.glGetUniformLocation(programID, variable);
	}
}
