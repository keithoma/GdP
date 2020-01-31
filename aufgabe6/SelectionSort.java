public class SelectionSort
{
    int frequency(String s, char ch)
    {
        int f = 0;
        for (char c : s)
            if (c == ch)
                ++f;
        return f;
    }

    // sortiere das Array a mit SelectionSort
    public static void sort(String[] a, char c)
    {
        // for (int i = 0; i < a.length - 1; ++i) {
        //     int fi = frequency(a[i]);
        //     int j = i + 1;
        //     for (; j < a.length; ++j) {
        //         int fj = frequency(a[j]);
        //         if (fj < )
        //     }
        //     if (i != j) {
        //         String tmp = a[j];
        //         a[j] = a[i];
        //         a[i] = tmp;
        //     }
        // }
        // TODO: selection sort
    }

    public static void main(String[] argv)
    {
        // ueberpruefe Eingabeparameter
        if(argv.length == 0 || argv[0].length() != 1) {
            System.out.println("Fehler: es wird ein Buchstabe als Parameter erwartet");
            System.exit(-1);
        }

        // den buchstaben aus dem Parameter extrahieren
        char c = argv[0].charAt(0);

        // alle Zeilen ueber die Konsole einlesen
        String[] a = StdIn.readAllLines();

        // Array sortieren
        sort(a, c);

        // TODO: sortierte Liste ausgeben

    }
}
