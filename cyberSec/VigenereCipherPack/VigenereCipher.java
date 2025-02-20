package VigenereCipherPack;
import java.util.HashMap;

class VigenereCipher {
    private String key;
    private HashMap<Character, Integer> char2Num = new HashMap<>();

    VigenereCipher(String key) {
        this.key = key.toLowerCase();
        this.populateCharMap();
    }

    private void populateCharMap() {
        for (char c = 'a'; c <= 'z'; c++) {
            this.char2Num.put(c, (int) (c - 'a'));
            this.char2Num.put(Character.toUpperCase(c), (int) (Character.toUpperCase(c) - 'A'));
        }
    }

    public String encrypt(String plainText) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : plainText.toCharArray()) {
            char encChar = c;
            if (i == this.key.length()) {
                i = 0;
            }
            if (Character.isAlphabetic(c)) {
                if (Character.isUpperCase(c)) {
                    encChar = (char) (((this.char2Num.get(c) + this.char2Num.get(this.key.charAt(i++))) % 26) + 'A');
                } else {
                    encChar = (char) (((this.char2Num.get(c) + this.char2Num.get(this.key.charAt(i++))) % 26) + 'a');
                }
            }

            sb.append(encChar);
        }

        return sb.toString();
    }

    public String decrypt(String cipherText) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : cipherText.toCharArray()) {
            char decChar = c;
            if (i == this.key.length()) {
                i = 0;
            }
            if (Character.isAlphabetic(c)) {
                int val = this.char2Num.get(c) - this.char2Num.get(this.key.charAt(i++)) % 26;
                if (val < 0) {
                    val += 26;
                }
                if (Character.isUpperCase(c)) {
                    decChar = (char) (val + 'A');
                } else {
                    decChar = (char) (val + 'a');
                }
            }

            sb.append(decChar);
        }

        return sb.toString();
    }

}