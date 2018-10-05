package com.patrick.ghosts.entities.dummies;

import com.patrick.ghosts.controller.Dir;
import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.entities.Type.DummyType;
import com.patrick.ghosts.entities.Type.EntityType;

/**
 * Dummy is any Entity which is either a Player or NPC.
 * 
 * @author lenovo
 *
 */
public abstract class Dummy extends Entity {
	
	private float baseAttackPower;
	
	private float baseDefense;
	
	private float health;
	
	private boolean moveable;
	
	/**
	 * The required Dummy instantiation.
	 * 
	 * @param type
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param visible
	 */
	public Dummy(DummyType type, float x, float y, int width, int height, boolean visible) {
		super(x, y, visible);
		setType(type.name());
		setWidth(width);
		setHeight(height);
		setEntityType(EntityType.DUMMY);
		setInteractable(true);
		setCollidable(true);
		setAnimatable(true);
		setDestroyable(true);
		setMoveable(true);
	}

	public float getBaseAttackPower() {
		return baseAttackPower;
	}

	public void setBaseAttackPower(float baseAttackPower) {
		this.baseAttackPower = baseAttackPower;
	}

	public float getBaseDefense() {
		return baseDefense;
	}

	public void setBaseDefense(float baseDefense) {
		this.baseDefense = baseDefense;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}
}