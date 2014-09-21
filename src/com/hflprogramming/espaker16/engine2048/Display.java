package com.hflprogramming.espaker16.engine2048;

import java.util.Map;

import view.Blueprints;
import view.Structure;
import view.View;

public class Display {
	Board board = new Board();
	View view = new View();
	Structure board_structure = new Structure();
	Structure screen = new Structure();

	Display() {
		// TODO: add constructor functionality.
	}
}

//might make this static
class Board extends Blueprints {

	Map<String, String> defaults;

	void Board(){
		defaults = {"position":"relative","posX":"0","posY":"0","height":"100","width":"100"};
	}

	void Board(Map<String, String> d) {
		defaults = d;
	}

	Structure newBoard() {
		final Structure structure = new Structure();
		structure.blueprints = this;
		structure.update();
		return structure;
	}

	@Override
	public void draw(Structure structure) {
		// TODO: add draw() functionality
	}
}
