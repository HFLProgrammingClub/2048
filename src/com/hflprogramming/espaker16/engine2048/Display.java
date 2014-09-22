package com.hflprogramming.espaker16.engine2048;

import view.Blueprints;
import view.Pane;
import view.Structure;
import view.View;

public class Display {
	View view;

	Structure board;
	Structure screen;

	Display() {
		view = new View();

		screen = new Structure(view.width, view.height);
		board = new DisplayBoard(screen);

		screen.addPane(board);
		view.addPane(screen);

		view.refresh();
	}

}

class DisplayBoard extends Structure {

	DisplayBoard(Pane parentPane) {
		super(parentPane.width / 2, parentPane.height - 4, parentPane.width / 4, 2);//(parentPane.height < parentPane.width ? parentPane.height : parentPane.width) - 4);

		final Pane internalView = newPane(width - 4, height - 2, 2, 1);

		//create boarder
		Blueprints.drawBorder(this, '2', '0', '4', '8');

		final int tileWidth = internalView.width / 4;
		final int tileHeight = internalView.height / 4;

		for (int i = 0; i < 16; i++) {
			final int x = i / 4;
			final int y = i % 4;

			internalView.subpanes.add(new DisplayTile(tileWidth - 1, tileHeight, tileWidth * x + x, tileHeight * y));
		}

		draw();
	}
}

class DisplayTile extends Structure {

	DisplayTile(int x, int y, int posX, int posY) {
		super(x, y, posX, posY);

		//create boarder
		Blueprints.drawBorder(this, '|', '|', '_', '\u203E');//\u203E is an overline character

		draw();

	}
}
