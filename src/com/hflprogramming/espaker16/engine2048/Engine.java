package com.hflprogramming.espaker16.engine2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Engine {
	int[][] gameboard;
	public Display display;

	public Engine() {
		gameboard = new int[4][4];//rows then columns, 0,0 at bottom left corner
	}

	public int[][] look() {
		return gameboard;
	}

	public int score;

	public static final byte D_LEFT = 2;
	public static final byte D_DOWN = 0;
	public static final byte D_UP = 1;
	public static final byte D_RIGHT = 3;

	public void startGame() {
		final Random random = new Random();
		final int cell = random.nextInt(16);//Get a random integer between 0(inclusive) and the 16(exclusive)
		gameboard[cell % 4][cell / 4] = random.nextInt(10) < 9 ? 2 : 4;//Place new tile. There is a one out of ten chance that the tile will be a four instead of a two.
	}

	//0:invalid, 1:valid, -1:game-over
	public int swipe(byte direction) {
		switch (direction) {
		case D_RIGHT:
			//two rotations
			gameboard = rotateBoard(1, gameboard);
			gameboard = rotateBoard(1, gameboard);

			try {
				gameboard = moveDown(gameboard);
			} catch (final Exception e) {
				//invalid move return false
				return 0;
			}

			gameboard = rotateBoard(1, gameboard);
			gameboard = rotateBoard(1, gameboard);
			break;

		case D_LEFT:
			//no rotation
			try {
				gameboard = moveDown(gameboard);
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
				gameboard = moveDown(gameboard);
			} catch (final Exception e) {
				//invalid move return false
				return 0;
			}

			gameboard = rotateBoard(1, gameboard);
			break;

		case D_UP:
			//one rotation
			gameboard = rotateBoard(1, gameboard);

			try {
				gameboard = moveDown(gameboard);
			} catch (final Exception e) {
				//invalid move return false
				return 0;
			}

			gameboard = rotateBoard(1, gameboard);
			gameboard = rotateBoard(1, gameboard);
			gameboard = rotateBoard(1, gameboard);
			break;
		}

		//add random tile to board;
		//get all empty tiles (there will be at least one)
		final List<Integer> emptyCells = new ArrayList<>(0);

		for (int i = 0; i < 16; i++) {
			if (gameboard[i % 4][i / 4] == 0) {
				emptyCells.add(i);
			}
		}

		final Random random = new Random();
		final int cell = emptyCells.get(random.nextInt(emptyCells.size()));//Get a random integer between 0(inclusive) and the number of empty cells(exclusive).
		gameboard[cell % 4][cell / 4] = random.nextInt(10) < 9 ? 2 : 4;//Place new tile. There is a one out of ten chance that the tile will be a four instead of a two.

		//check for end of game
		if (emptyCells.size() < 2) {
			System.out.println("game may be over");
			if (isGameOver(gameboard)) {
				onGameOver();
				return -1;
			}
		}

		onMove();
		return 1;

	}

	private void onGameOver() {
		System.out.println("game over");
		updateDisplay();

	}

	private void onMove() {
		updateDisplay();
	}

	/*
	 * helper function written to rotate the buffer board so one function can be used for
	 * moving tiles (moveDown) and then the board can be rotated back
	 * 
	 * THIS HAS ISSUES do not pass any number more than one for times
	 */
	static int[][] rotateBoard(int times, int[][] board) {
		//TODO fix multible rotations funtionality
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
	private int[][] moveDown(int[][] board) throws Exception {
		boolean valid = false;

		for (int row = 0; row < 4; row++) {
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

					} else if (block == 0 && nblock != 0) {
						board[row][col] = nblock;
						board[row][b] = 0;

						valid = true;
					} else if (nblock != 0) {
						break;
					} else {
						continue;
					}

					break;
				}
			}
		}

		if (valid) {
			return board;

		} else {
			//throw to signify an invalid board move (aka: nothing moves)
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

	// 'newTileValue' is supposed to be set to the numerical value of the new tile created.
	public void setScore(int newTileValue) {
		this.score += newTileValue;
	}

	public int getScore() {
		return this.score;
	}

	public void updateDisplay() {
		if (display != null) {
			for (int i = 0; i < 16; i++) {
				final int x = i / 4;
				final int y = i % 4;
				display.setTile(x, y, gameboard[y][x]);
			}
		}

		display.refresh();
	}
}
