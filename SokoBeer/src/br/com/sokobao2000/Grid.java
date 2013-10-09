package br.com.sokobao2000;

import static br.com.sokobao2000.GameElement.StringRepresentation.BLOCK;
import static br.com.sokobao2000.GameElement.StringRepresentation.HERO;
import static br.com.sokobao2000.GameElement.StringRepresentation.TARGET_WITH_HERO;
import static br.com.sokobao2000.GameElement.StringRepresentation.WALL;
import junit.framework.Assert;
import android.graphics.Point;

public class Grid {

	private Cell[][] grid;
		
	public Grid(Cell[][] level) {
		grid = level;
	}
	
	private boolean isDesiredPositionAWall(Point desiredPosition) {
		return getElement(desiredPosition).toString().equals(WALL.represent());
	}

	private boolean isDesiredPositionABlock(Point desiredPosition) {
		return getElement(desiredPosition).toString().equals(BLOCK.represent());
	}

	private boolean isDesiredPositionABlockOrWall(Point desiredPosition) {
		return isDesiredPositionABlock(desiredPosition) || isDesiredPositionAWall(desiredPosition);
	}
	
	public Cell getCellWithHero() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j].toString().equals(HERO.represent()) || grid[i][j].toString().equals(TARGET_WITH_HERO.represent()))
					return grid[i][j];
			}
		}
		
		Assert.fail("Ops, no cell with Hero found here! :(");
		return null;		
	}

	public Cell[][] getGrid() {
		return grid;
	}
	
	public GameElement getElement(Point position) {
		return getCell(position).getSecondaryElement();
	}
	
	void setElement(GameElement gameElement, Point position) {
		getCell(position).setSecondaryElement(gameElement);
	}

	public Cell getCell(Point position) {
		return grid[position.y][position.x];
	}
	
	public Cell getCell(int x, int y) {
		return getCell(new Point(x, y));
	}
	
	public void moveHero(Direction direction) {
		Point originalHeroPosition = getCellWithHero().getPosition();
		Point desiredPosition = direction.newPosition(originalHeroPosition);
		
		if (isDesiredPositionAWall(desiredPosition))
			return;
		
		if (!isDesiredPositionABlock(desiredPosition)) {
			moveGameElement(originalHeroPosition, desiredPosition);
			return;
		}
		
		Point blockDesiredPosition = direction.newPosition(desiredPosition);
		
		if (isDesiredPositionABlockOrWall(blockDesiredPosition))
			return;
		
		moveGameElements(originalHeroPosition, desiredPosition, blockDesiredPosition);
	}

	public void moveGameElement(Point originalHeroPosition, Point desiredPosition) {
		moveGameElements(originalHeroPosition, desiredPosition, null);
	}
	
	private void moveGameElements(Point originalHeroPosition, Point desiredPosition, Point blockDesiredPosition) {
		if(blockDesiredPosition != null)
			getCell(blockDesiredPosition).setSecondaryElement(getElement(desiredPosition));
		
		getCell(desiredPosition).setSecondaryElement(getElement(originalHeroPosition));
		getCell(originalHeroPosition).setSecondaryElement(GameElementFactory.newDumbElement());
	}

	public void setCell(Cell cell) {
		grid[cell.getPosition().y][cell.getPosition().x] = cell; 
	}
	
	@Override
	public String toString() {
		return grid.toString();
	}
	
}
