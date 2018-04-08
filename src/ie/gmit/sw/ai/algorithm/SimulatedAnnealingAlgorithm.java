package ie.gmit.sw.ai.algorithm;

import ie.gmit.sw.ai.cipher.Decoder;
import ie.gmit.sw.ai.cipher.PlayfairCipherDecoder;
import ie.gmit.sw.ai.cipher.PlayfairCipherMatrix;
import ie.gmit.sw.ai.heuristics.HeuristicsCalculator;
import ie.gmit.sw.ai.keys.Key;
import ie.gmit.sw.ai.utils.NGramBuilder;
import ie.gmit.sw.ai.utils.TextFilesLoader;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SimulatedAnnealingAlgorithm {

    private static final int BEST_INDEX = 0;
    private static final int PARENT_INDEX = 1;

    private int temp, transitions;
    private double parentScore, childScore, bestScore;
    private Key[] keyRing;
    private Decoder decoder;
    private HeuristicsCalculator calculator;
    private PlayfairCipherMatrix cipherMatrix;

    private Random rand = ThreadLocalRandom.current();

    public SimulatedAnnealingAlgorithm(int temp, int transitions) {
        this.temp = temp;
        this.transitions = transitions;

        bestScore = parentScore = childScore = Double.NEGATIVE_INFINITY;

        initKeyRing();
        initDecoder();

        // Cipher matrix is initialized with a random key on construction
        // and key is transformed as an algorithm progresses.
        // Decoder doesn't need to worry about the keys, as it contains an
        // instance of cipher matrix to use for decoding.
        this.cipherMatrix = PlayfairCipherMatrix.getInstance();
        this.calculator = new HeuristicsCalculator();
    }

    public Key findBestKey(){

        double delta; // child - parent score
        // initial steps to set Parent
        // decode
        String decodedText = decoder.decode();
        // score the key
        parentScore = calculator.scoreDecoding(decodedText);
        // set Parent
        keyRing[PARENT_INDEX].setScore(parentScore);
        keyRing[PARENT_INDEX].setMatrix(cipherMatrix.copyMatrix());
        System.out.println("First Parent: " + keyRing[PARENT_INDEX].toString());

        for(int i = temp; i > 0; i--){
            for(int j = transitions; j > 0; j--){

                // transform matrix to make child
                cipherMatrix.transform();
                // decode
                decodedText = decoder.decode();
                // score the child key
                childScore = calculator.scoreDecoding(decodedText);

                delta = childScore - parentScore;
                // compare parent score to a child score
                if(delta > 0){
                    // child key is better
                    // copy matrix and score to set new parent
                    parentScore = childScore;
                    keyRing[PARENT_INDEX].setScore(parentScore);
                    keyRing[PARENT_INDEX].setMatrix(cipherMatrix.copyMatrix());
                    System.out.println("New Parent: " + keyRing[PARENT_INDEX].toString());

                } else {
                    // cool down algorithm to redirect search for better key
                    // rand.nextInt(10)
                    if(rand.nextInt() < Math.exp(delta/i)){

                        System.out.println("Cooling Probability: " + Math.exp(delta/i));
                        System.out.println("COOL DOWN ALGORITHM AT TEMP = " + i);
                        // try set new best key from current parent
                        if(parentScore - bestScore > 0){
                            bestScore = parentScore;
                            // copy parent key values to set best key values
                            keyRing[BEST_INDEX].setScore(bestScore);
                            // note that getMatrix method of the Key return copy of the matrix
                            keyRing[BEST_INDEX].setMatrix(keyRing[PARENT_INDEX].getMatrix());

                            System.out.println("New Best: " + keyRing[PARENT_INDEX].toString());
                        }

                        // set child key to be parent
                        parentScore = childScore;
                        keyRing[PARENT_INDEX].setScore(parentScore);
                        keyRing[PARENT_INDEX].setMatrix(cipherMatrix.copyMatrix());
                    }
                }
            } // transitions loop
        } // temperature loop

        // finally, when algorithm exit from loops,
        // check if last parent key is better than the last best key
        if(parentScore - bestScore > 0){
            bestScore = parentScore;
            // copy parent key values to set best key values
            keyRing[BEST_INDEX].setScore(bestScore);
            // note that getMatrix method of the Key return copy of the matrix
            keyRing[BEST_INDEX].setMatrix(keyRing[PARENT_INDEX].getMatrix());
        }
        // return the best key
        return keyRing[BEST_INDEX];
    } // end of method


    private void initKeyRing(){
        Key key;
        keyRing = new Key[2];

        for(int i = 0; i < 2; i++){
            key = new Key(Double.NEGATIVE_INFINITY);
            keyRing[i] = key;
        }
    }

    private void initDecoder(){
        // Load decryption target and prep it for decoder
        String enc = TextFilesLoader.loadEncryption("enc_hobbit.txt");
        NGramBuilder nGramBuilder = new NGramBuilder(enc, 2);
        // Create decoder and load preped decryption target
        this.decoder = new PlayfairCipherDecoder(nGramBuilder.build());
    }

}
