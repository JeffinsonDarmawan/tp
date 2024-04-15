package florizz.objects;

import florizz.core.FlorizzException;
import florizz.core.FlowerDictionary;
import florizz.core.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BouquetTest {
    @Test
    void testToString() {
        Bouquet testBouquet =  new Bouquet("For Mom");
        assertEquals("For Mom", testBouquet.toString());
    }

    @Test
    void testEquals(){
        Bouquet testBouquet1 = new Bouquet ("For Mom");
        Bouquet testBouquet2 = new Bouquet ("For Mom");
        Bouquet testBouquet3 = new Bouquet ("For GF");
        assertEquals(testBouquet1, testBouquet2);
        assertNotEquals(testBouquet1, testBouquet3);
    }

    @Test
    void testBouquetSize() {
        int quantity = 3;
        FlowerDictionary.startup();
        Ui ui = new Ui();
        Bouquet testBouquet = new Bouquet();
        Flower flowerToAdd = FlowerDictionary.get(1);
        try {
            testBouquet.addFlower(flowerToAdd, quantity);
        } catch (FlorizzException e) {
            ui.printError(e);
        }

        // check for size
        assertEquals(quantity, testBouquet.totalNumberOfFlowers());
    }
}
