package com.patrick.ghosts.entities.props;

import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.entities.Type.EntityType;
import com.patrick.ghosts.entities.Type.PropType;

/**
 * Prop is any Entity which can be picked up from the ground.
 * 
 * @author lenovo
 *
 */
public abstract class Prop extends Entity {
	
	/**
	 * The required Prop instantiation.
	 * 
	 * @param type
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param visible
	 */
	public Prop(PropType type, float x, float y, int width, int height, boolean visible) {
		super(x, y, visible);
		setType(type.name());
		setWidth(width);
		setHeight(height);
		setEntityType(EntityType.PROP);
	}
}
