package com.letsdeveloper.game;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Getter;

import com.letsdeveloper.tictactoe.Cell;
import com.letsdeveloper.tictactoe.Field;
import com.letsdeveloper.tictactoe.House;
import com.letsdeveloper.tictactoe.Player;

public abstract class BaseGame {

	@Getter
	private Player nextPlayer = Player.X;
	protected final Set<Cell> cells;
	protected final Set<House> houses = new HashSet<>();
	@Getter
	private final int numRows;

	public BaseGame(int numCells, int numRows) {
		this.numRows = numRows;
		this.cells = IntStream.range(0, numCells)
				.mapToObj(index -> new Cell(new Field(index % numRows, index / numRows)))
				.collect(Collectors.toSet());
	}

	protected void switchPlayer() {
		nextPlayer = getNextPlayer() == Player.X ? Player.O : Player.X;
	}

	public abstract boolean canMove(Field field);

	protected boolean isFieldEmpty(Field field) {
		return getPlayerOn(field) == Player.NONE;
	}

	protected Optional<Cell> findCell(Field field) {
		Optional<Cell> findAny = cells.stream().filter(cell -> cell.getField().equals(field)).findAny();
		return findAny;
	}

	public Player getPlayerOn(Field field) {
		return findCell(field).get().getOwner();
	}

	protected boolean isOnBoard(Field field) {
		return findCell(field).isPresent();
	}

	public void move(Field field) {
		findCell(field).get().setOwner(getNextPlayer());
		switchPlayer();
	}

	public boolean isGameOver() {
		return houses.stream().anyMatch(house -> house.isWinner());
	}
}
