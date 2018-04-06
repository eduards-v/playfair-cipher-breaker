package ie.gmit.sw.ai.algorithm;

import ie.gmit.sw.ai.cipher.Decoder;
import ie.gmit.sw.ai.cipher.PlayfairCipherDecoder;
import ie.gmit.sw.ai.heuristics.HeuristicsCalculator;
import ie.gmit.sw.ai.keys.KeyRing;
import ie.gmit.sw.ai.utils.NGramBuilder;
import ie.gmit.sw.ai.utils.TextFilesLoader;

public class SimulatedAnnealingAlgorithm {

    private int temp;
    private int transitions;
    private KeyRing keyRing;
    private Decoder decoder;
    private HeuristicsCalculator calculator;

    public SimulatedAnnealingAlgorithm(int temp, int transitions) {
        this.temp = temp;
        this.transitions = transitions;
        this.keyRing = new KeyRing(this.temp);

        String enc = TextFilesLoader.loadEncryption("enc_hobbit.txt");
        NGramBuilder nGramBuilder = new NGramBuilder(enc, 2);
        this.decoder = new PlayfairCipherDecoder(nGramBuilder.build());

        this.calculator = new HeuristicsCalculator();
    }

    public String findBestKey(){

        for(int i = temp; i > 0; i--){
            for(int j = transitions; j > 0; j--){
                String decoded = decoder.decode(keyRing.getNew());
                double keyScore = calculator.scoreDecoding(decoded);
                keyRing.scoreNewKey(keyScore);
            }
        }

        return keyRing.getBest().toString();
    }

}
