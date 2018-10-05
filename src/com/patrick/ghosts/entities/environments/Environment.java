package com.patrick.ghosts.entities.environments;

import java.io.File;

import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.entities.Type.EntityType;
import com.patrick.ghosts.entities.Type.EnvironmentType;

/**
 * Environment is any Entity which is directly involved in creating
 * the background of a Scene.
 * 
 * @author lenovo
 *
 */
public abstract class Environment extends Entity {
	
	/**
	 * The required Environment instantiation.
	 * 
	 * @param type
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param visible
	 */
	public Environment(EnvironmentType type, float x, float y, int width, int height, boolean visible) {
		super(x, y, visible);
		setType(type.name());
		setWidth(width);
		setHeight(height);
		setEntityType(EntityType.ENVIRONMENT);
	}
	
	/**
	 * Constructor which forces a specific spritesheet to be used.
	 * 
	 * @param type
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param visible
	 * @param sprites
	 */
	public Environment(EnvironmentType type, float x, float y, int width, int height, boolean visible, File sprites) {
		super(x, y, visible, sprites);
		setType(type.name());
		setWidth(width);
		setHeight(height);
		setEntityType(EntityType.ENVIRONMENT);
	}
}