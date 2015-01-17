package gameloop;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.Test;

public class GameLoopTest {

	@Test
	public void doesNothingWhenTheGameIsStopped() {
		Game testGame = createMock(Game.class);
		expect(testGame.isRunning()).andReturn(false);
		replay(testGame);

		InputHandler inputHandler = createMock(InputHandler.class);
		replay(inputHandler);

		GameLoop gameLoop = new GameLoop(testGame, inputHandler);
		gameLoop.run();

		verify(testGame);
	}

	@Test
	public void runsUpdateOnceBeforeTheGameIsStopped() {
		InputHandler inputHandler = createMock(InputHandler.class);
		expect(inputHandler.getNextInput()).andReturn(null);
		replay(inputHandler);

		Game testGame = createMock(Game.class);
		expect(testGame.isRunning()).andReturn(true).andReturn(false);
		testGame.update(null);
		testGame.draw();
		replay(testGame);

		GameLoop gameLoop = new GameLoop(testGame, inputHandler);
		gameLoop.run();

		verify(testGame);
	}

	@Test
	public void updatesUntilTheGameIsStopped() {
		InputHandler inputHandler = createMock(InputHandler.class);
		expect(inputHandler.getNextInput()).andReturn(null).times(4);
		replay(inputHandler);

		Game testGame = createMock(Game.class);
		expect(testGame.isRunning()).andReturn(true).times(4).andReturn(false);
		testGame.update(null);
		expectLastCall().times(4);
		testGame.draw();
		expectLastCall().times(4);
		replay(testGame);

		GameLoop gameLoop = new GameLoop(testGame, inputHandler);
		gameLoop.run();

		verify(testGame);
	}
}
