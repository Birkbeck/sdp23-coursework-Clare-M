package sml;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * This class translates the program instructions to their internal form, to allow the instructions to be processed.
 *
 * @author Clare Melvin
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException, Labels.IncorrectLabelException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        try {
                            labels.addLabel(label, program.size());
                        } catch (Labels.IncorrectLabelException e) {
                            System.out.println("Error:" + e.getMessage());
                            throw e;
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        String r = scan();
        String s = scan();

        // Load the beans xml file from the resources folder.
        BeanFactory context = new ClassPathXmlApplicationContext("/beans.xml");
        SmlInstructionFactory factory = (SmlInstructionFactory) context.getBean("instructionFactory");
        Instruction instruction = factory.createInstruction(opcode, r, s, label);

        return instruction;
    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
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