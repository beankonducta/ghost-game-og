package com.patrick.ghosts.scenes;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.entities.environments.Chunk;
import com.patrick.ghosts.scenes.effects.Effect;
import com.patrick.ghosts.util.CameraTools;
import com.patrick.ghosts.util.Settings;

/**
 * Scene is the frame in which all objects are drawn/updated. 
 * Basically the viewing window.
 * 
 * @author lenovo
 *
 */
public abstract class Scene extends Canvas {

	private BufferStrategy strategy;

	/**
	 * Every single entity on the current scene.
	 * 
	 */
	private Entity[] entities;

	/**
	 * Every single effect on the current scene.
	 * 
	 */
	private Effect[] effects;

	/**
	 * Default constructor.
	 * 
	 */
	public Scene() {
		super();
	}

	/**
	 * Creates the BufferStrategy.
	 * 
	 */
	public void render() {
		createBufferStrategy(2);
		strategy = getBufferStrategy();
	}

	/**
	 * Sets the size of the panel.
	 * 
	 */
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
	}

	/**
	 * Paint to the scene. Only paints entities that are currently within the clipping box
	 * of the main window.
	 * 
	 */
	public void paint() {
		if(strategy != null) {
			Graphics2D g2d = (Graphics2D)strategy.getDrawGraphics();
			g2d.scale(Settings.VIEW_SCALE, Settings.VIEW_SCALE);
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, getWidth()+200, getHeight()+200);
			g2d.translate(CameraTools.CAMERA_X, CameraTools.CAMERA_Y);
			int total = 0;
			if(entities != null && entities.length > 0) {
				for(Entity e : entities) {
					if(e != null) {
						if(CameraTools.isInFrame(e, true)) {
							total++;
							e.draw(g2d);
						}
					}
				}
			}
			total = 0;
			strategy.show();
			Toolkit.getDefaultToolkit().sync();
			g2d.dispose();
		}
	}

	/**
	 * Update the scene.
	 *
	 */
	public void update() {

	}

	public Entity[] getEntities() {
		return entities;
	}

	public void setEntities(Entity[] ent) {
		this.entities = ent;
	}

	public Effect[] getEffects() {
		return effects;
	}

	public void setEffects(Effect[] effects) {
		this.effects = effects;
	}
}