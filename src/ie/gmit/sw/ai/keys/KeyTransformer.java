package ie.gmit.sw.ai.keys;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class KeyTransformer {

    private Random rand = ThreadLocalRandom.current();

    public void transformKey(char[][] matrix){
        int val = rand.nextInt(100);

        if (val == 5 || val == 55)
            // swap random rows
            System.out.println(val);
        else if (val == 6 || val == 66)
            // swap random columns
            System.out.println(val);
        else if (val == 7 || val == 77)
            // flip all rows
            System.out.println(val);
        else if (val == 8 || val == 88)
            // flip all columns
            System.out.println(val);
        else if (val == 9 || val == 99)
            // new random key
            System.out.println(val);
        else
            // swap single cells
            System.out.println(val);
    }

    public void swapRows(char[][] matrix){

        System.out.println("Old matrix");
        printMatrix(matrix);
        int index1 = rand.nextInt(5);
        int index2 = rand.nextInt(5);

        if (index1 == index2) swapRows(matrix);

        for (int i = 0; i < 5; i++){
            matrix[index1][i] ^= matrix[index2][i];
            matrix[index2][i] ^= matrix[index1][i];
            matrix[index1][i] ^= matrix[index2][i];
        }

        System.out.println("Transformed matrix");
        printMatrix(matrix);

    }

    private void printMatrix(char[][] matrix){

        for(char[] row : matrix){
            System.out.println(Arrays.toString(row));
        }
    }
}
