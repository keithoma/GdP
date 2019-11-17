// @KEI: is it okay to use them? Otherwise it would be trivial to implement them, but i'm lazy. :)
import java.lang.Character.toLowerCase;
import java.lang.Character.toUpperCase;

/**
 * Caesar Encryption Algorithm Engine.
 * 
 * @see Caesar.encrypt()
 */
public class Caesar {
    /// The alphabet to rotate on.
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /// Rotation offset to be used when encrypting.
    private int k;

    /**
     * Constructs the Caesar encryption engine.
     *
     * @param k Encryption offset.
     */
    public Caesar(int k) {
        this.k = k;
    }

    /**
     * Encrypts a single character.
     * 
     * @param input the input character to encrypt
     * @return the encrypted character.
     */
    public char encryptChar(char input)
    {
        char upperCaseChar = Character.toUpperCase(input);
        int index = alphabet.indexOf(upperCaseChar, 0);
        if (index == -1)
            // not found in alphabet
            return input;
        else if (upperCaseChar == input)
            // uppercase char found in alphabet
            return alphabet.charAt((index + this.k) % alphabet.length());
        else
            // lowercase char found in alphabet
            return Character.toLowerCase(alphabet.charAt((index + this.k) % alphabet.length()));
    }

    /**
     * Encrypts a text string.
     * 
     * @param input Input text string to encrypt.
     * @return the encrypted string.
     */
    public String encrypt(String input)
    {
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++)
            sb.append(encryptChar(chars[i]));
        return sb.toString();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.printf("Usage: <java> Caesar K [TEXT ...]\n");
        } else {
            // NB: Should we perform CLI input validation? (Home assignment leaves that open it seems).
            int k = Integer.parseInt(args[0]);
            Caesar caesar = new Caesar(k);

            for (int i = 1; i < args.length; i++) {
                String input = args[i];
                String output = caesar.encrypt(input);
                System.out.printf("%s\n", output);
            }
        }
    }
}