package com.patrick.ghosts.util;

import com.patrick.ghosts.entities.Entity;

public class CameraTools {

	public static float CAMERA_X = 0;

	public static float CAMERA_Y = 0;
	
	public static float LAST_CAMERA_X = 0;
	
	public static float LAST_CAMERA_Y = 0;

	public static boolean isInFrame(Entity e, boolean checkOpacity) {
		if(e.getX() + e.getWidth() < Settings.VIEW_X+Settings.TILE_SIZE_DRAW-CameraTools.CAMERA_X && e.getX() + e.getWidth() > -10-CameraTools.CAMERA_X &&
				e.getY() < Settings.VIEW_Y+Settings.TILE_SIZE_DRAW-CameraTools.CAMERA_Y && e.getY() + e.getHeight() > -10-CameraTools.CAMERA_Y) {
			if(checkOpacity) {
				if(e.getImageOpacity() > 0) {
					return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}
}