package com.patrick.ghosts.entities.environments;

public class Cursor {

	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	private int x;
	private int y;

	private int max;
	private int min;

	public Cursor(int min, int max) {
		setMin(min);
		setMax(max);
	}

	public Cursor(int x, int y, int min, int max) {
		setX(x);
		setY(y);
		setMin(min);
		setMax(max);
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		if(x <= max && x >= min) {
			this.x = x;
		}
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		if(y <= max && y >= min) {
			this.y = y;
		}
	}

	public void moveTo(int x, int y) {
		setX(x);
		setY(y);
	}

	public void moveBy(int x, int y) {
		setX(getX()+x);
		setY(getY()+y);
	}

	public void moveByDirection(int dir) {
		switch(dir) {
		case LEFT:
			moveBy(-1, 0);
			break;
		case RIGHT:
			moveBy(1, 0);
			break;
		case UP:
			moveBy(0, -1);
			break;
		case DOWN:
			moveBy(0, 1);
			break;
		}
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}
}