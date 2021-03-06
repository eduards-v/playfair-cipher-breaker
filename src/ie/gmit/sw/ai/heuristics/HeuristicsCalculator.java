package ie.gmit.sw.ai.heuristics;

import ie.gmit.sw.ai.utils.NGramBuilder;

public class HeuristicsCalculator {

    private QuadGramsRepo repo = QuadGramsRepo.getInstance();

    public double scoreDecoding(String decoding){

        double score = 0;
        // prep decoded text into quadgrams
        NGramBuilder nGramBuilder = new NGramBuilder(decoding, 4, 1);

        String [] quadgrams = nGramBuilder.build();

        // String [] test = {"HAPP", "APPY", "PPYD", "PYDA", "YDAY", "DAYS"};

        // query quadgrams repository for each quadgram frequency
        for(String quad : quadgrams){
            // sum up frequency of each quadgram
            score += repo.getQuadFrequency(quad);
        }

//        System.out.println(repo.getQuadFrequency("HAPP"));
//        System.out.println(repo.getQuadFrequency("APPY"));
//        System.out.println(repo.getQuadFrequency("PPYD"));


        return score;
    }
}
