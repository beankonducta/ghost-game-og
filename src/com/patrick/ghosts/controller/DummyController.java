package com.patrick.ghosts.controller;

import com.patrick.ghosts.entities.dummies.Dummy;

/**
 * Extension of basic Controller class which adds the Move method.
 * 
 * @author lenovo
 *
 */
public interface DummyController extends Controller {
	
	public void move(Dummy dummy, Dir dir);

}
