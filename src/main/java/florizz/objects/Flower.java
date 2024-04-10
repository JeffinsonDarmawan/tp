package florizz.objects;


import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a flower with its name, colour, occasions, and price.
 */
public class Flower {
    private String name = "";
    private ArrayList<Occasion> occasions = null;
    private Colour colour = null;
    private Double price;
    private ArrayList<String> meanings = null;
    private Type type = null;


    /**
     * Enumerates different colours a flower can have.
     */
    public enum Colour {
        WHITE, BLUE, RED, PINK, DARK_CRIMSON,GREEN, YELLOW, PURPLE, ORANGE
    }

    /**
     * Enumerates different occasions for which a flower can be used.
     */
    public enum Occasion {
        FUNERAL, WEDDING, VALENTINES, MOTHERS_DAY
    }

    public enum Type {
        FLOWER, FILLER
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
     * @param occasion  The occasion associated with the flower.
     * @param price The price of the flower.
     * @param meanings The meanings associated with the flower.
     * @param type The type of the flower.
     */
    public Flower(String name, Colour colour, ArrayList<Occasion> occasion,
                  Double price, ArrayList<String> meanings, Type type){
        this.name = name;
        this.occasions = occasion;
        this.colour = colour;
        this.price = price;
        this.meanings = meanings;
        this.type = type;
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
     * check if argument inputted is a valid occasion
     * @param argument
     * @return boolean true if occasion is valid
     */
    public static Boolean isValidOccasion(String argument) {
        for (Flower.Occasion occasion : Flower.Occasion.values()) {
            if (Flower.occasionToString(occasion).equalsIgnoreCase(argument)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if argument inputted is a valid colour
     * @param argument
     * @return boolean true if colour is valid
     */
    public static Boolean isValidColour(String argument) {
        for (Flower.Colour colour : Flower.Colour.values()) {
            if (Flower.colourToString(colour).equalsIgnoreCase(argument)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts a string representation of an occasion to an Occasion enum value.
     * @param ocassionString The string representation of an occasion.
     * @return The Occasion enum value corresponding to the string.
     */
    public static Occasion stringToOccasion(String ocassionString){
        assert ocassionString != null;
        return Occasion.valueOf(ocassionString.replaceAll(" ", "_").toUpperCase());
    }

    /**
     * Converts an Occasion enum value to a string representation.
     * @param occasion The Occasion enum value.
     * @return The string representation of the occasion.
     */
    public static String occasionToString(Occasion occasion){
        // check if occasion is valid
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

    public static String colourToString(Colour colour){
        return colour.toString().charAt(0) +
                colour.toString().replaceAll("_", " ").toLowerCase().substring(1);
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
        return name + " (" + colour.toString().charAt(0) +
                colour.toString().replaceAll("_", " " ).substring(1).toLowerCase() +
                ")";
    }

    /**
     * Gets the colour of the flower.
     * @return The colour of the flower.
     */
    public String getColour (){
        return colourToString(colour);
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
        String finalOccasion;
        String finalMeaning;
        StringBuilder occasionsString = new StringBuilder("Occasions: ");
        StringBuilder meaningsString = new StringBuilder("Meanings: ");
        for (Occasion occasion : occasions){
            occasionsString.append(occasionToString(occasion));
            occasionsString.append(", ");
        }

        for (String meaning : meanings){
            meaningsString.append(meaning);
            meaningsString.append(", ");
        }
        // Check if occasionString was updated
        if (!(occasionsString.toString().equals("Occasions: "))) {
            finalOccasion = occasionsString.substring(0,occasionsString.lastIndexOf(","));
        } else {
            finalOccasion = occasionsString.toString();
        }
        // Check if meaning String was updated
        if (!(meaningsString.toString().equals("Meanings: "))) {
            finalMeaning = meaningsString.substring(0,meaningsString.lastIndexOf(","));
        } else {
            finalMeaning = meaningsString.toString();
        }

        return ("Name: " + name + "\n" +
                "Colour: " + colourToString(colour) + "\n" +
                finalOccasion + "\n" +
                "Price: $" + String.format("%.2f", price) + "\n" +
                finalMeaning);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Flower)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Flower c = (Flower) obj;

        // Compare the data members and return accordingly
        return (Objects.equals(c.name.toUpperCase(), this.name.toUpperCase()) &&
                Objects.equals(c.colour, this.colour));
    }
}
