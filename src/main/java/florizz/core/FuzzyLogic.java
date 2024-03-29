package florizz.core;

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
    private static final int SIMILARITY_THRESHOLD = 3;
    private static final Logger logger = Logger.getLogger(FuzzyLogic.class.getName());

    static {
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
    public static String detectItem(String userInput) throws FlorizzException {
        if (userInput == null) {
            throw new FlorizzException("Input cannot be null");
        }

        String bestMatch = null;
        int bestDistance = Integer.MAX_VALUE;
        Ui uiFuzzy = new Ui();

        // Iterate over predefined commands
        for (String command : ITEMS.keySet()) {
            int distance = computeLevenshteinDistance(command, userInput);
            if (distance < bestDistance) {
                bestDistance = distance;
                bestMatch = command;
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
            logger.log(Level.WARNING, "No matching command/item/occasion found for input: {0}", userInput);
            throw new FlorizzException("No matching command/item/occasion found for input: " + userInput);
        }
    }

    /**
     * Computes the Levenshtein distance which is a metric used to measure the similarity between two strings.
     * It calculates the minimum number of single-character edits required to change one string into another.
     * These edits can be insertions, deletions, or substitutions of individual characters.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The Levenshtein distance between the two strings.
     */
    private static int computeLevenshteinDistance(String s1, String s2) {
        assert s1 != null && s2 != null : "Strings cannot be null";

        int m = s1.length();
        int n = s2.length();

        int[] previousRow = new int[n + 1];
        int[] currentRow = new int[n + 1];

        // Initialize the first row
        for (int j = 0; j <= n; j++) {
            previousRow[j] = j;
        }

        // Calculate the Levenshtein distance
        for (int i = 1; i <= m; i++) {
            currentRow[0] = i;

            for (int j = 1; j <= n; j++) {
                int substitutionCost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                currentRow[j] = Math.min(Math.min(
                                previousRow[j] + 1,             // deletion
                                currentRow[j - 1] + 1),         // insertion
                        previousRow[j - 1] + substitutionCost); // substitution
            }

            // Swap rows
            int[] tempRow = previousRow;
            previousRow = currentRow;
            currentRow = tempRow;
        }

        // Return the Levenshtein distance
        assert previousRow[n] >= 0 : "Levenshtein distance cannot be negative";
        return previousRow[n];
    }
}
