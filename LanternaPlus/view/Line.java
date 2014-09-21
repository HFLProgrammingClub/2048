package view;

import com.googlecode.lanterna.screen.ScreenCharacter;

public class Line {
	String pointOne;
	String pointTwo;

	ScreenCharacter character;

	boolean visible = true;

	public Line(String a, String b, ScreenCharacter c) {
		pointOne = a;
		pointTwo = b;

		character = c;
	}

	public Line(String a, String b, char c) {
		pointOne = a;
		pointTwo = b;

		character = new ScreenCharacter(c);
	}
}
