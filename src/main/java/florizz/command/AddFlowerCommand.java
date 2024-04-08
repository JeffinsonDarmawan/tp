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

        if (matchingFlowers.isEmpty()) {
            throw new FlorizzException("Mentioned flower is not in our database." + System.lineSeparator() +
                                       "Check available flowers: `flower` " + System.lineSeparator() +
                                       "Add custom flowers: {{TO BE DONE}}");
        }
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
            Flower flowerToAdd = chooseColour(ui, matchingFlowers, flowerName);
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

    private Flower chooseColour(Ui ui, ArrayList<Flower> flowers, String flowerName){
        ui.printAddFlowerColour(flowers, flowerName);

        while (true){
            String colourInput = ui.getInput().trim().toLowerCase();
            try {
                switch (colourInput) {
                case "back":
                    ui.printBackPage();
                    break;
                case "next":
                    ui.printNextPage();
                    break;
                case "cancel":
                    return new Flower();
                default:
                    Flower.Colour chosenColour = Flower.stringToColour(colourInput);
                    ArrayList<Flower> chosenFlowerList = FlowerDictionary.filterByColour(flowers, chosenColour);
                    if (!chosenFlowerList.isEmpty()){
                        return chosenFlowerList.get(0);
                    } else {
                        throw new FlorizzException("This flower is not available in this colour, " +
                                "try typing a colour shown above!");
                    }

                }
            } catch(FlorizzException error){
                ui.printError(error);
            } catch(IllegalArgumentException error){
                System.out.println("Unrecognised input, type a colour that's available for this flower, " +
                        "or 'cancel' to stop adding a flower.");
            }
        }
    }
}
