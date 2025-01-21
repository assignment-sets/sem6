package CaesarCypherPack;

class CaesarCypher {
    private int keyVal;

    CaesarCypher() {
        this(3);
    }

    CaesarCypher(int keyVal) {
        if (keyVal > 0) {
            this.keyVal = keyVal;
        }
    }

    public static void main(String[] args) {
        CaesarCypher cc = new CaesarCypher();
        String text2 = "xyz abc";
        String cipher2 = cc.encrypt(text2);
        String decrypted2 = cc.decrypt(cipher2);
        System.out.println("Original: " + text2 + ", Encrypted: " + cipher2 + ", Decrypted: " + decrypted2);
    }

    /**
     * Formats the input text by converting it to lowercase and removing any
     * non-alphabet characters
     * except spaces.
     *
     * @param text The input text to be formatted.
     * @return The formatted text with only lowercase alphabetic characters and
     *         spaces.
     */
    private String formatText(String text) {
        text = text.toLowerCase(); // Convert text to lowercase
        return text.replaceAll("[^a-z ]", ""); // Remove non-alphabet characters
    }

    /**
     * Shifts the given character by the specified key, wrapping around if necessary
     * within
     * the alphabet range (a-z). Spaces are left unchanged.
     *
     * @param c   The character to be shifted.
     * @param key The number of positions to shift the character. A positive value
     *            shifts forward,
     *            and a negative value shifts backward.
     * @return The shifted character, or the original character if it is a space.
     */
    private char shiftWithWraparound(char c, int key) {
        if (c == ' ')
            return ' ';
        // Calculate the shifted character based on 0-based index for 'a'
        char base = 'a'; // You can also use 'A' for uppercase letters
        int shiftedChar = (c - base + key) % 26; // Wrap around within 'a' to 'z'

        // If the result is negative (for decryption), add 26 to make it positive
        if (shiftedChar < 0) {
            shiftedChar += 26;
        }

        // Return the shifted character
        return (char) (shiftedChar + base);
    }

    /**
     * Encrypts the given text using the Caesar Cipher technique with the current
     * key value.
     * The text is first formatted by removing non-alphabet characters and
     * converting to lowercase.
     *
     * @param text The plain text to be encrypted.
     * @return The encrypted text, where each character is shifted by the key value.
     */
    public String encrypt(String text) {
        String formattedText = this.formatText(text);
        StringBuilder sb = new StringBuilder();
        for (char c : formattedText.toCharArray()) {
            sb.append(this.shiftWithWraparound(c, this.keyVal));
        }

        return sb.toString();
    }

    /**
     * Decrypts the given cipher text using the Caesar Cipher technique with the
     * current key value.
     * The text is first formatted by removing non-alphabet characters and
     * converting to lowercase.
     *
     * @param cipher The encrypted text to be decrypted.
     * @return The decrypted plain text, where each character is shifted back by the
     *         key value.
     */
    public String decrypt(String cipher) {
        String formattedText = this.formatText(cipher);
        StringBuilder sb = new StringBuilder();
        for (char c : formattedText.toCharArray()) {
            sb.append(this.shiftWithWraparound(c, -this.keyVal));
        }

        return sb.toString();
    }
}
