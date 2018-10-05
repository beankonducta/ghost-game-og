package com.patrick.ghosts.controller;

import java.util.ArrayList;
import java.util.List;

import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.logic.CameraMoveLogic;
import com.patrick.ghosts.logic.LogicFactory;
import com.patrick.ghosts.models.Collision;
import com.patrick.ghosts.models.CollisionType;
import com.patrick.ghosts.scenes.Scene;
import com.patrick.ghosts.scenes.effects.Effect;
import com.patrick.ghosts.util.Settings;

public class SceneController implements Controller {

	private int lastSlotEntity;

	private int lastSlotEffect;

	private Controller[] controllers;

	private Scene scene;

	private CameraMoveLogic cameraLogic;

	public SceneController(Scene scene, Controller... controllers) {
		this.controllers = controllers;
		this.scene = scene;
		scene.setEntities(getEntities());
		scene.setEffects(getEffects());
		cameraLogic = LogicFactory.newCameraMoveLogic();
	}

	@Override
	public void run() {
		while(true) {
			if(scene != null) {
				update();
			}
			try {
				Thread.sleep(Settings.TICK_SPEED);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update() {
		scene.paint();
		for(Controller c : controllers) {
			c.update();
		}
	}

	@Override
	public Entity[] getEntities() {
		Entity[] entities = new Entity[Settings.MAX_ENTITIES];
		for(Controller c : controllers) {
			if(c != null) {
				if(c.getEntities() != null) {
					for(Entity e : c.getEntities()) {
						if(lastSlotEntity < entities.length) {
							entities[lastSlotEntity] = e;
							lastSlotEntity++;
						}
					}
				}
			}
		}
		return entities;
	}

	@Override
	public Effect[] getEffects() {
		Effect[] effects = new Effect[Settings.MAX_EFFECTS];
		for(Controller c : controllers) {
			if(c != null) {
				if(c.getEffects() != null) {
					for(Effect e : c.getEffects()) {
						if(lastSlotEffect < effects.length) {
							effects[lastSlotEffect] = e;
							lastSlotEffect++;
						}
					}
				}
			}
		}
		return effects;
	}
}