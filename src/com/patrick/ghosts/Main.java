package com.patrick.ghosts;

public class Main implements Runnable {

	private Window window;

	public Main() {
		init(0);
	}

	private void init(int stage) {
		if(stage == 0) {
			window = new Window();
		}
	}

	public static void main(String [] args) {
		// performance settings.
		System.setProperty("sun.java2d.transaccel", "True");
		System.setProperty("sun.java2d.opengl", "True");
		System.setProperty("sun.java2d.d3d", "True");
		System.setProperty("sun.java2d.ddforcevram", "True");

		new Thread(new Main()).start();
	}

	@Override
	public void run() {

	}
}