package florizz.objects;

import java.util.ArrayList;

/**
 * Represents a flower with its name, colour, occasions, and price.
 */
public class Flower {
    private String name = "";
    private ArrayList<Occasion> occasions = null;
    private Colour colour = null;
    private Double price;
    private ArrayList<String> meanings = null;

    /**
     * Enumerates different colours a flower can have.
     */
    public enum Colour {
        WHITE, BLUE, RED, PINK, DARK_CRIMSON
    }

    /**
     * Enumerates different occasions for which a flower can be used.
     */
    public enum Occasion {
        FUNERAL, WEDDING, VALENTINES, MOTHERS_DAY
    }

    /**
     * Default constructor for the Flower class.
     */
    public Flower() {
        this.name = "";
    }
  
    /**
     * Constructs a Flower object with specified parameters.
     * @param name The name of the flower.
     * @param colour The colour of the flower.
     * @param occasion The occasion(s) associated with the flower.
     * @param price The price of the flower.
     */
    public Flower(String name, Colour colour, ArrayList<Occasion> occasion, Double price, ArrayList<String> meanings) {
        this.name = name;
        this.occasions = occasion;
        this.colour = colour;
        this.price = price;
        this.meanings = meanings;
    }

    /**
     * Constructs a Flower object with specified parameters.
     * @param name The name of the flower.
     * @param colour The colour of the flower.
     * @param occasion The occasion associated with the flower.
     * @param price The price of the flower.
     */
    public Flower(String name, Colour colour, Occasion occasion, Double price) {
        this.name = name;
        this.occasions = new ArrayList<Occasion>();
        occasions.add(occasion);
        this.colour = colour;
        this.price = price;
    }

    /**
     * Converts a string representation of an occasion to an Occasion enum value.
     * @param ocassionString The string representation of an occasion.
     * @return The Occasion enum value corresponding to the string.
     */
    public static Occasion stringToOccasion(String ocassionString){
        return Occasion.valueOf(ocassionString.replaceAll(" ", "_").toUpperCase());
    }

    /**
     * Converts an Occasion enum value to a string representation.
     * @param occasion The Occasion enum value.
     * @return The string representation of the occasion.
     */
    public static String occasionToString(Occasion occasion){
        return occasion.toString().charAt(0) +
                occasion.toString().replaceAll("_", " ").toLowerCase().substring(1);
    }

    /**
     * Converts a string representation of a colour to a Colour enum value.
     * @param colourString The string representation of a colour.
     * @return The Colour enum value corresponding to the string.
     */
    public static Colour stringToColour(String colourString){
        return Colour.valueOf(colourString.replaceAll(" ", "_").toUpperCase());
    }

    /**
     * Gets the name of the flower.
     * @return The name of the flower.
     */
    public String getFlowerName() {
        return name;
    }

    /**
     * Gets the name and colour of the flower.
     * @return The name and colour of the flower.
     */
    public String getNameAndColour() {
        return colour.toString().charAt(0) + colour.toString().substring(1).toLowerCase() + " " + name;
    }

    /**
     * Gets the colour of the flower.
     * @return The colour of the flower.
     */
    public String getColour (){
        return colour.toString();
    }

    /**
     * Gets the occasion(s) associated with the flower.
     * @return The occasion(s) associated with the flower.
     */
    public ArrayList<Occasion> getOccasion() {
        return occasions;
    }

    /**
     * Gets the price of the flower.
     * @return The price of the flower.
     */
    public Double getPrice () {
        return price;
    }

    /**
     * Generates a string representation of the Flower object.
     * @return A string representation of the Flower object.
     */
    @Override
    public String toString() {
        return ("Name: " + name + "\n" + "Colours: " + colour.toString() + "\n" + "Occasions: " + occasions.toString()
            + "\n" + "Price: $" + String.format("%.2f", price) + "\n" + "Meanings: " + meanings.toString());
    }
}
