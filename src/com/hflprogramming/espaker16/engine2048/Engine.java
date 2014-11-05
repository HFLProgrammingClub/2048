package com.hflprogramming.espaker16.engine2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Engine {
	private boolean doDisplay = true;
	private Display display;

	private int[][] gameboard;

	private int score;

	public static final byte D_LEFT = 2;
	public static final byte D_DOWN = 0;
	public static final byte D_UP = 1;
	public static final byte D_RIGHT = 3;

	public Engine() {
		gameboard = new int[4][4];

		for (int col = 0; col < 4; col++) {
			for (int row = 0; row < 4; row++) {
				gameboard[row][col] = 0;
			}
		}
	}

	public void startGame(boolean debug) {
		gameboard = addRandomTile(gameboard);
		gameboard = addRandomTile(gameboard);

		onMove();
	}

	public int getScore() {
		return this.score;
	}

	public int[][] look() {
		final int[][] gb = new int[4][4];
		//returns the gameboard as [rows][columns] with [0][0] at the top right corner
		for (int col = 0; col < 4; col++) {
			for (int row = 0; row < 4; row++) {
				gb[row][col] = gameboard[row][col];
			}
		}
		return gb;
	}

	public int look(int row, int col) {
		//returns the value of the gameboard tile at [row][col]
		return gameboard[row][col];
	}

	//0:invalid, 1:valid, -1:game-over
	public int swipe(byte direction) {
		switch (direction) {
		case D_RIGHT:
			//two rotations
			gameboard = rotateBoard(1, gameboard);
			gameboard = rotateBoard(1, gameboard);

			try {
				gameboard = moveRight(gameboard);
			} catch (final Exception e) {
				//invalid move return false
				gameboard = rotateBoard(1, gameboard);
				gameboard = rotateBoard(1, gameboard);
				return 0;
			}

			gameboard = rotateBoard(1, gameboard);
			gameboard = rotateBoard(1, gameboard);
			break;

		case D_LEFT:
			//no rotation
			try {
				gameboard = moveRight(gameboard);
			} catch (final Exception e) {
				//invalid move return false
				return 0;
			}
			break;

		case D_DOWN:
			//three rotations
			gameboard = rotateBoard(1, gameboard);
			gameboard = rotateBoard(1, gameboard);
			gameboard = rotateBoard(1, gameboard);

			try {
				gameboard = moveRight(gameboard);
			} catch (final Exception e) {
				//invalid move return false
				gameboard = rotateBoard(1, gameboard);
				return 0;
			}

			gameboard = rotateBoard(1, gameboard);
			break;

		case D_UP:
			//one rotation
			gameboard = rotateBoard(1, gameboard);

			try {
				gameboard = moveRight(gameboard);
			} catch (final Exception e) {
				//invalid move return false
				gameboard = rotateBoard(1, gameboard);
				gameboard = rotateBoard(1, gameboard);
				gameboard = rotateBoard(1, gameboard);
				return 0;
			}

			gameboard = rotateBoard(1, gameboard);
			gameboard = rotateBoard(1, gameboard);
			gameboard = rotateBoard(1, gameboard);
			break;
		}

		//add random tile to board;
		gameboard = addRandomTile(gameboard);

		//check for end of game
		if (getEmptyCells(gameboard).size() < 1) {
			if (isGameOver(gameboard)) {
				onGameOver();
				return -1;
			}

		}

		onMove();
		return 1;

	}

	private void onGameOver() {
		System.out.println("Game Over");
		System.out.println("Score: " + this.score);

		onMove();
	}

	private void onMove() {
		if (doDisplay) {
			updateDisplay();
		}
	}

	// 'newTileValue' is supposed to be set to the numerical value of the new tile created.
	private void setScore(int newTileValue) {
		this.score += newTileValue;
	}

	/*
	 * helper function written to rotate the buffer board so one function can be used for
	 * moving tiles (moveDown) and then the board can be rotated back
	 *
	 * THIS HAS ISSUES do not pass any number more than one for times
	 */
	private static int[][] rotateBoard(int times, int[][] board) {
		//TODO fix multiple rotations functionality
		final int[][] buffer = new int[4][4];//rows then columns, 0,0 at bottom left corner
		//will rotate board right "times" times

		for (int i = 0; i < times; i++) {
			for (int row = 0; row < 4; row++) {
				//for each row, copy contents to opposite column (row 0 goes to column 3, row 2 goes to column 1 )
				for (int col = 0; col < 4; col++) {
					//because the board is stored as [rows] then [columns] we will set the value of buffer[row][column] to board[column][3-row]
					buffer[row][col] = board[col][3 - row];
				}
				//finally set the board to buffer for next rotation
				//this breaks everything next line breaks everything
				//board = buffer.clone();
			}
		}
		return buffer;
	}

	//helper function for sliding and merging the tiles down
	private int[][] moveRight(int[][] board) throws Exception {
		boolean valid = false;

		for (int row = 0; row < 4; row++) {

			//first merge tiles
			for (int col = 0; col < 4; col++) {
				for (int b = col + 1; b < 4; b++) {
					//TODO feel like explaining this in comments
					final int block = board[row][col];
					final int nblock = board[row][b];

					if (block == nblock && block != 0) {
						board[row][col] = nblock * 2;
						board[row][b] = 0;

						setScore(nblock * 2);

						valid = true;
						break;

					} else if (nblock != 0) {
						break;

					} else {
						continue;
					}
				}
			}

			// now move down
			for (int col = 0; col < 4; col++) {
				for (int b = col + 1; b < 4; b++) {
					//TODO feel like explaining this in comments
					final int block = board[row][col];
					final int nblock = board[row][b];

					if (block == 0 && nblock != 0) {
						board[row][col] = nblock;
						board[row][b] = 0;

						valid = true;
						break;

					} else if (block != 0) {
						break;

					} else {
						continue;
					}

				}
			}
		}

		if (valid) {
			return board;

		} else {
			//throw to signify an invalid board move (AKA: nothing moves)
			throw new Exception();
		}
	}

	private static boolean isGameOver(int[][] board) {
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col] == board[row][col + 1]) {
					return false;
				}
			}
		}

		for (int col = 0; col < 4; col++) {
			for (int row = 0; row < 3; row++) {
				if (board[row][col] == board[row + 1][col]) {
					return false;
				}
			}
		}
		return true;
	}

	private static List<Integer> getEmptyCells(int[][] board) {
		final List<Integer> emptyCells = new ArrayList<>(0);

		for (int iteration = 0; iteration < 16; iteration++) {
			if (board[iteration % 4][iteration / 4] == 0) {
				emptyCells.add(iteration);
			}
		}
		return emptyCells;
	}

	private static int[][] addRandomTile(int[][] board) {
		final List<Integer> emptyCells = getEmptyCells(board);

		final Random random = new Random();

		final int cell = emptyCells.get(random.nextInt(emptyCells.size()));//Get a random integer between 0(inclusive) and the number of empty cells(exclusive).
		board[cell % 4][cell / 4] = random.nextInt(10) < 9 ? 2 : 4;//Place new tile. There is a one out of ten chance that the tile will be a four instead of a two

		return board;
	}

	public void updateDisplay() {
		if (display != null) {
			for (int iteration = 0; iteration < 16; iteration++) {
				final int x = iteration / 4;
				final int y = iteration % 4;
				display.setTile(x, y, gameboard[y][x]);
			}
		}

		display.refresh();
	}

	public void initDisplay() {
		display = new Display();
		updateDisplay();
		doDisplay = true;

	}

	public void closeDisplay() {
		if (display != null) {
			display.view.screen.stopScreen();
			display = null;
		}
		doDisplay = false;
	}

	public Display getDisplay() {
		return display;
	}
}
