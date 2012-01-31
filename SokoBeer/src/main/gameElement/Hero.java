package main.gameElement;

import android.graphics.Point;

public class Hero extends GameElement {

	public Hero(Point position) {
		this.position = position;
		this.singleName = 'H';
	}
	
	public boolean isHero() {
		return true;
	}
	
}
