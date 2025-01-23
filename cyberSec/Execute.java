import PlayfairCypherPack.PlayfairCypher;

public class Execute {
    public static void main(String[] args) {
        PlayfairCypher pf = new PlayfairCypher();
        String encTxt = pf.encrypt("playfair");
        System.out.println(encTxt);
        String decTxt = pf.decrypt(encTxt);
        System.out.println(decTxt);
    }
}
