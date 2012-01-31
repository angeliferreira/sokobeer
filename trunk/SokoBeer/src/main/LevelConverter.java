package main;

import java.util.List;

import main.gameElement.GameElement;
import main.gameElement.GameElementFactory;
import android.graphics.Point;

public class LevelConverter {

	public static Grid templateToGrid(List<String> templateList) {
		Grid grid = new Grid(new GameElement[10][10]);
		
		for (int i = 0; i < grid.getMatrix().length; i++) {
			for (int j = 0; j < grid.getMatrix().length; j++) {
				grid.setPositionWithValue(new Point(j, i), GameElementFactory.newInstance(templateList.get(i).charAt(j), new Point(j, i)));
			}
		}
		
		return grid;
	}

}
