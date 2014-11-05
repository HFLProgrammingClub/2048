package com.hflprogramming.espaker16.engine2048;

import java.util.Scanner;

import com.googlecode.lanterna.input.Key;

//this file is not part of the engine but rather am example implementation along with some test code
public class Main {

	static Engine engine;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		engine = new Engine();

		engine.initDisplay();
		engine.startGame(false);

		//testPlay();

		//an example of an extremely simple 2048 bot
		try {
			gameloop();
		} catch (final Exception e) {
			System.out.println("Gameloop ended");
			//test();
		}

	}

	//test code for human players playing in the terminal with arrow keys
	public static void testPlay() {
		while (true) {
			final Key key = engine.getDisplay().readKeyInput();
			if (key != null) {
				switch (key.getKind()) {

				case ArrowLeft:
					engine.swipe(Engine.D_LEFT);
					break;

				case ArrowRight:
					engine.swipe(Engine.D_RIGHT);
					break;

				case ArrowUp:
					engine.swipe(Engine.D_UP);
					break;

				case ArrowDown:
					engine.swipe(Engine.D_DOWN);
					break;

				/*
				case PageUp:
				engine.undo();
				break;

				case PageDown:
				engine.redo();
				break;

				 */

				case Escape:
					engine.closeDisplay();
					System.exit(0);
					break;

				default:
					break;
				}
			}
		}
	}

	//an example of an extremely simple 2048 bot
	public static void gameloop() throws Exception {
		final byte[] directionPriority = { Engine.D_DOWN, Engine.D_LEFT, Engine.D_RIGHT, Engine.D_UP };

		System.out.println("started gameloop");

		while (true) {
			System.out.println("turn started");
			for (byte dir = 0; dir < 4; dir++) {
				System.out.println("attempting... " + directionPriority[dir]);

				final int status = engine.swipe(directionPriority[dir]);

				if (status == 1) {
					System.out.println("moved " + directionPriority[dir]);

					try {
						Thread.currentThread();
						Thread.sleep(7);//sleep for 80 ms

					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
					break;

				} else if (status == 0) {
					System.out.println("invalid... ");
					continue;

				} else {
					System.out.println("you lose");
					throw new Exception();
				}
			}
		}
	}

	public static void test() {
		//just a bit of test code
		//engine.getDisplay().setTile(0, 0, 333);
		//engine.getDisplay().setTile(0, 3, 444);
		//engine.getDisplay().setTile(3, 0, 888);
		//engine.getDisplay().setTile(3, 3, 999);
		//engine.getDisplay().refresh();

		System.out.println(engine.look()[0][0]);
		System.out.println(engine.look()[0][3]);
		System.out.println(engine.look()[3][0]);
		System.out.println(engine.look()[3][3]);

		engine.look()[0][0] = 555;
		int f = engine.look(3, 3);
		f = 666;
		int s = engine.getScore();
		System.out.println(s);
		s = -333;
		engine.updateDisplay();
		System.out.println(engine.getScore());
	}
}
