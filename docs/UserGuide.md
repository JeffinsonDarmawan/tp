# User Guide
Florizz is your personal digital florist which helps people in Singapore to curate flowers to create bouquets for all 
occasions.

## Features
### Viewing help: `help`
Shows a list of commands and its corresponding function

Format: `help`

Expected output:

```
Here are the list of commands you can use:
1. new <bouquetName> - Add a bouquet
2. delete <bouquetName> - Delete a bouquets
3. mybouquets - List current saved bouquets
4. info <flowerName> - Provide information on chosen flower
5. add <flowerName> /q <quantity> /to <bouquetName> - add flower to a bouquet
6. remove <flowerName> /q <quantity> /from <bouquetName> - remove flower from a bouquet
7. flowers - Shows a list of flowers that can be added into mybouquets
8. flowers <occasion> - Shows a list of flowers associated with said occasion
9. occasion - Shows a list of occasions associated with available flowers
10. save <bouquetName> - Saves a bouquet to an external <bouquetName>.txt file
11. recommend - Recommends a bouquet based on the chosen occasion and colour 
12. bye - Exits the programme
```

### Create a new bouquet: `new`
Creates an empty bouquet to add flowers to later

Format: `new NAME`

Bouquet name must not already exist

Examples:
`new For Girlfriend`

Expected output:
```
Added new bouquet to list: 
For Girlfriend
```
<div style="page-break-after: always;"></div>

### Delete existing bouquet: `delete`
Deletes a bouquet from the bouquet list

Format:  `delete <bouquetName>`

Bouquet of that name must exist in the list

Example:
`delete For Mother`

Expected output:
```
Deleted bouquet: 
For Mother
```

### View existing bouquets: `mybouquets`
Views all the bouquets in the list

Format: `mybouquets`

Expected output:
```
Here is the list of your saved bouquets:
1. For Girlfriend :
      No flowers added so far
```
<div style="page-break-after: always;"></div>

### List all available flowers: `flowers`
List all available flowers in the database currently, also able to filter presented flowers according to occasion, 
colour (coming soon) and meaning (coming soon).

Shows at most five flowers at once, type `next` to see flowers on the next page, and `back` to view a previous page.

Format: `flowers <occassion>`

Example: `flowers`

Expected output:
```
Showing page 1/2 of all the flowers you can add: 
1. White Orchid
2. Dark crimson Rose
3. Red Rose
4. White Lily
5. White Daisy
Type 'next' to go to the next page.
____________________________________________________________
```

Example: `flowers Funeral` 

Expected output:
```
Here is page 1/1 of all the flowers related to Funeral: 
1. Dark crimson Rose
2. White Lily
3. White Chrysanthemum
____________________________________________________________
```

### View detailed info of a flower: `info`

Get detailed info (colour, meaning and related occasions) about a specific flower in the database,
will show all colours and their associated meanings (specifying colours will come soon).

Shows at most five flowers at once, type `next` to see flowers on the next page, and `back` to view a previous page.

Format: `info <flowerName>`

Example: `info Lily`

Expected Output:
```
Here is page 1/1 of info regarding flowers whose name contains Lily: 
1. Name: Lily
Colour: White
Occasions: Funeral, Wedding
Price: $2.50
Meanings: Innocence
____________________________________________________________
```

### Add flower: `add`

Adds a flower into a bouquet

Format: `add <flowerName> /q <quantity> /to <bouquetName>`

- Flower must exist in the database
- Quantity must be a positive integer
- Bouquet must exist in the database

Examples:
- `add Rose /q 3 /to For Girlfriend`
- `add Babys breath /q 2 /to Sister’s graduation`

Expected Output:
```
You have successfully added the following: 
    - 3 x rose -> Bouquet: For Girlfriend
Here is the list of your saved bouquets:
1. For Girlfriend :
    - 3 x Rose
  Total estimated price = $6.00
____________________________________________________________
What can I do for you?
```

### Remove flower: `remove`

Removes a flower from a bouquet

Format: `remove <flowerName> /q <quantity> /from <bouquetName>`

- Flower must exist in the database and the bouquet specified
- Quantity must be between 1 and the current amount of flowers in the bouquet (inclusive).
- Bouquet must exist in the database

Examples:
- `remove Rose /q 1 /from For Girfriend`
- `remove Gerbera /q 3 /from Valentine’s Day`

Expected output:
```
You have successfully removed the following: 
    - 1 x rose -> Bouquet: For Girlfriend
Here is the list of your saved bouquets:
1. For Girlfriend :
    - 2 x Rose
  Total estimated price = $4.00
____________________________________________________________
What can I do for you?
```

### List occasions: `occasion`
Shows a list of occasions that the flowers in the database are associated with.

Format: `occasion`

Expected output:
```
Here are all the occasions associated with the available flowers: 
- Funeral
- Wedding
- Valentines
- Mothers day
____________________________________________________________
```
### Recommend A Bouquet: `recommend`
Recommends a bouquet based on the occasion and the recipient's preference

Steps:
1. Type command: `recommend`

Expected output:
```
For what occasion are you buying flowers for?
Here is the list of our available occasion:
Here are all the occasions associated with the available flowers: 
- Funeral
- Wedding
- Valentines
- Mothers day
____________________________________________________________
```
2. Type occasion: `Funeral`

Expected output:
```
What colour would you like your bouquets to be?
Here is the list of colours available for the occasion: 
- DARK_CRIMSON
- WHITE
```

3. Select colour of bouquet: `WHITE`

Expected output:

```
Would you like to save this bouquet to your list?
Here is the full list of flowers in Recommended Bouquet:
    - 3 x Lily
    - 2 x Chrysanthemum
____________________________________________________________
Type 'yes' to save, 'no' to discard
```
4. Confirm whether you want to add the recommended bouquet to your mybouquets list: `yes`

```
Added new bouquet to list: 
Recommended Bouquet
____________________________________________________________
```

### Save a bouquet to device: `save`
Saves chosen bouquet, if it exists, locally to the users device

Format: `save <bouquetName>`

- Bouquet must exist before it can be saved 

Examples:
- `save moms bouquet`

Expected Output:
```
Successfully saved moms bouquet. You can find it at 'florizz-out/saved/moms bouquet.txt'
```

### Exit programme: `bye`

Exits the program.

Format: `bye`

Expected output:
```
Enjoy your bouquet! Thank you for using Florizz!
```

### Fuzzy Logic

Florizz uses a type of fuzzy logic to rectifies typos in user input by utilising the Damerau-Levenshtein Distance 
to measure the similarity between the user input and a valid command/item/occasion.

The Damerau-Levenshtein distance measures the minimum number of single-character edits required to change one string
into another. These edits can be insertions, deletions, substitutions and transpositions of individual characters.

When a typo is detected, Florizz will make a calculated guess as to the valid commands that a user is actually calling.

Examples:
- `newq mybouquet1`
- `adds Chysanthenum /q 10 /to mybouquet1`
- `delate mybouquet1`

Expected output:

```
newq mybouquet1
--> Your input is [newq] but I am guessing you mean [new]
Added new bouquet to list: 
mybouquet1
____________________________________________________________
What can I do for you?
```
```
adds Chysanthenum /q 10 /to mybouquet1
--> Your input is [adds] but I am guessing you mean [add]
--> Your input is [Chysanthenum] but I am guessing you mean [Chrysanthemum]
You have successfully added the following: 
    - 10 x chrysanthemum -> Bouquet: mybouquet1
Here is the list of your saved bouquets:
1. mybouquet1 :
    - 10 x Chrysanthemum
  Total estimated price = $10.00
____________________________________________________________
What can I do for you?
```

```
delate mybouquet1
--> Your input is [delate] but I am guessing you mean [delete]
Deleted bouquet: 
mybouquet1
____________________________________________________________
What can I do for you?
```

### Autosave
Florizz automatically backs up all your bouquet data onto your device in a `FlorizzBouquets.txt` file.
As such the user can transfer their bouquet data between devices by simply moving the text file to the `florizz-out/data/`
folder. However, editing this text file is not recommended as the format is very specific, so users
should do so at their own risk


