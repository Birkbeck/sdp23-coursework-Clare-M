package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class JnzInstruction extends Instruction {
    private final RegisterName result;

    private final String testInput;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName result, String testInput) {
        super(label, OP_CODE);
        this.result = result;
        this.testInput = testInput;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getLabels().getAddress(testInput);
        System.out.println("PRINTING FROM JNZ INSTRUCTION");
        System.out.println(value2);
        // This should return the 'getAddress' number, if the register content is non-zero.
        if (value1 != 0){
            return value2;
        } else {
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " ";
    }
}
