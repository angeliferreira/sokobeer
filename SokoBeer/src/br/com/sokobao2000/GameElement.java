package br.com.sokobao2000;



public abstract class GameElement {
	
	protected char singleName;

	protected void setSingleName(char singleName) {
		this.singleName = singleName;
	}

	private char getSingleName() {
		return singleName;
	}
	
	@Override
	public String toString() {
		return String.valueOf(getSingleName());
	}
	
	public enum StringRepresentation {
	    WALL("W"), TARGET("X"), BLOCK("B"), HERO("H"), DUMB(" "), TARGET_WITH_HERO("#"), TARGET_WITH_BLOCK("$");
	    
	    private StringRepresentation(String representation) {
			this.representation = representation;
		}
	    
	    private String representation;
	    
	    public String represent() {
	    	return representation;
	    }
	}
	
}
