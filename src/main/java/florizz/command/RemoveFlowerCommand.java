package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.FlowerDictionary;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import florizz.objects.Flower;

import java.util.ArrayList;
import java.util.logging.Logger;

public class RemoveFlowerCommand extends Command {
    private static Logger logger = Logger.getLogger(RemoveFlowerCommand.class.getName());
    private String flowerName;
    private Integer quantity;
    private String bouquetName;
    private Flower.Colour colour;
    private boolean hasColour = false;

    public RemoveFlowerCommand(String flowerName, int quantity, String bouquetName) {
        this.flowerName = flowerName;
        this.quantity = quantity;
        this.bouquetName = bouquetName;
    }

    public RemoveFlowerCommand(String flowerName, Flower.Colour colour, int quantity, String bouquetName) {
        this.flowerName = flowerName;
        this.quantity = quantity;
        this.bouquetName = bouquetName;
        this.colour = colour;
        hasColour = true;
    }
    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        logger.entering(RemoveFlowerCommand.class.getName(), "execute");
        assert !bouquetList.isEmpty() : "Bouquet list should not be empty";
        boolean doesBouquetExist = false;
        Bouquet bouquetToRemoveFlower = new Bouquet();
        for (int i = 0; !doesBouquetExist && i < bouquetList.size(); i++) {
            if (bouquetList.get(i).getBouquetName().equals(this.bouquetName)) {
                bouquetToRemoveFlower = bouquetList.get(i);
                doesBouquetExist = true;
            }
        }

        if (!doesBouquetExist) {
            throw new FlorizzException("ERROR: No such bouquet is found." + System.lineSeparator() +
                    "Create a bouquet by using 'new <bouquetName>`");
        }

        ArrayList<Flower> matchingFlowers = FlowerDictionary.filterByName(
                bouquetToRemoveFlower.getFlowerList(), flowerName);
        Flower flowerToRemove;
        if (hasColour){
            ArrayList<Flower> matchedFlowerAndColour = FlowerDictionary.filterByColour(matchingFlowers, colour);
            if (!matchedFlowerAndColour.isEmpty()){
                flowerToRemove = matchedFlowerAndColour.get(0);
                bouquetToRemoveFlower.removeFlower(flowerToRemove,this.quantity);
                ui.printRemoveFlowerSuccess(bouquetList, flowerToRemove.getNameAndColour(), quantity, bouquetName);
            } else {
                throw new FlorizzException("This bouquet does not contain that colour" +
                        "Type mybouquets to view all your bouquets.");
            }
        } else if (matchingFlowers.size()==1){
            flowerToRemove = matchingFlowers.get(0);
            bouquetToRemoveFlower.removeFlower(flowerToRemove, this.quantity);
            ui.printRemoveFlowerSuccess(bouquetList, flowerToRemove.getNameAndColour(), quantity, bouquetName);

        } else {
            flowerToRemove = ui.chooseColour(matchingFlowers, flowerName);
            if (!flowerToRemove.getFlowerName().isBlank()){
                bouquetToRemoveFlower.removeFlower(flowerToRemove, this.quantity);
                ui.printRemoveFlowerSuccess(bouquetList, flowerToRemove.getNameAndColour(), quantity, bouquetName);
            } else {
                ui.printCancelCommand();
            }

        }

        logger.exiting(RemoveFlowerCommand.class.getName(), "execute");
        return true;
    }


}
