package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompareCommandTest {

    @Test
    void testCompareExecute() { // Comparing two different flowers
        CompareCommand testCompareCommand = new CompareCommand("Rose", "Lily");
        ArrayList<Bouquet> testList = new ArrayList<>();
        Ui ui = new Ui();
        try {
            assertTrue(testCompareCommand.execute(testList, ui));
        } catch (FlorizzException error) {
            ui.printError(error);
        }
    }

    @Test
    void testCompareException1() { // Comparing the same flower
        ArrayList<Bouquet> testList = new ArrayList<>();
        Ui ui = new Ui();
        CompareCommand testCompareCommand2 = new CompareCommand("Rose", "Rose");
        assertThrows(FlorizzException.class, () -> testCompareCommand2.execute(testList, ui));
    }

    @Test
    void testCompareException2() { // Comparing a flower that does not exist
        ArrayList<Bouquet> testList = new ArrayList<>();
        Ui ui = new Ui();
        CompareCommand testCompareCommand3 = new CompareCommand("Rose", "Tulip");
        assertThrows(FlorizzException.class, () -> testCompareCommand3.execute(testList, ui));
    }

    @Test
    void testCompareException3() { // Comparing empty flower names
        ArrayList<Bouquet> testList = new ArrayList<>();
        Ui ui = new Ui();
        CompareCommand testCompareCommand4 = new CompareCommand("", "");
        assertThrows(FlorizzException.class, () -> testCompareCommand4.execute(testList, ui));
    }
}
