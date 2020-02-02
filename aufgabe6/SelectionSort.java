public class SelectionSort
{
    static int frequency(String s, char ch)
    {
        int f = 0;
        for (int i = 0; i < s.length(); ++i)
            if (s.charAt(i) == ch)
                ++f;
        return f;
    }

    // sortiere das Array a mit SelectionSort
    public static void sort(String[] a, char c)
    {
        for (int i = 0; i < a.length - 1; i++) {
            // int fi = frequency(a[i], c);
            int max_idx = i;
            for (int j = i + 1; j < a.length; j++) {
                int fj = frequency(a[j], c);
                int fmax = frequency(a[max_idx], c);
                if (fj > fmax) {
                    max_idx = j;
                }
            }
            // swap
            String tmp = a[max_idx];
            a[max_idx] = a[i];
            a[i] = tmp;
        }
    }

    public static void main(String[] argv)
    {
        if (argv.length == 0 || argv[0].length() != 1) {
            System.out.println("Fehler: es wird ein Buchstabe als Parameter erwartet");
            System.exit(-1);
        }

        // den buchstaben aus dem Parameter extrahieren
        char c = argv[0].charAt(0);

        // alle Zeilen ueber die Konsole einlesen
        String[] a = StdIn.readAllLines();

        // Array sortieren
        sort(a, c);

        // sortierte Liste ausgeben
        for (int i = 0; i < a.length; ++i) {
            int f = frequency(a[i], 'o');
            System.out.print(String.format("%d: %s\n", f, a[i]));
        }
    }
}
