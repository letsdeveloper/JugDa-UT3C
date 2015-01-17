package com.letsdeveloper.tictactoe;

import java.util.Arrays;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TicTacToe {

	private final Player[][] board = new Player[3][3];

	private Player currentPlayer = Player.X;

	public TicTacToe() {
		Arrays.fill(board[0], Player.NONE);
		Arrays.fill(board[1], Player.NONE);
		Arrays.fill(board[2], Player.NONE);
	}

	public Player getNextPlayer() {
		return currentPlayer;
	}

	public boolean canMove(int row, int column) {
		return isOnBoard(row, column) && getPlayerOn(row, column) == Player.NONE;
	}

	private boolean isOnBoard(int row, int column) {
		return (row >= 0 && row < board.length) && (column >= 0 && column < board[0].length);
	}

	public void move(Field field) {
		board[field.getRow()][field.getColumn()] = currentPlayer;
		currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
	}

	public Player getPlayerOn(int row, int column) {
		return board[row][column];
	}

	public boolean isGameOver() {
		boolean winnerInRow = IntStream.range(0, board.length).anyMatch(row -> isRowOwnedBySinglePlayer(row));
		boolean winnerInColumn = IntStream.range(0, board.length).anyMatch(row -> isColumnOwnedBySinglePlayer(row));
		boolean winnerInDiagonal = isSinglePlayer(getPlayersInDiagonal1()) || isSinglePlayer(getPlayersInDiagonal2());
		return winnerInRow || winnerInColumn || winnerInDiagonal;
	}

	private boolean isColumnOwnedBySinglePlayer(int row) {
		return isSinglePlayer(getPlayersInColumn(row));
	}

	private boolean isRowOwnedBySinglePlayer(int row) {
		return isSinglePlayer(getPlayersInRow(row));
	}

	private boolean isSinglePlayer(Set<Player> players) {
		return players.size() == 1 && !players.contains(Player.NONE);
	}

	private Set<Player> getPlayersInRow(int row) {
		return Arrays.stream(board[row]).collect(Collectors.toSet());
	}

	private Set<Player> getPlayersInColumn(int column) {
		return Arrays.stream(board).map(row -> row[column]).collect(Collectors.toSet());
	}

	private Set<Player> getPlayersInDiagonal1() {
		return getPlayers(index -> board[index][index]);
	}

	private Set<Player> getPlayersInDiagonal2() {
		return getPlayers(index -> board[index][board.length - 1 - index]);
	}

	private Set<Player> getPlayers(IntFunction<Player> mappingFunction) {
		return IntStream.range(0, board.length).mapToObj(mappingFunction).collect(Collectors.toSet());
	}

}
