package com.patrick.ghosts.actions;

import com.patrick.ghosts.entities.Entity;

public interface Action {
	
	public boolean start();
	
	public boolean stop();
	
	public void setEntities(Entity... entities);
	
	public void update();

}