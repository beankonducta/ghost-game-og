package com.patrick.ghosts.logic;

import java.util.ArrayList;
import java.util.List;

import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.entities.dummies.Dummy;
import com.patrick.ghosts.entities.environments.Chunk;
import com.patrick.ghosts.models.Collision;
import com.patrick.ghosts.models.CollisionType;
import com.patrick.ghosts.util.CameraTools;
import com.patrick.ghosts.util.io.KeyboardIO;

public class CollisionLogic {

	/**
	 * Returns the entity(s) that the 'collider' is colliding with.
	 * 
	 * @param collider
	 * @param collidable
	 * @return
	 */
	public Entity[] getColliding(Entity collider, Entity... collidable) {
		List<Entity> rtrn = new ArrayList<Entity>();
		for(Entity e : collidable) {
			if(e != null) {
				if(e.getBounds().intersects(collider.getBounds())) {
					if((e.isCollidable() || e instanceof Chunk) && collider.isCollidable()) {
						e.setCollide(true);
						collider.setCollide(true);
						rtrn.add(e);
					}
				} else {
					e.setCollide(false);
				}
			}
		}
		return rtrn.toArray(new Entity[rtrn.size()]);
	}

	/**
	 * Returns whether or not the entity is colliding.
	 * 
	 * @param collider
	 * @param collidable
	 * @return
	 */
	public boolean isColliding(Entity collider, Entity... collidable) {
		for(Entity e : collidable) {
			if(e.getBounds().intersects(collider.getBounds())) {
				if(e.isCollidable() && collider.isCollidable())
					return true;
			}
		}
		return false;
	}
}