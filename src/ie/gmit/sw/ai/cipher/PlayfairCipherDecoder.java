package ie.gmit.sw.ai.cipher;

public class PlayfairCipherDecoder implements Decoder {

    // Target for decoding, contains pairs of characters from incoming resource.
    private String[] encodedPairs;
    private PlayfairCipherMatrix matrixContainer; // Playfair cipher matrix


    public PlayfairCipherDecoder(String[] encodedPairs) {
        // Break incoming encoded string into digrams/bigrams (pairs of 2 characters).
        this.encodedPairs = encodedPairs;
        matrixContainer = PlayfairCipherMatrix.getInstance();
    }

    // Function logic adopted from: http://rosettacode.org/wiki/Playfair_cipher#Java
    @Override
    public String decode(String key) {
        StringBuilder stringBuilder = new StringBuilder();

        // Generate new matrix from the given key. It is fast enough
        // even with the nested for loop since matrix dimension is only 5x5...
        matrixContainer.newMatrix(key);

        // Positions of first and second character of digram relative to the cipher matrix.
        int coords_one[], coords_two[];

        // Loop digram list and decode each pair based on the rule they apply to.
        for(String pair : encodedPairs){

            // Get the coordinates for both characters in pair.
            // Note that container returns copy of the coordinates array
            // since they are subjects to change after one of the rules applies.
            // Ensures immutability of the matrix until new key
            // is supplied and newMatrix method is called.
            coords_one = matrixContainer.getCoordsOfChar(pair.charAt(0));
            coords_two = matrixContainer.getCoordsOfChar(pair.charAt(1));

            // Rule 2: Diagraph Letters in Same Row
            // If true, shift them by 1 column index to the left.
            if (coords_one[0] == coords_two[0]){
                // Check if first character is not located in column indexed at 0
                if (coords_one[1] > 0)
                    coords_one[1]--; // if not, shift left by 1 index
                else
                    coords_one[1] = 4; // if yes, set column position to the last index
                // Repeat the same for second character...
                if (coords_two[1] > 0)
                    coords_two[1]--;
                else
                    coords_two[1] = 4;
            }
            // Rule 3: Diagraph Letters in Same Column
            // if true, shift row coordinate ONE UP
            else if (coords_one[1] == coords_two[1]){   // no different to the rule above, this time decrement row position of both chars
                if (coords_one[0] > 0)
                    coords_one[0]--;
                else
                    coords_one[0] = 4;
                if (coords_two[0] > 0)
                    coords_two[0]--;
                else
                    coords_two[0] = 4;
            }
            // Rule 1: Diagraph Letters in Different Rows and Columns
            else{
                // It's sufficient enough to swap column position of 2 chars.
                int temp = coords_one[1];
                coords_one[1] = coords_two[1];
                coords_two[1] = temp;
            }
            // This time call matrix to retrieve decoding character from
            // its corresponding position in Playfair cipher matrix,
            // acquired by following cipher rules.
            // Append both chars to a StringBuilder and continue loop until
            // all digrams are decoded...
            stringBuilder.append(matrixContainer.getCharFromCoords(coords_one))
                         .append(matrixContainer.getCharFromCoords(coords_two));
        } // end of for loop

        // Return decrypted string text.
        return stringBuilder.toString();
    }
}
