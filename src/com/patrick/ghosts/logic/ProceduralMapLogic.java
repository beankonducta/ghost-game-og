package com.patrick.ghosts.logic;

import java.util.ArrayList;
import java.util.List;

import com.patrick.ghosts.entities.Type.EnvironmentType;
import com.patrick.ghosts.entities.environments.Tile;
import com.patrick.ghosts.util.MathTools;
import com.patrick.ghosts.util.Settings;
import com.patrick.ghosts.util.TileTools;

public class ProceduralMapLogic extends MapLogic {
	
	private PathLogic pathLogic;
	
	public ProceduralMapLogic() {
		pathLogic = LogicFactory.newPathLogic();
	}

	@Override
	public Tile[][] getMap(int width, int height) {
		Tile[][] tiles = new Tile[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				tiles[i][j] = new Tile(EnvironmentType.TILE, i*Settings.TILE_SIZE_DRAW, j*Settings.TILE_SIZE_DRAW, 
						Settings.TILE_SIZE_DRAW, Settings.TILE_SIZE_DRAW, true, Tile.WALL);
			}
		}
		tiles = makeRooms(tiles, width, height);
		tiles = makePaths(tiles, width, height);
		return tiles;
	}
	
	@Override
	public Tile[][] resetMap(Tile[][] tiles, int width, int height) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				tiles[i][j].setTileID(Tile.WALL);
			}
		}
		tiles = makeRooms(tiles, width, height);
		tiles = makePaths(tiles, width, height);
		return tiles;
	}

	public Tile[][] makeRooms(Tile[][] tiles, int width, int height) {
		List<Tile> usableTileList = new ArrayList<Tile>();
		int oldRoomSize = Settings.MAX_ROOM_SIZE;
		int currentRooms = 0;
		int currentAttempts = 0;
		/*
		 * Let's first make the outer wall for the map.
		 * 
		 */
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				usableTileList.add(tiles[i][j]);
				if(i == 0 || i == width-1) {
					TileTools.convertTile(tiles[i][j], Tile.WALL, false);
					usableTileList.remove(tiles[i][j]);
				}
				if(j == 0 || j == height-1) {
					TileTools.convertTile(tiles[i][j], Tile.WALL, false);
					usableTileList.remove(tiles[i][j]);
				}
			}
		}
		/*
		 * Next let's place the inner walls.
		 * 
		 */
		while(currentRooms < Settings.MAX_ROOM_COUNT && currentAttempts < Settings.MAX_ROOM_ATTEMPTS) {
			currentAttempts++;
			Tile[][] temp = attemptRoom(tiles, usableTileList, width, height);
			if(temp != null) {
				tiles = temp;
				currentRooms++;
				if(Settings.MAX_ROOM_SIZE - 1 > Settings.MIN_ROOM_SIZE) {
					Settings.MAX_ROOM_SIZE --;
				}
				currentAttempts = 0;
			}
		}
		Settings.MAX_ROOM_SIZE = oldRoomSize;
		return tiles;
	}

	public Tile[][] attemptRoom(Tile[][] tiles, List<Tile> usableTiles, int width, int height) {
		int w = 0;
		int h = 0;
		int x = MathTools.randomInt(0, width);
		int y = MathTools.randomInt(0, height);
		int currentAttempts = 0;
		/*
		 * Reroll these values.
		 * 
		 */
		while(w < Settings.MIN_ROOM_SIZE || h < Settings.MIN_ROOM_SIZE) {
			w = MathTools.randomInt(Settings.MIN_ROOM_SIZE, Settings.MAX_ROOM_SIZE);
			h = MathTools.randomInt(Settings.MIN_ROOM_SIZE, Settings.MAX_ROOM_SIZE);
		}
		while(!usableTiles.contains(tiles[x][y]) && usableTiles.size() > 1) {
			x = MathTools.randomInt(0, width);
			y = MathTools.randomInt(0, height);
		}
		while(currentAttempts < Settings.MAX_ROOM_ATTEMPTS) {
			currentAttempts++;
			if(x+w < width || y+h < height) {
				if(TileTools.containsAll(x, y, w, h, tiles, usableTiles)) {
					break;
				}
			}
		}

		/*
		 * No match. Return null.
		 * 
		 */
		if(currentAttempts >= Settings.MAX_ROOM_ATTEMPTS) {
			return null;
		}
		/*
		 * If we've made it this far we have a match!
		 * 
		 */
		for(int i = x; i < x+w; i++) {
			for(int j = y; j < y+h; j++) {
				//if(i == x || i == x+w-1 || j == y || j == y+h-1) {
					TileTools.convertTile(tiles[i][j], Tile.FLOOR, false);
				//}
				usableTiles.remove(tiles[i][j]);
			}
		}
		return tiles;
	}
	
	public Tile[][] makePaths(Tile[][] tiles, int width, int height) {
		pathLogic.carveWalkablePath(tiles);
		return tiles;
	}
}