package com.patrick.ghosts.util;

import java.util.List;

import com.patrick.ghosts.entities.environments.Tile;

public class TileTools {

	public static void convertTiles(List<Tile> tiles, int tileType) {
		for(Tile tile : tiles) {
			if(tile != null) {
				tile.setTileID(tileType);
			}
		}
	}

	public static void convertTile(Tile tile, int tileType, boolean random) {
		if(tile != null) {
			if(!random) {
				tile.setTileID(tileType);
			} else {
				if(MathTools.randomInt(14) != 1) {
					tile.setTileID(tileType);
				}
			}
		}
	}

	public static void convertTileRadius(Tile[][] tiles, int tileID) {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				if(i == 0 || i == tiles.length-1) {
					convertTile(tiles[i][j], tileID, false);
				}
				if(j == 0 || j == tiles[i].length-1) {
					convertTile(tiles[i][j], tileID, false);
				}
			}
		}
	}

	public static void fillListOfType(Tile[][] tiles, List<Tile> list, int tileID) {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				if(tiles[i][j].getTileID() == tileID) {
					list.add(tiles[i][j]);
				}
			}
		}
	}

	public static boolean enoughSpaceForRoom(List<Tile> tiles) {
		return tiles.size() > Settings.MIN_ROOM_SIZE*Settings.MIN_ROOM_SIZE;
	}

	public static boolean containsAll(int x, int y, int width, int height, Tile[][] tiles, List<Tile> usableTiles) {
		for(int i = x; i < x+width; i++) {
			for(int j = y; j < y+height; j++) {
				if(!usableTiles.contains(tiles[i][j])) {
					return false;
				}
			}
		}
		return true;
	}
}