package ie.gmit.sw.ai.heuristics;

import ie.gmit.sw.ai.utils.NGramBuilder;

public class HeuristicsCalculator {


    public double scoreDecoding(String decoding){

        double score = 0;
        // prep decoded text into quadgrams
        NGramBuilder nGramBuilder = new NGramBuilder(decoding, 4, 1);

        String [] quadgrams = nGramBuilder.build();

        // query quadgrams repository for each quadgram frequency
        for(String quad : quadgrams){
            System.out.println(quad);
        }

        // sum up frequency of all quadgrams and return

        return 0;
    }
}
