package com.hflprogramming.espaker16.view;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.screen.ScreenCharacter;

public class Pane {
	public List<Pane> subpanes = new ArrayList<Pane>();//top is last in list
	ScreenCharacter[][] buffer;

	public int width;
	public int height;

	public int offsetX = 0;
	public int offsetY = 0;

	public Pane(int x, int y) {
		width = x;
		height = y;
		buffer = new ScreenCharacter[x][y];
	}

	public Pane(int x, int y, int offX, int offY) {
		width = x;
		height = y;

		//position in parent pane/view
		offsetX = offX;
		offsetY = offY;

		buffer = new ScreenCharacter[x][y];
	}

	public void putChar(int x, int y, ScreenCharacter charater) {
		if (x < width && y < height) {
			buffer[x][y] = charater;
		}
	}

	public void write(int x, int y, String text) {
		final char[] chars = text.toCharArray();
		int posX;
		int posY;

		for (int iteration = 0; iteration < chars.length; iteration++) {
			posY = y + iteration / width;
			posX = x + iteration % width;

			if (posX < width && posY < height) {
				buffer[posX][posY] = new ScreenCharacter(chars[iteration]);
			}

		}
	}

	public void vectorWrite(int x, int y, String text, String direction) {
		final char[] chars = text.toCharArray();

		switch (direction) {
		case "up":
			for (int iteration = 0; iteration < chars.length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x][y - iteration] = new ScreenCharacter(chars[iteration]);
				}
			}
			break;

		case "down":
			for (int iteration = 0; iteration < chars.length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x][y + iteration] = new ScreenCharacter(chars[iteration]);
				}
			}
			break;

		case "left":
			for (int iteration = 0; iteration < chars.length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x - iteration][y] = new ScreenCharacter(chars[iteration]);
				}
			}
			break;

		case "right":
			for (int iteration = 0; iteration < chars.length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x + iteration][y] = new ScreenCharacter(chars[iteration]);
				}
			}
			break;

		case "up-right":
			for (int iteration = 0; iteration < chars.length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x + iteration][y - iteration] = new ScreenCharacter(chars[iteration]);
				}
			}
			break;

		case "down-right":
			for (int iteration = 0; iteration < chars.length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x + iteration][y + iteration] = new ScreenCharacter(chars[iteration]);
				}
			}
			break;

		case "up-left":
			for (int iteration = 0; iteration < chars.length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x - iteration][y - iteration] = new ScreenCharacter(chars[iteration]);
				}
			}
			break;

		case "down-left":
			for (int iteration = 0; iteration < chars.length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x - iteration][y + iteration] = new ScreenCharacter(chars[iteration]);
				}
			}
			break;
		}

	}

	//A line from point x1 y1 to point x2 y2
	public void line(int x1, int y1, int x2, int y2, ScreenCharacter character) {

		//first find change in x and y
		final int deltaX = x2 - x1;
		final int deltaY = y2 - y1;

		//make sure slope isn't undefined
		if (deltaX != 0) {

			//calculate the slope
			final int m = deltaY / deltaX;

			//determine which axis to draw line by(larger delta is better)
			if (Math.abs(deltaX) > Math.abs(deltaY)) {

				//go from x1 to x2, decrementing/incrementing by one
				for (int posX = x1; posX != x2; posX += Integer.signum(deltaX)) {
					//calculate then y value for the given x using point slope formula, then round to the nearest integer draw the char
					final int posY = Math.round(m * (posX - x1) + y1);
					if (posX >= 0 && posX < width && posY >= 0 && posY < height) {
						putChar(posX, posY, character);
					}
				}

			} else {
				for (int posY = y1; posY != y2; posY += Integer.signum(deltaY)) {
					final int posX = Math.round(1 / m * (posY - y1) + x1);

					if (posX >= 0 && posX < width && posY >= 0 && posY < height) {
						putChar(posX, posY, character);
					}
				}
			}

			//or else last char wont print
			putChar(x2, y2, character);

		} else {
			for (int posY = y1; posY != y2; posY += Integer.signum(deltaY)) {
				putChar(x1, posY, character);
			}
		}

		/* code for exact line
		 * 		int factor = Math.abs(GCF(deltaX, deltaY));

		deltaX = deltaX/factor;
		deltaY = deltaY/factor;

		for( int posX = x1, posY = y1; posX != x2 || posY != y2; posX += deltaX, posY += deltaY ){
			if(posX<width && posY<height){
				buffer[posX][posY]= new ScreenCharacter(character);
			}
		}
		if(x2<width && y2<height){
			buffer[x2][y2]= new ScreenCharacter(character);
		}
		 */

	}

	public void vectorLine(int x, int y, int length, String direction, ScreenCharacter character) {
		switch (direction) {
		case "up":
			for (int iteration = 0; iteration < length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x][y - iteration] = new ScreenCharacter(character);
				}
			}
			break;

		case "down":
			for (int iteration = 0; iteration < length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x][y + iteration] = new ScreenCharacter(character);
				}
			}
			break;

		case "left":
			for (int iteration = 0; iteration < length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x - iteration][y] = new ScreenCharacter(character);
				}
			}
			break;

		case "right":
			for (int iteration = 0; iteration < length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x + iteration][y] = new ScreenCharacter(character);
				}
			}
			break;

		case "up-right":
			for (int iteration = 0; iteration < length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x + iteration][y - iteration] = new ScreenCharacter(character);
				}
			}
			break;

		case "down-right":
			for (int iteration = 0; iteration < length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x + iteration][y + iteration] = new ScreenCharacter(character);
				}
			}
			break;

		case "up-left":
			for (int iteration = 0; iteration < length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x - iteration][y - iteration] = new ScreenCharacter(character);
				}
			}
			break;

		case "down-left":
			for (int iteration = 0; iteration < length; iteration++) {
				if (x + iteration < width && y < height && x + iteration >= 0 && y >= 0) {
					buffer[x - iteration][y + iteration] = new ScreenCharacter(character);
				}
			}
			break;
		}
	}

	public void clear() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				buffer[x][y] = null;
			}
		}
	}

	public void fill(ScreenCharacter character) {
		fill(0, 0, width, height, character);
	}

	public void fill(char character) {
		fill(new ScreenCharacter(character));
	}

	public void fill(int x, int y, int w, int h, ScreenCharacter c) {
		for (int posX = x; posX < x + w; posX++) {
			for (int posY = y; posY < y + h; posY++) {
				try {
					buffer[posX][posY] = new ScreenCharacter(c);
				} catch (final ArrayIndexOutOfBoundsException e) {
					System.out.println("out of bounds ERROR");
				}
			}

		}
	}

	public Pane newPane() {
		final Pane pane = new Pane(width, height);
		subpanes.add(pane);
		return pane;
	}

	public Pane newPane(int x, int y) {
		final Pane pane = new Pane(x, y);
		subpanes.add(pane);
		return pane;
	}

	public Pane newPane(int x, int y, int offsetX, int offsetY) {
		final Pane pane = new Pane(x, y, offsetX, offsetY);
		subpanes.add(pane);
		return pane;
	}

	public void addPane(Pane pane) {
		subpanes.add(pane);
	}

	public void refresh() {
		for (int iteration = 0; iteration < subpanes.size(); iteration++) {
			final Pane pane = subpanes.get(iteration);
			pane.refresh();

			for (int x = 0; x < pane.width; x++) {
				for (int y = 0; y < pane.height; y++) {
					if (x + pane.offsetX < width && y + pane.offsetY < height && pane.buffer[x][y] != null) {
						buffer[x + pane.offsetX][y + pane.offsetY] = pane.buffer[x][y];
					}
				}
			}
		}
	}

	public static int GCF(int a, int b) {
		if (b == 0) {
			return a;
		}
		return GCF(b, a % b);
	}

}
