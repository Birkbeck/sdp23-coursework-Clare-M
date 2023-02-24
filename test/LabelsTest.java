
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Machine;
import sml.Registers;
import sml.Labels;


public class LabelsTest {
    private Machine machine;
    private Registers registers;
    private Labels labels;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        labels = machine.getLabels();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        labels = null;
    }

    @Test
    void duplicateLabelFailure() throws Exception {
        labels.addLabel("l1", 1);
        labels.addLabel("l2", 2);
        Assertions.assertThrows(Labels.IncorrectLabelException.class, () -> { labels.addLabel("l1", 3); });
    }

}
