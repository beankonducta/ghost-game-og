package com.patrick.ghosts.controller;

import java.util.ArrayList;
import java.util.List;

import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.logic.CollisionLogic;
import com.patrick.ghosts.logic.LogicFactory;
import com.patrick.ghosts.models.Collision;
import com.patrick.ghosts.models.CollisionType;
import com.patrick.ghosts.scenes.effects.Effect;
import com.patrick.ghosts.util.Settings;
import com.patrick.ghosts.util.io.KeyboardIO;

public class CollisionController implements Controller {

	private Controller[] controllers;

	private Entity[] chunks;

	private Entity[] activeChunks;

	private Entity[] tiles;

	private Entity[] activeTiles;

	private Entity player;

	private Entity[] ghosts;

	private CollisionLogic collisionLogic;

	public static List<Collision> COLLISION_LIST = new ArrayList<Collision>();

	public CollisionController(Controller... controllers) {
		this.controllers = controllers;
		this.collisionLogic = LogicFactory.newCollisionLogic();
		initValues();
	}

	public static List<Collision> GET_COLLISIONS(CollisionType type) {
		List<Collision> rtrn = new ArrayList<Collision>();
		for(Collision c : COLLISION_LIST) {
			if(c.getType() == type) {
				rtrn.add(c);
			}
		}
		return rtrn;
	}

	private void initValues() {

		for(Controller c : this.controllers) {
			if(c instanceof PlayerController) {
				this.player = c.getEntities()[0];
			}
			if(c instanceof GhostController) {
				this.ghosts = c.getEntities();
			}
			if(c instanceof MapController) {
				this.chunks = c.getEntities();
			}
		}
	}

	private void updateTilePlayerCollisions() {
		for(Entity e : this.chunks) {
			if(e != null) {
				e.setCollide(false);
				for(Entity e1 : e.getEntities()) {
					if(e1 != null) {
						e1.setCollide(false);
					}
				}
			}
		}
		this.activeChunks = collisionLogic.getColliding(this.player, this.chunks);
		this.activeTiles = new Entity[(Settings.CHUNK_SIZE*Settings.CHUNK_SIZE)*4];
		this.tiles = new Entity[(Settings.CHUNK_SIZE*Settings.CHUNK_SIZE)*4];
		int slot = 0;
		for(Entity e : this.activeChunks) {
			for(Entity e1 : e.getEntities()) {
				this.tiles[slot] = e1;
				slot ++;
			}
		}
		this.activeTiles = collisionLogic.getColliding(this.player, this.tiles);
		for(Entity e : this.activeTiles) {
			COLLISION_LIST.add(new Collision(this.player, e, CollisionType.PLAYER_WALL, KeyboardIO.LAST_DIR));
		}
	}

	private void removeEmptyCollisions() {
		List<Collision> toRemove = new ArrayList<Collision>();
		for(Collision c : COLLISION_LIST) {
			if(c.isProcessed()) {
				toRemove.add(c);
			}
		}
		for(Collision c : toRemove) {
			COLLISION_LIST.remove(COLLISION_LIST.indexOf(c));
		}
	}


	@Override
	public void run() {
	}

	@Override
	public void update() {
		updateTilePlayerCollisions();
		removeEmptyCollisions();
	}

	@Override
	public Entity[] getEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Effect[] getEffects() {
		// TODO Auto-generated method stub
		return null;
	}
}