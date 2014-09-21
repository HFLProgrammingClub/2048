package com.hflprogramming.espaker16.engine2048;

import java.util.Map;

import com.googlecode.lanterna.screen.ScreenCharacter;

import view.Blueprints;
import view.Pane;
import view.Structure;
import view.View;

public class Display {
	Board boardBlueprint = new Board();
	View view = new View();
	Structure board;
	Structure screen;

	Display() {
		
	}
}

//might make this static or final
class Board extends Blueprints {

	Map<String, String> defaults;

	Board(){
		defaults = {"position":"relative","posX":"0","posY":"0","height":"100","width":"100"};
	}

	Board(Map<String, String> d) {
		defaults = d;
	}

	Structure newBoard(Pane parentPane) {
		final int height = parentPane.height - 4;
		final int width = height;

		final Structure newBoard = new Structure(width, height);
		newBoard.blueprints = this;
		newBoard.update();
		return newBoard;
	}

	@Override
	public void draw(Structure structure) {
		structure.line(0, 0, structure.width-1, 0, new ScreenCharacter('='));
		structure.line(0, 0, 0, structure.height-1, new ScreenCharacter('|'));
		structure.line(structure.width-1, structure.height-1, structure.width-1, 0, new ScreenCharacter('|'));
		structure.line(structure.width-1, structure.height-1, 0, structure.height-1, new ScreenCharacter('='));

	}
}
