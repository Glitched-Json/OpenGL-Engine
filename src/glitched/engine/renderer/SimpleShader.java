package glitched.engine.renderer;

import glitched.engine.renderer.shaders.Shader;
import glitched.engine.renderer.shaders.uniforms.UniformMatrix4f;
import lombok.Getter;

@Getter
public class SimpleShader extends Shader {
	protected UniformMatrix4f transformation = new UniformMatrix4f("transformation");
	protected UniformMatrix4f view = new UniformMatrix4f("view");
	protected UniformMatrix4f projection = new UniformMatrix4f("projection");
	
	public SimpleShader() {
		super("/shaders/vert.glsl", "/shaders/frag.glsl");
		super.locateUniform(transformation);
		super.locateUniform(view);
		super.locateUniform(projection);
	}

}
