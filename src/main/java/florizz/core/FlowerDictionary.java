package florizz.core;


import java.util.ArrayList;
import florizz.objects.Flower;

/**
 * A class that contains a dictionary of preset flowers
 */
public class FlowerDictionary {
    private static ArrayList<Flower> flowerDict = new ArrayList<Flower>();

    /**
     * Adds a new flower to the flower dictionary
     *
     * @param name Name of flower to be added
     * @param colour Colour of flower to be added
     * @param occasions Occasions that the flower can be bought for
     */
    private static void add(String name, String colour, String[] occasions, Double price,String[] meanings) {

        Flower.Colour colourEnum = Flower.Colour.valueOf(colour.replaceAll(" ", "_").toUpperCase());
        ArrayList<Flower.Occasion> occasionsArrayList = new ArrayList<>();
        for (String occasion : occasions){
            occasionsArrayList.add(Flower.Occasion.valueOf(occasion.replaceAll(" ", "_").toUpperCase()));
        }
        ArrayList<String> meaningsArrayList = new ArrayList<>();
        for (String meaning : meanings){
            meaningsArrayList.add(meaning);
        }
        flowerDict.add(new Flower(name, colourEnum, occasionsArrayList, price, meaningsArrayList));
    }

    /**
     * Adds flowers to the dictionary when florizz starts up (temporary)
     */
    public static void startup() {
        add("Orchid", "White", new String[]{"Wedding"}, 10.00, new String[]{"Innocence","Respect","Beauty"});
        add("Rose", "Dark Crimson", new String[]{"Funeral"}, 2.00, new String[]{"Mourning"});
        add("Rose", "Red", new String[]{"Valentines", "Wedding", "Mothers Day"}, 2.00, new String[]{"Love"});
        add("Lily", "White", new String[]{"Funeral", "Wedding"}, 2.50, new String[]{"Innocence"});
        add("Daisy", "White", new String[]{"Valentines"}, 0.50, new String[]{"Innocence"});
        add("Baby Breath", "White", new String[] {"Wedding"}, 1.00, new String[]{"Innocence", "Kindness", "Care", "Humble"});
        add("Chrysanthemum", "White", new String[]{"Funeral"}, 1.00, new String[]{"Love", "Loyalty", "Innocence"});
        add("Hydrangea", "Blue", new String[] {"Wedding"}, 9.00, new String[]{"Forgiveness", "Gratitude"});
        add("Carnation", "Pink", new String[] {"Mothers Day"}, 2.00, new String[]{"Gratitude", "Love"});
    }

    /**
     * Returns the size of flower dictionary
     *
     * @return The size as an int
     */
    public static int size() {
        return flowerDict.size();
    }

    /**
     * Gets the flower at the selected index in the flower dictionary
     *
     * @param i Index to get flower from
     * @return The flower object at index i
     */
    public static Flower get(int i) {
        return flowerDict.get(i);
    }

    /**
     * Gets a list of flowers that suit that occasion
     * @param occasion occasion to filter flowers by
     * @return an ArrayList of flowers to be printed by ui
     */
    public static ArrayList<Flower> filterByOccasion(Flower.Occasion occasion) {
        ArrayList<Flower> filteredFlowers = new ArrayList<>();
        for (Flower flower : flowerDict) {
            if (flower.getOccasion().contains(occasion)) {
                filteredFlowers.add(flower);
            }
        }
        return filteredFlowers;
    }

    /**
     * Gets a list of flowers that contain the name search
     * @param name name of Flowers to filter by
     * @return an ArrayList of Flowers to be printed by ui
     */
    public static ArrayList<Flower> filterByName(String name){
        ArrayList<Flower> filteredFlowers = new ArrayList<>();
        for (Flower flower : flowerDict) {
            if (flower.getFlowerName().contains(name)) {
                filteredFlowers.add(flower);
            }
        }
        return filteredFlowers;
    }
}
