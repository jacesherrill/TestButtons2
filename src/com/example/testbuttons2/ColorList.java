package com.example.testbuttons2;
//LinkedList of Colors
public class ColorList {
	public ColorNode front;
	
	public ColorList(ColorNode node) {
		front = node;
	}
	
	public ColorList() {
		this(null);
	}
	
	//adds the node to the end of the list
	public void add(ColorNode node) {
		if (front == null) {
			front = node;
		} else {
			ColorNode current = front;
			while(current.hasNext()) {
				current = current.next;
			}
			current.next = node;
		}
	}
	
	public void add(int color) {
		add(new ColorNode(color));
	}
	
	public int size() {
		if (front != null) {
			int size = 1;
			ColorNode current = front;
			while(front.hasNext()) {
				current = current.next;
				size++;
			}
			return size;
		} 
		return 0;			
	}
	
	
	// removes front node and returns
	public ColorNode remove() {
		if(front != null) {
			ColorNode temp = new ColorNode(front);
			front = front.next;
			temp.next = null;
			return temp;
		} 
		return null;
	}
	
	// returns, but does not remove, front node
	public ColorNode peek() {
		return front;
	}
}
