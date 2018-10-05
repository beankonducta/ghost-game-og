package com.patrick.ghosts.logic;

import com.patrick.ghosts.entities.dummies.Dummy;
import com.patrick.ghosts.entities.environments.Tile;

public abstract class PathLogic {
	
	public static final int UP = 0;
	
	public static final int DOWN = 1;
	
	public static final int LEFT = 2;
	
	public static final int RIGHT = 3;
	
	public abstract void findPath(Tile[][] tiles, Dummy dummy);
	
	public abstract void findShortestPath(Tile[][] tiles, Dummy dummy);
	
	public abstract void carveWalkablePath(Tile[][] tiles);
}
