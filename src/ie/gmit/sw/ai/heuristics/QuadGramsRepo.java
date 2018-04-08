package ie.gmit.sw.ai.heuristics;

import ie.gmit.sw.ai.utils.TextFilesLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuadGramsRepo {
    private static QuadGramsRepo instance = new QuadGramsRepo();

    private Map<String, Double> quadGramsMap;

    public static QuadGramsRepo getInstance() {

        return instance;
    }

    private QuadGramsRepo() {
        quadGramsMap = new HashMap<>();

        List<String[]> allQuads = TextFilesLoader.loadQuadGrams();
        // total sum of all quadgrams occurrence from large text sample
        double n = 0; // 4224127912
        // count sum total of all quadgrams occurrence
        for(String[] quad : allQuads){
            n += Double.valueOf(quad[1]);
            //System.out.println(size);
        }

        System.out.println(n);
        // load quad grams and probability frequencies into map
        for (String[] quad : allQuads){
            quadGramsMap.put(quad[0], logBaseFrequncy(quad[1], n));
        }
    }

    public double getQuadFrequency(String quad){

        // Handling null if quadgram not found
        if(!quadGramsMap.containsKey(quad)) return 0;

        return quadGramsMap.get(quad);
    }

    private double logBaseFrequncy(String occurrance, double n){
        double logFrequency;

        logFrequency = Math.log10(Double.valueOf(occurrance)/n);

        return logFrequency;
    }


}
