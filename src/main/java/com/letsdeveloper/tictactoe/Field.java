package com.letsdeveloper.tictactoe;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Field {
	private final int row, column;
}
