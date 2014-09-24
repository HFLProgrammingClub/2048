package com.hflprogramming.espaker16.engine2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Engine {
	static final int D_UP = 1;
	static final int D_DOWN = 3;
	static final int D_LEFT = 4;
	static final int D_RIGHT = 2;

	public int score;

	private int[][] gameboard = new int[4][4];//rows then columns, 0,0 at bottom left corner

	public int[][] look() {
		return gameboard;
	}

	//0:invalid, 1:valid, -1:game-over
	public int swipe(int direction) {
		int[][] buffer = gameboard;//Don't know if i need this ( ".clone()" );

		switch (direction) {
		case D_UP:
			//two rotations
			buffer = rotateBoard(2, buffer);
			buffer = moveDown(buffer);
			buffer = rotateBoard(2, buffer);
			break;

		case D_DOWN:
			//no rotation
			buffer = moveDown(buffer);
			break;

		case D_LEFT:
			//three rotations
			buffer = rotateBoard(3, buffer);
			buffer = moveDown(buffer);
			buffer = rotateBoard(1, buffer);
			break;

		case D_RIGHT:
			//one rotation
			buffer = rotateBoard(1, buffer);
			buffer = moveDown(buffer);
			buffer = rotateBoard(3, buffer);
			break;
		}

		if (buffer[0][0] == -1) {
			//invalid move return false
			return 0;

		} else {
			//add random tile to board;
			//get all empty tiles (there will be at least one)
			final List<Integer> emptyCells = new ArrayList<>(0);

			for (int i = 0; i < 16; i++) {
				if (buffer[i % 4][i / 4] == 0) {
					emptyCells.add(i);
				}
			}

			final Random random = new Random();
			final int cell = emptyCells.get(random.nextInt(emptyCells.size()));//Get a random integer between 0(inclusive) and the number of empty cells(exclusive).
			buffer[cell % 4][cell / 4] = random.nextInt(10) <= 9 ? 2 : 4;//Place new tile. There is a one out of ten chance that the tile will be a four instead of a two.

			gameboard = buffer.clone();//set gameboard to new state

			//check for end of game
			if (emptyCells.size() < 2) {
				if (isGameOver(buffer)) {
					onGameOver();
					return -1;
				}
			}

			onMove();
			return 1;

		}

	}

	private void onGameOver() {
		// TODO: add onGameOver() functionality
	}

	private void onMove() {
		// TODO: add onMove() functionality
	}

	/*
	 * helper function written to rotate the buffer board so one function can be used for
	 * moving tiles (moveDown) and then the board can be rotated back
	 */
	private int[][] rotateBoard(int times, int[][] board) {
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
				board = buffer;
			}
		}
		return buffer;
	}

	//helper function for sliding and merging the tiles down
	private int[][] moveDown(int[][] board) {
		boolean valid = false;

		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				for (int b = col + 1; b < 4; b++) {
					//TODO feel like explaining this in comments
					final int block = board[row][col];
					final int nblock = board[row][b];

					if (block == nblock && block != 0) {
						board[row][col] = nblock * 2;
						board[row][b] = -1;

						setScore(nblock * 2);

						valid = true;

					} else if (block == -1 && nblock != 0) {
						board[row][col] = nblock;
						board[row][b] = -1;

						valid = true;

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
			//set board[0][0] to -1 to signify an invalid board move (aka: nothing moves)
			board[0][0] = -1;
			return board;

		}
	}

	private boolean isGameOver(int[][] buffer) {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (buffer[row][col] == buffer[row][col + 1]) {
					return false;
				}
			}
		}

		for (int col = 0; col < 3; col++) {
			for (int row = 0; row < 3; row++) {
				if (buffer[row][col] == buffer[row + 1][col]) {
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

}
