package com.patrick.ghosts.logic;

import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.entities.environments.Chunk;
import com.patrick.ghosts.entities.environments.Tile;
import com.patrick.ghosts.util.Settings;

public class ChunkLogic {

	public Chunk[] makeChunks(Tile[][] mapTiles) {

		int arraySize = (mapTiles.length * mapTiles[0].length) / Settings.CHUNK_SIZE;
		Chunk[] chunkArray = new Chunk[arraySize];
		int width = (mapTiles.length / Settings.CHUNK_SIZE) + 1;
		int height = (mapTiles[0].length / Settings.CHUNK_SIZE) + 1;
		int slot = 0;
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				chunkArray[slot] = makeChunk(mapTiles, i * Settings.CHUNK_SIZE, j * Settings.CHUNK_SIZE, Settings.CHUNK_SIZE);
				slot ++;
			}
		}
		return chunkArray;
	}


	private Chunk makeChunk(Tile[][] mapTiles, int startX, int startY, int chunkSize) {
		Entity[] entities = new Entity[chunkSize * chunkSize];
		int slot = 0;
		for(int i = startX; i < startX + chunkSize; i++) {
			for(int j = startY; j < startY + chunkSize; j++) {
				if(i < mapTiles.length) {
					if(j < mapTiles[i].length) {
						if(mapTiles[i][j] != null) {
							entities[slot] = mapTiles[i][j];
							slot ++;
						}
					}
				}
			}
		}
		return new Chunk(entities);
	}
}