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
        String[] poem = {
            "Some say the world will end in fire,",
            "Some say in ice.",
            "From what I've tasted of desire",
            "I hold with those who favor fire.",
            "But if it had to perish twice,",
            "I think I know enough of hate",
            "To say that for destruction ice",
            "Is also great",
            "And would suffice."
        };

        sort(poem, 'o');

        for (int i = 0; i < 9; ++i) {
            int f = frequency(poem[i], 'o');
            System.out.print(String.format("%d: %s\n", f, poem[i]));
        }
    }
}
