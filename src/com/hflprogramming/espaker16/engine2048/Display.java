package com.hflprogramming.espaker16.engine2048;

import com.googlecode.lanterna.screen.ScreenCharacter;
import com.hflprogramming.espaker16.view.Blueprints;
import com.hflprogramming.espaker16.view.Pane;
import com.hflprogramming.espaker16.view.Structure;
import com.hflprogramming.espaker16.view.View;

public class Display {
	View view;

	DisplayBoard board;
	Structure screen;

	Display() {
		view = new View();

		screen = new Structure(view.width, view.height);
		board = new DisplayBoard(screen);

		screen.addPane(board);
		view.addPane(screen);

		view.refresh();
	}

	public void setTile(int x, int y, int number) {
		board.setTile(x, y, number);
	}

	public void refresh() {
		view.refresh();
	}

}

class DisplayBoard extends Structure {
	final Pane internalView;

	DisplayBoard(Pane parentPane) {
		super(parentPane.width / 2, parentPane.height - 4, parentPane.width / 4, 2);//(parentPane.height < parentPane.width ? parentPane.height : parentPane.width) - 4);

		internalView = newPane(width - 4, height - 2, 2, 1);

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

	public void setTile(int x, int y, int number) {
		final DisplayTile tile = (DisplayTile) internalView.subpanes.get(x * 4 + y);
		tile.setNumber(number);
	}
}

class DisplayTile extends Structure {

	DisplayTile(int x, int y, int posX, int posY) {
		super(x, y, posX, posY);

		//create boarder
		Blueprints.drawBorder(this, '|', '|', '_', '\u203E');//\u203E is an overline character

		draw();
	}

	public void setNumber(int number) {
		final String numericString = String.valueOf(number);
		final int length = numericString.length();

		fill(1, 1, width - 2, height - 2, new ScreenCharacter(' '));

		if (length > width - 2) {
			//TODO fix issues with centering text

			final int lines = (int) Math.ceil((double) length / (width - 2));
			final int startLine = (height - 2 - lines) / 2;
			int startColumn = 0;

			if (lines > height - 2) {
				return; //cant display number
			}
			if (lines == 1) {
				startColumn = (width - 2 - length) / 2;
			}
			write(startColumn, startLine, numericString);

		} else {
			write((width - 2 - length) / 2 + 1, (height - 2) / 2 + 1, numericString);
		}

	}

}
