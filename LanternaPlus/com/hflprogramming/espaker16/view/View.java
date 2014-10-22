package com.hflprogramming.espaker16.view;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

public class View {

	public Screen screen;
	public Terminal terminal;
	ScreenWriter writer;

	List<Pane> panes = new ArrayList<Pane>(); //objects to draw, top is last in list

	public int width;
	public int height;

	public View() {

		initTerminal();

		screen = new Screen(terminal);

		screen.clear();
		screen.refresh();

		screen.startScreen();

		screen.refresh();

		writer = new ScreenWriter(screen);
	}

	public void initTerminal() {
		terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));

		terminal.setCursorVisible(false);

		width = terminal.getTerminalSize().getColumns();
		height = terminal.getTerminalSize().getRows();
	}

	public void test() {
		screen.putString(10, 5, "Hello Lanterna!", Terminal.Color.RED, Terminal.Color.GREEN);
		writer.setForegroundColor(Terminal.Color.BLACK);
		writer.setBackgroundColor(Terminal.Color.WHITE);
		writer.drawString(5, 3, "Hello Lanterna!", ScreenCharacterStyle.Bold);
		writer.setForegroundColor(Terminal.Color.DEFAULT);
		writer.setBackgroundColor(Terminal.Color.DEFAULT);
		writer.drawString(5, 5, "Hello Lanterna!");
		writer.drawString(5, 7, "Hello Lanterna!");
		screen.refresh();
	}

	public void refresh() {
		for (int iteration = 0; iteration < panes.size(); iteration++) {
			final Pane pane = panes.get(iteration);
			pane.refresh();

			//need to fix issue with small panes (index out of range)
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					if (pane.buffer[x][y] != null) {
						screen.putCharacter(x, y, pane.buffer[x][y]);
					}
				}
			}
		}
		screen.refresh();

	}

	public Pane newPane() {
		final Pane pane = new Pane(width, height);
		panes.add(pane);
		return pane;
	}

	public Pane newPane(int x, int y) {
		final Pane pane = new Pane(x, y);
		panes.add(pane);
		return pane;
	}

	public Pane newPane(int x, int y, int offsetX, int offsetY) {
		final Pane pane = new Pane(x, y, offsetX, offsetY);
		panes.add(pane);
		return pane;
	}

	public void addPane(Pane pane) {
		panes.add(pane);
	}

	public void stop() {
		screen.stopScreen();
	}

}
