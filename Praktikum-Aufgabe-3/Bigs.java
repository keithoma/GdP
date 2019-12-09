class Bigs {
    // Helper method to retrieve ceil(log10(.)) of a natural number.
    private static int log10d(int n)
    {
        int y = 0;
        while (n != 0)
        {
            n /= 10;
            y++;
        }
        return y;
    }

    // Helper method to test whether @p n is ZERO value or not.
    private static boolean isZero(int[] n)
    {
        for (int i = 0; i < n.length; ++i)
            if (n[i] != 0)
                return false;
        return true;
    }

    // Retrieves digit at given index position.
    private static int at(int[] n, int i)
    {
        return i >= 0 && i < n.length ? n[i] : 0;
    }

    // Creates copy of @p n with up to @p cutoff elements.
    private static int[] copy_n(int[] n, int cutoff)
    {
        // return Arrays.copyOf(n, cutoff);
        int len = n.length < cutoff ? n.length : cutoff;
        int[] m = new int[len];
        for (int i = 0; i < len; ++i)
            m[i] = n[i];
        return m;
    }

    // Converts nubmer @p n to String.
    private static String to_s(int[] n)
    {
        // gibt das Ziffernfeld n in lesbarer dezimaler Form *zurueck*.
        // bei sehr langen Zahlen soll das Format verwendet werden, welches auch von
        // bc (s.u.) benutzt wird: Umbruch nach 68 Ziffern mit einem \ am Ende

        char[] buf = new char[n.length + 2 * (n.length / 68)];
        for (int s = n.length - 1, d = 0, i = 1; s >= 0; --s, ++d, ++i)
        {
            buf[d] = (char) ('0' + n[s]);
            if ((i % 68) == 0)
            {
                buf[++d] = '\\';
                buf[++d] = '\n';
            }
        }
        return new String(buf);
    }

    // mini-unit-test fn boolean
    private static void check(String s, boolean b)
    {
        System.out.printf("Assert %s: %s\n", s, b ? "OK" : "FAIL");
    }

    // mini-unit-test fn =
    private static void checkEqu(int[] a, int[] b)
    {
        boolean ok = Bigs.equal(a, b);
        System.out.printf("Assert %s = %s. %s\n", to_s(a), to_s(b), ok ? "OK" : "FAIL");
    }

    // mini-unit-test fn <
    private static void checkLess(int[] a, int[] b)
    {
        boolean ok = Bigs.less(a, b);
        System.out.printf("Assert %s < %s. %s\n", to_s(a), to_s(b), ok ? "OK" : "FAIL");
    }

    // -------------------------------------------------------------------------------

    // addiert die Ziffernfelder a und b
    public static int[] add(int[] a, int[] b)
    {
        int mlen = 1 + (a.length > b.length ? a.length : b.length);
        int[] m = new int[mlen];
        int carry = 0;
        for (int i = 0; i < m.length - 1; ++i)
        {
            int sum = at(a, i) + at(b, i) + carry;
            m[i] = sum % 10;
            carry = sum / 10;
        }
        if (carry != 0)
        {
            m[m.length - 1] = carry;
            return m;
        }
        else
            return copy_n(m, m.length - 1);
    }

    // gibt das Ziffernfeld n in lesbarer dezimaler Form aus
    // bei sehr langen Zahlen soll das Format verwendet werden, welches auch von
    // bc (s.u.) benutzt wird: Umbruch nach 68 Ziffern mit einem \ am Ende
    static void print(int[] n)
    {
        System.out.println(to_s(n));
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
        return n[0];
    }

    // ganzzahliger Quotient bei Division durch 10
    static int[] div10(int[] n)
    {
        if (n.length < 2)
            return Bigs.Null();
        else
        {
            int[] m = new int[n.length - 1];
            for (int i = 1; i < n.length; ++i)
                m[i - 1] = n[i];
            return m;
        }
    }

    // Umwandlung einer beliebigen int-Zahl in ein Ziffernfeld
    static int[] fromInt(int n)
    {
        if (n > 0)
        {
            int cap = log10d(n);
            int[] big = new int[cap];
            for (int i = 0; n != 0; ++i, n /= 10)
                big[i] = n % 10;

            return big;
        }
        else
            return Null();
    }

    // kopiert den Wert von n
    static int[] copy(int[] n)
    {
        return n.clone();
    }

    // multipliziert das Ziffernfeld n mit einer (einstelligen!) int-Zahl
    static int[] times(int[] n, int d)
    {
        if (d == 0)
            return Null();
        else if (d == 1)
            return n;
        else
        {
            int carry = 0;
            int[] m = new int[n.length + 1];
            for (int i = 0; i < n.length; ++i)
            {
                int p = at(n, i) * d + carry;
                m[i] = p % 10;
                carry = p / 10;
            }
            m[n.length] = carry;

            if (carry != 0)
                return m;
            else
                return copy_n(m, m.length - 1);
        }
    }

    // multipliziert das Ziffernfeld n mit 10
    static int[] times10(int[] n)
    {
        if (Bigs.isZero(n))
            return n;
        else
        {
            int[] m = new int[n.length + 1];
            m[0] = 0;
            for (int i = 0; i < n.length; ++i)
                m[i + 1] = n[i];
            return m;
        }
    }

    // a^(10^n)
    private static int[] pow10(int[] n, int e)
    {
        if (Bigs.isZero(n) || e == 0)
            return n;
        else
        {
            int[] m = new int[n.length + e];
            for (int i = 0; i < e; ++i)
                m[0] = 0;
            for (int i = 0; i < n.length; ++i)
                m[i + e] = n[i];
            return m;
        }
    }

    // multipliziert zwei Ziffernfeld
    static int[] times(int[] a, int[] b)
    {
        int[] sum = Null();
        for (int i = 0; i < b.length; ++i)
        {
            int[] p = times(a, b[i]);
            int[] q = pow10(p, i);

            sum = add(sum, q);
        }

        return sum;
    }

    // Quadratzahl eines Ziffernfeldes
    static int[] square(int[] a)
    {
        // https://bakingsciencetraveller.wordpress.com/2017/07/28/schriftliches-quadrieren/
        return times(a, a);
    }

    // Kubikzahl eines Ziffernfeldes
    static int[] cubic(int[] a)
    {
        return times(times(a, a), a);
    }

    // Test auf kleiner-Relation zweier Ziffernfelder: a < b ?
    static boolean less(int[] a, int[] b)
    {
        if (a.length < b.length)
            return true;
        else if (a.length > b.length)
            return false;
        else
        {
            // a: 1234 < b: 1235
            for (int i = a.length - 1; i >= 0; --i)
                if (a[i] > b[i])
                    return false;
            return a[0] < b[0];
        }
    }

    // Test auf Gleichheit zweier Ziffernfelder
    static boolean equal(int[] a, int[] b)
    {
        if (a.length != b.length)
            return false;

        for (int i = 0; i < a.length; ++i)
            if (a[i] != b[i])
                return false;

        return true;
    }

    // Test auf Korrektheit eines Ziffernfeldes:
    // - Feld existiert und enthaelt mindenstens eine Ziffer,
    // - alle Positionen liegen zwischen 0 und 9
    // - keine fuehrenden Nullen (ausser bei Null selbst)
    static boolean ok(int[] n)
    {
        if (n.length == 0)
            return false;
        else if (n.length > 1 && n[0] == 0)
            return false;
        for (int i = 0; i < n.length; ++i)
            if (n[i] < 0 || n[i] > 9)
                return false;
        return true;
    }

    // gibt die (kleinste) Ziffer mit der groessten Haeufigkeit in n aus
    static void maxDigit(int[] n)
    {
        int freqs[] = new int[10];
        for (int i = 0; i < n.length; ++i)
            freqs[n[i]]++;

        int max = 0;
        for (int i = 1; i < freqs.length; ++i)
            if (freqs[i] > freqs[max])
                max = i;

        System.out.printf("%d: %d\n", max, freqs[max]);
    }

    public static void main(String[] s)
    {
        check("log10d(12345) == 5", log10d(12345) == 5);
        checkEqu(add(fromInt(1234), fromInt(5678)), fromInt(6912));
        checkLess(fromInt(0), fromInt(1));
        checkLess(fromInt(99), fromInt(888));
        checkLess(fromInt(33), fromInt(34));
        checkLess(fromInt(23), fromInt(24));
        check("!(1234 < 1234)", !less(fromInt(1234), fromInt(1234)));
        checkEqu(times(fromInt(8642), 9), fromInt(77778));
        checkEqu(times(fromInt(1234), 1), fromInt(1234));
        checkEqu(times(fromInt(1234), 0), fromInt(0));
        checkEqu(times(fromInt(123), fromInt(456)), fromInt(56088));
        checkEqu(times(fromInt(12), fromInt(34)), fromInt(408));
        checkEqu(times(fromInt(23), fromInt(45)), fromInt(1035));

        // --------------------------------------------------------
        /*!*/
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
        /*!*/
    }
}
