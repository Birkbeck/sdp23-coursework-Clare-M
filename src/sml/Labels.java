package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
		int labelIndex = getAddress(label);
		// getAddress will output -1 if the label does not exist.
		if (labelIndex != -1) {
			throw new IncorrectLabelException("Duplicate label cannot be added, label " + label + " already exists.");
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
		// NullPointerException will be thrown where the label doesn't exist. This is handled by returning -1.
		try {
			return labels.get(label);
		} catch (NullPointerException e) {
			return -1;
		}
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " = " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]")) ;
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
