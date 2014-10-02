package com.hflprogramming.espaker16.engine2048;

import java.util.Scanner;

public class Main {

	static Engine engine;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		engine = new Engine();
		engine.display = new Display();

		engine.startGame();

		engine.swipe(engine.D_DOWN);

		/*
		 * test code for playing manually (with numbers entered into console)
		while (true) {
			final byte i = (byte) scanner.nextInt();
			if (i >= 0 && i < 4) {
				if (engine.swipe(i) == 1) {
					System.out.println("swipe");
				}
			}
		}
		*/

		try {
			gameloop();
		} catch (final Exception e) {
			// TODO Auto-generated catch block
		}

	}

	public static void gameloop() throws Exception {
		System.out.println("started gameloop");

		final boolean x = Math.random() > .5 ? true : false;
		final boolean y = Math.random() > .5 ? true : false;
		while (x && y || !(x && y)) {
			for (byte dir = 1; dir <= 4; dir++) {
				final int status = engine.swipe(dir);

				if (status == 0) {
					try {
						Thread.currentThread().sleep(80);//sleep for 1000 ms
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
					continue;

				} else if (status == 1) {
					break;

				} else {
					throw new Exception();
				}
			}
		}
	}
}
