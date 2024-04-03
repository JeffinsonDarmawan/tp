package florizz.command;

import florizz.core.FlorizzException;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import florizz.storage.Storage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SaveCommand extends Command{

    private final String bouquetName;
    private final String savePath;
    private final File saveFile;

    public SaveCommand(String bouquetName) {
        this.bouquetName = bouquetName.trim();
        this.savePath = "./florizz-out/saved/" + bouquetName.trim() + ".txt";
        this.saveFile = new File(savePath);
    }

    /**
     * Executes command to save a bouquet externally
     * @param bouquetList list of bouquets to be manipulated by the command
     * @param ui ui class for printing
     * @return True if command is successfully executed
     * @throws FlorizzException If the bouquet does not exist
     */
    @Override
    public boolean execute(ArrayList<Bouquet> bouquetList, Ui ui) throws FlorizzException {
        int bouquetIdx = bouquetList.indexOf(new Bouquet(bouquetName));
        if (bouquetIdx == -1) {
            throw new FlorizzException("This bouquet does not exist. Create it by typing 'new <BOUQUETNAME>'");
        }
        Storage storage = new Storage();
        try {
            Bouquet bouquetToAdd = bouquetList.get(bouquetIdx);
            if (!saveFile.exists()) {
                Files.createFile(Paths.get(savePath));
            }
            FileWriter bouquetSaver = new FileWriter(savePath);
            storage.saveBouquet(bouquetToAdd, bouquetSaver);
            bouquetSaver.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
        ui.printSaveSuccess(bouquetName);
        return true;
    }
}
