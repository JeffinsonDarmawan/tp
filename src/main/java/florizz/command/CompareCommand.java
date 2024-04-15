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

    /**
     * Constructor for the CompareCommand class.
     *
     * @param firstFlowerName The name of the first flower to compare
     * @param secondFlowerName The name of the second flower to compare
     */
    public CompareCommand(String firstFlowerName, String secondFlowerName) {
        this.firstFlowerName = firstFlowerName;
        this.secondFlowerName = secondFlowerName;
    }

    /**
     * Executes the CompareCommand by comparing two flowers.
     *
     * @param bouquetList The list of bouquets to search for the specified bouquet
     * @param ui The user interface to interact with the user
     * @return True if the command is executed successfully, false otherwise
     */
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        if (firstFlowerName.equals(secondFlowerName)) {
            throw new FlorizzException("Unable to compare a flower with itself. Please input different flowers");
        }

        ArrayList<Flower> firstFilteredFlowers = FlowerDictionary.filterByName(firstFlowerName);
        ArrayList<Flower> secondFilteredFlowers = FlowerDictionary.filterByName(secondFlowerName);

        if (firstFilteredFlowers.isEmpty() || secondFilteredFlowers.isEmpty()) {
            throw new FlorizzException("Flower does not exist, type 'flowers' for a list of flowers");
        }

        ui.printCompareFlowers(firstFilteredFlowers, secondFilteredFlowers);
        return true;
    }
}
