package com.patrick.ghosts.models;

import java.awt.Rectangle;

import com.patrick.ghosts.controller.Dir;
import com.patrick.ghosts.entities.Entity;

public class Collision {
	
	private Entity firstEntity;
	
	private Entity secondEntity;
	
	private CollisionType type;
	
	private Dir direction;
	
	private boolean processed;
	
	public Collision(Entity firstEntity, Entity secondEntity, CollisionType type, Dir dir) {
		this.setFirstEntity(firstEntity);
		this.setSecondEntity(secondEntity);
		this.setType(type);
		this.setProcessed(false);
		this.setDirection(dir);
	}

	public Entity getFirstEntity() {
		return firstEntity;
	}

	public void setFirstEntity(Entity firstEntity) {
		this.firstEntity = firstEntity;
	}

	public Entity getSecondEntity() {
		return secondEntity;
	}

	public void setSecondEntity(Entity secondEntity) {
		this.secondEntity = secondEntity;
	}

	public CollisionType getType() {
		return type;
	}

	public void setType(CollisionType type) {
		this.type = type;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
	public void process() {
		this.processed = true;
	}

	public Dir getDirection() {
		return direction;
	}

	public void setDirection(Dir direction) {
		this.direction = direction;
	}
}