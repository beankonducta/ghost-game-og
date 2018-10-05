package com.patrick.ghosts.scenes.effects;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.patrick.ghosts.util.Settings;

public abstract class Effect {

	private float x;

	private float y;

	private float radius;

	private float opacity;

	private float maxDuration;

	private float currentDuration;

	private boolean animate;
	
	private Rectangle bounds;

	public Effect(float x, float y, float radius) {
		setX(x);
		setY(y);
		setRadius(radius);
	}

	public abstract void update();

	public abstract void paint(Graphics2D g2d);

	public void start() {
		setAnimate(true);
	}

	public void stop() {
		setAnimate(false);
	}

	public int getTileXLocation() {
		return (int) (getX()/Settings.TILE_SIZE_DRAW);
	}

	public int getTileYLocation() {
		return (int) (getY()/Settings.TILE_SIZE_DRAW);
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

	public float getOpacity() {
		return opacity;
	}

	public void setOpacity(float opacity) {
		if(opacity < 0.0f) {
			opacity = 0.0f;
		}
		if(opacity > 1.0f) {
			opacity = 1.0f;
		}
		this.opacity = opacity;
	}

	public float getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(float maxDuration) {
		this.maxDuration = maxDuration;
	}

	public float getCurrentDuration() {
		return currentDuration;
	}

	public void setCurrentDuration(float currentDuration) {
		this.currentDuration = currentDuration;
	}

	public boolean isAnimate() {
		return animate;
	}

	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public Rectangle getBounds() {
		if(bounds == null) {
			bounds = new Rectangle((int)this.getX(), (int)this.getY(), (int)this.getRadius(), (int)this.getRadius());
		}
		
		bounds.setBounds((int)this.getX(), (int)this.getY(), (int)this.getRadius(), (int)this.getRadius());
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
}