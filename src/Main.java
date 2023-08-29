import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        int[] currState = randomState();
        System.out.println("Current attackers: " + calcAttackers(currState));

        int numOfMoves = 0;

        while (calcAttackers(currState) != 0) {
            numOfMoves++;
            currState = gradientDescent(currState, calcNeighbours(currState));
            System.out.println("Move " + numOfMoves + ": " + "Current attackers: " + calcAttackers(currState));
        }

        finalPrinter(currState);
    }

    //METHOD "gradientDescent": Makes the move and controls if it's local minimum.
    public static int[] gradientDescent(int[] currState, int[] move) {
        int beforeAttackers = calcAttackers(currState);

        currState[move[0]] = move[1];

        int afterAttackers = calcAttackers(currState);

        if (beforeAttackers == afterAttackers) {
            System.out.println("Local minimum recognized. Random restarting...");
            return randomState();
        }

        return currState;
    }


    //METHOD "calcNeighbours": Calculates number of attackers for all (56 + 8) neighbour states and choose the best move.
    public static int[] calcNeighbours(int[] currState) {
        int[] locations = currState.clone();
        int minAttackers = Integer.MAX_VALUE;
        int queen = -1;
        int row = -1;

        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                locations[i] = k;
                int atc = calcAttackers(locations);
                if (atc <= minAttackers) {
                    minAttackers = atc;
                    queen = i;
                    row = k;
                }
            }
            locations = currState.clone();
        }

        return (new int[]{queen, row});
    }


    //METHOD: "randomState": Creates a random state and prints it.
    public static int[] randomState() {
        Random rnd = ThreadLocalRandom.current();
        int[] currState = new int[8];

        for (int i = 0; i < 8; i++) {
            currState[i] = rnd.nextInt(8);
        }

        System.out.println();
        System.out.println("Current state:");
        System.out.print("-");
        for (int a : currState) {
            System.out.print(a);
            System.out.print("-");
        }
        System.out.println();
        System.out.println();

        return currState;
    }


    //METHOD "calcAttackers": Calculates the number of attackers of a given state.
    public static int calcAttackers(int[] currState) {
        int attackers = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == j) {
                    continue;
                } else if (currState[i] == currState[j]) {
                    attackers++;
                } else if ((currState[i] - currState[j]) == (i - j)) {
                    attackers++;
                } else if ((currState[i] - currState[j]) == (j - i)) {
                    attackers++;
                }
            }
        }

        return (attackers / 2);
    }

    //METHOD "finalPrinter": Prints the message "Solved!" and the final state.
    public static void finalPrinter(int[] currState) {
        System.out.println();
        System.out.println("┏━━━━━━━━━━━━━━┓");
        System.out.println("┃ S O L V E D! ┃");
        System.out.println("┗━━━━━━━━━━━━━━┛");
        System.out.println();
        System.out.println("Final state:");
        System.out.print("-");
        for (int a : currState) {
            System.out.print(a);
            System.out.print("-");
        }
    }

}