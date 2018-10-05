package com.patrick.ghosts.logic;

import com.patrick.ghosts.controller.Dir;
import com.patrick.ghosts.util.CameraTools;

public class CameraMoveLogic {
	
	public void moveCamera(Dir direction, float speed) {
		CameraTools.LAST_CAMERA_X = CameraTools.CAMERA_X;
		CameraTools.LAST_CAMERA_Y = CameraTools.CAMERA_Y;
		CameraTools.CAMERA_X += direction.getX() * speed;
		CameraTools.CAMERA_Y += direction.getY() * speed;
	}
	
	public void debounceCamera() {
		CameraTools.CAMERA_X = CameraTools.LAST_CAMERA_X;
		CameraTools.CAMERA_Y = CameraTools.LAST_CAMERA_Y;
	}
}