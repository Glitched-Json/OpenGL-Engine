package glitched.engine.utilities;
//Credit to LWJGL 2 Util

import java.nio.FloatBuffer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Vector3f {
  @Getter @Setter
  public float x, y, z;
  
  public Vector3f() {
      super();
  }
  
  public Vector3f(Vector3f src) {
      set(src);
  }
  
  public Vector3f(float x, float y, float z) {
      set(x, y, z);
  }
  
  public void set(float x, float y) {
      this.x = x;
      this.y = y;
  }
  
  public void set(float x, float y, float z) {
      this.x = x;
      this.y = y;
      this.z = z;
  }
  
  public Vector3f set(Vector3f src) {
      x = src.getX();
      y = src.getY();
      z = src.getZ();
      return this;
  }
  
  public float lengthSquared() {
      return x * x + y * y + z * z;
  }
  
  public Vector3f translate(float x, float y, float z) {
      this.x += x;
      this.y += y;
      this.z += z;
      return this;
  }
  
  public static Vector3f add(Vector3f left, Vector3f right, Vector3f dest) {
      if (dest == null)
          return new Vector3f(left.x + right.x, left.y + right.y, left.z + right.z);
      else {
          dest.set(left.x + right.x, left.y + right.y, left.z + right.z);
          return dest;
      }
  }
  
  public static Vector3f sub(Vector3f left, Vector3f right, Vector3f dest) {
      if (dest == null)
          return new Vector3f(left.x - right.x, left.y - right.y, left.z - right.z);
      else {
          dest.set(left.x - right.x, left.y - right.y, left.z - right.z);
          return dest;
      }
  }
  
  public static Vector3f cross(
          Vector3f left,
          Vector3f right,
          Vector3f dest)
  {

      if (dest == null)
          dest = new Vector3f();

      dest.set(
              left.y * right.z - left.z * right.y,
              right.x * left.z - right.z * left.x,
              left.x * right.y - left.y * right.x
              );

      return dest;
  }
  
  public Vector3f negate() {
      x = -x;
      y = -y;
      z = -z;
      return this;
  }
  
  public Vector3f negate(Vector3f dest) {
      if (dest == null)
          dest = new Vector3f();
      dest.x = -x;
      dest.y = -y;
      dest.z = -z;
      return dest;
  }
  
  public Vector3f normalise(Vector3f dest) {
      float l = length();

      if (dest == null)
          dest = new Vector3f(x / l, y / l, z / l);
      else
          dest.set(x / l, y / l, z / l);

      return dest;
  }
  
  public float length() {
      return (float) Math.sqrt(lengthSquared());
  }
  
  public Vector3f normalize() {
      float length = length();
      return divide(length);
  }
  
  public Vector3f add(Vector3f other) {
      float x = this.x + other.x;
      float y = this.y + other.y;
      float z = this.z + other.z;
      return new Vector3f(x, y, z);
  }
  
  public Vector3f subtract(Vector3f other) {
      return this.add(other.negate());
  }
  
  public Vector3f divide(float scalar) {
      return scale(1f / scalar);
  }
  
  public float dot(Vector3f other) {
      return this.x * other.x + this.y * other.y + this.z * other.z;
  }
  
  public Vector3f cross(Vector3f other) {
      float x = this.y * other.z - this.z * other.y;
      float y = this.z * other.x - this.x * other.z;
      float z = this.x * other.y - this.y * other.x;
      return new Vector3f(x, y, z);
  }
  
  public static float dot(Vector3f left, Vector3f right) {
      return left.x * right.x + left.y * right.y + left.z * right.z;
  }
  
  public static float angle(Vector3f a, Vector3f b) {
      float dls = dot(a, b) / (a.length() * b.length());
      if (dls < -1f)
          dls = -1f;
      else if (dls > 1.0f)
          dls = 1.0f;
      return (float)Math.acos(dls);
  }
  
  public Vector3f load(FloatBuffer buf) {
      x = buf.get();
      y = buf.get();
      z = buf.get();
      return this;
  }
  
  public Vector3f scale(float scale) {

      x *= scale;
      y *= scale;
      z *= scale;

      return this;

  }
  
  public Vector3f store(FloatBuffer buf) {

      buf.put(x);
      buf.put(y);
      buf.put(z);

      return this;
  }
  
}