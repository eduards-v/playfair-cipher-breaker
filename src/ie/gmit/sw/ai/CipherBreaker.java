package ie.gmit.sw.ai;

import ie.gmit.sw.ai.cipher.Decoder;
import ie.gmit.sw.ai.cipher.PlayfairCipherDecoder;
import ie.gmit.sw.ai.keys.KeyRing;
import ie.gmit.sw.ai.utils.NGramBuilder;

public class CipherBreaker {

    public static void main(String[] args) {

        // Original encryption key.
        String key = "THEQUICKBROWNFXMPDVLAZYGS";
        // Peace of text encrypted by this key
        String encoded = "HEQEFIRCHITZMHUKOTXEDKWLHKHQVDSIEAKOZTXMTKOEEQSBXTDYHEUKUDBMKYZ";

        KeyRing keyRing = new KeyRing(10);
        // Prepare digrams for decoder
        NGramBuilder nGramBuilder = new NGramBuilder(encoded, 2);

        // Create decoder and aggregate it with encoded digrams array.
        Decoder playfairDecoder = new PlayfairCipherDecoder(nGramBuilder.build());

        // Use decode function by providing it the key to decrypt text.
        String decoded = playfairDecoder.decode(keyRing.getNew());

        // Works fine.
        System.out.println(decoded);

    }
}
