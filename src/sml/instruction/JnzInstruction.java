package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * This class implements the jnz command, which checks the content of register r, and assigns statement with label L
 * to be the next statement to be executed if the content of r is non-zero.
 * @author Clare Melvin
 */

public class JnzInstruction extends Instruction {
    private final RegisterName result;

    private final String labelString;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName result, String labelString) {
        super(label, OP_CODE);
        this.result = result;
        this.labelString = labelString;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getLabels().getAddress(labelString);
        // This should return the 'getAddress' number, if the register content is non-zero.
        if (value1 != 0){
            return value2;
        } else {
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + labelString;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof JnzInstruction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, labelString);
    }
}
