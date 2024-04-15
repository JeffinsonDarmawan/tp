package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.FlowerDictionary;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import florizz.objects.Flower;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static florizz.objects.Flower.colourToString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecommendTest {
    private static final int SMALL_BOUQUET_FLOWER_COUNT = 1;
    private static final int MEDIUM_BOUQUET_FLOWER_COUNT = 3;
    private static final int LARGE_BOUQUET_FLOWER_COUNT = 5;
    private static final int SMALL_BOUQUET_FILLER_COUNT = 2;
    private static final int MEDIUM_BOUQUET_FILLER_COUNT = 4;
    private static final int LARGE_BOUQUET_FILLER_COUNT = 6;
    @Test
    void testRecommendCommand_addRandomFlowersSmallValentines() {
        Ui ui = new Ui();
        FlowerDictionary.startup();
        Flower.Occasion occasion = Flower.Occasion.VALENTINES;
        ArrayList<Flower> eligibleFlowersByOccasion = FlowerDictionary.filterByOccasion(occasion);
        Bouquet recommendedBouquet = new Bouquet("TestBouquet");
        // NOTE: testSize must be in lowercase. In actual case,
        // validation will be done before addRandomFlower is called.
        String testSize = "small";
        Flower.Colour testColour = Flower.Colour.WHITE;
        ArrayList<Flower> eligibleFlowersByOccasionAndColour =
                FlowerDictionary.filterByColour(eligibleFlowersByOccasion, testColour);
        RecommendCommand testCommand = new RecommendCommand();
        try {
            testCommand.addRandomFlowers(eligibleFlowersByOccasionAndColour, recommendedBouquet, testSize, testColour);
        } catch (FlorizzException e) {
            ui.printError(e);
        }

        // check if all flowers in recommendedBouquet have the correct colour
        for (Flower flower : recommendedBouquet.getFlowerList()) {
            assertTrue(flower.getColour().equalsIgnoreCase(testColour.toString())
                        || flower.getColour().equalsIgnoreCase("green"));
        }

        // check if the number of flowers in recommendedBouquet is correct
        assertEquals(SMALL_BOUQUET_FLOWER_COUNT + SMALL_BOUQUET_FILLER_COUNT,
                     recommendedBouquet.totalNumberOfFlowers());

        // check if the number of main flowers is correct
        int mainFlowerCount = 0;
        for (Flower flower : recommendedBouquet.getFlowerList()) {
            if (flower.getType() == Flower.Type.MAIN_FLOWER) {
                mainFlowerCount += recommendedBouquet.getFlowerHashMap().get(flower);
            }
        }
        assertEquals(SMALL_BOUQUET_FLOWER_COUNT, mainFlowerCount);

        // check if the number of filler flowers is correct
        int fillerFlowerCount = 0;
        for (Flower flower : recommendedBouquet.getFlowerList()) {
            if (flower.getType() == Flower.Type.FILLER_FLOWER) {
                fillerFlowerCount += recommendedBouquet.getFlowerHashMap().get(flower);
            }
        }
        assertEquals(SMALL_BOUQUET_FILLER_COUNT, fillerFlowerCount);
    }

    @Test
    void testRecommendCommand_addRandomFlowersMediumMothersDay() {
        Ui ui = new Ui();
        FlowerDictionary.startup();
        Flower.Occasion occasion = Flower.Occasion.MOTHERS_DAY;
        ArrayList<Flower> eligibleFlowersByOccasion = FlowerDictionary.filterByOccasion(occasion);
        Bouquet recommendedBouquet = new Bouquet("TestBouquet");
        String testSize = "medium";
        Flower.Colour testColour = Flower.Colour.PINK;
        ArrayList<Flower> eligibleFlowersByOccasionAndColour =
                FlowerDictionary.filterByColour(eligibleFlowersByOccasion, testColour);
        RecommendCommand testCommand = new RecommendCommand();
        try {
            testCommand.addRandomFlowers(eligibleFlowersByOccasionAndColour, recommendedBouquet, testSize, testColour);
        } catch (FlorizzException e) {
            ui.printError(e);
        }

        ArrayList<Bouquet> test = new ArrayList<>();
        test.add(recommendedBouquet);
        ui.printAllBouquets(test);

        // check if all flowers in recommendedBouquet have the correct colour
        for (Flower flower : recommendedBouquet.getFlowerList()) {
            assertTrue(flower.getColour().equalsIgnoreCase(testColour.toString())
                    || flower.getColour().equalsIgnoreCase("green"));
        }

        // check if the number of flowers in recommendedBouquet is correct
        assertEquals(MEDIUM_BOUQUET_FLOWER_COUNT + MEDIUM_BOUQUET_FILLER_COUNT,
                recommendedBouquet.totalNumberOfFlowers());

        // check if the number of main flowers is correct
        int mainFlowerCount = 0;
        for (Flower flower : recommendedBouquet.getFlowerList()) {
            if (flower.getType() == Flower.Type.MAIN_FLOWER) {
                mainFlowerCount += recommendedBouquet.getFlowerHashMap().get(flower);
            }
        }
        assertEquals(MEDIUM_BOUQUET_FLOWER_COUNT, mainFlowerCount);

        // check if the number of filler flowers is correct
        int fillerFlowerCount = 0;
        for (Flower flower : recommendedBouquet.getFlowerList()) {
            if (flower.getType() == Flower.Type.FILLER_FLOWER) {
                fillerFlowerCount += recommendedBouquet.getFlowerHashMap().get(flower);
            }
        }
        assertEquals(MEDIUM_BOUQUET_FILLER_COUNT, fillerFlowerCount);
    }

    @Test
    void testRecommendCommand_addRandomFlowersLargeFuneral() {
        Ui ui = new Ui();
        FlowerDictionary.startup();
        Flower.Occasion occasion = Flower.Occasion.FUNERAL;
        ArrayList<Flower> eligibleFlowersByOccasion = FlowerDictionary.filterByOccasion(occasion);
        Bouquet recommendedBouquet = new Bouquet("TestBouquet");
        String testSize = "large";
        Flower.Colour testColour = Flower.Colour.DARK_CRIMSON;
        ArrayList<Flower> eligibleFlowersByOccasionAndColour =
                FlowerDictionary.filterByColour(eligibleFlowersByOccasion, testColour);
        RecommendCommand testCommand = new RecommendCommand();
        try {
            testCommand.addRandomFlowers(eligibleFlowersByOccasionAndColour, recommendedBouquet, testSize, testColour);
        } catch (FlorizzException e) {
            ui.printError(e);
        }

        ArrayList<Bouquet> test = new ArrayList<>();
        test.add(recommendedBouquet);
        ui.printAllBouquets(test);

        // check if all flowers in recommendedBouquet have the correct colour
        for (Flower flower : recommendedBouquet.getFlowerList()) {
            assertTrue(flower.getColour().equalsIgnoreCase(colourToString(testColour))
                    || flower.getColour().equalsIgnoreCase("green"));
        }

        // check if the number of flowers in recommendedBouquet is correct
        assertEquals(LARGE_BOUQUET_FLOWER_COUNT + LARGE_BOUQUET_FILLER_COUNT,
                recommendedBouquet.totalNumberOfFlowers());

        // check if the number of main flowers is correct
        int mainFlowerCount = 0;
        for (Flower flower : recommendedBouquet.getFlowerList()) {
            if (flower.getType() == Flower.Type.MAIN_FLOWER) {
                mainFlowerCount += recommendedBouquet.getFlowerHashMap().get(flower);
            }
        }
        assertEquals(LARGE_BOUQUET_FLOWER_COUNT, mainFlowerCount);

        // check if the number of filler flowers is correct
        int fillerFlowerCount = 0;
        for (Flower flower : recommendedBouquet.getFlowerList()) {
            if (flower.getType() == Flower.Type.FILLER_FLOWER) {
                fillerFlowerCount += recommendedBouquet.getFlowerHashMap().get(flower);
            }
        }
        assertEquals(LARGE_BOUQUET_FILLER_COUNT, fillerFlowerCount);
    }
}
