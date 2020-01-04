public class PlaetzchenPruefer {
    public static int pruefePlaetzchen(int _count) {
        if (_count == 0)
            return _count;
        else if (_count == 1)
            return _count;
        else if (_count % 2 == 0)
            return 2 + pruefePlaetzchen((_count - 2) / 2);
        else
            return 1 + pruefePlaetzchen(_count - 1);
    }

    public static void main(String[] args) {
        if (args.length == 2)
            System.out.println(pruefePlaetzchen(Integer.parseInt(args[0])));
        else
            System.out.println(pruefePlaetzchen(100)); // expected output: 13
    }
}