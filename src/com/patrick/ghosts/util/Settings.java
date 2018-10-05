package com.patrick.ghosts.util;

public class Settings {
	
	public static final int WINDOW_WIDTH = 1200;
	
	public static final int WINDOW_HEIGHT = 800;
	
	public static boolean DEBUG = false;
	
	public static int DIFFICULTY = 1;
	
	public static int CURRENT_LEVEL = 1;
	
	public static float IMAGE_SCALE = 2f;
	
	public static int TILE_SIZE = 32;
	
	public static int TILE_SIZE_DRAW = (int) (TILE_SIZE * IMAGE_SCALE);
	
	public static int MAX_ENTITIES = 100000;
	
	public static int MAX_EFFECTS = 1000;

	public static final int TICK_SPEED = 8;
	
	public static float PLAYER_SPEED = 4.5f;
	
	public static int MAX_MAX_ROOM_SIZE = 32;
	
	public static int MAX_ROOM_SIZE = 7;
	
	public static int MIN_MIN_ROOM_SIZE = 4;
	
	public static int MIN_ROOM_SIZE = 4;
	
	public static int MAX_ROOM_COUNT = 128;
	
	public static int MAX_ROOM_ATTEMPTS = 500;
	
	public static int REQUIRED_DISTANCE = 4;
	
	public static float VIEW_SCALE = 1f;
	
	public static float VIEW_X = WINDOW_WIDTH / VIEW_SCALE;
	
	public static float VIEW_Y = WINDOW_HEIGHT / VIEW_SCALE;
	
	public static float FUEL_USAGE_RATE = .01f;
	
	public static float PLAYER_LIGHT_RADIUS = TILE_SIZE * 4.0f;
	
	public static int MAX_EFFECTS_PER_ENTITY = 10;
	
	public static int CHUNK_SIZE = 7;

}