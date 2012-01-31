package main.directions;

import android.graphics.Point;

public class South implements Direction {

	private static South instance;

	private South() { }

	public static South getInstance() {
		if (instance == null) {
			instance = new South();
		}
		return instance;
	}
	
	@Override
	public Point newPosition(Point originalPosition) {
		return new Point(originalPosition.x, originalPosition.y + 1);
	}

}
