# Report

## Info

- Name: Shaofeng Wang
- ID: 24202293
- GitHub ID: @IWantDayDayUp

## Functionality

| Feature                            | Points |    Comment    |
| :--------------------------------- | :----: | :-----------: |
| Display Cards and Labels           |   4    | Fully working |
| Prompt for user input              |   1    | Fully working |
| Accept user input                  |   1    | Fully working |
| Error message for invalid commands |   2    | Fully working |
| Error message for invalid play     |   2    | Fully working |
| Draw card from pile working        |   4    | Fully working |
| More card working                  |   6    | Fully working |
| Game over working                  |   2    | Fully working |
| Quit working                       |   2    | Fully working |
| Total                              |   24   | Fully working |

## Extra features

| Feature              |    Comment    |
| :------------------- | :-----------: |
| Display welcome info | Fully working |
| Display Rules info   | Fully working |
| Show card color      | Fully working |

## How features work

### Uppercase and LowerCase command

```java
    this.command = newCommand.trim().toUpperCase();
```

### Check the command is valid

We use `Regex` to match the command

```java
    this.command.equals("Q") || this.command.equals("D")
                || this.command.matches("[P1-7DHCS][1-7DHCS][0-9]*");
```

### Display card color

We override the method `toString()` to change the string representation of the card

```java
    @Override
    public String toString() {
        String s;
        if (this.isFaceUp) {
            if (this.color.equals("red")) {
                // red color
                s = "[\033[0;31m" + this.suit + this.value + "\033[0m]";
            } else {
                // black color
                s = "[\033[0;30m" + this.suit + this.value + "\033[0m]";
            }
        } else {
            s = "[XX]";
        }

        return s;
    }
```

### Check if the card can be added to the target pile

In our `Pile` class, we have method `isNextCard(Card card)`, which always return `true`, and we will override this method to implement different check rules

```java
    /**
     * Verify that the cards are consecutive
     */
    public boolean isNextCard(Card card) {
        return true;
    }
```

- For `LanePile`:

```java
    /**
     * Verify that the card can add to this LanePile based on follows:
     * 
     * - the colors should be different
     * - the values should be sequential and descending
     */
    @Override
    public boolean isNextCard(Card card) {
        return this.isNextValue(card) && !this.isSameColor(card);
    }
```

- For `SuitPile`:

```java
    /**
     * Verify that the card can add to this SuitPile based on follows:
     * 
     * - the suits should be different
     * - the values should be sequential and ascending
     */
    @Override
    public boolean isNextCard(Card card) {
        return this.isNextValue(card) && this.isSameSuit(card);
    }
```

### Try to move cards as many as possible

We have two move commands:

- $<label1><label2>$ = move one card from $<label1>$ to $<label2>$. E.g. “P2” or “2D”
- $<label1><label2><number>$ = move $<number>$ cards from $<label1>$ to $<label2>$. E.g. “413”.

To implement this method, we have a boolean `isTryMultiCards`

- If we specifiy the number: `isTryMultiCards = false`
- If we do not specifiy the number: `isTryMultiCards = true`

```java
    if (this.command.length() != 2) {
        this.numCards = Integer.parseInt(this.command.substring(2));
        this.isTryMultiCards = false;
    } else {
        this.numCards = 1;
        this.isTryMultiCards = true;
    }
```

When we have `isTryMultiCards = true`, we use method `tryMultiCardsPossible(Command command)` to find out it is possible to move multiple cards and the maximum number we can move

```java
    public boolean tryMultiCardsPossible(Command command) {
        if (!command.isMoveFromLane() || !command.isMoveToLane()) {
            return false;
        }

        int idxFrom = command.getIndex()[0];
        int tryMaxtime = this.lanePiles[idxFrom].getSize();

        for (int i = tryMaxtime; i >= 0; i--) {
            command.numCards = i;
            if (this.isMoveFromLanePilePossible(command) && this.isMoveToLanePilePossible(command)) {
                return true;
            }
        }
        return false;
    }
```

### The refactor example

- If we want to check the command is executable: `isReadOnly = true`
- If we want to execute the command: `isReadOnly = false`

```java
    /**
     * Get a certain number of cards from a specific pile based on the isReadOnly
     * 
     * @param pile       LanePile/DrawPile
     * @param num        The number of cards that need to be moved
     * @param isReadOnly 'true' -> read only, 'false' -> read & remove
     */
    private ArrayList<Card> getCardsFromPile(Pile pile, int num, boolean isReadOnly) {
        ArrayList<Card> cards = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            if (isReadOnly) {
                cards.addFirst(pile.cards.get(pile.getSize() - 1 - i));
            } else {
                cards.addFirst(pile.removeCard());
            }
        }

        return cards;
    }
```

### Re-display the board

In our `Board` class, we have a boolean `isNewBoard` and a method `displayBoard()`

- If the command executed successfully, `isNewBoard = true`
- If the command executed unsuccessfully, `isNewBoard = false`

```java
    /**
     * Display the board if the board changes(the command execute successfully)
     */
    public void displayBoard() {
        if (!this.isNewBoard) {
            return;
        }

        System.out.println("Score: " + this.score.toString() + "    Move: " + this.moveCnt);
        this.displayLanePile();
        this.displayDrawPile();
        this.displaySuitPile();
        System.out.println();
    }
```

### Game over check

In our `SuitPile` class, We have method `isNextCard(Card card)` to make sure that the cards are all consecutive, otherwise we can not add card to this suitPile.

So, we can directly check if each suitpile has `13` cards to verify the game is over or not

```java
    /**
     * Verify that the game is over(All cards should be in right place)
     */
    public boolean isGameOver() {
        for (SuitPile suit : this.suitPiles) {
            if (suit.getSize() != 13) {
                return false;
            }
        }

        return true;
    }
```
