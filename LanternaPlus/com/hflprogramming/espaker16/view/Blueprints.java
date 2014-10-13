package com.hflprogramming.espaker16.view;

import java.awt.Point;

import com.googlecode.lanterna.screen.ScreenCharacter;
import com.googlecode.lanterna.terminal.Terminal;

public final class Blueprints {
	public static void drawBorder(Structure structure, char left, char right, char top, char bottom) {
		drawBorder(structure, new ScreenCharacter(left), new ScreenCharacter(right), new ScreenCharacter(top), new ScreenCharacter(bottom));
	}

	public static void drawBorder(Structure structure, ScreenCharacter left, ScreenCharacter right, ScreenCharacter top, ScreenCharacter bottom) {
		//create boarder

		structure.points.put("top_left", new Point(0, 0));
		structure.points.put("top_right", new Point(structure.width - 1, 0));
		structure.points.put("bottom_left", new Point(0, structure.height - 1));
		structure.points.put("bottom_right", new Point(structure.width - 1, structure.height - 1));

		structure.lines.put("top", new Line("top_left", "top_right", top));
		structure.lines.put("left", new Line("top_left", "bottom_left", left));
		structure.lines.put("bottom", new Line("bottom_left", "bottom_right", bottom));
		structure.lines.put("right", new Line("top_right", "bottom_right", right));
	}

	public static void setColor(Structure structure, Terminal.Color[] colors, int border) {
		for (int x = border; x < structure.width - border; x++) {
			for (int y = border; y < structure.height - border; y++) {
				structure.buffer[x][y].foregroundColor = colors[0];
				structure.buffer[x][y].backgroundColor = colors[1];
			}
		}
	}
}
