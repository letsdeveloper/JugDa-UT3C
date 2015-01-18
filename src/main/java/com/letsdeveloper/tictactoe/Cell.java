package com.letsdeveloper.tictactoe;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Cell {
	private final Field field;
	private Player owner = Player.NONE;
}