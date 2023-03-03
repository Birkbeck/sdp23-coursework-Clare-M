package sml;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static sml.Registers.Register;

public class SmlInstructionFactory implements InstructionFactory {
    private String line = "";
    @Override
    public Instruction createInstruction(String opcode, String r, String s, String label) {
        String opcodePrefix = opcode.substring(0, 1).toUpperCase() + opcode.substring(1);
        Class<?> c;
        try {
            c = Class.forName("sml.instruction." + opcodePrefix + "Instruction");
            Constructor<?>[] constructors = c.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Constructor<?> con = c.getConstructor(parameterTypes);
                Object[] args = null;
                // Determine which arguments are needed, based on parameter types.
                if (parameterTypes.length == 2) {
                    args = new Object[] {label, Register.valueOf(r)};
                } else if (parameterTypes[2] == int.class) {
                    args = new Object[] {label, Register.valueOf(r), Integer.parseInt(s)};
                } else if (parameterTypes[2] == RegisterName.class) {
                    args = new Object[] {label, Register.valueOf(r), Register.valueOf(s)};
                } else if (parameterTypes[2] == String.class) {
                    args = new Object[] {label, Register.valueOf(r), s};
                }
                return (Instruction) con.newInstance(args);
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            System.out.println(e);
        }
        return null;
    }

    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}
