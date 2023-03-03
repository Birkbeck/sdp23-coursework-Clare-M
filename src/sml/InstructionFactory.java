package sml;

public interface InstructionFactory {

    public Instruction createInstruction(String opcode, String r, String s, String label);
}
