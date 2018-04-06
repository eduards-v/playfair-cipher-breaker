package ie.gmit.sw.ai;

import ie.gmit.sw.ai.algorithm.SimulatedAnnealingAlgorithm;

public class CipherBreaker {

    public static void main(String[] args) {

        // Original encryption key.
        String key = "THEQUICKBROWNFXMPDVLAZYGS";

        SimulatedAnnealingAlgorithm algorithm = new SimulatedAnnealingAlgorithm(10, 50000);

        System.out.println(algorithm.findBestKey());


    }
}
