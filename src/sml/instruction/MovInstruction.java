package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * This class stores the value x into register r.
 * @author Clare Melvin
 */

public class MovInstruction extends Instruction {
    private final int inputInt;
    private final RegisterName source;

    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName source, int inputInt) {
        super(label, OP_CODE);
        this.inputInt = inputInt;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value1 = this.inputInt;
        RegisterName value2 = this.source;
        //int value2 = m.getRegisters().get(source);
        m.getRegisters().set(value2, value1);
        //m.getRegisters().set(result, value1 + value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + inputInt;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MovInstruction;
    }
}
