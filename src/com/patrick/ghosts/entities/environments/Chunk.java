package com.patrick.ghosts.entities.environments;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.scenes.effects.Effect;
import com.patrick.ghosts.util.BufferedImageTools;
import com.patrick.ghosts.util.Settings;

public class Chunk extends Entity {

	/**
	 * Chunk will hold a group of entities for improved rendering speed.
	 * 
	 */

	private Entity[] entities;

	public Chunk(Entity[] entities) {
		super();
		this.setEntities(entities);
		this.setAnimatable(false);
		this.setWidth((int)(this.entities[0].getWidth()) * Settings.CHUNK_SIZE);
		this.setHeight((int)(this.entities[0].getHeight()) * Settings.CHUNK_SIZE);
		this.setX(this.entities[0].getX());
		this.setY(this.entities[0].getY());
		this.setVisible(true);
	}



	@Override
	public String toString() {
		return "Chunk [entities=" + Arrays.toString(entities) + "]";
	}


	@Override
	public void update() {
		super.update();
		for(Entity e : entities) {
			if(e != null)
				e.update();
		}
		this.setSprite();
	}

	@Override
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		for(Entity e : entities) {
			if(e != null)
				e.draw(g2d);
		}
	}

	public void setSprite() {
		if(entities[0].getSprites() != null && this.getSprites() == null) {
			this.setSprites(new BufferedImage[1]);
			this.getSprites()[0] = BufferedImageTools.buildChunkSprite(this.entities, this.getWidth(), this.getHeight(), (int)this.getImageOpacity());
			this.setCurrentAnimationFrame(0);
		}
	}

	public Entity[] getEntities() {
		return entities;
	}

	public void setEntities(Entity[] entities) {
		this.entities = entities;
	}

}
