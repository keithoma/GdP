public class Roman {
    /*
    let map = Map [(1000, "M");
                    (900, "CM"); (500, "D"); (400, "CD"); (100, "C");
                    (90, "XC");  (50, "L");  (40, "XL");  (10, "X");
                    (9, "IX");   (5, "V");   (4, "IV");   (1, "I")]
    */
    private static int floorKey(int n)
    {
        // Map.fold (fun acc k _ -> if k <= key && k > acc then k else acc) 0 map
        if (n >= 1000) return 13;
        else if (n >= 900) return 12;
        else if (n >= 500) return 11;
        else if (n >= 400) return 10;
        else if (n >= 100) return 9;
        else if (n >= 90) return 8;
        else if (n >= 50) return 7;
        else if (n >= 40) return 6;
        else if (n >= 10) return 5;
        else if (n >= 9) return 4;
        else if (n >= 5) return 3;
        else if (n >= 4) return 2;
        else if (n >= 1) return 1;
        else return 0;
    }

    private static String[] map = {
        "M",
        "CM", "D", "CD", "C",
        "XC", "L", "XL", "X",
        "IX", "V", "IV", "I"
    };

    static String toRoman(int n) {
        int l = floorKey(n);
        if (l == n)
            return map[l];
        else
            return map[l] + toRoman(n - l);
    }

    public static void main(String[] args) {
        int N = 42;
        if (args.length != 0)
            N = Integer.parseInt(args[0]);

        System.out.println(toRoman(N));
    }
} 