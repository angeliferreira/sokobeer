package main.gameElement;

import android.graphics.Point;

public class GameElementFactory {
	
	public static GameElement newInstance(char elementSingleName, Point position) {
		
		if (elementSingleName == 'H') return new Hero(position);
		
		if (elementSingleName == 'W') return new Wall(position);
		
		if (elementSingleName == 'B') return new Block(position);
		
		if (elementSingleName == 'X') return new Target(position);
		
		if (elementSingleName == ' ') return new DumbElement(position);
		
		return null;
	}

}
