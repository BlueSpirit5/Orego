package orego.patternanalyze;

import static orego.core.Coordinates.*;

public class PatternCounter6 extends PatternCounter5 {
	
	public PatternCounter6() {
		super();
		outputFile = "BadPatterns";
	}

	@Override
	protected int[] getPointsToAnalyze(int p) {
		return ALL_POINTS_ON_BOARD;
	}
	
}