package com.hflprogramming.espaker16.engine2048;

public class Main {

	static Engine engine;
	static Display display;

	public static void main(String[] args) {
		display = new Display();
		engine = new Engine();

		engine.startGame();

	}

	public void gameloop() {

		while (true) {
			for (byte dir = 1; dir <= 4; dir++) {
				final int status = engine.swipe(dir);
				if (status == 0) {
					continue;
				} else if (status == 1) {
					break;
				} else {
					return;
				}
			}

		}
	}
}