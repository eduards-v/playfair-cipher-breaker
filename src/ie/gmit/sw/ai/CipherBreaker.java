package ie.gmit.sw.ai;

import ie.gmit.sw.ai.algorithm.SimulatedAnnealingAlgorithm;
import ie.gmit.sw.ai.keys.Key;

import java.util.ArrayList;
import java.util.List;

public class CipherBreaker {

    public static void main(String[] args) {

        // Original encryption key.
        String key = "THEQUICKBROWNFXMPDVLAZYGS";

        Key cKey;

        SimulatedAnnealingAlgorithm algorithm = new SimulatedAnnealingAlgorithm(10, 50000);

        cKey = algorithm.findBestKey();

        System.out.println("_________________________________________________");
        System.out.println("BEST FOUND KEY");
        System.out.println(cKey.toString());
        System.out.println("TARGET KEY");
        System.out.println(key);


    }
}
