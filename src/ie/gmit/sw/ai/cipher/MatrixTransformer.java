package ie.gmit.sw.ai.cipher;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MatrixTransformer {

    private static MatrixTransformer instance = new MatrixTransformer();
    private Random rand = ThreadLocalRandom.current();

    private MatrixTransformer() {
    }

    public static MatrixTransformer getInstance() {
        return instance;
    }

    public void transformKey(char[][] matrix){
        int val = rand.nextInt(100);

        if (val == rand.nextInt(100) || val == rand.nextInt(100)){
            // swap random rows
            System.out.println("SWAP ROWS");
            swapRows(matrix);
        }
        else if (val == rand.nextInt(100) || val == rand.nextInt(100)) {
            // swap random columns
            System.out.println("SWAP COLS");
            swapColumns(matrix);
        }
        else if (val == rand.nextInt(100) || val == rand.nextInt(100)) {
            // flip all rows
            System.out.println("FLIP ROWS");
            flipRows(matrix);
        }
        else if (val == rand.nextInt(100) || val == rand.nextInt(100)) {
            // flip all columns
            System.out.println("FLIP COLS");
            flipColumns(matrix);
        }
        else if (val == rand.nextInt(100) || val == rand.nextInt(100)) {
            // reverse matrix
            System.out.println("REVERSE");
            reverseMetrix(matrix);
        }
        else {
            // swap single cells
            swapCells(matrix);
        }
    }

    private void swapRows(char[][] matrix){

        //System.out.println("Swap rows");
        int index1 = rand.nextInt(5);
        int index2 = rand.nextInt(5);

        // recursion to ensure random swapping in case
        // both indexes turns out to be equal
        if (index1 == index2){
            swapRows(matrix);
        }else{
            for (int i = 0; i < 5; i++){
                matrix[index1][i] ^= matrix[index2][i];
                matrix[index2][i] ^= matrix[index1][i];
                matrix[index1][i] ^= matrix[index2][i];
            }
        }
    }

    private void swapColumns(char[][] matrix){

        //System.out.println("Swap Columns");
        int index1 = rand.nextInt(5);
        int index2 = rand.nextInt(5);

        if (index1 == index2){
            swapColumns(matrix);
        }else{
            for (int i = 0; i < 5; i++){
                matrix[i][index1] ^= matrix[i][index2];
                matrix[i][index2] ^= matrix[i][index1];
                matrix[i][index1] ^= matrix[i][index2];
            }
        }
    }

    private void flipRows(char[][] matrix){
        //System.out.println("Flip rows");
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length / 2; j++){
                matrix[i][j] ^= matrix[i][matrix.length - j - 1];
                matrix[i][matrix.length - j - 1] ^= matrix[i][j];
                matrix[i][j] ^= matrix[i][matrix.length - j - 1];
            }
        }

    }

    private void flipColumns(char[][] matrix){

        //System.out.println("Flip Columns");
        for (int i = 0; i < matrix.length / 2; i++){
            for (int j = 0; j < matrix.length; j++){
                matrix[i][j] ^= matrix[matrix.length - i - 1][j];
                matrix[matrix.length - i - 1][j] ^= matrix[i][j];
                matrix[i][j] ^= matrix[matrix.length - i - 1][j];
            }
        }

    }

    private void reverseMetrix(char[][] matrix){
        //System.out.println("Reverse Key");
        flipRows(matrix);
        flipColumns(matrix);
    }

    private void swapCells(char[][] matrix){

        //System.out.println("Swap chars");

        int index1 = rand.nextInt(5);
        int index2 = rand.nextInt(5);
        int index3 = rand.nextInt(5);
        int index4 = rand.nextInt(5);

        //System.out.println(index1 + " ," + index2 + " | " + index3 + " ," + index4);
        if (index1 == index3 && index2 == index4){
            swapCells(matrix);
        }else{
            matrix[index1][index2] ^= matrix[index3][index4];
            matrix[index3][index4] ^= matrix[index1][index2];
            matrix[index1][index2] ^= matrix[index3][index4];
        }
    }

    private void printMatrix(char[][] matrix){

        for(char[] row : matrix){
            System.out.println(Arrays.toString(row));
        }
    }
}
