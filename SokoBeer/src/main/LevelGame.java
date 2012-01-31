package main;

import java.util.ArrayList;
import java.util.List;

public class LevelGame {

	public static Grid gridLevel1() {
		List<String> listLevel1 = new ArrayList<String>();
		listLevel1.add("WWWWWWWWWW"); 
		listLevel1.add("W        W"); 
		listLevel1.add("W  B   X W"); 
		listLevel1.add("W     B  W");
		listLevel1.add("W H      W");
		listLevel1.add("W     X  W");
		listLevel1.add("W   B    W");
		listLevel1.add("W X    B W");
		listLevel1.add("W     X  W");
		listLevel1.add("WWWWWWWWWW");
		
		return LevelConverter.templateToGrid(listLevel1);
	}
	
}
