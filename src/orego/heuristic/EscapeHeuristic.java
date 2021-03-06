package orego.heuristic;

import orego.core.*;
import static orego.core.Coordinates.*;
import static orego.core.Colors.*;
import orego.util.*;

/**
 * Tries to save any friendly group put in atari by the last move, by capturing
 * or extending.
 */
public class EscapeHeuristic extends Heuristic {

	/** Friendly groups put in atari by the last move. */
	private IntSet friends;

	/** Enemy chains in atari adjacent to friends. */
	private IntSet targets;

	public EscapeHeuristic(int weight) {
		super(weight);
		friends = new IntSet(getFirstPointBeyondBoard());
		targets = new IntSet(getFirstPointBeyondBoard());
	}

	@Override
	public EscapeHeuristic clone() {
		EscapeHeuristic copy = (EscapeHeuristic) super.clone();
		copy.friends = new IntSet(getFirstPointBeyondBoard());
		copy.targets = new IntSet(getFirstPointBeyondBoard());
		return copy;
	}
	
	/** Recommends moves that capture adjacent enemies of chain. */
	protected void escapeByCapturing(int chain, int enemyColor,
			Board board) {
		int color = board.getColorToPlay();
		int p = chain;
		do {
			for (int i = 0; i < 4; i++) {
				int neighbor = getNeighbors(p)[i];
				if (board.getColor(neighbor) == enemyColor) {
					int target = board.getChainId(neighbor);
					if (!targets.contains(target)) {
						targets.add(target);
						int capturePoint = board.getCapturePoint(target);
						if (capturePoint != NO_POINT) {
							if (!board.isSelfAtari(capturePoint, color)) {
								recommend(capturePoint);
							}
						}
					}
				}
			}
			p = board.getChainNextPoint(p);
		} while (p != chain);
	}
	
	@Override
	public void prepare(Board board) {
		super.prepare(board);
		int lastMove = board.getMove(board.getTurn() - 1);
		int color = board.getColorToPlay();
		// Find friendly groups in danger
		friends.clear();
		targets.clear();
		for (int i = 0; i < 4; i++) {
			int neighbor = getNeighbors(lastMove)[i];
			if (board.getColor(neighbor) == color) {
				int chain = board.getChainId(neighbor);
				if (!friends.contains(chain)) {
					int liberty = board.getCapturePoint(chain);
					if (liberty != NO_POINT) {
						friends.add(chain);
						// Consider escaping by capturing
						escapeByCapturing(chain, opposite(color), board);
						// Consider escaping by extending
						if (!board.isSelfAtari(liberty, color)) {
							recommend(liberty);
						}
					}
				}
			}
		}
	}
	
}
