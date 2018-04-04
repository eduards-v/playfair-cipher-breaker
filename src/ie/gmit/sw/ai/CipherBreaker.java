package ie.gmit.sw.ai;

import ie.gmit.sw.ai.cipher.Decoder;
import ie.gmit.sw.ai.cipher.PlayfairCipherDecoder;

public class CipherBreaker {

    public static void main(String[] args) {

        // Original encryption key.
        String key = "THEQUICKBROWNFXMPDVLAZYGS";
        // Peace of text encrypted by this key
        String encoded = "HEQEFIRCHITZMHUKOTXEDKWLHKHQVDSIEAKOZTXMTKOEEQSBXTDYHEUKUDBMKYZ";

        // Create decoder and aggregate it with encoded text.
        Decoder playfairDecoder = new PlayfairCipherDecoder(encoded);

        // Use decode function by providing it the key to decrypt text.
        String decoded = playfairDecoder.decode(key);

        // Works fine.
        System.out.println(decoded);

    }
}
