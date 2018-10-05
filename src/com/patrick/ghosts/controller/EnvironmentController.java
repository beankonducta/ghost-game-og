 package com.patrick.ghosts.controller;

import com.patrick.ghosts.entities.environments.Environment;

public interface EnvironmentController extends Controller {

	public void move(Environment environment, Dir dir);
}
