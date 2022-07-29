import java.util.Scanner;
import java.util.ArrayList; 

public class guessing_game {

    // method to generate random number
    public static int generateNumber() {
        return 1 + (int)(100 * Math.random());
    }

    // method to generate number of guesses
    public static int numGuesses(int min, int max) {
        if (min < 1) {
            throw new IllegalArgumentException("need at least one guess");
        }
        if (max > 20) {
            throw new IllegalArgumentException("too many guesses"); 
        }
        if (max < min) {
            throw new IllegalArgumentException("max has to be greater than min");
        }
        return (int) ((Math.random() * (max - min) + min)); 
    }

    // method to run the main game 
    public static void mainGame() {
        Scanner sc = new Scanner(System.in);   // so we can register user input
        ArrayList<Integer> previousGuesses = new ArrayList<Integer>(); 
        boolean won = false; 

        // Generate number 
        int target = generateNumber();
        System.out.println("Number has been generated. Happy guessing!");

        // Now get user to enter a range of guesses
        System.out.println("Enter a range of guesses, starting with the min");
        int min = sc.nextInt(); 
        int max = sc.nextInt();
        int guesses = numGuesses(min, max);
        System.out.println("You will have " + guesses + " tries to guess the number");

        // now start iterating over the amount of guesses
        for (int i = 0; i < guesses; i++) {
            System.out.println("Take a guess");
            int guess = sc.nextInt(); 
            //previousGuesses.add(guess); 

            // check if the guess is correct and determine corresponding output
            if (guess == target) {
                System.out.println("You got it! Congratulations!");
                won = true; 
                previousGuesses.add(guess);
                break;
            } else if (guess > target && i < guesses - 1) {
                System.out.println("The guess is too high"); 
                previousGuesses.add(guess);
            } else if (guess < target && i < guesses - 1) {
                System.out.println("The guess is too low"); 
                previousGuesses.add(guess);
            } else if (previousGuesses.contains(guess)) {
                System.out.println("You already guessed this number. Pick another one");
                i--; 
            }
        }
        if (!won) {
            System.out.println("You have used up all " + guesses + " of your guesses."); 
            System.out.println("The number was " + target); 
        }
        //sc.close();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        // get user to input maximum number of times they want to play
        System.out.println("Enter how many games you want to play");
        int maxGames = sc.nextInt();
        System.out.println("You will play " + maxGames + " games");

        // keep playing until we reach the max number of games
        int played = 0; 
        while (played < maxGames) {
            mainGame();
            played++;
        }
        System.out.println("You have played all " + maxGames + " of your games");
    }
}