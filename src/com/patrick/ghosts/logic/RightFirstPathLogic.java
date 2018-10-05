package com.patrick.ghosts.logic;

import java.util.ArrayList;
import java.util.List;

import com.patrick.ghosts.entities.dummies.Dummy;
import com.patrick.ghosts.entities.environments.Cursor;
import com.patrick.ghosts.entities.environments.Tile;
import com.patrick.ghosts.util.MathTools;
import com.patrick.ghosts.util.Settings;
import com.patrick.ghosts.util.TileTools;

public class RightFirstPathLogic extends PathLogic {

	@Override
	public void findPath(Tile[][] tiles, Dummy dummy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void findShortestPath(Tile[][] tiles, Dummy dummy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void carveWalkablePath(Tile[][] tiles) {
		try{
			Cursor cursor = new Cursor(0, tiles.length-1);
			List<Tile> walkableTiles = new ArrayList<Tile>();
			List<Tile> tileSearchQue = new ArrayList<Tile>();
			TileTools.fillListOfType(tiles, walkableTiles, Tile.FLOOR);

			boolean inRoom = false;
			cursor.moveTo(1, 1);
			while(walkableTiles.size() > 10) {
				while(!walkableTiles.contains(tiles[cursor.getX()][cursor.getY()])) {
					randomizeCursor(cursor, tiles);
				}
				inRoom = true;
				while(inRoom) {
					hasNeighbor(tiles, cursor, tileSearchQue, Tile.FLOOR, true);
					recursiveTileSearch(tiles, cursor, tileSearchQue, walkableTiles);
					inRoom = false;
				}
				tileSearchQue.clear();
				randomizeCursor(cursor, tiles);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void markTile(List<Tile> walkableTiles, Tile[][] tiles, Cursor cursor) {
		if(walkableTiles != null) {
			walkableTiles.remove(tiles[cursor.getX()][cursor.getY()]);
		}
		tiles[cursor.getX()][cursor.getY()].setFlagged(true);
	}

	private void randomizeCursor(Cursor cursor, Tile[][] tiles) {
		cursor.moveTo(MathTools.randomInt(0, tiles[1].length), MathTools.randomInt(0, tiles[1].length));
	}

	private void recursiveTileSearch(Tile[][] tiles, Cursor cursor, List<Tile> searchQue, List<Tile> walkableTiles) {
		try {
			boolean moreToFind = false;
			List<Tile> newQue = new ArrayList<Tile>();
			for(int i = 0; i < tiles.length; i++) {
				for(int j = 0; j < tiles[i].length; j++) {
					if(searchQue.contains(tiles[i][j]) && walkableTiles.contains(tiles[i][j])) {
						cursor.moveTo(i, j);
						markTile(walkableTiles, tiles, cursor);
						if(hasNeighbor(tiles, cursor, newQue, Tile.FLOOR, true)) {
							moreToFind = true;
							break;
						}
					}
				}
			}
			if(!searchQue.containsAll(newQue)) {
				searchQue.addAll(newQue);
				newQue.clear();
			}
			if(moreToFind) {
				recursiveTileSearch(tiles, cursor, searchQue, walkableTiles);
			} else {
				connectRooms(tiles, cursor, searchQue, walkableTiles);
				TileTools.convertTileRadius(tiles, Tile.WALL);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private boolean connectRooms(Tile[][] tiles, Cursor cursor, List<Tile> searchQue, List<Tile> walkableTiles) {
		boolean rtrn = false;
		List<Tile> edgeList = new ArrayList<Tile>();
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				if(searchQue.contains(tiles[i][j])) {
					cursor.moveTo(i, j);
					markTile(null, tiles, cursor);
					if(hasNeighbor(tiles, cursor, edgeList, Tile.WALL, true)) {
						System.out.println(edgeList.size());
						for(Tile tile : edgeList) {
							if(hasNeighbor(tiles, cursor, edgeList, Tile.WALL, false)) {
								tile.setTileID(Tile.FLOOR);
								rtrn =  true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean hasMultipleNeighbor(Tile[][] tiles, Cursor cursor, List<Tile> searchQue, boolean add, int... tileID) {
		if(tileID.length > 2) {
			
		}
		return false;
	}

	private boolean hasNeighbor(Tile[][] tiles, Cursor cursor, List<Tile> searchQue, int tileID, boolean add) {
		boolean rtrn = false;
		cursor.moveBy(1, 0);
		if(tiles[cursor.getX()][cursor.getY()].getTileID() == tileID && !tiles[cursor.getX()][cursor.getY()].isFlagged()) {
			rtrn = true;
			if(add) {
				searchQue.add(tiles[cursor.getX()][cursor.getY()]);
			}
		}			
		cursor.moveBy(0, -1);
		if(tiles[cursor.getX()][cursor.getY()].getTileID() == tileID && !tiles[cursor.getX()][cursor.getY()].isFlagged()) {
			rtrn = true;
			if(add) {
				searchQue.add(tiles[cursor.getX()][cursor.getY()]);
			}
		}
		cursor.moveBy(-1, 0);
		if(tiles[cursor.getX()][cursor.getY()].getTileID() == tileID && !tiles[cursor.getX()][cursor.getY()].isFlagged()) {
			rtrn = true;
			if(add) {
				searchQue.add(tiles[cursor.getX()][cursor.getY()]);
			}
		}
		cursor.moveBy(0, 1);
		if(tiles[cursor.getX()][cursor.getY()].getTileID() == tileID && !tiles[cursor.getX()][cursor.getY()].isFlagged()) {
			rtrn = true;
			if(add) {
				searchQue.add(tiles[cursor.getX()][cursor.getY()]);
			}
		}
		return rtrn;
	}
}