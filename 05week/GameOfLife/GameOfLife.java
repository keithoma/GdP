import java.io.*;

public class GameOfLife {
    private enum CellState { Dead, Alive };

    private CellState[][] board;
    private int rowCount;
    private int columnCount;
    private int generation;

    public interface IntGetter {
        public int op();
    }

    private static class Pair {
        int row;
        int column;

        public Pair(int a, int b) {
            row = a;
            column = b;
        }
    }

    // Returns 1 if cell state reflects Alive, 0 otherwise.
    private int abs(CellState state)
    {
        return state == CellState.Alive ? 1 : 0;
    }

    // Returns number of living neighboars that are alive.
    //
    // @param i row index to the cell
    // @param j column index to the cell
    private int getLivingNeighbours(int i, int j)
    {
        int top = i > 0 ? i - 1 : this.rowCount - 1;
        int bottom = (i + 1) % this.rowCount;

        int left = j > 0 ? j - 1 : columnCount - 1;
        int right = (j + 1) % this.columnCount;

        return abs(this.board[top][left])
             + abs(this.board[top][j])
             + abs(this.board[top][right])
             + abs(this.board[i][left])
             + abs(this.board[i][right])
             + abs(this.board[bottom][left])
             + abs(this.board[bottom][j])
             + abs(this.board[bottom][right]);
    }

    // progresses live from t to t+1
    private void evolution()
    {
        for (int i = 0; i < board.length; ++i)
        {
            for (int j = 0; j < board[i].length; ++j)
            {
                boolean selfAlive = this.board[i][j] == CellState.Alive;
                int neighAlive = getLivingNeighbours(i, j);

                if (!selfAlive && neighAlive == 3)
                    this.board[i][j] = CellState.Alive;
                else if (!(selfAlive && (neighAlive == 2 || neighAlive == 3)))
                    this.board[i][j] = CellState.Dead;
            }
        }

        this.generation = this.generation + 1;
    }

    GameOfLife(int rows, int columns, Pair[] living)
    {
        this.rowCount = rows;
        this.columnCount = columns;
        this.board = new CellState[rows][columns];
        this.generation = 1;

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; ++columns)
                this.board[i][j] = CellState.Dead;

        for (int i = 0; i < living.length; ++i)
            this.board[living[i].row][living[i].column] = CellState.Alive;
    }

    public static GameOfLife construct(IntGetter readInt)
    {
        int cols = readInt.op();
        int rows = readInt.op();
        int count = readInt.op();

        Pair[] values = new Pair[count];

        for (int k = 0; k < count; ++k)
        {
            int i = readInt.op();
            int j = readInt.op();
            values[k] = new Pair(i, j);
        }

        return new GameOfLife(rows, cols, values);
    }

    private void drawBoardToConsole()
    {
		System.out.print("\033[H\033[2J");
		System.out.flush();

        System.out.printf("+");
        for (int j = 0; j < this.columnCount; ++j)
            System.out.printf("-");
        System.out.printf("+\n");

        for (int i = 0; i < this.rowCount; ++i)
        {
            System.out.printf("|");
            for (int j = 0; j < this.columnCount; ++j)
                System.out.printf("%c", this.board[i][j] == CellState.Alive ? 'X' : ' ');
            System.out.printf("|\n");
        }

        System.out.printf("+");
        for (int j = 0; j < this.columnCount; ++j)
            System.out.printf("-");
        System.out.printf("+\n");
        System.out.printf("Generation %d\n", this.generation);
    }

    public static GameOfLife constructFromStdIn()
    {
        return construct(() -> StdIn.readInt());
    }

    public static GameOfLife constructFromFile(String fileName)
    {
        //FileReader reader = new FileReader(fileName);
        return null; // here be dragons, no time.
    }

    public void textMain()
    {
        drawBoardToConsole();
		for (;;)
		{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
			evolution();
			drawBoardToConsole();
		}
    }

    public void guiMain()
    {
        StdDraw.enableDoubleBuffering();
        for (;;)
        {
            double width = 10.0;
            double height = 10.0;
    
            for (int i = 0; i < this.rowCount; ++i)
            {
                for (int j = 0; j < this.columnCount; ++j)
                {
                    double y = i * height - height / 2;
                    double x = j * width - width / 2;
                    StdDraw.filledRectangle(x, y, width / 2, height / 2);
                }
            }

            StdDraw.show();
            StdDraw.pause(500);
            evolution();
        }
    }

    public static void main(String[] args)
    {
        GameOfLife gol = constructFromStdIn();

        if (args.length > 0)
            gol.textMain(); // debugging on non-GUI systems made eas(y|ier).
        else
            gol.guiMain();
    }
}
