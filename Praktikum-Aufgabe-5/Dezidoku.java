// vim:et:ts=4:sw=4
import java.util.BitSet;
import java.util.stream.IntStream;

// TODO: see if we can/want/have to get rid of IntStream but that's more readable!

public class Dezidoku {
    private int BOARD_SIZE = 10;
    private int INVALID_VALUE = -1;
    private int EMPTY_VALUE = -2;

    public enum Variante { normal, mitDiagonalen };
    private Variante variant;

    private int[] board;
    private int numRows;
    private int numColumns;

    private BitSet expected = new BitSet(BOARD_SIZE);

    public Dezidoku(Variante _variant)
    {
        expected.set(0, 9, true);

        this.variant = _variant;
        this.board = new int[BOARD_SIZE * BOARD_SIZE];
        for (int i = 0; i < this.board.length; ++i)
            this.board[i] = INVALID_VALUE;
    }

    // reads map from stdin
    public void read()
    {
        int i = 0;
        while (!StdIn.isEmpty())
        {
            char ch = StdIn.readChar();
            if (ch >= '0' && ch <= '9')
            {
                this.board[i] = ch - '0';
                i++;
            }
            else if (ch == '.')
            {
                this.board[i] = EMPTY_VALUE;
                i++;
            }
            else if (ch != '\n' && ch != '\r')
            {
                //System.out.printf("Invalid character in input at offset %d: %c\n", i, ch);
                i++;
            }
        }
    }

    // helper function to peak into a specific coordinate at row @i and column @j (zero index based).
    public int at(int i, int j)
    {
        return this.board[i * BOARD_SIZE + j];
    }

    // writes solution (including frames) to stdout
    public void write()
    {
        if (this.variant == Variante.normal)
            StdOut.printf("Dezidoku\n");
        else
            StdOut.printf("Dezidoku mit Diagonalen\n");
        for (int i = 0; i < BOARD_SIZE; ++i)
        {
            if (i % 2 == 0)
                StdOut.printf("+-----------+-----------+\n");

            for (int j = 0; j < BOARD_SIZE; ++j)
            {
                if (j % 5 == 0)
                    StdOut.printf("| ");
                if (this.at(i, j) >=  0)
                    StdOut.printf("%c ", '0' + this.at(i, j));
                else
                    StdOut.printf("  ");
            }
            StdOut.printf("|\n");
        }
        StdOut.printf("+-----------+-----------+\n");
    }

    // checks validity of the game (permits unsolved slots)
    public boolean check()
    {
        return check(false);
    }

    public boolean check(boolean strict)
    {
        BitSet acc = new BitSet(BOARD_SIZE);

        // XXX blocks (5 cols x 2 rows)
        for (int bi = 0; bi < 5; ++bi)
        {
            for (int bj = 0; bj < 2; ++bj)
            {
                // check this!
                // +-------------+
                // | a b c d e f |
                // | g h i j k l |
                // +-------------+

                acc.clear();
                for (int i = 0; i < 2; ++i)
                {
                    for (int j = 0; j < 5; ++j)
                    {
                        int ai = bi * 2 + i; // absolute row offset
                        int aj = bj * 5 + j; // absolute column offset
                        int v = this.at(ai, aj);
                        if (v < 0)
                            continue;
                        if (!acc.get(v))
                            acc.set(v, true);
                        else
                        {
                            //StdOut.printf("Failed block [%d,%d]. %d already set: %s\n", ai, aj, v, acc);
                            return false;
                        }
                    }
                }

                if (strict && !acc.equals(expected))
                {
                    //StdOut.printf("Failed in strict mode. Block (%d,%d) is incomplete: %s\n", bi, bj, acc);
                    return false;
                }
            }
        }

        // XXX horizontal
        for (int i = 0; i < BOARD_SIZE; ++i)
        {
            acc.clear();
            for (int j = 0; j < BOARD_SIZE; ++j)
            {
                int v = this.at(i, j);
                if (v < 0)
                    continue;
                else if (!acc.get(v))
                    acc.set(v, true);
                else
                {
                    //StdOut.printf("Failed horizontal line %d. %d already set: %s\n", i, v, acc);
                    return false;
                }
            }
        }

        // XXX vertical
        for (int j = 0; j < BOARD_SIZE; ++j)
        {
            acc.clear();
            for (int i = 0; i < BOARD_SIZE; ++i)
            {
                int v = this.at(i, j);
                if (v < 0)
                    continue;
                else if (!acc.get(v))
                    acc.set(v, true);
                else
                {
                    //StdOut.printf("Failed vertical line %d. %d already set: %s\n", j, v, acc);
                    return false;
                }
            }
        }

        // XXX diagonal
        if (this.variant == Variante.mitDiagonalen)
        {
            // main diagonal
            acc.clear();
            for (int i = 0; i < BOARD_SIZE; i++)
            {
                int j = i;
                int v = this.at(i, j);
                if (v < 0)
                    continue;
                else if (!acc.get(v))
                    acc.set(v, true);
                else
                {
                    //StdOut.printf("Failed diagonal line in [%d,%d]. %d already set: %s\n", i, j, v, acc);
                    return false;
                }
            }

            // bottom-left to top-right diagonal
            acc.clear();
            for (int i = 0; i < BOARD_SIZE; i++)
            {
                int j = BOARD_SIZE - 1 - i;
                int v = this.at(i, j);
                if (v < 0)
                    continue;
                else if (!acc.get(v))
                    acc.set(v, true);
                else
                {
                    //StdOut.printf("Failed diagonal line in [%d,%d]. %d already set: %s\n", i, j, v, acc);
                    return false;
                }
            }
        }

        return true;
    }

    // attempts to solve the game
    public void solve()
    {
        if (trySolve())
            write();
        else
            StdOut.printf("nicht loesbar ;-(\n");
    }

    private boolean trySolve()
    {
        //System.out.printf("trySolve: @%d\n", startOffset);
        // iterate through each cell and look for empty values
        for (int i = 0; i < BOARD_SIZE; ++i)
        {
            for (int j = 0; j < BOARD_SIZE; ++j)
            {
                //System.out.printf("trySolve[%d:%d]\n", i, j);
                if (at(i, j) == EMPTY_VALUE)
                {
                    // check any possible value on that coordinate
                    for (int t = 0; t <= 9; ++t)
                    {
                        // System.out.printf("trySolve[%d] empty value at %d:%d (%d)\n",
                        //         startOffset, u / BOARD_SIZE, u % BOARD_SIZE, t);
                        this.board[i * BOARD_SIZE + j] = t;

                        // if valid, continue (recursively)
                        if (validate(i, j) && trySolve())
                            return true;
                        else
                            ;//this.board[i * BOARD_SIZE + j] = EMPTY_VALUE;
                    }
                    this.board[i * BOARD_SIZE + j] = EMPTY_VALUE;
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validate(int i, int j)
    {
        return true
            && rowConstraint(i)
            && columnConstraint(j)
            && blockConstraint(i, j)
            && diagonalsConstraint(i, j)
            ;
    }

    private boolean rowConstraint(int row) {
        BitSet constraint = new BitSet(BOARD_SIZE);
        return IntStream.range(0, BOARD_SIZE)
                        .allMatch(column -> verifyAt(constraint, row, column));
    }

    private boolean columnConstraint(int column) {
        BitSet constraint = new BitSet(BOARD_SIZE);
        return IntStream.range(0, BOARD_SIZE)
                        .allMatch(row -> verifyAt(constraint, row, column));
    }

    private boolean blockConstraint(int i, int j)
    {
        // +-------------+-----+
        // | a b c d e f | B1r |
        // | g h i j k l |     |
        // +-------------+-----+
        //   B2          | B2r
        //   B3          | B3r
        //   B4          | B4r
        //   B5          | B5r

        int colStart = (j / 5) * 5;
        int rowStart = (i / 2) * 2;

        int colEnd = colStart + 5;
        int rowEnd = rowStart + 2;

        BitSet constraint = new BitSet(BOARD_SIZE);
        for (int ai = rowStart; ai < rowEnd; ai++)
            for (int aj = colStart; aj < colEnd; aj++)
                if (!verifyAt(constraint, ai, aj))
                    return false;

        // if (constraint.equals(expected))
        //     System.out.printf("!SUCC blockConstraint(%d,%d) | %s\n", bi, bj, constraint);

        return true;
    }

    private boolean diagonalsConstraint(int i, int j)
    {
        if (variant == Variante.mitDiagonalen)
        {
            if (i == j)
            {
                // main diagonal
                BitSet constraint = new BitSet(BOARD_SIZE);
                for (int d = 0; d < BOARD_SIZE; ++d)
                    if (!verifyAt(constraint, d, d))
                        return false;
            }
            if (i == BOARD_SIZE - 1 - j)
            {
                // alternate diagonal
                BitSet constraint = new BitSet(BOARD_SIZE);
                for (int d = 0; d < BOARD_SIZE; ++d)
                    if (!verifyAt(constraint, d, BOARD_SIZE - 1 - d))
                        return false;
            }
        }
        return true;
    }

    private boolean verifyAt(BitSet constraint, int row, int column) {
        if (at(row, column) != EMPTY_VALUE)
        {
            if (!constraint.get(at(row, column)))
                constraint.set(at(row, column), true);
            else
                return false;
        }
        return true;
    }
}
