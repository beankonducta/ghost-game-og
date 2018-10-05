package com.patrick.ghosts.entities.dummies;

import com.patrick.ghosts.entities.Type.DummyType;
import com.patrick.ghosts.util.CameraTools;

/**
 * It's the player!
 * 
 * @author lenovo
 *
 */
public class Player extends Dummy {
	
	private float lastCameraX;
	
	private float lastCameraY;

	/**
	 * Required instantiation by Dummy class.
	 * Set up some basic player stats here.
	 * 
	 * @param type
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param visible
	 */
	public Player(float x, float y, int width, int height, boolean visible) {
		super(DummyType.PLAYER, x, y, width, height, visible);
		setHealth(100);
		setBaseAttackPower(1.0f);
		setBaseDefense(1.0f);
		setMoveSpeed(1.0f);
	}
	
	public void update() {
		super.update();
		setX(getX()-CameraTools.CAMERA_X+lastCameraX);
		setY(getY()-CameraTools.CAMERA_Y+lastCameraY);
		lastCameraX = CameraTools.CAMERA_X;
		lastCameraY = CameraTools.CAMERA_Y;
	}
}