package com.example.testbuttons2;
//Node for a Linked List of Colors
public class ColorNode {
	private int color;
	public ColorNode next;
	
	public ColorNode(int color) {
		this(color, null);
	}
	
	public ColorNode(int color, ColorNode next) {
		this.color = color;
		this.next = next;
	}
	public ColorNode(ColorNode node) {
		this(node.color, node.next);
	}
	
	public int getColor() {
		return color;
	}
	
	public boolean hasNext() {
		return next != null;
	}
	

	
}
