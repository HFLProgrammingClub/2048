package com.hflprogramming.espaker16.engine2048;


public class Main {

	static Engine engine;

	public static void main(String[] args) {
		engine = new Engine();
		engine.display = new Display();

		engine.startGame();

		//gameloop();

	}

	public static void gameloop() {
		System.out.println("started gameloop");

		boolean x = Math.random() > .5 ? true : false;
		boolean y = Math.random() > .5 ? true : false;
		while ((x && y) || !(x && y)) {
			for (byte dir = 1; dir <= 4; dir++) {
				final int status = engine.swipe(dir);

				if (status == 0) {
					try {
						Thread.currentThread().sleep(100);//sleep for 1000 ms
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
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
