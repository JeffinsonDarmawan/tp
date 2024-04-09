package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.FlowerDictionary;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import florizz.objects.Flower;

import java.util.ArrayList;
import java.util.logging.Logger;

public class AddFlowerCommand extends Command{
    private static Logger logger = Logger.getLogger(AddFlowerCommand.class.getName());
    private String flowerName;
    private Flower.Colour colour;
    private boolean hasColour = false;
    private Integer quantity;
    private String bouquetName;
    private boolean enableUi;

    public AddFlowerCommand(String flowerName, int quantity, String bouquetName, boolean enableUi) {
        this.flowerName = flowerName;
        this.quantity = quantity;
        this.bouquetName = bouquetName;
        this.enableUi = enableUi;
    }
    public AddFlowerCommand(String flowerName, Flower.Colour colour,
                            int quantity, String bouquetName, boolean enableUi) {
        this.flowerName = flowerName;
        this.colour = colour;
        this.quantity = quantity;
        this.bouquetName = bouquetName;
        this.enableUi = enableUi;
        this.hasColour = true;
    }
    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        logger.entering(AddFlowerCommand.class.getName(), "execute");
        boolean doesBouquetExist = false;
        Bouquet bouquetToAddFlower = new Bouquet();
        for (int i = 0; !doesBouquetExist && i < bouquetList.size(); i++) {
            if (bouquetList.get(i).getBouquetName().equals(this.bouquetName)) {
                bouquetToAddFlower = bouquetList.get(i);
                doesBouquetExist = true;
            }
        }

        if (!doesBouquetExist) {
            throw new FlorizzException("No such bouquet is found.");
        }

        ArrayList<Flower> matchingFlowers = FlowerDictionary.filterByName(flowerName);

        if (hasColour){
            ArrayList<Flower> matchedFlowerAndColour = FlowerDictionary.filterByColour(matchingFlowers, colour);
            if (!matchedFlowerAndColour.isEmpty()){
                bouquetToAddFlower.addFlower(matchedFlowerAndColour.get(0),this.quantity);
                if (enableUi) {
                    ui.printAddFlowerSuccess(bouquetList, flowerName, quantity, bouquetName);
                }
            } else {
                throw new FlorizzException("This flower does not exist in that colour. " +
                        "Type info <flower> to view all available colours for this flower");
            }
        } else if (matchingFlowers.size()==1){
            bouquetToAddFlower.addFlower(matchingFlowers.get(0), this.quantity);
            if (enableUi) {
                ui.printAddFlowerSuccess(bouquetList, flowerName, quantity, bouquetName);
            }
        } else {
            Flower flowerToAdd = ui.chooseColour(matchingFlowers, flowerName);
            if (!flowerToAdd.getFlowerName().isBlank()){
                bouquetToAddFlower.addFlower(flowerToAdd, this.quantity);
                if (enableUi) {
                    ui.printAddFlowerSuccess(bouquetList, flowerName, quantity, bouquetName);
                }
            } else {
                ui.printCancelCommand();
            }

        }

        logger.exiting(AddFlowerCommand.class.getName(), "execute");
        return true;
    }

}
