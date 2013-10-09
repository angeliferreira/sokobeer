package br.com.sokobao2000;

import android.graphics.Point;


public class West implements Direction {

	private static West instance;

	private West() { }

	public static West getInstance() {
		if (instance == null)
			instance = new West();

		return instance;
	}
	
	@Override
	public Point newPosition(Point originalPosition) {
		return new Point(originalPosition.x - 1, originalPosition.y);
	}

}
