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
        int size = allQuads.size();
        // load quadgrams from text file
        for(String[] quad : allQuads){
            quadGramsMap.put(quad[0], logBaseFrequncy(quad[1], size));
        }

    }

    private double logBaseFrequncy(String frequency, int size){
        double logFrequency;

        logFrequency = Math.log10(new Double(frequency) / size);

        return logFrequency;
    }

    public void printMap(){
        quadGramsMap.forEach((k, v)-> System.out.println("Quad : " + k + " Frequency : " + v));
    }

}
