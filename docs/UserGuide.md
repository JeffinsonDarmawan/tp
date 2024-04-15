# User Guide
Florizz is your personal digital florist which helps people in Singapore to curate flowers to create bouquets for all 
occasions.

## Table of Contents
<!-- TOC -->
* [User Guide](#user-guide)
  * [Table of Contents](#table-of-contents)
  * [Features <a name="Features"></a>](#features-a-namefeaturesa)
    * [Viewing help: `help` <a name="help"></a>](#viewing-help-help-a-namehelpa)
    * [Create a new bouquet: `new`](#create-a-new-bouquet-new)
    * [Delete existing bouquet: `delete`](#delete-existing-bouquet-delete)
    * [View existing bouquets: `mybouquets`](#view-existing-bouquets-mybouquets)
    * [List all available flowers: `flowers`](#list-all-available-flowers-flowers)
    * [View detailed info of a flower: `info`](#view-detailed-info-of-a-flower-info)
    * [Add flower: `add`](#add-flower-add)
    * [Remove flower: `remove`](#remove-flower-remove)
    * [List occasions: `occasion`](#list-occasions-occasion)
    * [Recommend A Bouquet: `recommend`](#recommend-a-bouquet-recommend)
    * [Save a bouquet to device: `save`](#save-a-bouquet-to-device-save)
    * [Compare two flowers: `compare`](#compare-two-flowers-compare)
    * [Exit programme: `bye`](#exit-programme-bye)
    * [Fuzzy Logic](#fuzzy-logic)
    * [Autosave](#autosave)
<!-- TOC -->

## Features <a name="Features"></a>
### Viewing help: `help` <a name="help"></a>
Shows a list of commands and its corresponding function

Format: `help`

Expected output:

```
Here is the table showing a list of commands you can use:
+-----+------------------------------------------------+----------------------------------------+------------------------------+
| No. | Command                                        | Explanation                            | Example                      |
+-----+------------------------------------------------+----------------------------------------+------------------------------+
|  1  | new <bouquetName>                              | Add a bouquet                          | new Birthday Bouquet         |
|  2  | delete <bouquetName>                           | Delete a bouquets                      | delete Birthday Bouquet      |
|  3  | mybouquets                                     | List current saved bouquets            | mybouquets                   |
|  4  | info <flowerName>                              | Provide information on chosen flower   | info Rose                    |
|  5  | add <flowerName> /c <colour> (optional) /q     | Add flower to a bouquet                | add Rose /c Red /q 5 /to     |
|     | <quantity> /to <bouquetName>                   |                                        | Birthday Bouquet             |
|  6  | remove <flowerName> /c <colour> (optional) /q  | Remove flower from a bouquet           | remove Rose /c Red /q 5      |
|     | <quantity> /from <bouquetName>                 |                                        | /from Birthday Bouquet       |
|  7  | flowers                                        | Shows a list of flowers that can be    | flowers                      |
|     |                                                | added into mybouquets                  |                              |
|  8  | flowers <occasion>                             | Shows a list of flowers associated     | flowers Valentines           |
|     |                                                | with said occasion                     |                              |
|  9  | occasion                                       | Shows a list of occasions associated   | occasion                     |
|     |                                                | with available flowers                 |                              |
| 10  | save <bouquetName>                             | Saves a bouquet to an external         | save Birthday Bouquet        |
|     |                                                | <bouquetName>.txt file                 |                              |
| 11  | recommend                                      | Recommends a bouquet based on the      | recommend                    |
|     |                                                | chosen occasion and colour             |                              |
| 12  | compare <1st flowerName> /vs/ <2nd flowerName> | Show information regarding two flowers | compare Rose /vs/ Lily       |
|     |                                                | side-by-side for comparison            |                              |
| 13  | bye                                            | Exits the programme                    | bye                          |
+-----+------------------------------------------------+----------------------------------------+------------------------------+
```

### Create a new bouquet: `new`
Creates an empty bouquet to add flowers to later

Format: `new NAME`

- Bouquet name must not already exist
- Bouquet names are not case-sensitive i.e. For Girlfriend = for girlfriend

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

- Bouquet of that name must exist in the list

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
Showing page 1/6 of all the flowers you can add:
1. Orchid (White)
2. Rose (Dark crimson)
3. Rose (Red)
4. Rose (Yellow)
5. Lily (White)
Type 'next' to go to the next page.
```

Example: `flowers Funeral` 

Expected output:
```
Here is page 1/1 of all the flowers related to Funeral:
1. Rose (Dark crimson)
2. Lily (White)
3. Chrysanthemum (White)
```

### View detailed info of a flower: `info`

Get detailed info (colour, meaning and related occasions) about a specific flower in the database,
will show all colours and their associated meanings (specifying colours will come soon).

Shows at most five flowers at once, type `next` to see flowers on the next page, and `back` to view a previous page.

Format: `info <flowerName>`

Example: `info Lily`

Expected Output:
```
Here is a table of information about the flower Lily:
+-----+-------------+--------+------------------+-----------+-------------+-----------+
| No. | Flower Name | Colour | Occasion         | Meaning   | Type        | Price ($) |
+-----+-------------+--------+------------------+-----------+-------------+-----------+
|  1  | Lily        | White  | Funeral, Wedding | Innocence | MAIN_FLOWER |      2.50 |
|  2  | Lily        | Orange | -                | Hatred    | MAIN_FLOWER |      2.50 |
+-----+-------------+--------+------------------+-----------+-------------+-----------+
____________________________________________________________
```

### Add flower: `add`

Adds a flower into a bouquet

Format: `add <flowerName> /c <colour> (optional) /q <quantity> /to <bouquetName>`

- Flower must exist in the database
- Quantity must be a positive integer
- Bouquet must exist in the database
- If flower has several colours available, but user did not enter a colour in the command, user will be prompted to choose a colour from the available colours
- If flower only has one colour available, user does not need to enter a colour

Examples:
- `add Rose /c red /q 3 /to For Girlfriend`

Expected Output:
```
What can I do for you?
add Rose /c red /q 3 /to For Girlfriend
You have successfully added the following:
    - 3 x Rose (Red) -> Bouquet: For Girlfriend
Here is the list of your saved bouquets:
1. For Girlfriend :
    - 3 x Rose (Red)
  Total estimated price = $6.00
__________________________________
```
- `add Rose /q 3 /to For Girlfriend`

```
The flower you're looking for has more than one colour available, each with their own vastly different meanings.
Here is a table of information about the flower rose:
+-----+-------------+--------------+----------------------------------+----------------------------------------+-------------+-----------+
| No. | Flower Name | Colour       | Occasion                         | Meaning                                | Type        | Price ($) |
+-----+-------------+--------------+----------------------------------+----------------------------------------+-------------+-----------+
|  1  | Rose        | Dark crimson | Funeral                          | Mourning                               | MAIN_FLOWER |      2.00 |
|  2  | Rose        | Red          | Valentines, Wedding, Mothers day | Love                                   | MAIN_FLOWER |      2.00 |
|  3  | Rose        | Yellow       | -                                | Jealousy, Decrease of love, Infidelity | MAIN_FLOWER |      2.00 |
+-----+-------------+--------------+----------------------------------+----------------------------------------+-------------+-----------+
____________________________________________________________
Type the colour you want to add into the bouquet, or 'cancel' to return to the main menu.
red
You have successfully added the following:
    - 3 x Rose (Red) -> Bouquet: For Girlfriend
Here is the list of your saved bouquets:
1. For Girlfriend :
    - 3 x Rose (Red)
  Total estimated price = $6.00
```
- `add daisy /q 3 /to For Girlfriend`

```
You have successfully added the following:
    - 3 x Daisy (White) -> Bouquet: For Girlfriend
Here is the list of your saved bouquets:
1. For Girlfriend :
    - 3 x Daisy (White)
    - 3 x Rose (Red)
  Total estimated price = $7.50
```

### Remove flower: `remove`

Removes a flower from a bouquet

Format: `remove <flowerName> /c <colour> (optional) /q <quantity> /from <bouquetName>`

- Flower of that colour must exist in the database and the bouquet specified
- Quantity must be between 1 and the current amount of flowers in the bouquet (inclusive).
- Bouquet must exist in the database
- If multiple flowers of that colour exist in the bouquet and colour is not specified, user will be prompted to choose a colour from the ones available

Examples:
- `remove Carnation /q 1 /from for mother`

Expected output:
```
The flower you're looking for has more than one colour available, each with their own vastly different meanings.
Here is a table of information about the flower carnation:
+-----+-------------+--------+-------------+---------------------------+-------------+-----------+
| No. | Flower Name | Colour | Occasion    | Meaning                   | Type        | Price ($) |
+-----+-------------+--------+-------------+---------------------------+-------------+-----------+
|  1  | Carnation   | Pink   | Mothers day | Gratitude, Love           | MAIN_FLOWER |      2.00 |
|  2  | Carnation   | Red    | Valentines  | My heart aches, Deep Love | MAIN_FLOWER |      2.00 |
+-----+-------------+--------+-------------+---------------------------+-------------+-----------+
____________________________________________________________
Type the colour you want to add into the bouquet, or 'cancel' to return to the main menu.
red
You have successfully removed the following:
    - 1 x Carnation (Red) -> Bouquet: for mother
Here is the list of your saved bouquets:
1. For Mother :
    - 1 x Carnation (Pink)
  Total estimated price = $2.00
```
- `remove carnation /c pink /q 1 /from for mother`

```
You have successfully removed the following:
    - 1 x Carnation (Pink) -> Bouquet: for mother
Here is the list of your saved bouquets:
1. For Mother :
      No flowers added so far
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

Recommends a bouquet based on the occasion and the recipient's preference. Will enter user into 'recommend mode'. Type `cancel` to exit recommend mode.

Steps:
1. Type command: `recommend`

   Expected output:
   ```
    For what occasion are you buying flowers for?
    Here are all the occasions associated with the available flowers:
    - Funeral
    - Wedding
    - Valentines
    - Mothers day
    ____________________________________________________________
    Type 'cancel' if you would like to exit the recommendation page
   ```
   
2. Type occasion: `Funeral`
   
   Expected output:
   ```
   What colour would you like your bouquets to be?
   Here is the list of colours available for the occasion:
   - Dark crimson
   - White
    ____________________________________________________________
    Type 'cancel' if you would like to exit the recommendation page
   ```

3. Select colour of bouquet: `WHITE`

   Expected output:
   
   ```
    Great we managed to find some flowers for you!
    Before we carry on what would you like to call your bouquet?
    Note: please take note 'cancel' cannot be used as a bouquet name
    ____________________________________________________________
    Type 'cancel' if you would like to exit the recommendation page
   ```
   
4. Type bouquet name: `for grandma`

   Expected output:
   ```
    What size would you like your recommended bouquet to be?
    1. Small
    2. Medium
    3. Large
    ____________________________________________________________
    Type 'cancel' if you would like to exit the recommendation page
   ```
   
5. Select bouquet size: `Large`

   Expected output:
   ```
    Would you like to save this bouquet to your list?
    Here is the full list of flowers in for grandma:
    - 2 x Pittosporum
    - 2 x Freesia
    - 1 x Dusty Miller
    - 3 x Chrysanthemum
    - 1 x Pistacia
    - 2 x Lily
    ____________________________________________________________
    Type 'yes' to save, 'no' to discard
    Type 'cancel' if you would like to exit the recommendation page
   ```
   *Note: The flowers in the bouquet are randomly generated and may differ from the example*


6. Confirm whether you want to add the recommended bouquet to your mybouquets list: `yes`
   
   ```
    Added new bouquet to list:
    for grandma
    ____________________________________________________________
    What can I do for you?
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

### Compare two flowers: `compare`
Compares the colour, occasion, meaning, type and price of flowers based on flower names

Format: `compare <flowerName1> /vs/ <flowerName2>`

- Both flowers must exist in the database

Examples:
- `compare Rose /vs/ Lily`

Expected output:
```
Here is a table of comparison between the two flowers:
+-----+-------------+--------------+----------------------------------+----------------------------------------+-------------+-----------+
| No. | Flower Name | Colour       | Occasion                         | Meaning                                | Type        | Price ($) |
+-----+-------------+--------------+----------------------------------+----------------------------------------+-------------+-----------+
|  1  | Rose        | Dark crimson | Funeral                          | Mourning                               | MAIN_FLOWER |      2.00 |
|  2  | Rose        | Red          | Valentines, Wedding, Mothers day | Love                                   | MAIN_FLOWER |      2.00 |
|  3  | Rose        | Yellow       | -                                | Jealousy, Decrease of love, Infidelity | MAIN_FLOWER |      2.00 |
|  4  | Lily        | White        | Funeral, Wedding                 | Innocence                              | MAIN_FLOWER |      2.50 |
|  5  | Lily        | Orange       | -                                | Hatred                                 | MAIN_FLOWER |      2.50 |
+-----+-------------+--------------+----------------------------------+----------------------------------------+-------------+-----------+
```

### Exit programme: `bye`

Exits the program.

Format: `bye`

Expected output:
```
Enjoy your bouquet! Thank you for using Florizz!
```

### Fuzzy Logic

Florizz uses a type of fuzzy logic that rectifies typos in user input by utilising the Damerau-Levenshtein Distance 
to measure the similarity between the user input and a valid command/flower/colour/occasion.

The Damerau-Levenshtein distance measures the minimum number of single-character edits required to change one string
into another. These edits can be insertions, deletions, substitutions and transpositions of individual characters.

When a typo is detected, Florizz will make a calculated guess as to the valid command that a user is actually referring to.

Examples of typos that Florizz can rectify include:

1. Typo in spelling
   - `flowerz Vlentine`
   - `ads Ross /c ret /q 10 /to mybouquet1`
2. Incorrect placements of whitespaces
   - `c o m  p are R ose /v  s/ L i ly`
   - `m y b ouq ue ts`
3. Absence of whitespaces
   - `flowersmothersday`
   - `infoDaisy`

Note: Fuzzy Logic only works for commands, flower names, colours and occasions, and it cannot rectify typos which
combine no. 1 with no. 2 or no. 3.

Expected output:

```
flowerz Vlentine
--> Your input is [flowerz] but I am guessing you mean [flowers]
--> Your input is [Vlentine] but I am guessing you mean [Valentines]
```

```
ads Ross /c ret /q 10 /to mybouquet1
--> Your input is [ads] but I am guessing you mean [add]
--> Your input is [Ross] but I am guessing you mean [Rose]
--> Your input is [ret] but I am guessing you mean [Red]
```

```
c o m  p are R ose /v  s/ L i ly
--> Your input is [c o m  p are R ose /v  s/ L i ly] but I am guessing you mean [compare Rose /vs/ Lily]
```

```
flowersmothersday
--> Your input is [flowersmothersday] but I am guessing you mean [flowers Mothers Day]
```

### Autosave
Florizz automatically backs up all your bouquet data onto your device in a `FlorizzBouquets.txt` file.
As such the user can transfer their bouquet data between devices by simply moving the text file to the `florizz-out/data/`
folder. However, editing this text file is not recommended as the format is very specific, so users
should do so at their own risk


