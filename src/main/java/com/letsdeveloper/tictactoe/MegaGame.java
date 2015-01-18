package com.letsdeveloper.tictactoe;

import gameloop.Game;
import lombok.RequiredArgsConstructor;

import com.letsdeveloper.game.BaseGame;

@RequiredArgsConstructor
public class MegaGame implements Game {

	private final BaseGame game;

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

		printRowSeparator();
		for (int row = 0; row < game.getNumRows(); row++) {
			System.out.print("|");
			for (int column = 0; column < game.getNumRows(); column++) {
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
			printRowSeparator();
		}

		if (game.isGameOver()) {
			System.out.println("Game Over.");
		} else {
			System.out.println("Next turn: " + game.getNextPlayer());
		}
	}

	private void printRowSeparator() {
		for (int i = 0; i < game.getNumRows(); i++) {
			System.out.print("+-");
		}
		System.out.println("+");
	}

}
