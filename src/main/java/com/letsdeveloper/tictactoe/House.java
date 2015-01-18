package com.letsdeveloper.tictactoe;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class House {

	private final int c;
	private final List<Cell> cells;

	public boolean isWinner() {
		List<Player> players = getPlayers();
		Player last = null;
		int count = 0;
		for (Player player : players) {
			if (player == Player.NONE) {
				continue;
			}

			if (last == null || !last.equals(player)) {
				last = player;
				count = 1;
			}
			else {
				count++;
			}
			if (count == c) {
				return true;
			}
		}
		return false;
	}

	private List<Player> getPlayers() {
		return getCells().stream().map(cell -> cell.getOwner()).collect(Collectors.toList());
	}
}
