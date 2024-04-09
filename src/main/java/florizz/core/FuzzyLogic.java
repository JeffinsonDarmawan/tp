package florizz.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides functionality for detecting the closest command based on user input,
 * and computing Levenshtein Distance between strings.
 */
public class FuzzyLogic {

    private static final Map<String, String> ITEMS = new HashMap<>();
    private static final int SIMILARITY_THRESHOLD = 2;
    private static final Logger logger = Logger.getLogger(FuzzyLogic.class.getName());

    static {
        ITEMS.put("save", "Command: Save a bouquet to device");
        ITEMS.put("new", "Command: Add a new bouquet");
        ITEMS.put("delete", "Command: Delete a bouquet");
        ITEMS.put("mybouquets", "Command: List all bouquets");
        ITEMS.put("info", "Command: Get information about a flower");
        ITEMS.put("bye", "Command: Exits the programme");
        ITEMS.put("flowers", "Command: List all flowers");
        ITEMS.put("remove","Command: Remove flower(s) from a bouquet");
        ITEMS.put("occasion", "Command: List flowers of specified occasion");
        ITEMS.put("add", "Command: Add flower(s) to a bouquet");
        ITEMS.put("help", "Command: List available commands");
        ITEMS.put("recommend", "Command: Get a recommended bouquet");
        ITEMS.put("next", "Command: Goes to next page");
        ITEMS.put("back", "Command: Goes to previous page");
        ITEMS.put("Orchid", "Flower");
        ITEMS.put("Rose", "Flower");
        ITEMS.put("Lily", "Flower");
        ITEMS.put("Daisy", "Flower");
        ITEMS.put("Baby Breath", "Flower");
        ITEMS.put("Chrysanthemum", "Flower");
        ITEMS.put("Hydrangea", "Flower");
        ITEMS.put("Carnation", "Flower");
        ITEMS.put("Wedding", "Occasion");
        ITEMS.put("Valentines", "Occasion");
        ITEMS.put("Mothers Day", "Occasion");
        ITEMS.put("Funeral", "Occasion");
    }

    /**
     * Detects the closest predefined command/item/occasion based on user input.
     *
     * @param userInput The user input to be matched with predefined command/item/occasion.
     * @return The closest matching command/item/occasion.
     * @throws FlorizzException if the input is null or no matching command/item/occasion is found.
     */
    protected static String detectItem(String userInput) throws FlorizzException {
        if (userInput == null) {
            throw new FlorizzException("Input cannot be null");
        }

        if (userInput.length() == 1) {
            throw new FlorizzException("No matching command/item/occasion found for input: " + userInput);
        }

        String bestMatch = null;
        int bestDistance = Integer.MAX_VALUE;
        Ui uiFuzzy = new Ui();

        // Iterate over predefined commands
        for (String item : ITEMS.keySet()) {
            int distance = computeDLDistance(item, userInput);
            if (distance < bestDistance) {
                bestDistance = distance;
                bestMatch = item;
            }
        }

        // If the best match is within threshold, return it; otherwise, return null
        if (bestDistance <= SIMILARITY_THRESHOLD && bestDistance != 0) {
            logger.log(Level.INFO, "--> Detected input: [{0}]", bestMatch);
            uiFuzzy.printFuzzyInputDetection(userInput, bestMatch);
            return bestMatch;
        } else if (bestDistance == 0) {
            return bestMatch;
        } else {
            logger.log(Level.SEVERE, "No matching command/item/occasion found for input: {0}", userInput);
            throw new FlorizzException("No matching command/item/occasion found for input: " + userInput);
        }
    }

    /**
     * Computes the Damerau-Levenshtein distance which is a metric used to measure the similarity between two strings.
     * It calculates the minimum number of single-character edits required to change one string into another.
     * These edits can be insertions, deletions, substitutions and transpositions of individual characters.
     *
     * @param item The first string.
     * @param userInput The second string.
     * @return The Damerau-Levenshtein distance between the two strings.
     */
    private static int computeDLDistance(String item, String userInput) {
        assert item != null && userInput != null : "Strings cannot be null";

        int itemLength = item.length();
        int inputLength = userInput.length();

        int[] previousRow = new int[inputLength + 1];
        int[] currentRow = new int[inputLength + 1];

        // Initialize the first row
        for (int j = 0; j <= inputLength; j++) {
            previousRow[j] = j;
        }

        // Calculate the Damerau-Levenshtein distance
        for (int i = 1; i <= itemLength; i++) {
            currentRow[0] = i;

            for (int j = 1; j <= inputLength; j++) {
                int substitutionCost = (item.charAt(i - 1) == userInput.charAt(j - 1)) ? 0 : 1;
                currentRow[j] = Math.min(Math.min(
                                previousRow[j] + 1,             // deletion
                                currentRow[j - 1] + 1),         // insertion
                        previousRow[j - 1] + substitutionCost); // substitution

                // Check for transpositions
                if (i > 1 && j > 1 && item.charAt(i - 1) == userInput.charAt(j - 2)
                        && item.charAt(i - 2) == userInput.charAt(j - 1)) {
                    currentRow[j] = Math.min(currentRow[j], previousRow[j - 2] + substitutionCost);
                }
            }

            // Swap rows
            int[] tempRow = previousRow;
            previousRow = currentRow;
            currentRow = tempRow;
        }

        // Return the Damerau-Levenshtein distance
        assert previousRow[inputLength] >= 0 : "Levenshtein distance cannot be negative";
        return previousRow[inputLength];
    }

    // doesnt account for deletes abc --> something must be done before split and merge input
    // my bouquets
    //

    protected static String processCommand(String userInput) throws FlorizzException {
        if (userInput == null) {
            throw new FlorizzException("Input cannot be empty.");
        }
        String correctedInput = splitAndMergeInput(userInput);
        return correctedInput;
    }

    protected static String splitAndMergeInput(String userInput) throws FlorizzException {
        String correctedInput = "";
        String mergedInput = mergeInput(userInput);
        String splitMergedInput = splitInput(mergedInput);
        String[] arguments = splitMergedInput.split(" ");
        System.out.println(Arrays.toString(arguments));
        String bouquetName = "";
        String removeArgument = "";
        String addArgument = "";

        if (arguments.length == 1 && !arguments[0].matches("(mybouquets|flowers|occasion|recommend|bye|help)")) {
            correctedInput = userInput;
        } else if (arguments[0].matches("(mybouquets|flowers|occasion|recommend|bye|help)")) {
            correctedInput = detectItem(mergedInput);
        } else if (arguments[0].matches("(info|flowers)")) {
            correctedInput = detectItem(arguments[0]) + " " + detectItem(arguments[1]);
        } else if (arguments[0].matches("(new)")) {
            bouquetName = userInput.replace("n", "")
                    .replace("e", "")
                    .replace("w", "")
                    .strip();
            correctedInput = "new " + bouquetName;
        } else if (arguments[0].matches("(delete)")) {
            bouquetName = userInput.replace("d", "")
                    .replace("e", "")
                    .replace("l", "")
                    .replace("e", "")
                    .replace("t", "")
                    .replace("e", "")
                    .strip();
            correctedInput = "delete " + bouquetName;
        } else if (arguments[0].matches("(save)")) {
            bouquetName = userInput.replace("s", "")
                    .replace("a", "")
                    .replace("v", "")
                    .replace("e", "")
                    .strip();
            correctedInput = "save " + bouquetName;
        } else if (arguments[0].matches("(remove)")) {
            removeArgument = userInput.replace("r", "")
                    .replace("e", "")
                    .replace("m", "")
                    .replace("o", "")
                    .replace("v", "")
                    .replace("e", "")
                    .strip();
            correctedInput = "remove " + removeArgument;
        } else if (arguments[0].matches("(add)")) {
            addArgument = userInput.replace("a", "")
                    .replace("d", "")
                    .replace("d", "")
                    .strip();
            correctedInput = "add " + addArgument;
        }
        System.out.println(correctedInput);
        return correctedInput;
    }

    /**
     * Separates the input string into a command and an argument.
     * If the input string matches a predefined command pattern, it corrects the input by adding a space between the
     * command and the argument.
     * Supported commands include: info, delete, flowers, new, add, remove, save.
     * If the input does not match any predefined command pattern, it returns the original input string unchanged.
     *
     * @param userInput The input string to be separated and corrected.
     * @return correctedInput The input with a space between the command and the argument if applicable.
     * @throws FlorizzException if the input string is null.
     */
    protected static String splitInput(String userInput) throws FlorizzException {
        if (userInput == null) {
            throw new FlorizzException("Input cannot be empty.");
        }

        String correctedInput = "";
        if (userInput.matches("(info)[a-zA-Z].*")) {
            String argumentInfo = userInput.replaceAll("(info)", "");
            correctedInput = "info " + argumentInfo;
        } else if (userInput.matches("(delete).+")) {
            String argumentDelete = userInput.replace("delete", "");
            correctedInput = "delete " + argumentDelete;
        } else if (userInput.matches("(flowers)[a-zA-Z].*")) {
            String argumentFlowers = userInput.replaceAll("(flowers)", "");
            correctedInput = "flowers " + argumentFlowers;
        } else if (userInput.matches("(new).+")) {
            String argumentNew = userInput.replace("new", "");
            correctedInput = "new " + argumentNew;
        } else if (userInput.matches("(add)[a-zA-Z].+")) {
            System.out.println(userInput);
            String argumentAdd = userInput.replace("add", "");
            System.out.println(argumentAdd);
            correctedInput = "add " + argumentAdd;
        } else if (userInput.matches("(remove)[a-zA-Z].+")) {
            String argumentRemove = userInput.replace("remove", "");
            correctedInput = "remove " + argumentRemove;
        } else if (userInput.matches("(save)[a-zA-Z].+")) {
            String argumentSave = userInput.replace("save", "");
            correctedInput = "save " + argumentSave;
        }

        if (correctedInput.isEmpty()) {
            correctedInput = userInput;
        }
        return correctedInput;
    }

    protected static String mergeInput(String userInput) throws FlorizzException {
        if (userInput == null) {
            throw new FlorizzException("Input cannot be empty.");
        }
        return userInput.replaceAll("\\s", "");
    }

}
