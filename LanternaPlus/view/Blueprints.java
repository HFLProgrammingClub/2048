package view;

import java.awt.Point;

import com.googlecode.lanterna.screen.ScreenCharacter;

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

		structure.lines.put("top", new Line("top_left", "top_right", '='));
		structure.lines.put("left", new Line("top_left", "bottom_left", '|'));
		structure.lines.put("right", new Line("bottom_left", "bottom_right", '='));
		structure.lines.put("bottom", new Line("top_right", "bottom_right", '|'));
	}
}
