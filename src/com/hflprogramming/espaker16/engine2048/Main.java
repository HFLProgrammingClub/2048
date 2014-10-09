package com.hflprogramming.espaker16.engine2048;

import java.util.Scanner;

import com.googlecode.lanterna.input.Key;

public class Main {

	static Engine engine;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		engine = new Engine();
		engine.display = new Display();

		engine.startGame();

		testPlay();

		try {
			gameloop();
		} catch (final Exception e) {
			System.out.println("Gameloop ended");
		}

	}

	public static void testPlay() {
		//final test code for final playing manually (final with numbers entered final into console)
		while (true) {
			final Key key = engine.display.view.terminal.readInput();
			if (key != null) {
				switch (key.getKind()) {

				case ArrowLeft:
					engine.swipe(engine.D_LEFT);
					break;

				case ArrowRight:
					engine.swipe(engine.D_RIGHT);
					break;

				case ArrowUp:
					engine.swipe(engine.D_UP);
					break;

				case ArrowDown:
					engine.swipe(engine.D_DOWN);
					break;

				case PageUp:
					engine.undo();
					break;

				case PageDown:
					engine.redo();
					break;

				default:
					break;
				}
			}

		}
	}

	public static void gameloop() throws Exception {
		final byte[] directionPriority = { Engine.D_DOWN, Engine.D_LEFT, Engine.D_RIGHT, Engine.D_UP };

		System.out.println("started gameloop");

		while (true) {
			System.out.println("turn started");
			for (byte dir = 0; dir < 4; dir++) {
				System.out.println("trying direction " + directionPriority[dir]);

				final int status = engine.swipe(directionPriority[dir]);

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
