package com.patrick.ghosts.logic;

import com.patrick.ghosts.entities.Type.EnvironmentType;
import com.patrick.ghosts.entities.environments.Tile;
import com.patrick.ghosts.util.Settings;

public class MapLogic {

	public Tile[][] getMap(int width, int height) {
		Tile[][] tiles = new Tile[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				tiles[i][j] = new Tile(EnvironmentType.TILE, i*Settings.TILE_SIZE_DRAW, j*Settings.TILE_SIZE_DRAW, Settings.TILE_SIZE_DRAW, Settings.TILE_SIZE_DRAW, true, 0);
			}
		}

		return tiles;
	}
	
	public Tile[][] resetMap(Tile[][] tiles, int width, int height) {
		return tiles;
	}
}