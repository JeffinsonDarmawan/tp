package florizz.core;

import florizz.command.*;
import florizz.objects.Bouquet;

import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Parses user input and generates appropriate Command objects.
 */
public class Parser {
    private static Logger logger = Logger.getLogger(Florizz.class.getName());
    // prefixes to parse input
    private static final String QUANTITY = "/q";
    private static final String ADD_FLOWER_PREFIX = "/to";
    private static final String REMOVE_FLOWER_PREFIX = "/from";

    // regex
    private static final String ADD_FLOWER_REGEX = "(.+)/q(\\s*)(\\d+)(\\s*)/to(.+)";
    private static final String REMOVE_FLOWER_REGEX = "(.+)/q(\\s*)(\\d+)(\\s*)/from(.+)";
    private static final String PARSE_OCCASION_REGEX = "\\b[A-Za-z]+\\b";

    public static Command parse (String input) throws FlorizzException {
        logger.entering("Parser", "parse");
        Command command = null;

        try {
            String[] decodedInput = commandHandler(input);
            switch (decodedInput[0]) {
            case ("mybouquets"):
                command = new ListBouquetCommand();
                break;
            case ("new"):
                command = handleAddBouquet(input);
                break;
            case ("delete"):
                command = handleDeleteBouquet(input);
                break;
            case ("bye"):
                command = new ExitCommand();
                break;
            case ("help"):
                command = new HelpCommand();
                break;
            case ("flowers"):
                command = handleFlowerCommand(decodedInput);
                break;
            case ("info"):
                command = handleInfoCommand(decodedInput[1]);
                break;
            case ("occasion"):
                command = new ListOccasionCommand();
                break;
            case ("add"):
                command = handleAddFlower(decodedInput[1]);
                break;
            case ("remove"):
                command = handleRemoveFlower(decodedInput[1]);
                break;
            case ("recommend"):
                command = new RecommendCommand();
                break;
            default:
                throw new FlorizzException("Unidentified input, type help to get a list of all commands!");
            }
            logger.log(Level.INFO, "Command parsed successfully");
        } catch (FlorizzException ex) {
            logger.log(Level.INFO, "Exception occurred while parsing command: " + ex.errorMessage, ex);
            throw ex;
        } finally {
            logger.exiting("Parser", "parse");
        }
        return command;
    }


    /**
     * Splits input into command and arguments (if any). Handles capitalisation, whitespaces and small typos.
     *
     * @param input
     * @return String[] output; output[0] = item ; output[1] = argument(s)
     */

    private static String[] commandHandler(String input) throws FlorizzException {
        String[] outputs = new String[2];
        try {
            String trimmedInput = input.trim();
            int firstWhitespace = trimmedInput.indexOf(" ");
            if (firstWhitespace != -1) {
                outputs[0] = FuzzyLogic.detectItem(trimmedInput.substring(0,firstWhitespace).toLowerCase());
                switch (outputs[0]) {
                case ("delete"): // Fallthrough
                case ("new"):
                    outputs[1] = trimmedInput.substring(firstWhitespace).trim();
                    break;
                case ("remove"): // Fallthrough
                case ("add"):
                    String[] arguments = new String[2];
                    String trimmedArgument = trimmedInput.substring(firstWhitespace).trim();
                    int secondWhitespace = trimmedArgument.indexOf(" ");
                    arguments[0] = FuzzyLogic.detectItem(trimmedArgument.substring(0,secondWhitespace));
                    arguments[1] = trimmedArgument.substring(secondWhitespace).trim();
                    outputs[1] = arguments[0] + " " + arguments[1];
                    break;
                default:
                    outputs[1] = FuzzyLogic.detectItem(trimmedInput.substring(firstWhitespace).trim());
                    break;
                }
            } else {
                outputs[0] = FuzzyLogic.detectItem(trimmedInput.toLowerCase());
            }
        } catch (FlorizzException ex) {
            Logger.getLogger("CommandHandler").log(Level.SEVERE, "Exception occurred in commandHandler", ex);
            throw ex;
        }
        return outputs;
    }

    /**
     * remove prefix from an input string
     * e.g. "/to For Mom" -> " For Mom"
     *
     * @param input
     * @param prefix
     * @return input with prefix removed
     */
    private static String removePrefix(String input, String prefix) {
        return input.replace(prefix, "");
    }

    /**
     * Handles the parsing and creation of an AddBouquetCommand object based on user input.
     * @param input The user input to be parsed.
     * @return An AddBouquetCommand object corresponding to the parsed input.
     * @throws FlorizzException If the input does not contain the required bouquet information.
     */
    private static AddBouquetCommand handleAddBouquet(String input) throws FlorizzException{
        if (!input.contains(" ")){
            throw new FlorizzException("Did not include bouquet to add");
        }
        String newBouquetName = input.substring(input.indexOf(" ") + 1).trim();
        return new AddBouquetCommand(new Bouquet(newBouquetName));
    }

    /**
     * Handles the parsing and creation of a DeleteBouquetCommand object based on user input.
     * @param input The user input to be parsed.
     * @return A DeleteBouquetCommand object corresponding to the parsed input.
     * @throws FlorizzException If the input does not contain the required bouquet information.
     */
    private static DeleteBouquetCommand handleDeleteBouquet(String input) throws FlorizzException{
        if (!input.contains(" ")){
            throw new FlorizzException("Did not include bouquet to delete");
        }
        String bouquetToDelete = input.substring(input.indexOf(" ") + 1).trim();

        return new DeleteBouquetCommand(new Bouquet(bouquetToDelete));
    }

    /**
     * Handles the parsing and creation of a FlowerCommand object based on user input.
     * @param input The user input to be parsed.
     * @return A FlowerCommand object corresponding to the parsed input.
     */
    private static FlowerCommand handleFlowerCommand(String[] input) {
        String occasion = (input[1] == null) ? " " : input[1].trim();
        return new FlowerCommand(occasion);
    }

    /**
     * Handles the parsing and creation of an AddFlowerCommand object based on user input.
     * @param argument The user input to be parsed.
     * @return An AddFlowerCommand object corresponding to the parsed input.
     * @throws FlorizzException If the input does not match the required format.
     */
    private static AddFlowerCommand handleAddFlower(String argument) throws FlorizzException {
        if (argument == null) {
            throw new FlorizzException("No argument detected! " +
                    "Please use the correct format of 'add <flowerName> /q <quantity> /to <bouquetName>");
        }

        if (!argument.matches(ADD_FLOWER_REGEX)) {
            throw new FlorizzException("Incorrect format detected! " +
                    "Please use the correct format of 'add <flowerName> /q <quantity> /to <bouquetName>");
        }

        // [WARNING] might need to check for extra slash k

        int prefixIndex = argument.indexOf(ADD_FLOWER_PREFIX);
        int quantityIndex = argument.indexOf(QUANTITY);

        String flowerName = argument.substring(0,quantityIndex).trim().toLowerCase();
        String quantityString = removePrefix(argument.substring(quantityIndex, prefixIndex), QUANTITY).trim();
        // [WARNING] might need to check if it's a valid integer
        Integer quantity = Integer.parseInt(quantityString);
        String bouquetName = removePrefix(argument.substring(prefixIndex), ADD_FLOWER_PREFIX).trim();

        return new AddFlowerCommand(flowerName, quantity, bouquetName);
    }

    /**
     * Handles the parsing and creation of a RemoveFlowerCommand object based on user input.
     * @param argument The user input to be parsed.
     * @return A RemoveFlowerCommand object corresponding to the parsed input.
     * @throws FlorizzException If the input does not match the required format.
     */
    private static RemoveFlowerCommand handleRemoveFlower(String argument) throws FlorizzException {
        if (argument == null) {
            throw new FlorizzException("No argument detected! " +
                    "Please use the correct format of 'remove <flowerName> /q <quantity> /from <bouquetName>");
        }

        if (!argument.matches(REMOVE_FLOWER_REGEX)) {
            throw new FlorizzException("Incorrect format detected! " +
                    "Please use the correct format of 'remove <flowerName> /q <quantity> /from <bouquetName>");
        }

        // [WARNING] might need to check for extra slash k

        int prefixIndex = argument.indexOf(REMOVE_FLOWER_PREFIX);
        int quantityIndex = argument.indexOf(QUANTITY);

        String flowerName = argument.substring(0, quantityIndex).trim().toLowerCase();
        String quantityString = removePrefix(argument.substring(quantityIndex, prefixIndex), QUANTITY).trim();
        // [WARNING] might need to check if it's a valid integer
        Integer quantity = Integer.parseInt(quantityString);
        String bouquetName = removePrefix(argument.substring(prefixIndex), REMOVE_FLOWER_PREFIX).trim();

        return new RemoveFlowerCommand(flowerName, quantity, bouquetName);
    }

    /**
     * Handles the parsing and creation of an InfoCommand object based on user input.
     * @param flowerName The user input to be parsed.
     * @return An InfoCommand object corresponding to the parsed input.
     */
    private static InfoCommand handleInfoCommand(String flowerName) {
        assert !flowerName.isEmpty() : "This string is empty";
        return new InfoCommand(flowerName);
    }

    /**
     * Parses the occasion from the user input.
     * @param argument The user input to be parsed.
     * @return The parsed occasion.
     * @throws FlorizzException If the input does not match the required format.
     */
    public static String parseOccasion(String argument) throws FlorizzException{
        if (argument == null) {
            throw new FlorizzException("No argument detected! " +
                    "Please input an occasion");
        }

        if (!argument.matches(PARSE_OCCASION_REGEX)) {
            throw new FlorizzException("Incorrect format detected! " +
                    "Please input a single occasion");
        }

        return argument.toLowerCase();
    }
}
