package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.FlowerDictionary;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import florizz.objects.Flower;

import java.util.ArrayList;
import java.util.logging.Logger;

public class CompareCommand extends Command {
    private static Logger logger = Logger.getLogger(CompareCommand.class.getName());
    private String flowerNameOne;
    private String flowerNameTwo;
    public CompareCommand (String flowerNameOne, String flowerNameTwo) {
        this.flowerNameOne = flowerNameOne;
        this.flowerNameTwo = flowerNameTwo;
    }
    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        logger.entering(CompareCommand.class.getName(), "execute");

        ArrayList<Flower> matchingFlowersOne = FlowerDictionary.filterByName(flowerNameOne);
        ArrayList<Flower> matchingFlowersTwo = FlowerDictionary.filterByName(flowerNameTwo);



        logger.exiting(CompareCommand.class.getName(), "execute");
        return true;
    }
}
