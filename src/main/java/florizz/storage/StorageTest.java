package florizz.storage;

import florizz.core.FlorizzException;

public class StorageTest {
    public static void main(String[] args) {
        StorageManager storageManager = new StorageManager();
        try {
            storageManager.loadDatabase();
            storageManager.printAll();
        } catch (FlorizzException e) {
            throw new RuntimeException(e);
        }
    }

}
