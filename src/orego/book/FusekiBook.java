package orego.book;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;
import orego.core.Board;
import static orego.experiment.Debug.*;
import static orego.core.Coordinates.*;

/**
 * Selects the most popular move given the current whole-board state.
 */
public class FusekiBook implements OpeningBook, Serializable {

	/** For serialization. */
	private static final long serialVersionUID = 1L;

	/** The fuseki book proper. */
	private Map<Long, Integer> book;

	/** Gets the hashMap out of the file. */
	public FusekiBook() {
		this("SgfFiles");
	}

	
	/** Gets the Fuseki Book from a data file */
	@SuppressWarnings("unchecked")
	public FusekiBook(String directory) {
		try {
			directory = OREGO_ROOT_DIRECTORY + directory + File.separator
					+ getBoardWidth();
			File file = new File(directory + File.separator + "Fuseki"
					+ getBoardWidth() + ".data");
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream(file));
			book = (HashMap<Long, Integer>) (in.readObject());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public int nextMove(Board board) {
		long boardHash = board.getHash();
		if (board.getTurn() < BookBuilder.MAX_BOOK_DEPTH) {
			if (book.containsKey(boardHash)) {
				int move = book.get(boardHash);
				if (board.isLegal(move)) {
					debug(pointToString(move)
							+ " was generated by the fuseki book");
					return move;
				}
			}
		}
		return NO_POINT;
	}

}
