package main.gameElement;

import android.graphics.Point;

public class DumbElement extends GameElement {

	public DumbElement(Point position) {
		this.position = position;
		this.singleName = ' ';
	}
	
	public boolean isDumbElement() {
		return true;
	}
	
}
