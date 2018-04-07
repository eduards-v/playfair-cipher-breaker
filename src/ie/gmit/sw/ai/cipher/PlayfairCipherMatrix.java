package ie.gmit.sw.ai.cipher;

import ie.gmit.sw.ai.utils.KeyMatrixGen;

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
    private MatrixTransformer transformer;


    public static PlayfairCipherMatrix getInstance() {
        return instance;
    }

    private PlayfairCipherMatrix() {

        // initialize matrix and map
        matrix = KeyMatrixGen.generateKeyMatrix();
        matrixMap = new HashMap<>();
        for(int i = 0; i < 5; i++){
            for(int k = 0; k < 5; k++){
                matrixMap.put(matrix[i][k], copyCoords(i, k));
            }
        }

        // get transformer instance
        transformer = MatrixTransformer.getInstance();
    }


    // CHANGE TO TRANSFORM METHOD
    // set new matrix to try and generate map for it
    public void transform(){
        // Delegate transformation to a transformer.
        // Note that we pass address reference of the
        // matrix used in this class reflecting changes
        // to the matrix map as well.
        transformer.transformKey(matrix);
    }

    // Return coordinates of the associated character in the matrix
    public int[] getCoordsOfChar(char x){
        int[] coords = matrixMap.get(x);
        return copyCoords(coords[0], coords[1]);
    }

    // Returns a character from the matrix from passed coordinates.
    public char getCharFromCoords(int[] coords){
        return matrix[coords[0]][coords[1]];
    }


    // COPY MATRIX
    // Defensive copy of the matrix to ensure that we do not
    // loose "good" keys while transforming.
    public char[][] copyMatrix(){
        char[][] copy = new char[5][5];

        for(int i=0; i<matrix.length; i++)
            for(int j=0; j<matrix[i].length; j++)
                copy[i][j]=matrix[i][j];
        return copy;
    }

    // Since array is a mutable object helper method is required to perform
    // defensive copying of coordinates array to ensure that map values
    // are consistent until matrix transform.
    private int[] copyCoords(int x, int y){
        int[] coords = new int[2];
        coords[0] = x;
        coords[1] = y;

        return coords;
    }
}