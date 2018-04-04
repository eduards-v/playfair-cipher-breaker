package ie.gmit.sw.ai;

import ie.gmit.sw.ai.cipher.PlayfairCipherMatrix;

public class CipherBreaker {

    public static void main(String[] args) {

        // Parent key
        String key = "THEQUICKBROWNFXMPDVLAZYGS";

        // Testing PlayfairCipherMatrix

        PlayfairCipherMatrix matrixContainer = PlayfairCipherMatrix.getInstance();

        // Construct new matrix from the key
        matrixContainer.newMatrix(key);

        // Get character coordinates
        int[] coords = matrixContainer.getCoordsOfChar('A');
        System.out.println("Row: " + coords[0] + " Column: " + coords[1]);

        coords[0] = 3;
        coords[1] = 1;

        // Get character from coordinates
        char character = matrixContainer.getCharFromCoords(coords);

        System.out.println(character + " from (" + coords[0] + ", " + coords[1] + ")");

    }
}
