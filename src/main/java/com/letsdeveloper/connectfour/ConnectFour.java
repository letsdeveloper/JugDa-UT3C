package com.letsdeveloper.connectfour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.letsdeveloper.game.BaseGame;
import com.letsdeveloper.tictactoe.Cell;
import com.letsdeveloper.tictactoe.Field;
import com.letsdeveloper.tictactoe.House;

public class ConnectFour extends BaseGame {

	public ConnectFour() {
		super(36, 6);
		int numRows = 6;
		IntStream.range(0, numRows).forEach(i -> {
			houses.add(createHouse(index -> new Field(index, i)));
			houses.add(createHouse(index -> new Field(i, index)));
		});
		for (int row = 0; row < 6; row++) {
			List<Cell> cells = new ArrayList<>();
			for (int column = 0; column <= row; column++) {
				cells.add(new Cell(new Field(row - column, column)));
			}
			houses.add(new House(4, cells));
		}
	}

	private House createHouse(IntFunction<Field> fct) {
		IntFunction<Cell> mapper = index -> findCell(fct.apply(index)).get();
		return new House(4, IntStream.range(0, 6).mapToObj(mapper).collect(Collectors.toList()));
	}

	public boolean canMove(Field field) {
		if (!isFieldEmpty(field)) {
			return false;
		}
		Field fieldBelow = getFieldBelow(field);
		return !isOnBoard(fieldBelow) || !isFieldEmpty(fieldBelow);
	}

	private Field getFieldBelow(Field field) {
		return new Field(field.getRow() + 1, field.getColumn());
	}

}
