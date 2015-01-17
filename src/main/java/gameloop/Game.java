package gameloop;

import com.letsdeveloper.tictactoe.Field;

public interface Game {

	boolean isRunning();

	void update(Field field);

	void draw();

}
