package gameloop;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameLoop {

	private final Game game;
	private final InputHandler inputHandler;

	public void run() {
		while (game.isRunning()) {
			game.update(inputHandler.getNextInput());
			game.draw();
		}
	}

}
