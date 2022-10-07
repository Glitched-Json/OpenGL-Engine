package glitched.engine.entity;

import glitched.engine.utilities.Vector3f;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Camera {
	private Vector3f position;
	private float yaw, pitch, roll;
	
	public Camera() {
		this.position = new Vector3f(0f, 0f, 0f);
		this.pitch = 0;
		this.yaw = 0;
		this.roll = 0;
	}
}
