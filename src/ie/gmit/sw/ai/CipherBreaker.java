package ie.gmit.sw.ai;

import ie.gmit.sw.ai.algorithm.SimulatedAnnealingAlgorithm;
import ie.gmit.sw.ai.cipher.Decoder;
import ie.gmit.sw.ai.cipher.PlayfairCipherDecoder;
import ie.gmit.sw.ai.keys.KeyRing;
import ie.gmit.sw.ai.utils.NGramBuilder;

public class CipherBreaker {

    public static void main(String[] args) {

        // Original encryption key.
        String key = "THEQUICKBROWNFXMPDVLAZYGS";

        SimulatedAnnealingAlgorithm algorithm = new SimulatedAnnealingAlgorithm(10, 50000);

        System.out.println(algorithm.findBestKey());


    }
}
