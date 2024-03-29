# Design & Implementation

## Adding / Removing Flowers from Bouquet
Adding and Removing Flowers mechanism is facilitated by `AddFlowerCommand`


## [Proposed] Storage

In order to make database of flowers more robust and extensible, 
an SQL database can be used instead of current implementation of in-built data structure.

### Proposed Implementation:

The proposed storage mechanism will utilize a new interface `storage` 
which will define the behaviour of multiple storage classes. These classes include `fileHandler`, `querier`, `


### Design Considerations:
- Alternative 1 (current choice): use .txt files as storage
  - Pros:
    - Easier to implement
    - User can easily view .txt file outside of programme
  - Cons:
    - .txt file can be easily changed outside of programme, making storage of model prone to parsing failure
- Alternative 1 (current choice): use .txt file storage
    - Pros:
        - Use of SQL database instead of storing data in .txt files allows database to be more robust and less prone to external changes
        - Use of SQL database allows easier querying of needed data from the programme, skipping the parsing of .txt files when the programme starts
    - Cons:
        - Use of SQL database might be unfriendly to users who wants to access their data outside of the programme. Thus, an exporting to .txt feature is considered
# Appendix: Requirements

## Product scope

### Target user profile:
- has a need of purchasing a bouquet
- prefers desktop apps over other types 
- can type reasonably fast
- prefers typing to mouse interactions
- is comfortable using CLI apps

### Value proposition:
- organise flowers faster than using a GUI driven app
- curate flowers automatically based on context given through typed text

## User Stories
Priorities: High (must have) `* * *`, Medium (nice to have) `* *`, Low (unlikely to have) `*`

| No  | As a ...        | I can ...                                        | So that I can ...                                                             | Priority |
|-----|-----------------|--------------------------------------------------|-------------------------------------------------------------------------------|----------|
| 1   | first-time user | see an intro message detailing a command flow    | easily know what commands to type                                             | ***      |
| 2   | user            | add flowers into my list                         | easily manage the list                                                        | ***      |
| 3   | user            | remove flowers from my list                      | update my list of flowers                                                     | ***      |
| 4   | user            | navigate a list of flowers                       | easily know what flowers to add to my bouquet                                 | ***      |
| 5   | user            | specify the occasion I am buying flowers for     | get suggestion on appropriate flowers to buy without wasting time researching | ***      |
| 6   | user            | make new bouquets                                | make multiple bouquets for different occasion                                 | ***      |
| 7   | advanced user   | see detailed information of each flower          | be more knowledgeable in the flowers I buy                                    | **       |
| 8   | user            | save my bouquets to hardware                     | view it for a later date                                                      | **       |
| 9   | user            | export my list of flowers to readable files      | save and share it externally                                                  | **       |     
| 10  | advanced user   | mix and match flowers manually                   | have a customised bouquet                                                     | **       |
| 11  | user            | filter flowers based on colours                  | add matching flowers into my bouquet                                          | **       |
| 12  | first-time user | type `help` for usage of command                 | better use the app                                                            | **       |
| 13  | user            | view an image of specified flower                | better visualise how the bouquet will look like                               | **       |
| 14  | user            | calculate the approximate cost of bouquets       | better budget my bouquet                                                      | *        |
| 15  | user            | ask how to prepare bouquets                      | make my own bouquets from flowers I bought                                    | *        |
| 16  | user            | view locations of flower shops around me         | buy fresh flower conveniently                                                 | *        |

## Use cases

(For all all use cases below, the System is `Florizz` and the Actor is the `user`, unless specified otherwise)

**Use case: Create a new bouquet named "for valentine" and add 3 stalks of rose into the bouquet**

Main Success Scenario (MSS):
1. User creates new bouquet named "for valentine"
2. Programme responds that a new bouquet is successfully created
3. User adds 3 stalks of rose into the bouquet
4. Programme responds that the 3 stalks of rose has been successfully added
5. Programme displays the current state of "for valentine" bouquet and all the flowers inside it

** Use case: Removing flowers from a bouquet named "for valentine" **

Main Success Scenario (MSS):
1. User removes 5 stalks of rose from the bouquet named "for valentine"
2. Programme responds that 5 stalks of rose from the specified bouquet successfully
3. Programme displays the current state of "for valentine" bouquet and all the flowers inside it

**Extensions**
- 1a. The bouquet named "for valentine" does not exist
  - 1a. Florizz shows an error message

      Use case ends.

- 2a. There are less than 5 stalks of rose in the specified bouquet
  - 2a1. Florizz shows an error message

      Use case ends. 
