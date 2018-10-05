package com.patrick.ghosts.controller;

/**
 * Movement directions. Typesafe yo!
 * 
 * @author lenovo
 *
 */
public enum Dir { 
	
	UP(0f, 1f), DOWN(0f, -1f), LEFT(1f, 0f), RIGHT(-1f, 0f), DFLT(0f, 0f);
	
	private float x;
	private float y;
	
	private Dir(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
};