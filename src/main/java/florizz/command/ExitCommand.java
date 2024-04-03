package florizz.command;

import florizz.core.Ui;
import florizz.objects.Bouquet;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ExitCommand extends Command {
    private Logger logger = Logger.getLogger(ExitCommand.class.getName());

    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) {
        logger.entering(ExitCommand.class.getName(), "execute");
        ui.printExitMessage();
        logger.exiting(ExitCommand.class.getName(), "execute");
        return false;
    }
}
