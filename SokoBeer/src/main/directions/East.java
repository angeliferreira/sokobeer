package main.directions;

import android.graphics.Point;

public class East implements Direction {

	private static East instance;

	private East() { }

	public static East getInstance() {
		if (instance == null) {
			instance = new East();
		}
		return instance;
	}
	
	@Override
	public Point newPosition(Point originalPosition) {
		return new Point(originalPosition.x + 1, originalPosition.y);
	}

}
