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
    private static final Map<String, String> COMMANDS = new HashMap<>();
    private static final int SIMILARITY_THRESHOLD = 3;

    private static final Logger logger = Logger.getLogger(FuzzyLogic.class.getName());

    static {
        COMMANDS.put("new", "Add a new bouquet");
        COMMANDS.put("delete", "Delete a bouquet");
        COMMANDS.put("mybouquet", "List all bouquets");
        COMMANDS.put("info", "Get information about a flower");
        COMMANDS.put("bye", "Exits the programme");
        COMMANDS.put("flowers", "List all flowers");
        COMMANDS.put("remove","Remove flower(s) from a bouquet");
        COMMANDS.put("occasion", "List flowers of specified occasion");
        COMMANDS.put("add", "Add flower(s) to a bouquet");
        COMMANDS.put("help", "List available commands");
    }

    /**
     * Detects the closest predefined command based on user input.
     *
     * @param userInput The user input to be matched with predefined commands.
     * @return The closest matching command.
     * @throws FlorizzException if the input is null or no matching command is found.
     */
    public static String detectCommand(String userInput) throws FlorizzException {
        if (userInput == null) {
            throw new FlorizzException("Input cannot be null");
        }

        String bestMatch = null;
        int bestDistance = Integer.MAX_VALUE;

        // Iterate over predefined commands
        for (String command : COMMANDS.keySet()) {
            int distance = computeLevenshteinDistance(command, userInput);
            if (distance < bestDistance) {
                bestDistance = distance;
                bestMatch = command;
            }
        }

        // If the best match is within threshold, return it; otherwise, return null
        if (bestDistance <= SIMILARITY_THRESHOLD) {
            logger.log(Level.INFO, "--> Detected command: [{0}]", bestMatch);
            System.out.println("--> Detected command: [" + bestMatch + "]");
            return bestMatch;
        } else {
            logger.log(Level.WARNING, "No matching command found for input: {0}", userInput);
            throw new FlorizzException("No matching command found for input: " + userInput);
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
