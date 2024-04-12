package florizz.command;



import florizz.core.Ui;
import florizz.objects.Bouquet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpTest {
    @Test
    void executeHelpCommandTrue(){
        ArrayList<Bouquet> tempBouquetList = new ArrayList<>();
        Ui ui = new Ui();
        HelpCommand helpCommand = new HelpCommand();
        assertTrue(helpCommand.execute(tempBouquetList, ui));
    }
}
