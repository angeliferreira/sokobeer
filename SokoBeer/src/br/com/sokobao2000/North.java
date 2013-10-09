package br.com.sokobao2000;

import android.graphics.Point;


public class North implements Direction {

	private static North instance;

	private North() { }

	public static North getInstance() {
		if (instance == null)
			instance = new North();

		return instance;
	}
	
	@Override
	public Point newPosition(Point originalPosition) {
		return new Point(originalPosition.x, originalPosition.y - 1);
	}

}
