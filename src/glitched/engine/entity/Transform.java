package glitched.engine.entity;

import glitched.engine.utilities.Vector3f;
import lombok.Getter;
import lombok.Setter;

public class Transform {
	@Getter @Setter
	private Vector3f position, rotation, scale;

	public Transform(Vector3f position, Vector3f rotation, Vector3f scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	
}
