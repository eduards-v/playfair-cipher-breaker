package ie.gmit.sw.ai.cipher;


import java.util.HashMap;
import java.util.Map;

// Flexible Matrix container class that will transform cipher key
// to a playfair cipher matrix. Provides fast access to a character and
// its corresponding coordinates inside matrix cell providing that you know
// one to get another.
public class PlayfairCipherMatrix {
    private static PlayfairCipherMatrix instance = new PlayfairCipherMatrix();

    // Trading space for time. Map contains coordinates in the matrix for each
    // character and can be accessed in constant time providing we know the key.
    // Coordinates arrays are living on the heap as long as the map also exist.
    private Map<Character, int[]> matrixMap;
    // To avoid iteration over the map and comparing coordinate from it,
    // I also generate 2d array that can be accessed in constant time
    // providing that desired item is in the known index.
    private char[][] matrix;

    public static PlayfairCipherMatrix getInstance() {
        return instance;
    }

    private PlayfairCipherMatrix() {
    }

    //
    public void newMatrix(String key){
        int ctr = 0;

        matrixMap = new HashMap<>();
        matrix = new char[5][5];

        for(int i = 0; i < 5; i++){
            for(int k = 0; k < 5; k++){

                matrixMap.put(key.charAt(ctr), createCoordsArray(i, k));
                matrix[i][k] = key.charAt(ctr);
                ctr++;
            }
        }
    }

    // Return coordinates of the associated character in the matrix
    public int[] getCoordsOfChar(char x){
        int[] coords = matrixMap.get(x);
        return createCoordsArray(coords[0], coords[1]);
    }

    // Returns a character from the matrix from passed coordinates.
    public char getCharFromCoords(int[] coords){
        return matrix[coords[0]][coords[1]];
    }

    // Since array is a mutable object helper method is required to perform
    // defensive copying of coordinates array to ensure that map values
    // are consistent until new matrix is generated.
    private int[] createCoordsArray(int x, int y){
        int[] coords = new int[2];
        coords[0] = x;
        coords[1] = y;

        return coords;
    }
}