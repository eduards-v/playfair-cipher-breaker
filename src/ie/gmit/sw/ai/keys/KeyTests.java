package ie.gmit.sw.ai.keys;

import ie.gmit.sw.ai.utils.KeyMatrixGen;

public class KeyTests {

    public static void main(String[] args) {

        KeyTransformer keyTransformer = new KeyTransformer();

        keyTransformer.swapCells(KeyMatrixGen.generateKeyMatrix());
    }
}
