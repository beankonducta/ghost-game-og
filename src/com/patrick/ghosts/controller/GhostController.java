package com.patrick.ghosts.controller;

import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.entities.dummies.Dummy;
import com.patrick.ghosts.logic.GhostMoveLogic;
import com.patrick.ghosts.logic.GhostSpawnLogic;
import com.patrick.ghosts.logic.LogicFactory;
import com.patrick.ghosts.scenes.effects.Effect;
import com.patrick.ghosts.util.Settings;

/**
 * An implementation of the DummyController interface. Provides update and 
 * move methods for the array of ghosts on the screen.
 * 
 * @author lenovo
 *
 */
public class GhostController implements DummyController {

	private Dummy[] ghosts;

	private GhostMoveLogic ghostMoveLogic;

	private GhostSpawnLogic ghostSpawnLogic;

	public static final int TICK_SPEED = 10;

	/**
	 * Default constructor. Array of ghosts must be created outside of
	 * this class.
	 * 
	 * @param ghosts
	 */
	public GhostController() {
		ghostMoveLogic = LogicFactory.newGhostMoveLogic();
		ghostSpawnLogic = LogicFactory.newGhostSpawnLogic();
		ghosts = ghostSpawnLogic.spawnNewGhosts(Settings.DIFFICULTY + Settings.CURRENT_LEVEL);
	}

	/**
	 * Perform timely tasks.
	 * 
	 */
	@Override
	public void update() {
		for(Dummy ghost : ghosts) {
			if(ghost != null) {
				ghost.update();
			}
		}
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

	/**
	 * Move the ghosts via the GhostMoveLogic class.
	 * 
	 */
	@Override
	public void move(Dummy dummy, Dir dir) {
		ghostMoveLogic.moveGhost(dummy, dir);
	}

	@Override
	public Entity[] getEntities() {
		return ghosts;
	}

	@Override
	public Effect[] getEffects() {
		// TODO Auto-generated method stub
		return null;
	}

	public void processCollision() {
		// TODO Auto-generated method stub
		
	}
}