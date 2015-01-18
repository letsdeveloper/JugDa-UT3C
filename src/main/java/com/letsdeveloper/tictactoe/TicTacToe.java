package com.letsdeveloper.tictactoe;

import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.letsdeveloper.game.BaseGame;

public class TicTacToe extends BaseGame {

	public TicTacToe() {
		super(9, 3);
		houses.add(createHouse(index -> new Field(index, 0)));
		houses.add(createHouse(index -> new Field(index, 1)));
		houses.add(createHouse(index -> new Field(index, 2)));
		houses.add(createHouse(index -> new Field(0, index)));
		houses.add(createHouse(index -> new Field(1, index)));
		houses.add(createHouse(index -> new Field(2, index)));
		houses.add(createHouse(index -> new Field(index, index)));
		houses.add(createHouse(index -> new Field(index, 2 - index)));
	}

	private House createHouse(IntFunction<Field> fct) {
		IntFunction<Cell> mapper = index -> findCell(fct.apply(index)).get();
		return new House(3, IntStream.range(0, 3).mapToObj(mapper).collect(Collectors.toList()));
	}

	public boolean canMove(Field field) {
		return isOnBoard(field) && isFieldEmpty(field);
	}
}
