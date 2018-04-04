package ie.gmit.sw.ai.utils;

public class NGramBuilder {

    private String stringText;
    private int size;
    private int overlap;


    // Inspired by Null Object Pattern
    public NGramBuilder(String stringText, int size) {
        this(stringText, size, size); // set overlap to be equal to default, size of ngram,
                                     //  as a result no overlapping occurs.
    }

    // Use this if specific overlapping is required
    public NGramBuilder(String stringText, int size, int overlap) {
        this.stringText = stringText;
        this.size = size;
        this.overlap = overlap;
    }

    public String[] build(){

        int length = validateTextSize();
        validateOverlap();

        String x[] = new String[length/size];

        int counter = 0;
        for (int i = 0; i < length / size; i++) {
            x[i] = stringText.substring(counter, counter + size);
            counter = counter + overlap;
        }

        return x;
    }

    // Check if string length is odd, append X to the end to make it even
    // and return length.
    private int validateTextSize(){
        int length = stringText.length();

        if(length % size != 0){
            length++;
            stringText = stringText + 'X';
        }

        return length;
    }

    // Validate passed overlap value, if something is wrong LOG it
    // and set overlap to default (ngram size).
    private void validateOverlap(){
        if(overlap < 0 || (size - overlap) < 0){
            overlap = size;
            System.out.println("Inappropriate value for overlap is passed!\n"
                    + "Overlap value must be greater than 0 "
                    + "and less than or equal to ngram size.\n"
                    + "Activating fools defense! Setting overlap to none.\n");
        }
    }
}
