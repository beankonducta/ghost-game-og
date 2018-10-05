package com.patrick.ghosts.controller;

import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.entities.dummies.Dummy;
import com.patrick.ghosts.logic.CameraMoveLogic;
import com.patrick.ghosts.logic.LogicFactory;
import com.patrick.ghosts.logic.PlayerSpawnLogic;
import com.patrick.ghosts.models.Collision;
import com.patrick.ghosts.models.CollisionType;
import com.patrick.ghosts.scenes.effects.Effect;
import com.patrick.ghosts.scenes.effects.Light;
import com.patrick.ghosts.util.Settings;
import com.patrick.ghosts.util.io.KeyboardIO;

/**
 * An implementation of the DummyController interface - creates the framework
 * to adjust and update the player.
 * 
 * @author lenovo
 *
 */
public class PlayerController implements DummyController {

	private Dummy player;

	private Light playerLight;

	private PlayerSpawnLogic playerSpawnLogic;

	private CameraMoveLogic cameraMoveLogic;

	public static final int TICK_SPEED = 10;

	/**
	 * Default constructor, must construct the player
	 * outside of this class.
	 * 
	 * @param player
	 */
	public PlayerController() {
		playerSpawnLogic = LogicFactory.newPlayerSpawnLogic();
		player = playerSpawnLogic.spawnNewPlayer(Settings.DIFFICULTY);
		cameraMoveLogic = LogicFactory.newCameraMoveLogic();
		playerLight = Light.newLight(player.getX(), player.getY(), Settings.PLAYER_LIGHT_RADIUS, true);
		player.addEffect(playerLight);
	}

	/**
	 * Timely tasks here.
	 * 
	 */
	@Override
	public void update() {
		processCollision();
		if(!player.isDestroyed()) {
			if(!KeyboardIO.noMoveKeysDown()) {
				if(processCollision()) {
					move(player, null);
				}
			} else {
				player.setAnimate(false);
			}
			player.update();
		}
		playerLight.setX(player.getX());
		playerLight.setY(player.getY());
	}

	/**
	 * Run!
	 * 
	 */
	@Override
	public void run() {
		while(true) {
			update();
			try {
				Thread.sleep(TICK_SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void move(Dummy dummy, Dir dir) {
		dummy.setAnimate(true);
			cameraMoveLogic.moveCamera(KeyboardIO.LAST_DIR, Settings.PLAYER_SPEED);
	}

	@Override
	public Entity[] getEntities() {
		Entity[] rtrn =  { player };
		return rtrn;
	}

	@Override
	public Effect[] getEffects() {
		return player.getEffects();
	}

	public boolean processCollision() {
		if(!CollisionController.GET_COLLISIONS(CollisionType.PLAYER_WALL).isEmpty()) {
			for(Collision c : CollisionController.GET_COLLISIONS(CollisionType.PLAYER_WALL)) {
				cameraMoveLogic.debounceCamera();
				c.setProcessed(true);
			} 
			return false;
		} 
		return true;
	}
}