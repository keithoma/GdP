public class Wuerfel {
    private static int simulate(int numSides, int numRequiredWins)
    {
        int iterations = 0;
        int hitCounter = 0;
        for (;;)
        {
            ++iterations;

            int diceRoll = Double.valueOf(1.0 + Math.random() * numSides).intValue();
            if (diceRoll == numSides)
            {
                ++hitCounter;
                if (hitCounter == numRequiredWins)
                    return iterations;
            }
            else
                hitCounter = 0;
        }
    }

    public static void main(String[] args)
    {
        int numSides = Integer.parseInt(args[0]);
        int numRequiredWins = Integer.parseInt(args[1]);
        int numGames = Integer.parseInt(args[2]);

        int sum = 0;
        for (int game = 0; game < numGames; ++game)
            sum += simulate(numSides, numRequiredWins);

        double avg = Double.valueOf(sum) / numGames;
        System.out.println(avg);
    }
}