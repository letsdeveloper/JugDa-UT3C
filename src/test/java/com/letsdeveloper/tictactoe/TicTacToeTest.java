package com.letsdeveloper.tictactoe;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TicTacToeTest {

	private TicTacToe gameUnderTest;

	@Before
	public void setUpGame() {
		gameUnderTest = new TicTacToe();
	}

	@Test
	public void startWithPlayerX() {
		Player nextPlayer = gameUnderTest.getNextPlayer();

		assertThat(nextPlayer, is(Player.X));
	}

	@Test
	public void canMove() {
		assertTrue("Can not move :(", gameUnderTest.canMove(0, 0));
		assertTrue("Can not move :(", gameUnderTest.canMove(0, 1));
		assertTrue("Can not move :(", gameUnderTest.canMove(0, 2));
		assertTrue("Can not move :(", gameUnderTest.canMove(1, 0));
		assertTrue("Can not move :(", gameUnderTest.canMove(1, 1));
		assertTrue("Can not move :(", gameUnderTest.canMove(1, 2));
		assertTrue("Can not move :(", gameUnderTest.canMove(2, 0));
		assertTrue("Can not move :(", gameUnderTest.canMove(2, 1));
		assertTrue("Can not move :(", gameUnderTest.canMove(2, 2));
	}

	@Test
	public void returnPlayerOnField() {
		Player owner = gameUnderTest.getPlayerOn(0, 0);

		assertThat(owner, is(Player.NONE));
	}

	@Test
	public void setPlayerOnMove() {
		gameUnderTest.move(new Field(0, 0));
		Player owner = gameUnderTest.getPlayerOn(0, 0);

		assertThat(owner, is(Player.X));
	}

	@Test
	public void setPlayerOnMoveOnlyOnMovedField() {
		gameUnderTest.move(new Field(0, 1));
		Player owner = gameUnderTest.getPlayerOn(0, 0);

		assertThat(owner, is(Player.NONE));
	}

	@Test
	public void setPlayerXOnMoveOnMovedField() {
		gameUnderTest.move(new Field(0, 1));
		Player owner = gameUnderTest.getPlayerOn(0, 1);

		assertThat(owner, is(Player.X));
	}

	@Test
	public void switchPlayerAfterMove() {
		gameUnderTest.move(new Field(0, 0));
		Player nextPlayer = gameUnderTest.getNextPlayer();

		assertThat(nextPlayer, is(Player.O));
	}

	@Test
	public void switchPlayerAfterSecondMove() {
		gameUnderTest.move(new Field(0, 0));
		gameUnderTest.move(new Field(0, 1));
		Player nextPlayer = gameUnderTest.getNextPlayer();

		assertThat(nextPlayer, is(Player.X));
	}

	@Test
	public void canNotMoveOnOccupiedField() {
		gameUnderTest.move(new Field(0, 0));
		boolean canMove = gameUnderTest.canMove(0, 0);

		assertFalse("Can move! :o", canMove);
	}

	@Test
	public void secondMoveSetsFieldForPlayerO() {
		gameUnderTest.move(new Field(0, 0));
		gameUnderTest.move(new Field(0, 1));

		Player owner = gameUnderTest.getPlayerOn(0, 1);

		assertThat(owner, is(Player.O));
	}

	@Test
	public void canNotMoveOutsideBoard() {
		assertFalse("Can move! :o", gameUnderTest.canMove(-1, -1));
		assertFalse("Can move! :o", gameUnderTest.canMove(0, -1));
		assertFalse("Can move! :o", gameUnderTest.canMove(-1, 0));
		assertFalse("Can move! :o", gameUnderTest.canMove(3, 3));
		assertFalse("Can move! :o", gameUnderTest.canMove(3, 2));
		assertFalse("Can move! :o", gameUnderTest.canMove(2, 3));
		assertFalse("Can move! :o", gameUnderTest.canMove(2, -1));
		assertFalse("Can move! :o", gameUnderTest.canMove(3, -1));
		assertFalse("Can move! :o", gameUnderTest.canMove(3, 0));
		assertFalse("Can move! :o", gameUnderTest.canMove(-1, 2));
		assertFalse("Can move! :o", gameUnderTest.canMove(-1, 3));
		assertFalse("Can move! :o", gameUnderTest.canMove(0, 3));
	}

	@Test
	public void isGameNotOverInitially() {
		boolean gameOver = gameUnderTest.isGameOver();

		assertFalse("Game not over", gameOver);
	}

	@Test
	public void isGameNotOver() {
		gameUnderTest.move(new Field(0, 0));
		boolean gameOver = gameUnderTest.isGameOver();

		assertFalse("Game over", gameOver);
	}

	@Test
	public void isGameOverIfPlayerOwnsFirstRow() {
		assertGameOverAfterMoves(new Field(0, 0), new Field(1, 0), new Field(0, 1), new Field(2, 0), new Field(0, 2));
	}

	@Test
	public void isGameOverIfPlayerOwnsSecondRow() {
		assertGameOverAfterMoves(new Field(1, 0), new Field(2, 0), new Field(1, 1), new Field(2, 1), new Field(1, 2));
	}

	@Test
	public void isGameOverIfPlayerOwnsThirdRow() {
		assertGameOverAfterMoves(new Field(2, 0), new Field(1, 0), new Field(2, 1), new Field(1, 1), new Field(2, 2));
	}

	@Test
	public void isGameOverIfPlayerOwnsFirstColumn() {
		assertGameOverAfterMoves(new Field(0, 0), new Field(1, 2), new Field(1, 0), new Field(1, 1), new Field(2, 0));
	}

	@Test
	public void isGameOverIfPlayerOwnsSecondColumn() {
		assertGameOverAfterMoves(new Field(0, 1), new Field(1, 2), new Field(1, 1), new Field(2, 2), new Field(2, 1));
	}

	@Test
	public void isGameOverIfPlayerOwnsThirdColumn() {
		assertGameOverAfterMoves(new Field(0, 2), new Field(1, 2), new Field(1, 2), new Field(1, 1), new Field(2, 2));
	}

	@Test
	public void isGameOverIfPlayerOwnsDiagonal1() {
		assertGameOverAfterMoves(new Field(0, 0), new Field(1, 2), new Field(1, 1), new Field(0, 1), new Field(2, 2));
	}

	@Test
	public void isGameOverIfPlayerOwnsDiagonal2() {
		assertGameOverAfterMoves(new Field(0, 2), new Field(1, 2), new Field(1, 1), new Field(0, 1), new Field(2, 0));
	}

	private void assertGameOverAfterMoves(Field... fields) {
		Arrays.stream(fields).forEach(f -> gameUnderTest.move(f));
		boolean gameOver = gameUnderTest.isGameOver();
		assertTrue("Game not over", gameOver);
	}
}
