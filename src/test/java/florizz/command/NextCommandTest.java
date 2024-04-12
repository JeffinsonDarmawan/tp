package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.FlowerDictionary;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import florizz.objects.Flower;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NextCommandTest {

    @Test
    void testNextExecuteNoNextPage() { // Valentines only has one page
        ArrayList<Bouquet> testBouquetList = new ArrayList<>();
        Ui ui = new Ui();
        NextCommand nextCommand = new NextCommand();
        Flower.Occasion occasionEnum = Flower.stringToOccasion("Valentines");
        ui.printFilteredFlowers(FlowerDictionary.filterByOccasion(occasionEnum),"Valentines", 1);
        assertThrows(FlorizzException.class, () -> nextCommand.execute(testBouquetList, ui));
    }
}
