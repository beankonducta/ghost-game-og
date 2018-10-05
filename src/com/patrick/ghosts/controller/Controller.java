package com.patrick.ghosts.controller;

import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.scenes.effects.Effect;

public interface Controller extends Runnable {
	
	public void update();
	
	public Entity[] getEntities();
	
	public Effect[] getEffects();

}
