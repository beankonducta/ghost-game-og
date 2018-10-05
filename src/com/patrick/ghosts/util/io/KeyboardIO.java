package com.patrick.ghosts.util.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.patrick.ghosts.controller.Dir;

public class KeyboardIO implements KeyListener {

	public static boolean KEY_DOWN;
	
	public static boolean LEFT_DOWN;
	
	public static boolean RIGHT_DOWN;
	
	public static boolean UP_DOWN;
	
	public static boolean DOWN_DOWN;
	
	public static boolean REFRESH_DOWN;
	
	public static Dir LAST_DIR = Dir.DFLT; 

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			UP_DOWN = true;
			LAST_DIR = Dir.UP;
		}

		else if(e.getKeyCode() ==KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			DOWN_DOWN = true;
			LAST_DIR = Dir.DOWN;
		}

		else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			LEFT_DOWN = true;
			LAST_DIR = Dir.LEFT;
		}

		else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() ==KeyEvent.VK_D) {
			RIGHT_DOWN = true;
			LAST_DIR = Dir.RIGHT;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_R) {
			REFRESH_DOWN = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			UP_DOWN = false;
		}

		else if(e.getKeyCode() ==KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			DOWN_DOWN = false;
		}

		else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			LEFT_DOWN = false;
		}

		else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() ==KeyEvent.VK_D) {
			RIGHT_DOWN = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_R) {
			REFRESH_DOWN = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public static boolean noMoveKeysDown() {
		if(UP_DOWN || DOWN_DOWN || LEFT_DOWN || RIGHT_DOWN) {
			return false;
		} else {
			return true;
		}
	}
	
	public static Dir OPPOSITE_DIR(Dir dir) {
		if(dir == Dir.LEFT) return Dir.RIGHT;
		if(dir == Dir.RIGHT) return Dir.LEFT;
		if(dir == Dir.UP) return Dir.DOWN;
		if(dir == Dir.DOWN) return Dir.UP;
		return Dir.DFLT;
	}
}