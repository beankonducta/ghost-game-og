package com.patrick.ghosts;

import javax.swing.JFrame;

import com.patrick.ghosts.controller.ControllerManager;
import com.patrick.ghosts.scenes.ProceduralScene;
import com.patrick.ghosts.scenes.Scene;
import com.patrick.ghosts.util.Settings;
import com.patrick.ghosts.util.io.KeyboardIO;

/**
 * Window is the container which houses scenes (the frame essentially)
 * 
 * @author lenovo
 *
 */
public class Window {
	
	private JFrame frame;
	
	private Scene currentScene;
	
	private ControllerManager conManager;
	
	/**
	 * Default constructor.
	 * 
	 */
	public Window() {
		init(0);
		init(1);
	}
	
	/**
	 * Init the window in stages.
	 * 
	 * @param stage
	 */
	private void init(int stage) {
		if(stage == 0) {
			frame = new JFrame();
			frame.setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
			currentScene = new ProceduralScene();
			currentScene.setVisible(true);
			frame.add(currentScene);
			currentScene.render();
			currentScene.addKeyListener(new KeyboardIO());
		}
		if(stage == 1) {
			conManager = new ControllerManager(currentScene);
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Scene getCurrentScene() {
		return currentScene;
	}

	public void setCurrentScene(Scene currentScene) {
		this.currentScene = currentScene;
	}

}