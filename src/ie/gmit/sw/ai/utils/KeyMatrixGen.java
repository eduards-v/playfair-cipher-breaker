package ie.gmit.sw.ai.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class KeyMatrixGen {

    private static final char [] alphabet = "abcdefghiklmnopqrstuvwxyz".toUpperCase().toCharArray();

    public static char[][] generateKeyMatrix(){
        char[][] matrix = new char[5][5];
        int ctr = 0;
        shuffleAlphabet();
        for(int i=0; i<matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = alphabet[ctr];
                ctr++;
            }
        }

        return matrix;
    }

    private static void shuffleAlphabet() {
        int index;
        Random random = ThreadLocalRandom.current();
        for (int i = alphabet.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            if (index != i) {
                alphabet[index] ^= alphabet[i];
                alphabet[i] ^= alphabet[index];
                alphabet[index] ^= alphabet[i];
            }
        }
    }
}
