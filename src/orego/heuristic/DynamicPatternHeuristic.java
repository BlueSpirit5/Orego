package orego.heuristic;

import java.io.*;
import java.util.HashMap;
import orego.patternanalyze.*;
import static orego.core.Colors.VACANT;
import static orego.core.Coordinates.getNeighbors;
import static orego.experiment.Debug.*;

import orego.core.Board;

public class DynamicPatternHeuristic extends Heuristic {

	private static HashMap<Long, DynamicPattern> patternList;
	
	private static int PATTERNS_TO_LOAD = 100;
	
	static {
		patternList = new HashMap<Long, DynamicPattern>();
		extractPatternsFromFile(OREGO_ROOT_DIRECTORY+File.separator+"testFiles/patternPlayed8.dat", patternList);
		extractPatternsFromFile(OREGO_ROOT_DIRECTORY+File.separator+"testFiles/patternPlayed12.dat", patternList);
		extractPatternsFromFile(OREGO_ROOT_DIRECTORY+File.separator+"testFiles/patternPlayed20.dat", patternList);
		extractPatternsFromFile(OREGO_ROOT_DIRECTORY+File.separator+"testFiles/patternPlayed24.dat", patternList);
	}
	
	public DynamicPatternHeuristic(int weight) {
		super(weight);
	}

	private static void extractPatternsFromFile(String fileName, HashMap<Long, DynamicPattern> patternList) {
		ObjectInputStream input;
		try {
			input = new ObjectInputStream(new FileInputStream(
					new File(fileName)));
			DynamicPattern pattern = null;
			try {
				int counter = 0;
				while ((pattern = (DynamicPattern) input.readObject()) != null && counter < PATTERNS_TO_LOAD) {
					for (int i = 0; i < 8; i++){
						patternList.put(pattern.getPattern()[i], pattern);
					}
					counter++;
				}
				input.close();
			} catch (EOFException ex) {
				input.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}

	public int evaluate(int p, Board board) {
		long pattern24 = DynamicPattern.setupPattern(p, board, 24);
		long pattern20 = pattern24 & (~(255L << 40));
		long pattern12 = pattern20 & (~((255L << 24) | (255L << 32)));
		long pattern8 = pattern12 & (~(255L << 16));
		if (patternList.get(pattern8) != null) {
			return 1;
		}
		if (patternList.get(pattern12) != null) {
			return 1;
		}
		if (patternList.get(pattern20) != null) {
			return 1;
		}
		if (patternList.get(pattern24) != null) {
			return 1;
		}
		return 0;
	}
	
	public void prepare(Board board) {
		super.prepare(board);
		for (int p : getNeighbors(board.getMove(board.getTurn() - 1))) {
			if (board.getColor(p) == VACANT) {
				int playValue = evaluate(p, board);
				if(playValue > 0) {
					recommend(p);
				}
			}
		}
	}

	@Override
	public DynamicPatternHeuristic clone() {
		return (DynamicPatternHeuristic) super.clone();
	}

}
