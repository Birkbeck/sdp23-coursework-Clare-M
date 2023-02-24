package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents Labels. Labels are used in the SML language as tags associated with instructions, these
 * instructions can then be found from the given label.
 * @author Clare Melvin
 */
public final class Labels {
	public static Class<? extends Throwable> IncorrectLabelException;
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) throws Exception {
		Objects.requireNonNull(label);
		// TODO: Add a check that there are no label duplicates.
		int labelIndex = getAddress(label);
		if (labelIndex != -1) {
			//System.out.println("Label already exists, a duplicate cannot be added");
			throw new IncorrectLabelException("Duplicate label cannot be added");
		}
		labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		//       Add code to deal with non-existent labels.
//		if (labels.get(label) == null){
//			return -1;
//		}
		try {
			return labels.get(label);
		} catch (NullPointerException e) {
			System.out.println("The label " + label + " was not found");
			//throw new Exception("The label was not found so an error was thrown");
		}
		return -1;
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers).
		return "";
	}

	// TODO: Implement equals and hashCode (needed in class Machine).

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}

	public class IncorrectLabelException extends Exception {
		public IncorrectLabelException(String message) {
			super(message);
		}
	}
}
