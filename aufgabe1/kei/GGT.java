// Praktikum, Aufgabe 1
public class GGT
{
    public static int ggt(int a, int b)
    {
        while (b != 0)
        {
            int m = a % b;
            a = b;
            b = m;
        }
        return a;
    }

    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.out.println("Benutz mich: GGT a b            | a > 0 ^ b > 0\n");
            System.exit(1);
        }

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        if (a <= 0 || b <= 0)
        {
            System.out.println("Beide Parameter muessen groesser als 0 sein.");
            System.exit(2);
        }

        System.out.printf("ggT(%d, %d) = %d\n", a, b, ggt(a, b));
    }
}
