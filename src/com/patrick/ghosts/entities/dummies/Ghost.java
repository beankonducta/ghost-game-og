package com.patrick.ghosts.entities.dummies;

import com.patrick.ghosts.entities.Type.DummyType;

/**
 * It's the Ghost! The main enemy in the game.
 * 
 * @author lenovo
 *
 */
public class Ghost extends Dummy {

	/**
	 * Required instantiation by Dummy.
	 * Set up some basic stats for Ghost.
	 * 
	 * @param type
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param visible
	 */
	public Ghost(float x, float y, int width, int height, boolean visible) {
		super(DummyType.GHOST, x, y, width, height, visible);
		setHealth(20);
		setBaseAttackPower(.2f);
		setBaseDefense(.2f);
		setMoveSpeed(.2f);
	}
	
	public static Ghost[] NewGhosts(int count) {
		return new Ghost[count];
	}
}