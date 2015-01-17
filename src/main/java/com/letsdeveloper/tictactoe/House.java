package com.letsdeveloper.tictactoe;

import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class House {

	private final Set<Cell> cells;

	boolean isSinglePlayer() {
		Set<Player> players = getPlayers();
		return players.size() == 1 && !players.contains(Player.NONE);
	}

	private Set<Player> getPlayers() {
		return getCells().stream().map(cell -> cell.getOwner()).collect(Collectors.toSet());
	}
}
