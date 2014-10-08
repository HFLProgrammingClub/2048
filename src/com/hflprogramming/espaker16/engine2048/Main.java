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
			System.out.println("Gameloop ended");
		}

	}

	public static void gameloop() throws Exception {
		System.out.println("started gameloop");

		while (true) {
			System.out.println("turn started");
			for (byte dir = 0; dir < 4; dir++) {
				System.out.println("trying direction " + dir);

				final int status = engine.swipe(dir);

				if (status == 1) {
					System.out.println("direction worked next turn");

					try {
						Thread.currentThread().sleep(80);//sleep for 1000 ms
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
					break;

				} else if (status == 0) {
					System.out.println("direction failed");
					continue;

				} else {
					System.out.println("direction worked game over");
					throw new Exception();
				}
			}
		}
	}
}
