package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.Ui;
import florizz.objects.Bouquet;

import java.util.ArrayList;

public class AddBouquetCommand extends Command{
    private final Bouquet bouquetToAdd;
    private final boolean enableUi;
    public AddBouquetCommand(Bouquet bouquetToAdd, boolean enableUi){
        this.bouquetToAdd = bouquetToAdd;
        this.enableUi = enableUi;

    }
    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        if (bouquetList.contains(bouquetToAdd)){
            throw new FlorizzException("Tried to add bouquet already in list");
        }
        bouquetList.add(bouquetToAdd);
        if (enableUi) {
            ui.printBouquetAdded(bouquetToAdd);
        }
        assert !bouquetList.isEmpty() : "Bouquet list should not be empty";
        return true;
    }
}
