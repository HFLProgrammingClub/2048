package com.hflprogramming.espaker16.engine2048;

import view.Blueprints;
import view.Pane;
import view.Structure;
import view.View;

public class Display {
	View view = new View();
<<<<<<< HEAD
	Structure board;
	Structure screen;

	Display() {

=======
	Structure board_structure = new Structure();
	Structure screen = new Structure();

	Display() {
		// TODO: add constructor functionality.
>>>>>>> 2b66c5bb0ed7b7370bb987e3ed9bef08230ab7ed
	}
}

//might make this static or final
class Board extends Structure {

	Board(Pane parentPane) {
		super(parentPane.height - 4, (parentPane.height < parentPane.width ? parentPane.height : parentPane.width) - 4);

		//create boarder
		Blueprints.drawBorder(this, '|', '|', '=', '=');

		draw();
	}

}

<<<<<<< HEAD
class Tile extends Structure {

	Tile(int x, int y, int posX, int posY) {
		super(x, y, posX, posY);

		//create boarder
		Blueprints.drawBorder(this, '|', '|', '=', '=');

		draw();
=======
	@Override
	public void draw(Structure structure) {
		// TODO: add draw() functionality
>>>>>>> 2b66c5bb0ed7b7370bb987e3ed9bef08230ab7ed
	}

}
