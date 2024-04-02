package florizz.logging;

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

    public void trySaveAllBouquets(ArrayList<Bouquet> bouquetList) {
        try {
            FileWriter bouquetStorageWriter = new FileWriter(storagePath);
            saveAllBouquets(bouquetStorageWriter, bouquetList);
            bouquetStorageWriter.close();
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }

    public void saveAllBouquets(FileWriter w, ArrayList<Bouquet> list) throws IOException {
        for (Bouquet bouquet : list) {
            String bouquetName = bouquet.getBouquetName();
            w.write("new " + bouquetName + "\n");
            saveBouquet(bouquet, w);
        }
    }

    public void saveBouquet(Bouquet bouquet, FileWriter w)  throws IOException{
        String bouquetName = bouquet.getBouquetName();
        HashMap<Flower, Integer> tempHashMap = bouquet.getFlowerHashMap();
        for (Map.Entry<Flower, Integer> k : tempHashMap.entrySet()) {
            Flower flower = k.getKey();
            Integer quantity = k.getValue();
            w.write("add " + flower.getFlowerName() + " /q " + quantity + " /to " + bouquetName + "\n");
        }
    }
}
