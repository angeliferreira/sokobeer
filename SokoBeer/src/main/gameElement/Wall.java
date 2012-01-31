package main.gameElement;

import android.graphics.Point;

public class Wall extends GameElement {

	public Wall(Point position) {
		this.position = position;
		this.singleName = 'W';
	}
	
	public boolean isWall() {
		return true;
	}
	
}
