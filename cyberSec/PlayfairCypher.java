import java.util.*;

class PlayfairCypher {
    private final int mat_size = 5; // Size of the key matrix (5x5)
    private char[][] keyMat = new char[mat_size][mat_size]; // 2D array to store the key matrix
    private String key; // The key used for the Playfair cipher
    private static final String defaultKey = "Monarchy"; // Default key if no custom key is provided
    private LinkedHashSet<Character> keySet = new LinkedHashSet<>(); // Set to store unique characters from the key
    private List<Character> alphabets = new ArrayList<>(); // List to store alphabets excluding 'j'

    /**
     * Default constructor for the PlayfairCypher class.
     * This constructor initializes the PlayfairCypher object with a default key.
     * It chains to the single-argument constructor with the default key "Monarchy".
     */
    PlayfairCypher() {
        this(defaultKey);
    }

    /**
     * Constructor for the PlayfairCypher class that accepts a custom key.
     * This constructor initializes the PlayfairCypher object with the provided key.
     * If the provided key is null or empty, it defaults to the key "Monarchy".
     * It then populates the alphabet list, formats the key, and creates the key
     * matrix.
     *
     * @param key The custom key to be used for the Playfair cipher. If null or
     *            empty, defaults to "Monarchy".
     */
    PlayfairCypher(String key) {
        if (key != null && key.length() != 0) {
            this.key = key;
        } else {
            this.key = defaultKey;
        }
        this.populateAlphabets();
        String formattedKey = this.formatKey(this.key);
        this.createKeyMatrix(formattedKey);
    }

    /**
     * Populates the alphabets list with lowercase letters from 'a' to 'z',
     * excluding the letter 'j'. This method is used to initialize the list
     * of alphabets that will be used in the Playfair cipher key matrix.
     */
    private void populateAlphabets() { // populates the alphabets list with lower case
        for (char c = 'a'; c <= 'z'; c++) {
            if (c != 'j') {
                this.alphabets.add(c);
            }
        }
    }

    /**
     * Formats the given key string by performing the following operations:
     * 1. Removes all non-alphabetic characters.
     * 2. Converts all characters to lowercase.
     * 3. Replaces the character 'j' with 'i'.
     * 4. Removes duplicate characters from the resulting string.
     *
     * @param key The input key string to be formatted.
     * @return The formatted key string with duplicates removed.
     */
    private String formatKey(String key) {
        key = key.toLowerCase();
        key = key.replace('j', 'i');
        key = key.replaceAll("[^a-zA-Z]", "");
        try {
            return this.stripDuplicateChars(key);
        } catch (IllegalArgumentException e) {
            // Handle the exception (e.g., return a default string, rethrow it, or log it)
            System.out.println("Error: " + e.getMessage());
            return ""; // Or return some other fallback value
        }
    }

    /**
     * Removes duplicate characters from the given string while preserving the order
     * of first occurrences.
     * Populates the keySet with unique characters from the input string.
     *
     * @param str The input string from which duplicate characters need to be
     *            removed.
     * @return A new string with duplicate characters removed.
     * @throws IllegalArgumentException if the input string is null or empty.
     */
    private String stripDuplicateChars(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        for (char c : str.toCharArray()) { // populating the key LinkedHashSet
            this.keySet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (char c : this.keySet) {
            sb.append(c);
        }

        return sb.toString();
    }

    /**
     * Creates the key matrix for the Playfair cipher using the provided formatted
     * key
     * The key matrix is a 5x5 grid of characters, where the characters from the key
     * are placed first, followed by the remaining letters of the alphabet
     * (excluding 'j').
     * The key matrix is populated in row-major order.
     *
     * @param key The formatted key string to be used for creating the key matrix.
     *            This string should already have duplicates removed and be in
     *            lowercase.
     */
    private void createKeyMatrix(String key) {
        int s = 0; // for inserting key in key matrix
        int k = 0; // for inserting remaining alphabets in key matrix
        for (int i = 0; i < this.mat_size; i++) {
            for (int j = 0; j < this.mat_size; j++) {
                if (s < key.length()) { // populating unique alphabets from the stripped key itself
                    this.keyMat[i][j] = key.charAt(s++);
                } else { // populating remaining non-repeating alphabets
                    // Skip 'j' since it's already represented by 'i'
                    while (this.keySet.contains(this.alphabets.get(k))) {
                        k++; // Skip characters that are already in the key matrix
                    }
                    this.keyMat[i][j] = this.alphabets.get(k++);
                }
            }
        }
    }

    public void displayKeyMatrix() {
        for (int i = 0; i < mat_size; i++) {
            for (int j = 0; j < mat_size; j++) {
                System.out.print(keyMat[i][j] + " ");
            }
            System.out.println();
        }
    }
}