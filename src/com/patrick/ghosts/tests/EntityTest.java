package com.patrick.ghosts.tests;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.patrick.ghosts.entities.Entity;
import com.patrick.ghosts.entities.Type.DummyType;
import com.patrick.ghosts.entities.dummies.Dummy;
import com.patrick.ghosts.entities.dummies.Player;

public class EntityTest extends JPanel implements Runnable {

	Entity e1;

	public EntityTest(int w, int h) {
		super();
		setSize(w, h);
		init(0);
		init(1);
		repaint();
	}

	private void init(int stage) {
		if(stage == 0) {
			JFrame frame = new JFrame();
			frame.setSize(getWidth(), getHeight());
			frame.add(this);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		if(stage == 1) {
			e1 = new Player(300.0f, 300.0f, 32, 32, true);
			e1.setAnimatable(true);
			e1.setAnimate(true);

		}
	}

	protected void update() {
		e1.update();
		e1.setX(e1.getX()+.1f);
		e1.setY(e1.getY()+.1f);
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(e1 != null) {
			e1.draw(g2d);
		}
	}

	public static void main(String[] args) {
		EntityTest e = new EntityTest(600, 600);
		new Thread(e).start();
	}

	@Override
	public void run() {
		while(true) {
			update();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}