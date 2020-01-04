public class Quadratwurzel
{
    public static double quadratwurzel(double x, int n)
    {
        if (n != 0)
        {
            double x_n_minus_1 = quadratwurzel(x, n - 1);
            return (x_n_minus_1 + x / x_n_minus_1) / 2;
        }
        else
        {
            return (x + 1) / 2;
        }
    }

    public static void main(String[] args) {
        double x = 125.45;
        int n = 10;
        if (args.length == 2) {
            x = Double.parseDouble(args[0]);
            n = Integer.parseInt(args[1]);
        }
        System.out.println(quadratwurzel(x, n));
    }
}