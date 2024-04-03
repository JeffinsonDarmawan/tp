package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.FlowerDictionary;
import florizz.core.Parser;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import florizz.objects.Flower;

import java.util.ArrayList;

public class RecommendCommand extends Command{
    private Bouquet bouquetToRecommend;
    public RecommendCommand() {
        this.bouquetToRecommend = new Bouquet();
    }

    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        // ask for occasion
        String occasion = askOccasion(ui);

        // ask for colour preference
        String colour = askColour(ui);

        // ask for size

        // ask if they want to save bouquet to array

        return true;
    }

    private String askOccasion(Ui ui) throws FlorizzException {
        String occasionInput = Parser.parseOccasion(ui.printAskOccasion());

        // check if occasion is in our dictionary
        Flower.Occasion occasionEnum = Flower.stringToOccasion(occasionInput);
        ArrayList<Flower> filteredFlowers = FlowerDictionary.filterByOccasion(occasionEnum);

        return filteredFlowers.toString();
    }

    private String askColour(Ui ui) throws FlorizzException {
        return "Red";
    }
}
