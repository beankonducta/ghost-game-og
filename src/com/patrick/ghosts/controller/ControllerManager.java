package com.patrick.ghosts.controller;

import com.patrick.ghosts.scenes.Scene;
import com.patrick.ghosts.util.Settings;

public class ControllerManager {

	private DummyController ghostController;

	private DummyController playerController;

	private EnvironmentController mapController;

	private PropController itemController;

	private SceneController sceneController;

	private CollisionController collisionController;

	public ControllerManager(Scene scene) {
		ghostController = new GhostController();
		playerController = new PlayerController();
		mapController = new MapController();
		itemController = new ItemController();
		collisionController = new CollisionController(mapController, ghostController, playerController, itemController);
		sceneController = new SceneController(scene, mapController, ghostController, playerController, itemController);
		new Thread() { 
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted()) {
					if(scene != null) {
						sceneController.update();
						collisionController.update();
					}
					try {
						Thread.sleep(Settings.TICK_SPEED);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}