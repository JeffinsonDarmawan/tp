# Jeffinson Darmawan - Project Portfolio Page

## Flower Removal Command

**Step 1:** Removing flowers utilise the `parser` class to parse user command to identify which flower to remove, 
how much to remove and from which bouquet to remove.

**Step 2:** User will input `remove <flowerName> /q <quantity> /from <bouquetName>` and once `parser` identifies the 
appropriate keywords, it will instantiate the `HelpCommand` class and run its `execute()` method.

**Step 3:** `HelpCommand` class will call `printHelpMessage()` method of `Ui` class

**Step 4:** The list of valid commands will be printed by the `Ui` class
