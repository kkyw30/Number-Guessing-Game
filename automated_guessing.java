import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.Date; 

public class automated_guessing {
    // method to generate number of guesses for computer 
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

    // method for computer to generate a guess
    public static int generateGuess(int min, int max) {
        return (min + max) / 2;
    }

    // method for finding lowest value in an ArrayList
    public static int getLowest(ArrayList<Integer> values) {
        int lowest = Integer.MAX_VALUE;
        // iterate thru ArrayList to find the smallest value
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) < lowest) {
                lowest = values.get(i);
            } 
        }
        return lowest; 
    }

    // method for finding highest value in an ArrayList 
    public static int getHighest(ArrayList<Integer> values) {
        int highest = Integer.MIN_VALUE;
        // iterate to find highest value
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) > highest) {
                highest = values.get(i);
            }
        }
        return highest; 
    }

    public static void mainGame() {
        Scanner sc = new Scanner(System.in); 
        ArrayList<Integer> previousGuesses = new ArrayList<Integer>();
        boolean won = false; 

        // Get the user to generate the target number 
        System.out.println("Enter your target number");
        int target = sc.nextInt(); 
        if (target < 0 || target > 100) {
            System.out.println("Enter new target number between 0 and 100");
            target = sc.nextInt();
        }
        System.out.println("You have chosen " + target + " to be your target number");

        // Get user to input range of guesses
        System.out.println("Enter a range of guesses for the computer, starting with the min");
        int min = sc.nextInt();
        int max = sc.nextInt();
        int guesses = numGuesses(min, max);
        System.out.println("The computer will have " + guesses + " attempts to guess your number");

        // booleans to help computer know how to guess--will be same size as ArrayList of guesses
        ArrayList<Boolean> lows = new ArrayList<Boolean>();
        ArrayList<Boolean> highs = new ArrayList<Boolean>();

        // constantly change limits of guessing for generateGuess() 
        int bottom = 0; 
        int highest = 100; 
        // now start iterating over the amount of guesses
        for (int i = 0; i < guesses; i++) {
            int guess = 0; 
            System.out.println("" + bottom + "" + ", " + highest);
            guess = generateGuess(bottom, highest);

            // delay terminal output so humans can better follow along with computer guesses
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }

            // check that the guess is not a repeated guess
            if (previousGuesses.contains(guess)) {
                System.out.println("Repeated guess" + "(" + guess + ")." + "Computer will pick another number");
                previousGuesses.remove(previousGuesses.size() - 1);
                i--; 
            }

            previousGuesses.add(guess);
            
            // check if the guess is correct and determine corresponding message, and update highest and bottom accordingly
            if (guess == target) {
                System.out.println("The computer has guessed it");
                won = true; 
                lows.add(false);
                highs.add(false);
                break; 
            } else if (guess > target && i < guesses - 1) {
                System.out.println("Computer guessed too high " + "(" + guess + ")");
                highs.add(true);
                lows.add(false);
                // only update highest value if guess is lower than current highest value
                if (guess < highest) {
                    highest = guess;
                }
            } else if (guess < target && i < guesses - 1) {
                System.out.println("Computer guessed too low " + "(" + guess + ")");
                highs.add(false); 
                lows.add(true);
                // only update lowest value if guess is higher than current lowest value
                if (guess > bottom) {
                    bottom = guess; 
                }
            }
        }
        if (!won) {
            System.out.println("The number was " + target + " . Take it easy on the computer next time");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        // get user to input maximum number of games they want to play
        System.out.println("Enter how many games you want to play");
        int maxGames = sc.nextInt();
        System.out.println("You will play " + maxGames + " games");

        // keep playing until we reach max number of games 
        int played = 0; 
        while (played < maxGames) {
            mainGame(); 
            played++; 
        }
        System.out.println("Game over");
    }
}
