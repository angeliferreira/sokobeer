package main;

import main.directions.East;
import main.directions.North;
import main.directions.South;
import main.directions.West;
import main.gameElement.GameElement;

public class Sokobao2000 {

	private Grid grid;
	private GameElement hero;
		
	public Sokobao2000() {
		this.grid = LevelGame.gridLevel1();
		this.hero = this.grid.getElement(this.grid.getHeroPosition());
	}

	public void moveHeroEast() {
		this.grid.move(this.hero.getPosition(), East.getInstance());
	}

	public void moveHeroWest() {
		this.grid.move(this.hero.getPosition(), West.getInstance());
	}
	
	public void moveHeroNorth() {
		this.grid.move(this.hero.getPosition(), North.getInstance());
	}

	public void moveHeroSouth() {
		this.grid.move(this.hero.getPosition(), South.getInstance());
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public Grid getGrid() {
		return this.grid;
	}

	public void setHero(GameElement hero) {
		this.hero = hero;
	}

	public GameElement getHero() {
		return hero;
	}

}
