package florizz.core;

import florizz.objects.Bouquet;
import florizz.objects.Flower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Ui {
    private static ArrayList<Flower> lastShownList = new ArrayList<>();
    private static int lastPageNo = 0;
    private static final int PAGE_SIZE = 5;
    private final Scanner inputScanner = new Scanner (System.in);




    private String lastCommand = "";
    /**
     * Prints the introductory message.
     */
    public void printIntroMessage(){
        String logo = "\n" +
                "   __ _            _\n" +
                "  / _| |          (_)\n" +
                " | |_| | ___  _ __ _ ________\n" +
                " |  _| |/ _ \\| '__| |_  /_  /\n" +
                " | | | | (_) | |  | |/ / / /\n" +
                " |_| |_|\\___/|_|  |_/___/___|\n" +
                "\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a break line.
     */
    public static void printBreakLine(){
        System.out.println(("____________________________________________________________"));
    }

    /**
     * Gets input from the user typing commands into the CLI.
     * @return Returns the user input as one String.
     */
    public String getInput(){
        System.out.println("What can I do for you?");
        return inputScanner.nextLine();
    }

    /**
     * Prints the message indicating a new bouquet has been added.
     * @param bouquetAdded The bouquet that has been added.
     */
    public void printBouquetAdded(Bouquet bouquetAdded){
        lastCommand = "OTHERS";
        System.out.println("Added new bouquet to list: \n" + bouquetAdded);
        printBreakLine();

    }

    /**
     * Prints the message indicating a bouquet has been deleted.
     * @param bouquetDeleted The bouquet that has been deleted.
     */
    public void printBouquetDeleted(Bouquet bouquetDeleted){
        lastCommand = "OTHERS";
        System.out.println("Deleted bouquet: \n" + bouquetDeleted);
        printBreakLine();

    }

    /**
     * Prints all saved bouquets with their flowers and total estimated price.
     * @param bouquetList The list of saved bouquets.
     */
    public void printAllBouquets(ArrayList<Bouquet> bouquetList){
        lastCommand = "BOUQUETS";
        System.out.println("Here is the list of your saved bouquets:");
        int i = 1;
        double totalPrice;
        for (Bouquet bouquet : bouquetList){
            System.out.println(i++ + ". " + bouquet + " :");
            HashMap<Flower, Integer> flowerHashMap = bouquet.getFlowerHashMap();
            totalPrice = 0;
            if (!flowerHashMap.isEmpty() ) {
                for (Flower j : flowerHashMap.keySet()) {
                    if (flowerHashMap.get(j) != 0) {
                        System.out.println("    - " + flowerHashMap.get(j) + " x " + j.getFlowerName());
                        totalPrice += (flowerHashMap.get(j) * j.getPrice());
                    }
                }
                System.out.println("  Total estimated price = $" + String.format("%.2f", (double) totalPrice));
            } else {
                System.out.println("      No flowers added so far");
            }

        }
        printBreakLine();
    }

    /**
     * Prints all flowers in a bouquet.
     * @param bouquet The bouquet to print.
     */
    public void printFullBouquet(Bouquet bouquet) {
        System.out.println("Here is the full list of flowers in " + bouquet.getBouquetName() + ":");
        HashMap<Flower, Integer> flowerHashMap = bouquet.getFlowerHashMap();
        if (!flowerHashMap.isEmpty()) {
            for (Flower j : flowerHashMap.keySet()) {
                System.out.println("    - " + flowerHashMap.get(j) + " x " + j.getFlowerName());
            }
        } else {
            System.out.println("      No flowers added so far");
        }
        printBreakLine();
    }

    /**
     * print all available command
     */
    public void printHelpMessage() {
        lastCommand = "OTHERS";
        System.out.println("Here are the list of commands you can use:");
        System.out.println("1. new <bouquetName> - Add a bouquet");
        System.out.println("2. delete <bouquetName> - Delete a bouquets");
        System.out.println("3. mybouquets - List current saved bouquets");
        System.out.println("4. info <flowerName> - Provide information on chosen flower");
        System.out.println("5. add <flowerName> /q <quantity> /to <bouquetName> - add flower to a bouquet");
        System.out.println("6. remove <flowerName> /q <quantity> /from <bouquetName> - remove flower from a bouquet");
        System.out.println("7. flowers - Shows a list of flowers that can be added into mybouquets");
        System.out.println("8. flowers <occasion> - Shows a list of flowers associated with said occasion");
        System.out.println("9. occasion - Shows a list of occasions associated with available flowers");
        System.out.println("10. save <bouquetName> - Saves a bouquet to an external <bouquetName>.txt file");
        System.out.println("11. recommend - Recommends a bouquet based on the chosen occasion and colour");
        System.out.println("12. bye - Exits the programme");
        printBreakLine();
    }

    /**
     * Prints error message thrown by Florizz Exception.
     *
     * @param error
     */
    public void printError(FlorizzException error){
        System.out.println(error.errorMessage);
        printBreakLine();
    }

    /**
     * Prints exit message.
     */
    public void printExitMessage() {
        System.out.println("Enjoy your bouquet! Thank you for using Florizz");
        printBreakLine();
    }

    private static void printNextOrBack(int pageNo, int maxPages){
        if (pageNo < maxPages && pageNo > 1){
            System.out.println("Type 'next' to go to the next page, or 'back' to go to the previous page.");
        } else if (pageNo < maxPages){
            System.out.println("Type 'next' to go to the next page.");
        } else if (pageNo > 1){
            System.out.println("Type 'back' to go to the previous page.");
        }
    }

    private static void printFlowerList(boolean needsInfo){
        int maxPages = (int) Math.ceil((double)lastShownList.size() / PAGE_SIZE);
        for (int i = (lastPageNo-1)*PAGE_SIZE; i < Math.min(lastPageNo*PAGE_SIZE, lastShownList.size()); i++) {
            if (needsInfo) {
                System.out.println(i+1 + ". " + lastShownList.get(i));
            } else {
                System.out.println(i + 1 + ". " + lastShownList.get(i).getNameAndColour());
            }
        }
        printNextOrBack(lastPageNo, maxPages);
        printBreakLine();
    }
    /**
     * Prints flowers in the dictionary
     */
    public void printAllDictFlowerName(int pageNo) {
        lastShownList = FlowerDictionary.getAllFlowers();
        lastPageNo = pageNo;
        lastCommand = "ALL_FLOWERS";
        int maxPages = (int) Math.ceil((double)lastShownList.size() / PAGE_SIZE);
        System.out.println("Showing page "  + pageNo + "/" + maxPages
                + " of all the flowers you can add: ");
        printFlowerList(false);
    }

    /**
     * Prints filtered flowers based on a given filter.
     * @param flowers The list of flowers to filter.
     * @param filter The filter to apply to the flowers.
     */
    public void printFilteredFlowers(ArrayList<Flower> flowers, String filter, int pageNo){
        lastShownList = flowers;
        lastPageNo = pageNo;
        lastCommand = "FILTERED_FLOWERS " + filter;
        int maxPages = (int) Math.ceil((double)lastShownList.size() / PAGE_SIZE);
        System.out.println("Here is page " + lastPageNo + "/" + maxPages +
                " of all the flowers related to " + filter + ": ");
        printFlowerList(false);
    }

    /**
     * Prints name, colour, occasion and price information about a specific flower.
     * @param targetFlower The name of the flower the user searched for.
     * @param flowers The list of flowers that contain that name.
     */
    public void printFlowerInfo(ArrayList<Flower> flowers, String targetFlower, int pageNo) {
        lastShownList = flowers;
        lastPageNo = pageNo;
        lastCommand = "INFO_FLOWERS " + targetFlower;
        int maxPages = (int) Math.ceil((double)lastShownList.size() / PAGE_SIZE);
        System.out.println("Here is page " + lastPageNo + "/" + maxPages +
                " of info regarding flowers whose name contains " + targetFlower + ": ");
        printFlowerList(true);
    }

    public void printNextPage() throws FlorizzException{
        switch (lastCommand.split(" ")[0]) {
        case ("ALL_FLOWERS"):
            if (lastPageNo*PAGE_SIZE >= lastShownList.size()){
                throw new FlorizzException("There is no next page, type 'back' to go to the previous page");
            }
            printAllDictFlowerName(lastPageNo+1);
            break;
        case ("FILTERED_FLOWERS"):
            if (lastPageNo*PAGE_SIZE >= lastShownList.size()){
                throw new FlorizzException("There is no next page, type 'back' to go to the previous page");
            }
            printFilteredFlowers(lastShownList, lastCommand.split(" ")[1],lastPageNo+1);
            break;
        case ("INFO_FLOWERS"):
            if (lastPageNo*PAGE_SIZE >= lastShownList.size()){
                throw new FlorizzException("There is no next page, type 'back' to go to the previous page");
            }
            printFlowerInfo(lastShownList, lastCommand.split(" ")[1], lastPageNo+1);
            break;
        default:
            throw new FlorizzException("There is no list of flowers to view. " +
                    "Type 'flowers' to view a list of all flowers.");
        }


    }

    public void printBackPage() throws FlorizzException{
        if (lastPageNo == 1){
            throw new FlorizzException("There is no previous page, type 'next' to go to the next page");
        } else {
            switch (lastCommand.split(" ")[0]) {
            case ("ALL_FLOWERS"):
                if (lastPageNo*PAGE_SIZE >= lastShownList.size()){
                    throw new FlorizzException("There is no next page, type 'back' to go to the previous page");
                }
                printAllDictFlowerName(lastPageNo-1);
                break;
            case ("FILTERED_FLOWERS"):
                if (lastPageNo*PAGE_SIZE >= lastShownList.size()){
                    throw new FlorizzException("There is no next page, type 'back' to go to the previous page");
                }
                printFilteredFlowers(lastShownList, lastCommand.split(" ")[1],lastPageNo-1);
                break;
            case ("INFO_FLOWERS"):
                if (lastPageNo*PAGE_SIZE >= lastShownList.size()){
                    throw new FlorizzException("There is no next page, type 'back' to go to the previous page");
                }
                printFlowerInfo(lastShownList, lastCommand.split(" ")[1], lastPageNo-1);
                break;
            default:
                throw new FlorizzException("There is no list of flowers to view. " +
                        "Type 'flowers' to view a list of all flowers.");
            }

        }
    }
    /**
     * Prints all possible occasions the user can query
     */
    public void printAllOccasions() {
        lastCommand = "OTHERS";
        System.out.println("Here are all the occasions associated with the available flowers: ");
        for (Flower.Occasion occasion : Flower.Occasion.values()){
            System.out.println("- " + Flower.occasionToString(occasion));
        }

        printBreakLine();
    }

    /**
     * print ui if flower added successfully
     *
     * @param bouquetList
     * @param flowerName
     * @param quantity
     * @param bouquetName
     */
    public void printAddFlowerSuccess(ArrayList<Bouquet> bouquetList,
                                      String flowerName, Integer quantity, String bouquetName) {
        lastCommand = "OTHERS";
        System.out.println("You have successfully added the following: " + System.lineSeparator() +
                           "    - " + quantity + " x " + flowerName + " -> Bouquet: " + bouquetName);
        printAllBouquets(bouquetList);
    }

    /**
     * print ui if flower removed successfully
     *
     * @param bouquetList
     * @param flowerName
     * @param quantity
     * @param bouquetName
     */
    public void printRemoveFlowerSuccess(ArrayList<Bouquet> bouquetList,
                                         String flowerName, Integer quantity, String bouquetName) {
        lastCommand = "OTHERS";
        System.out.println("You have successfully removed the following: " + System.lineSeparator() +
                           "    - " + quantity + " x " + flowerName + " -> Bouquet: " + bouquetName);
        printAllBouquets(bouquetList);
    }

    /**
     * print ui if flower removed can't be found
     *
     * @param bouquetList
     * @param flowerName
     * @param bouquetName
     */
    public void printRemoveFlowerUnsuccessful(ArrayList<Bouquet> bouquetList, String flowerName, String bouquetName) {
        lastCommand = "OTHERS";
        System.out.println(flowerName + " cannot be found in bouquet: " + bouquetName);
        printAllBouquets(bouquetList);
    }

    public void printFuzzyInputDetection (String userInput, String bestMatch) {
        System.out.println("--> Your input is [" + userInput
                + "] but I am guessing you mean [" + bestMatch + "]");
    }

    /**
     * print if IOException is caught in a try catch block
     */
    public void printIOError() {
        System.out.println("ERROR: IO Error Encountered Xd");
    }

    /**
     * ask user for occasion input
     * @return String of occasion input
     */
    public String printAskOccasion() {
        System.out.println("For what occasion are you buying flowers for?");
        this.printAllOccasions();
        return inputScanner.nextLine();
    }

    /**
     * ask user for colour input
     * @param eligibleFlowers list of flowers that are eligible for the occasion
     * @return String of colour input
     */
    public String printAskColour(ArrayList<Flower> eligibleFlowers) {
        System.out.println("What colour would you like your bouquets to be?");

        // print all available colours in a given array list
        System.out.println("Here is the list of colours available for the occasion: ");
        // remove duplicate colours in eligible flowers
        ArrayList<String> colourList = new ArrayList<>();
        for (Flower flower : eligibleFlowers) {
            if(!colourList.contains(flower.getColour())){
                colourList.add(flower.getColour());
            }
        }
        for (String colour : colourList){
            System.out.println("- " + colour);
        }
        printBreakLine();
        return inputScanner.nextLine();
    }

    public String printAskSaveBouquet(Bouquet recommendedBouquet) {
        System.out.println("Would you like to save this bouquet to your list?");
        printFullBouquet(recommendedBouquet);
        System.out.println("Type 'yes' to save, 'no' to discard");
        return inputScanner.nextLine();
    }

    /**
     * Prints out to user when bouquet has been successfully saved
     * @param bouquetName Bouquet to save externally
     */
    public void printSaveSuccess(String bouquetName) {
        System.out.println("Successfully saved " + bouquetName + ". You can find it at 'florizz-out/saved/"
                + bouquetName + ".txt'");
        printBreakLine();
    }
}
