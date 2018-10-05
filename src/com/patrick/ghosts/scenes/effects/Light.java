package com.patrick.ghosts.scenes.effects;

import java.awt.Graphics2D;

import com.patrick.ghosts.util.Settings;

public class Light extends Effect {

	public static final int NO_FUEL = -1;
	
	private float fuel;
	
	private boolean active;
	
	public Light(float x, float y, float radius, boolean temporary) {
		super(x, y, radius);
		setMaxDuration(-1f);
		setActive(true);
		if(!temporary) {
			setFuel(NO_FUEL);
		} else {
			setFuel(100000); // need to set to actual value of fuel which the light contains
		}
	}
	
	/**
	 * Spawn a new light.
	 * 
	 * @param x
	 * @param y
	 * @param height
	 * @param width
	 * @param temporary
	 * @return
	 */
	public static Light newLight(float x, float y, float radius, boolean temporary) {
		return new Light(x, y, radius, temporary);
	}

	@Override
	public void update() {
		if(fuel == 0) {
			setActive(false);
			setOpacity(0f);
		}
		if(fuel > 0f && fuel != NO_FUEL) {
			fuel -= Settings.FUEL_USAGE_RATE;
		}
	}

	@Override
	public void paint(Graphics2D g2d) {
	}

	public float getFuel() {
		return fuel;
	}

	public void setFuel(float fuel) {
		this.fuel = fuel;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}