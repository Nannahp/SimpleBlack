import java.util.Random;
import java.util.Scanner;

public class SimpleBlackJack {
    public static void main(String[] args) {
        new SimpleBlackJack().run();
    }
    Random randomNumber = new Random();
    Scanner scanner = new Scanner(System.in);
    int drawnCard, playerHand, compHand;      // Value of drawn card, total player value, total computer value
    boolean playerValid = true;               // false if player has stopped drawing cards
    boolean compValid = true;                 // false is computer has stopped drawing cards
    int round = 0;                            // Number of rounds

    private void run() {
        startGame();                // Welcome + rules
        runGame();                  // Starts game
    }
    //Welcome + rules:
    public void startGame() {
        System.out.println("Welcome to Black Jack \nRules: \n- picture cards are worth 10 \n" +
                "- The player closest to 21 wins \n- If your stack is over 21 you loose");
    }
    // Checks for a win or a draw. If neither is true then gives player and computer 1 turn and displays
    // the result.
    public void runGame() {
        while (!checkWin() && !checkStop()) {
            playerTurn();
            compTurn();
            displayGame();
        }
    }
    // Prints the game round + the value of Player and Computer hand.
    public void displayGame() {
        round += 1;
        System.out.println("Round " + round);
        System.out.println("Player 1: " + playerHand);
        System.out.println("Player 2: " + compHand);
        System.out.println();
    }
    // Prompts the player if they want to draw. If yes draws a new card, if no ends future turns.
    public void playerTurn() {
        if (playerValid) {
            System.out.println("\nWould you like to draw? (y/n)");
            String playerDraw = scanner.nextLine();
            if (playerDraw.equalsIgnoreCase("y")) {
                drawCard();
                playerHand += drawnCard;  //Ads the new card value to the previous values.
                playerValid = true;
            } else
                playerValid = false;   //Ends future turns
        }
    }
    // Computer player assets if it should draw a card dependent on the value of its hand.
    public void compTurn() {
        if (compValid) {
            int probability;  // Probability that the computer will draw a card. If == 0 then it will draw.
            if (compHand >= 0 && compHand <= 15) {
                probability = 0;                              // Will always draw if its total value is less or equal to 15.
            } else if (compHand > 14 && compHand <= 17) {
                probability = randomNumber.nextInt(0, 2);     // 50 % chance that it will draw if total value it between 14 and 15
            } else if (compHand > 17 && compHand <= 20) {
                probability = randomNumber.nextInt(6);        // 16 % chance it will draw if total value is between 18 and 20
            } else probability = 1;

            if (probability == 0) {                          // If probability is ==  0 then draw
                drawCard();                                  // and add card to total value
                compHand += drawnCard;
            } else
                compValid = false;                           // If probability != 0 then end turn and future turns.
        }
    }
    // Draws a card between 1 and 13
    public void drawCard() {
        int newCard = (randomNumber.nextInt(1, 14));
        if (newCard <= 10) {                              // If card is less or equal to 10 then the value is the same
            drawnCard = newCard;
        } else {
            drawnCard = 10;                              // If the card is above 10 (picture cards) the value is 10
        }
    }
    // Checks if both Player and Computer has chosen to stop playing, and assets
    // who have the higher value or if there is a draw.
    public boolean checkStop() {
        if (!playerValid && !compValid) {     // Checks if both players have stopped
            if (playerHand == compHand) {
                System.out.println("It's a Draw!");
                return true;
            } else if (playerHand > compHand) {
                System.out.println("Player 1 Wins!");
                return true;
            } else System.out.println("Player 2 Wins!");
            return true;
        } else return false;
    }
    //Checks if either player has won. If both players have reached 21 then it is a draw.
    // If either player reaches 21 before the other, they have won.
    public boolean checkWin() {
        if (compHand == 21 && playerHand == 21) {
            System.out.println("It's a Draw!");
            return true;
        } else if (compHand == 21 || playerHand > 21) {
            System.out.println("Player 2 Wins!");
            return true;
        } else if (playerHand == 21 || compHand > 21) {
            System.out.println("Player 1 Wins!");
            return true;
        } else return false;
    }
}
