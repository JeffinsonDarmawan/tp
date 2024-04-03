package florizz.storage;

import florizz.command.Command;
import florizz.core.FlorizzException;
import florizz.core.Parser;
import florizz.core.Ui;
import florizz.objects.Bouquet;
import florizz.objects.Flower;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Class that deals with creating directories if they do not exist for storage and
 * writing to txt files to store the data from run time
 */
public class Storage {
    private File bouquetStorage;
    private final String storagePath = "./florizz-out/data/FlorizzBouquets.txt";


    public Storage() {
        this.bouquetStorage = new File(storagePath);
        if (!bouquetStorage.exists()) {
            try {
                Files.createDirectories(Paths.get("./florizz-out/data/"));
                Files.createDirectory(Paths.get("./florizz-out/logs/"));
                Files.createFile(Paths.get(storagePath));
            } catch (IOException e) {
                System.out.println("File not created");
            }
        }
    }

    /**
     * Tries to save all bouquets to a txt file
     * catches an exception if it is unable to do so
     * @param bouquetList list of bouquets to try and save
     */
    public void trySaveAllBouquets(ArrayList<Bouquet> bouquetList) {
        try {
            FileWriter bouquetStorageWriter = new FileWriter(storagePath);
            saveAllBouquets(bouquetStorageWriter, bouquetList);
            bouquetStorageWriter.close();
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }

    /**
     * Saves all bouquets in the bouquetList to a txt file
     * @param bouquetStorageWriter A FileWriter that writes into the storage txt file
     * @param bouquetList An ArrayList that contains all the bouquets added during run time
     * @throws IOException Thrown when the file to write to does not exist
     */
    public void saveAllBouquets(FileWriter bouquetStorageWriter, ArrayList<Bouquet> bouquetList) throws IOException {
        for (Bouquet bouquet : bouquetList) {
            String bouquetName = bouquet.getBouquetName();
            bouquetStorageWriter.write("new " + bouquetName + "\n");
            saveBouquet(bouquet, bouquetStorageWriter);
        }
    }

    /**
     * Saves the flower, quantity and target bouquet to a txt file
     * text is formatted beforehand to allow the bouquets to be added back next time florizz is run
     * @param bouquet Target bouquet to be added to txt file
     * @param bouquetStorageWriter FileWriter that writes to a txt file
     * @throws IOException Thrown when the target file to write to does not exist
     */
    public void saveBouquet(Bouquet bouquet, FileWriter bouquetStorageWriter)  throws IOException{
        String bouquetName = bouquet.getBouquetName();
        HashMap<Flower, Integer> tempHashMap = bouquet.getFlowerHashMap();
        for (Map.Entry<Flower, Integer> k : tempHashMap.entrySet()) {
            Flower flower = k.getKey();
            Integer quantity = k.getValue();
            bouquetStorageWriter.write("add " + flower.getFlowerName() +
                    " /q " + quantity + " /to " + bouquetName + "\n");
        }
    }

    /**
     * Attempts to read stored bouquets in txt file
     * @param bouquetList List of bouquets to be populated at the start of the programme
     */
    public void tryReadStoredBouquets(ArrayList<Bouquet> bouquetList) {
        try {
            readStoredBouquets(bouquetList);
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (FlorizzException fe) {
            System.out.println("");
        }
    }

    /**
     * Reads from txt file to populate bouquet list with previously made bouquets
     * @param bouquetList Empty bouquet list to be populated
     * @throws IOException Thrown when the txt file cannot be found
     * @throws FlorizzException Thrown when an invalid command is given
     */
    public void readStoredBouquets(ArrayList<Bouquet> bouquetList) throws IOException, FlorizzException {
        Scanner in = new Scanner(bouquetStorage);
        Command command;
        boolean isRunning;
        Ui ui = new Ui();
        while (in.hasNext()) {
            String input = in.nextLine();
            command = Parser.parse(input, false);
            isRunning = command.execute(bouquetList, ui);
        }
        in.close();
    }
}
