package glitched.engine.utilities;

import glitched.engine.entity.Camera;
import glitched.engine.entity.Transform;

public class MatrixUtils {
	
	public Matrix4f createTransformation(Transform transform) {
		Matrix4f transformationMatrix = new Matrix4f();
		transformationMatrix.setIdentity();
		transformationMatrix = transformationMatrix.multiply(Matrix4f.translate(transform.getPosition().x, transform.getPosition().y, transform.getPosition().z));
		transformationMatrix = transformationMatrix.multiply(Matrix4f.rotate(transform.getRotation().x, 1, 0, 0));
		transformationMatrix = transformationMatrix.multiply(Matrix4f.rotate(transform.getRotation().y, 0, 1, 0));
		transformationMatrix = transformationMatrix.multiply(Matrix4f.rotate(transform.getRotation().z, 0, 0, 1));
		transformationMatrix = transformationMatrix.multiply(Matrix4f.scale(transform.getScale().x, transform.getScale().y, transform.getScale().z));
		return transformationMatrix;
	}
	
	public Matrix4f createCameraMatrix(Camera camera) {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Vector3f position = camera.getPosition();
		Vector3f cameraPosition = position.scale(-1f);
		Matrix4f.rotate(camera.getPitch(), new Vector3f(1f, 0f, 0f), viewMatrix, viewMatrix);
		Matrix4f.rotate(-camera.getYaw(),  new Vector3f(0f, 1f, 0f), viewMatrix, viewMatrix);
		Matrix4f.rotate(camera.getRoll(),  new Vector3f(0f, 0f, 1f), viewMatrix, viewMatrix);
		Matrix4f.translate(cameraPosition, viewMatrix, viewMatrix);
		return viewMatrix;
	}
	
	public Matrix4f createProjectionMatrix(float FOV, float NEAR_PLANE, float FAR_PLANE) {
		Matrix4f projectionMatrix = new Matrix4f();
		projectionMatrix.setIdentity();
		float aspectRatio = (float)  1280 / (float) 720; //TODO: get width/height from variables
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
		return projectionMatrix;
	}
}
