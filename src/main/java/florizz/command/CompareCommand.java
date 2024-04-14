package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.FlowerDictionary;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import florizz.objects.Flower;

import java.util.ArrayList;

public class CompareCommand extends Command {

    private String firstFlowerName;
    private String secondFlowerName;

    public CompareCommand(String firstFlowerName, String secondFlowerName) {
        this.firstFlowerName = firstFlowerName;
        this.secondFlowerName = secondFlowerName;
    }

    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        ArrayList<Flower> firstFilteredFlowers = FlowerDictionary.filterByName(firstFlowerName);
        ArrayList<Flower> secondFilteredFlowers = FlowerDictionary.filterByName(secondFlowerName);

        if (firstFilteredFlowers.isEmpty() || secondFilteredFlowers.isEmpty()) {
            throw new FlorizzException("Flower does not exist, type 'flowers' for a list of flowers");
        }

        ui.printCompareFlowers(firstFilteredFlowers, secondFilteredFlowers);
        return true;
    }
}
