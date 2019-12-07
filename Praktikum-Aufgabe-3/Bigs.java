import java.lang.Math.*;

class Bigs {
    // addiert die Ziffernfelder a und b
    public static int[] add(int[] a, int[] b)
    {
        return null; // TODO
    }

    // gibt das Ziffernfeld n in lesbarer dezimaler Form aus
    // bei sehr langen Zahlen soll das Format verwendet werden, welches auch von
    // bc (s.u.) benutzt wird: Umbruch nach 68 Ziffern mit einem \ am Ende
    static void print(int[] n)
    {
        System.out.println("Printing...");
        char[] buf = new char[n.length];
        int s = 0;
        int d = n.length - 1;
        while (s < n.length)
        {
            System.out.printf("|n|=%d, s=%d, d=%d\n", n, s, n.length);
            buf[d] = n[s];
            s++;
            d--;
        }
        System.out.println(buf);
    }

    // konstruiert ein einstelliges Ziffernfeld aus der Ziffer d
    static int[] digit(int d)
    {
        int[] theDigit = {d};
        return theDigit;
    }

    // konstruiert das Ziffernfeld, welches den Wert Null repraesentiert
    static int[] Null()
    {
        int[] valueZero = {0};
        return valueZero;
    }

    // konstruiert das Ziffernfeld, welches den Wert Eins repraesentiert
    static int[] One()
    {
        int[] valueOne = {1};
        return valueOne;
    }

    // Rest des Ziffernfeldes n bei Division durch 10 (eine int-Zahl!)
    static int mod10(int[] n)
    {
        return 0; // TODO
    }

    // ganzzahliger Quotient bei Division durch 10
    static int[] div10(int[] n)
    {
        return null; // TODO
    }

    // Umwandlung einer beliebigen int-Zahl in ein Ziffernfeld
    static int[] fromInt(int n)
    {
        int i = 0;
        double cap = java.lang.Math.log10(n);
        int[] big = new int[1 + (int)(cap)];
        while (n != 0)
        {
            big[i] = n % 10;
            n /= 10;
            i++;
        }

        return big;
    }

    // kopiert den Wert von n
    static int[] copy(int[] n)
    {
        return null; // TODO
    }

    // multipliziert das Ziffernfeld n mit einer (einstelligen!) int-Zahl
    static int[] times(int[] n, int d)
    {
        return null; // TODO
    }

    // multipliziert das Ziffernfeld n mit 10
    static int[] times10(int[] n)
    {
        return null; // TODO
    }

    // multipliziert zwei Ziffernfeld
    static int[] times(int[] a, int[] b)
    {
        return null; // TODO
    }

    // Quadratzahl eines Ziffernfeldes
    static int[] square(int[] a)
    {
        return null; // TODO
    }

    // Kubikzahl eines Ziffernfeldes
    static int[] cubic(int[] a)
    {
        return null; // TODO
    }

    // Test auf kleiner-Relation zweier Ziffernfelder: a < b ?
    static boolean less(int[] a, int[] b)
    {
        return null; // TODO
    }

    // Test auf Gleichheit zweier Ziffernfelder
    static boolean equal(int[] a, int[] b)
    {
        return null; // TODO
    }

    // Test auf Korrektheit eines Ziffernfeldes: Feld existiert und enthaelt
    // mindenstens eine Ziffer, alle Positionen liegen zwischen 0 und 9
    // keine fuehrenden Nullen (ausser bei Null selbst)
    static boolean ok(int[] n)
    {
        return null; // TODO
    }

    // gibt die (kleinste) Ziffer mit der groessten Haeufigkeit in n aus
    static void maxDigit(int[] n)
    {
        // TODO
    }

    public static void main(String[] s)
    {
        int[] x = fromInt(1234);
        print(x);

        int[] a = One();
        for (int i = 0; i < 33222; ++i)
            a = times(a, 2);

        System.out.println("2^33222 hat " + a.length + " Stellen");
        print(a);
        System.out.println();
        int[] b = fromInt(13);
        int[] c = copy(b);
        for (int i = 1; i < 8978; ++i)
            c = times(c, b);

        System.out.println("13^8978 hat " + c.length + " Stellen");
        print(c);
        System.out.println();
        System.out.println(less(a, c)); // Beantwortet die Frage aus der Aufgabenueberschrift
        maxDigit(a);
        maxDigit(c);
    }
}
