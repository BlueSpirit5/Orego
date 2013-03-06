package orego.neural;

import static orego.core.Colors.*;
import static orego.core.Coordinates.*;
import static org.junit.Assert.*;
import orego.mcts.McRunnable;
import orego.play.UnknownPropertyException;

import org.junit.Before;
import org.junit.Test;

public class RatioPlayerTest {

	private RatioPlayer player;

	@Before
	public void setUp() throws Exception {
		player = new RatioPlayer();
		player.setProperty("threads", "1");
		player.reset();
	}

	/**
	 * Incorporates the indicated moves as if they had been generated by a real
	 * playout. Two passes are added to the end.
	 */
	protected void fakeRun(int winner, String... labels) {
		int[] moves = new int[labels.length + 2];
		int i;
		for (i = 0; i < labels.length; i++) {
			moves[i] = at(labels[i]);
		}
		moves[i] = PASS;
		moves[i + 1] = PASS;
		McRunnable runnable = (McRunnable) player.getRunnable(0);
		runnable.copyDataFrom(player.getBoard());
		for (int p : moves) {
			runnable.acceptMove(p);
		}
		player.incorporateRun(winner, runnable);
	}

	@Test
	public void testIncorporateRun() throws UnknownPropertyException {
		if (BOARD_WIDTH == 9) {
			player.setProperty("playouts", "1000");
			player.reset();
			String[] problem = { //
					".........", // 9
					"#########", // 8
					"..#######", // 7
					"#########", // 6
					"OOOOOOOO.", // 5
					"OOOOOOOO.", // 4
					"OOOOOOOOO", // 3
					"OOOOOOOOO", // 2
					".OOOOOOOO" // 1
			// ABCDEFGHJ
			};
			player.setUpProblem(WHITE, problem);
			int best = player.bestMove();
			assertEquals(at("J5"), best);
		}
	}

}
