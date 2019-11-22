/**
 * written by Christian Parpart & Kei Thoma
 */

/**
 * This function simulates one dice game (see the docstring of main()).
 */
public class Wuerfel {
    private static int simulate(int numSides, int numRequiredWins)
    {
        int iterations = 0; // the counter for the overall iterations; number of total rounds
        int hitCounter = 0; // the current number of the highest rolls in succession
        for (;;)
        {
            // for every round, add +1 to the iterations counter
            ++iterations;

            // simulate dice roll
            int diceRoll = Double.valueOf(1.0 + Math.random() * numSides).intValue();

            // if the highest side is rolled, add +1 to the hit counter
            if (diceRoll == numSides)
            {
                ++hitCounter;

                // here, we have rolled the highest side the number of required wins, so we break
                // the loop by returning the number of total rounds
                if (hitCounter == numRequiredWins)
                    return iterations;
            }
            else
                // every time we roll a side which is not the highest, we must set the hit counter
                // to 0 as we are looking to count the wins in succession
                hitCounter = 0;
        }
    }

    /**
     * We simulate a dice game in which a round is won, if the player rolls the highest side of a
     * N-sided dice. A game is won, if the player wins a round K times in succession. This game is
     * repeated S times.
     * The following function returns the average number of rolls to win a game according to the
     * parameters stated above.
     */
    public static void main(String[] args)
    {
        int numSides = Integer.parseInt(args[0]);         // number of sides of the dice; N
        int numRequiredWins = Integer.parseInt(args[1]);  // number of required wins; K
        int numGames = Integer.parseInt(args[2]);         // number of games; S

        int sum = 0; // the total number of rounds of all played games
        for (int game = 0; game < numGames; ++game)
            sum += simulate(numSides, numRequiredWins); // after every game, add the number of
                                                        // rounds to the total

        double avg = Double.valueOf(sum) / numGames; // get the average
        System.out.println(avg); // print the average
    }
}
