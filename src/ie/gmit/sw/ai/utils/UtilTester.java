package ie.gmit.sw.ai.utils;

import ie.gmit.sw.ai.keys.Key;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class UtilTester {
    public static void main(String[] args) {
//        char[][] matrix = KeyMatrixGen.generateKeyMatrix();
//
//        StringBuilder builder = new StringBuilder(25);
//
//        for(int i=0; i<matrix.length; i++)
//            for (int j = 0; j < matrix[i].length; j++)
//                builder.append(matrix[i][j]);
//
//        System.out.println(builder.toString());

        Random rand = ThreadLocalRandom.current();
        int val;

        for (int i = 0; i < 100; i++){
            val = rand.nextInt(100);

            if (val == 5 || val == 55)
                System.out.println(val);
            else if (val == 7 || val == 32)
                System.out.println(val);
        }


    }
}
