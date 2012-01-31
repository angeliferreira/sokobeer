package main.gameElement;

import android.graphics.Point;

public class Block extends GameElement {

	public Block(Point position) {
		this.position = position;
		this.singleName = 'B';
	}
	
	public boolean isBlock() {
		return true;
	}

}
