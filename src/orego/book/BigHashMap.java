package orego.book;

import static java.lang.Math.*;
import static orego.core.SuperKoTable.IGNORE_SIGN_BIT;
import java.io.*;

/**
 * Associates keys (longs, Zobrist hashes of board configurations) with values.
 * The underlying data structure is a hash table with linear probing.
 * 
 * This mimics java.util.HashMap<Long, V>, but it does not support removal
 * and is significantly more space-efficient.
 */
public class BigHashMap<V> implements Serializable {

	/** For serialization. */
	private static final long serialVersionUID = 1L;

	/** Keys. */
	private long[] keys;

	/** How many keys are currently in the map. */
	private int size;

	/** Values associated with keys. */
	private V[] values;

	@SuppressWarnings("unchecked")
	public BigHashMap() {
		keys = new long[1];
		// The next line would give a warning
		values = (V[]) (new Object[1]);
	}

	/** True if this map contains key. */
	public boolean containsKey(long key) {
		int slot = (((int) key) & IGNORE_SIGN_BIT) % keys.length;
		while (true) {
			if ((keys[slot] == key) && (values[slot] != null)) {
				return true;
			} else if (values[slot] == null) {
				return false;
			}
			slot = (slot + 1) % keys.length;
		}
	}

	/** Returns the value associated with key. */
	public V get(long key) {
		int slot = (((int) key) & IGNORE_SIGN_BIT) % keys.length;
		while (true) {
			if ((keys[slot] == key) && (values[slot] != null)) {
				return values[slot];
			} else if (values[slot] == null) {
				return null;
			}
			slot = (slot + 1) % keys.length;
		}
	}

	/**
	 * Returns the raw key array for this hash map. This may contain many null
	 * entries, which are NOT valid keys.
	 */
	public long[] getKeys() {
		return keys;
	}

	/**
	 * Associates key with value, stretching the map if it is too full.
	 */
	public void put(long key, V value) {
		if (!containsKey(key)) {
			size++;
			// The maximum load factor is 0.5
			if (size > keys.length / 2) {
				rehash();
			}
		}
		putAfterTableKnownLargeEnough(key, value);
	}

	/**
	 * Associates key with value. Does not check that this map is large enough.
	 */
	protected void putAfterTableKnownLargeEnough(long key, V value) {
		int slot = (((int) key) & IGNORE_SIGN_BIT) % keys.length;
		while (true) {
			if ((keys[slot] == key) || (values[slot] == null)) {
				keys[slot] = key;
				values[slot] = value;
				return;
			}
			slot = (slot + 1) % keys.length;
		}
	}

	/**
	 * Copies the data into tables twice as large, stretching the map.
	 */
	@SuppressWarnings("unchecked")
	protected void rehash() {
		long[] oldKeys = keys;
		V[] oldValues = values;
		keys = new long[keys.length * 2];
		// The next line would give a warning
		values = (V[]) (new Object[values.length * 2]);
		for (int i = 0; i < oldKeys.length; i++) {
			if (oldValues[i] != null) {
				putAfterTableKnownLargeEnough(oldKeys[i], oldValues[i]);
			}
		}
	}

	/** Returns the number of keys currently in the map. */
	public int size() {
		return size;
	}

}
