package br.com.sokobao2000;


import junit.framework.Assert;

public class GameElementFactory {
	
	public static GameElement newGameElement(char gameElementSingleName) {
		
		if(gameElementSingleName == 'H')
			return newHero();
		
		if(gameElementSingleName == 'W')
			return newWall();
		
		if(gameElementSingleName == 'B')
			return newBlock();
		
		if(gameElementSingleName == 'X')
			return newTarget();
		
		if(gameElementSingleName == ' ')
			return newDumbElement();
		
		Assert.fail("Ops, " + gameElementSingleName + " is not a single name for a game element. Sorry.");
		return null;
	}
	
	public static GameElement newHero() {
		return new Hero();
	}

	public static GameElement newWall() {
		return new Wall();
	}

	public static GameElement newBlock() {
		return new Block();
	}
	
	public static GameElement newTarget() {
		return new Target();
	}

	public static GameElement newDumbElement() {
		return new DumbElement();
	}

}
