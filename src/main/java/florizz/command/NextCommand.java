package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.Ui;
import florizz.objects.Bouquet;

import java.util.ArrayList;

public class NextCommand extends Command{

    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        ui.printNextPage();
        return true;
    }
}
