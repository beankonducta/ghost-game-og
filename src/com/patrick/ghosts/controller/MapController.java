package com.patrick.ghosts.controller;

import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.entities.environments.Chunk;
import com.patrick.ghosts.entities.environments.Environment;
import com.patrick.ghosts.entities.environments.Tile;
import com.patrick.ghosts.logic.ChunkLogic;
import com.patrick.ghosts.logic.LogicFactory;
import com.patrick.ghosts.logic.MapLogic;
import com.patrick.ghosts.scenes.effects.Effect;
import com.patrick.ghosts.util.io.KeyboardIO;

public class MapController implements EnvironmentController {

	private MapLogic mapLogic;
	
	private ChunkLogic chunkLogic;
	
	private Chunk[] chunks;

	private Tile[][] mapTiles;

	public MapController() {
		mapLogic = LogicFactory.newProceduralMapLogic();
		mapTiles = mapLogic.getMap(64, 64);
		chunkLogic = LogicFactory.newChunkLogic();
		chunks = chunkLogic.makeChunks(mapTiles);

	}

	@Override
	public void update() {
		if(KeyboardIO.REFRESH_DOWN) {
			System.out.println("UPDATING");
			mapTiles = mapLogic.resetMap(mapTiles, mapTiles.length, mapTiles[0].length);
			chunks = chunkLogic.makeChunks(mapTiles);
		}
		for(Chunk c : chunks) {
			if(c != null)
			c.update();
		}
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity[] getEntities() {
		return chunks;
	}

	@Override
	public void move(Environment environment, Dir dir) {
	}

	@Override
	public Effect[] getEffects() {
		// TODO Auto-generated method stub
		return null;
	}
}