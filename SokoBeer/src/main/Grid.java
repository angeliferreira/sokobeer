package main;

import main.directions.Direction;
import main.gameElement.GameElement;
import android.graphics.Point;

public class Grid {

	private GameElement[][] matrix;
		
	public Grid(GameElement[][] level) {
		this.matrix = level;
	}
	
	public boolean isDesiredPositionAWall(Point desiredPosition) {
		return (this.getElement(desiredPosition).isWall());
	}

	public boolean isDesiredPositionABlock(Point desiredPosition) {
		return (this.getElement(desiredPosition).isBlock());
	}

	public boolean isDesiredPositionABlockOrWall(Point desiredPosition) {
		return (isDesiredPositionABlock(desiredPosition) || isDesiredPositionAWall(desiredPosition));
	}
	
	public void setPositionWithValue(Point position, GameElement gameElement) {
		this.matrix[position.y][position.x] = gameElement;
		gameElement.setPosition(position);
	}
	
	public Point getHeroPosition() {
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix.length; j++) {
				if (this.matrix[i][j].isHero()) return new Point(j, i);
			}
		}
		return null;		
	}

	public GameElement[][] getMatrix() {
		return matrix;
	}
	
	public GameElement getElement(Point position) {
		return this.matrix[position.y][position.x];
	}
	
	public GameElement getElement(int x, int y) {
		return this.matrix[x][y];
	}
	
	public void move(Point position, Direction direction) {
		Point desiredPosition = direction.newPosition(position); 
		if (isDesiredPositionAWall(desiredPosition)) return;
		if (isDesiredPositionABlock(desiredPosition)) {
			Point blockDesiredPosition = direction.newPosition(desiredPosition); 
			if (isDesiredPositionABlockOrWall(blockDesiredPosition)) return;
			this.changePositions(desiredPosition, blockDesiredPosition);
		}
		this.changePositions(position, desiredPosition);
	}
	
	public void changePositions(Point oldPosition, Point newPosition) {
		GameElement elementOldPosition = this.getElement(oldPosition);
				
		this.setPositionWithValue(oldPosition, this.getElement(newPosition));
		this.setPositionWithValue(newPosition, elementOldPosition);
	}

}
