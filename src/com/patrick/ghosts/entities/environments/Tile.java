package com.patrick.ghosts.entities.environments;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;

import com.patrick.ghosts.entities.Type.EnvironmentType;
import com.patrick.ghosts.util.Settings;

/**
 * Tile is an extension of Entity Environment which provides data 
 * to draw a floor tile to the map.
 * 
 * @author lenovo
 *
 */
public class Tile extends Environment {

	private int tileID;

	public static final int FLOOR = 0;

	public static final int WALL = 1;

	/**
	 * Required instantiation. tileID dictates which 'frame' of the bufferedImage array to display
	 * (in this case each frame is just a unique tile because tiles aren't animatable).
	 * 
	 * @param type
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param visible
	 * @param tileID
	 */
	public Tile(EnvironmentType type, float x, float y, int width, int height, boolean visible, int tileID) {
		super(type, x, y, width, height, visible);
		this.tileID = tileID;
		this.setAnimatable(false);
		setMoveSpeed(1.0f);
		setWalkabilityData();
	}

	@Override
	public String toString() {
		return "Tile [tileID=" + tileID + "]" + super.toString();
	}

	/**
	 * Constructor which forces a certain sprite file to be used.
	 * 
	 * @param type
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param visible
	 * @param tileID
	 * @param sprites
	 */
	public Tile(EnvironmentType type, float x, float y, int width, int height, boolean visible, int tileID, File sprites) {
		super(type, x, y, width, height, visible);
		this.tileID = tileID;
		this.setAnimatable(false);
		setMoveSpeed(1.0f);
		setWalkabilityData();
	}

	/**
	 * Simple switch to set parameters for each tileID.
	 * 
	 */
	private void setWalkabilityData() {
		setFlagged(false);
		switch(tileID) {
		case FLOOR:
			setCollidable(false);
			setInteractable(false);
			setCurrentAnimationFrame(0);
			setAnimatable(false);
			break;
		case WALL:
			setCollidable(true);
			setInteractable(false);
			setCurrentAnimationFrame(1);
			setAnimatable(false);
			break;
		}
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public void draw(Graphics2D g2d) {
		if(Settings.DEBUG) {
			if(this.isCollidable()) {
				g2d.setColor(Color.RED);
				g2d.drawString("X", (int)this.getX(), (int)this.getY());
			}
		}
		if(this.isCollide()) {
			g2d.setColor(new Color(1, 0, 0, .25f));
			g2d.fillRect(this.getBounds().x, this.getBounds().y, this.getBounds().width, this.getBounds().height);
		}
	}


	public int getTileID() {
		return tileID;
	}

	public void setTileID(int tileID) {
		this.tileID = tileID;
		setWalkabilityData();
	}

	public static Tile duplicate(Tile t) {
		try {
			return (Tile) t.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}