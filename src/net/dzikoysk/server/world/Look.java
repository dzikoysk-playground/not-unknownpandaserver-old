package net.dzikoysk.server.world;

public class Look {

	private float yaw, pitch;
	
	public Look(float yaw, float pitch){
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	public float getYaw(){
		return this.yaw;
	}
	
	public float getPitch(){
		return this.pitch;
	}
	
}
