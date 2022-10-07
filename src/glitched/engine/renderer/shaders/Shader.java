package glitched.engine.renderer.shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import glitched.engine.renderer.shaders.uniforms.Uniform;

public class Shader {
	
	private int programID;
	private int vertexShader, fragmentShader;
	
	public Shader(String vertFile, String fragFile) {
		vertexShader = loadShader(vertFile, GL20.GL_VERTEX_SHADER);
		fragmentShader = loadShader(fragFile, GL20.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexShader);
		GL20.glAttachShader(programID, fragmentShader);
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
	}
	
	public void locateUniform(Uniform uniform) {
		uniform.getUniformLocation(programID);
	}
	
	public void useProgram() {
		GL20.glUseProgram(programID);
	}
	
	public void stopProgram() {
		GL20.glUseProgram(0);
	}
	
	public void delete() {
		GL20.glDeleteProgram(programID);
	}
	
	private static int loadShader(String file, int type) {
		StringBuilder builder = new StringBuilder();
		
		try {
			InputStream in = Shader.class.getResourceAsStream(file);
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line).append("//\n");
			}
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, builder);
		GL20.glCompileShader(shaderID);
		
		if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println(GL20.glGetShaderInfoLog(shaderID, 1000));
			System.exit(-1);
		}
		
		return shaderID;
	}
}
