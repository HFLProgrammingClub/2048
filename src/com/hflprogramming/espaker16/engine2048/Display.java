package com.hflprogramming.espaker16.engine2048;

import view.Blueprints;
import view.Pane;
import view.Structure;
import view.View;

public class Display {
	View view = new View();

	Structure board;
	Structure screen;

	Display() {
		screen = new Structure(view.width, view.height);
		board = new Board(screen);

	}

}

class Board extends Structure {

	Board(Pane parentPane) {
		super(parentPane.height - 4, (parentPane.height < parentPane.width ? parentPane.height : parentPane.width) - 4);

		//create boarder
		Blueprints.drawBorder(this, '|', '|', '=', '=');

		draw();
	}

}

class Tile extends Structure {

	Tile(int x, int y, int posX, int posY) {
		super(x, y, posX, posY);

		//create boarder
		Blueprints.drawBorder(this, '|', '|', '=', '=');

		draw();

	}
}
