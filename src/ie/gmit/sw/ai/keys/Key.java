package ie.gmit.sw.ai.keys;

public class Key {

    private char[][] matrix;
    private double score;

    public Key(double score) {
        this.score = score;
    }

    // send a copy of matrix because it is frequently transformed
    public char[][] getMatrix() {
        return copyMatrix();
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    private char[][] copyMatrix(){
        char[][] copy = new char[5][5];

        for(int i=0; i<matrix.length; i++)
            for(int j=0; j<matrix[i].length; j++)
                copy[i][j]=matrix[i][j];

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(25);

        for(char[] row : matrix){
            builder.append(row);
        }

        builder.append(" : " ).append(score);
        return builder.toString();
    }
}
