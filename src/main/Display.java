package main;

import java.util.Map;

import view.Blueprints;
import view.Structure;
import view.View;

public class Display {
	Board board = new Board();
	View view = new View();
	Structure board = new Structure();
	Structure screen = new Structure();
	display(){
		
	}
}

//might make this static
class Board extends Blueprints{
	
	Map<String, String> defaults;
	
	void Board(){
		defaults = {"position":"relative","posX":"0","posY":"0","height":"100","width":"100"};
	}
	
	void Board(Map<String,String> d){
		defaults = d;
	}
	
	Structure newBoard(){
		Structure structure = new Structure();
		structure.blueprints = this;
		structure.update();
		return structure;
	}
	
	public void draw(Structure structure){
		
	}
}
