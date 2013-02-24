package com.example.testbuttons2;

public class Coordinates {
	private int x;
	private int y;
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean equals(Object o) {
		if(o instanceof Coordinates) {
			
			return ((Coordinates) o).getX() == this.getX() && ((Coordinates) o).getY() == this.getY();
		}
		return false;
	}
}
