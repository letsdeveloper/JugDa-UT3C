package com.letsdeveloper.tictactoe;

import gameloop.GameLoop;
import gameloop.InputHandler;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		new GameLoop(new TicTacToeGame(), new InputHandler() {

			@SuppressWarnings("resource")
			@Override
			public Field getNextInput() {
				Scanner scanner = new Scanner(System.in);
				System.out.print("Move on row: ");
				int row = scanner.nextInt();
				System.out.print("Move on column: ");
				int column = scanner.nextInt();
				return new Field(row, column);
			}
		}).run();
	}
}
