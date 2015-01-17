package com.letsdeveloper.tictactoe;

import gameloop.Game;

public class TicTacToeGame implements Game {

	private final TicTacToe game = new TicTacToe();

	private String message;

	@Override
	public boolean isRunning() {
		return !game.isGameOver();
	}

	@Override
	public void update(Field field) {
		if (game.canMove(field)) {
			game.move(field);
			message = "good move!";
		} else {
			message = "cannot move on that field";
		}
	}

	@Override
	public void draw() {
		System.out.println();
		System.out.println(message);
		System.out.println();

		System.out.println("+-+-+-+");
		for (int column = 0; column < 3; column++) {
			System.out.print("|");
			for (int row = 0; row < 3; row++) {
				Player owner = game.getPlayerOn(new Field(row, column));
				String output = " ";
				if (owner == Player.X) {
					output = "X";
				} else if (owner == Player.O) {
					output = "O";
				}
				System.out.print(output + "|");
			}
			System.out.println();
			System.out.println("+-+-+-+");
		}

		if (game.isGameOver()) {
			System.out.println("Game Over.");
		} else {
			System.out.println("Next turn: " + game.getNextPlayer());
		}
	}

}
