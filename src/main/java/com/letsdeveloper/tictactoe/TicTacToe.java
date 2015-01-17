package com.letsdeveloper.tictactoe;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TicTacToe {

	private final Set<Cell> cells;
	private final Set<House> houses;

	private Player currentPlayer = Player.X;

	public TicTacToe() {
		cells = IntStream.range(0, 9).mapToObj(index -> new Cell(new Field(index % 3, index / 3)))
				.collect(Collectors.toSet());
		houses = new HashSet<House>();
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
		return new House(IntStream.range(0, 3).mapToObj(mapper).collect(Collectors.toSet()));
	}

	public Player getNextPlayer() {
		return currentPlayer;
	}

	public boolean canMove(Field field) {
		return isOnBoard(field) && getPlayerOn(field) == Player.NONE;
	}

	private boolean isOnBoard(Field field) {
		return findCell(field).isPresent();
	}

	private Optional<Cell> findCell(Field field) {
		return cells.stream().filter(cell -> cell.getField().equals(field)).findAny();
	}

	public void move(Field field) {
		findCell(field).get().setOwner(currentPlayer);
		currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
	}

	public Player getPlayerOn(Field field) {
		return findCell(field).get().getOwner();
	}

	public boolean isGameOver() {
		return houses.stream().anyMatch(house -> house.isSinglePlayer());
	}
}
