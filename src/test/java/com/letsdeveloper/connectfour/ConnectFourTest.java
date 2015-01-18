package com.letsdeveloper.connectfour;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.letsdeveloper.tictactoe.Field;
import com.letsdeveloper.tictactoe.Player;

public class ConnectFourTest {

	private ConnectFour gameUnderTest;

	@Before
	public void setUpGame() {
		gameUnderTest = new ConnectFour();
	}

	@Test
	public void firstPlayerIsX() {
		Player firstPlayer = gameUnderTest.getNextPlayer();

		assertThat(firstPlayer, is(Player.X));
	}

	@Test
	public void canMove() {
		assertMovesPossible(new Field(5, 0), new Field(5, 1), new Field(5, 2), new Field(5, 3), new Field(5, 4),
				new Field(5, 5));
	}

	private void assertMovesPossible(Field... fields) {
		Arrays.stream(fields)
				.forEach(field -> assertTrue("Can not move on field " + field, gameUnderTest.canMove(field)));
	}

	@Test
	public void cannotMove() {
		assertMovesImpossible(new Field(0, 0), new Field(0, 5), new Field(4, 3));
	}

	private void assertMovesImpossible(Field... fields) {
		Arrays.stream(fields).forEach(field -> assertFalse("Can move! :o", gameUnderTest.canMove(field)));
	}

	@Test
	public void returnPlayerOnField() {
		Player owner = gameUnderTest.getPlayerOn(new Field(0, 0));

		assertThat(owner, is(Player.NONE));
	}

	@Test
	public void setPlayerOnMove() {
		gameUnderTest.move(new Field(5, 0));
		Player owner = gameUnderTest.getPlayerOn(new Field(5, 0));

		assertThat(owner, is(Player.X));
	}

	@Test
	public void setPlayerOnMoveOnlyOnMovedField() {
		gameUnderTest.move(new Field(5, 0));
		Player owner = gameUnderTest.getPlayerOn(new Field(0, 0));

		assertThat(owner, is(Player.NONE));
	}

	@Test
	public void switchPlayerAfterMove() {
		gameUnderTest.move(new Field(5, 0));
		Player nextPlayer = gameUnderTest.getNextPlayer();

		assertThat(nextPlayer, is(Player.O));
	}

	@Test
	public void switchPlayerAfterSecondMove() {
		gameUnderTest.move(new Field(5, 0));
		gameUnderTest.move(new Field(5, 3));
		Player nextPlayer = gameUnderTest.getNextPlayer();

		assertThat(nextPlayer, is(Player.X));
	}

	@Test
	public void canNotMoveOnOccupiedField() {
		gameUnderTest.move(new Field(5, 0));
		boolean canMove = gameUnderTest.canMove(new Field(5, 0));

		assertFalse("Can move! :o", canMove);
	}

	@Test
	public void secondMoveSetsFieldForPlayerO() {
		gameUnderTest.move(new Field(5, 0));
		gameUnderTest.move(new Field(5, 1));

		Player owner = gameUnderTest.getPlayerOn(new Field(5, 1));

		assertThat(owner, is(Player.O));
	}

	@Test
	public void isGameNotOverInitially() {
		assertGameNotOverAfterMoves(/* none */);
	}

	@Test
	public void isGameNotOver() {
		assertGameNotOverAfterMoves(new Field(5, 0));
	}

	@Test
	public void isGameOverIfPlayerOwnsFirstRow() {
		assertGameOverAfterMoves(new Field(5, 0), new Field(4, 0), new Field(5, 1), new Field(4, 1),
				new Field(5, 2), new Field(4, 2), new Field(5, 3));
	}

	@Test
	@Ignore
	public void isGameOverIfPlayerOwnsDiagonal1() {
		assertGameOverAfterMoves(new Field(5, 3), new Field(5, 2),
				new Field(4, 2), new Field(5, 1),
				new Field(5, 0), new Field(4, 1),
				new Field(3, 1), new Field(4, 0),
				new Field(3, 0), new Field(5, 4),
				new Field(2, 0));
	}

	private void assertGameOverAfterMoves(Field... fields) {
		Arrays.stream(fields).forEach(f -> gameUnderTest.move(f));
		boolean gameOver = gameUnderTest.isGameOver();
		assertTrue("Game not over", gameOver);
	}

	private void assertGameNotOverAfterMoves(Field... fields) {
		Arrays.stream(fields).forEach(f -> gameUnderTest.move(f));
		boolean gameOver = gameUnderTest.isGameOver();

		assertFalse("Game over", gameOver);
	}
}
