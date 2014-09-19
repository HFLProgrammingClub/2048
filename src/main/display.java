package main;

import java.util.Map;

import view.Blueprints;
import view.Structure;
import view.View;

public class display {
	View view = new View();
	display(){
		
	}
}

class Board extends Blueprints{
	
	Map<String, String> defaults;
	
	void board(){
		defaults = {"position":"relative","posX":"0","posY":"0","height":"100","width","100"}
	}
	
	public void draw(Structure structure){
		
	}
}
