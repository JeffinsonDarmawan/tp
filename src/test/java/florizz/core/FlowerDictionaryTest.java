package florizz.core;

import florizz.objects.Flower;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlowerDictionaryTest {
    @Test
    void testFilterByOccasion() {
        FlowerDictionary.startup();
        ArrayList<Flower> filteredFlowers = new ArrayList<>();
        filteredFlowers = FlowerDictionary.filterByOccasion(Flower.Occasion.FUNERAL);
        // check if all flowers in filteredFlowers have the correct occasion
        for (Flower flower : filteredFlowers) {
            assertTrue(flower.getOccasion().contains(Flower.Occasion.FUNERAL));
        }
    }

    @Test
    void testFilterByName() {
        FlowerDictionary.startup();
        Ui ui = new Ui();
        ArrayList<Flower> filteredFlowers = new ArrayList<>();
        try {
            filteredFlowers = FlowerDictionary.filterByName("Rose");
        } catch (FlorizzException e) {
            ui.printError(e);
        }
        // check if all flowers in filteredFlowers have the correct name
        for (Flower flower : filteredFlowers) {
            assertEquals("Rose", flower.getFlowerName());
        }
    }

    @Test
    void testFilterByColour() {
        FlowerDictionary.startup();
        ArrayList<Flower> filteredFlowers = new ArrayList<>();
        filteredFlowers = FlowerDictionary.filterByColour(Flower.Colour.RED);
        // check if all flowers in filteredFlowers have the correct colour
        for (Flower flower : filteredFlowers) {
            assertEquals(Flower.Colour.RED, flower.getColour());
        }
    }

    @Test
    void testFilterByColourWithList() {
        FlowerDictionary.startup();
        ArrayList<Flower> filteredFlowers = new ArrayList<>();
        ArrayList<Flower> listOfFlowers = new ArrayList<>();
        listOfFlowers = FlowerDictionary.filterByOccasion(Flower.Occasion.FUNERAL);
        filteredFlowers = FlowerDictionary.filterByColour(listOfFlowers, Flower.Colour.WHITE);
        // check if all flowers in filteredFlowers have the correct colour
        for (Flower flower : filteredFlowers) {
            assertEquals(Flower.Colour.WHITE.toString(), flower.getColour().toUpperCase());
        }
    }
}
