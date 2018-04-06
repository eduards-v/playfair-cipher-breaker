package ie.gmit.sw.ai.keys;

import ie.gmit.sw.ai.utils.KeyMatrixGen;

public class KeyRing {

    private Key[] keys;
    private int algorithmTemp;

    public KeyRing(int algorithmTemp) {
        this.algorithmTemp = algorithmTemp;
        this.keys = new Key[3];
    }

    public Key getBest(){
        return keys[0];
    }

    public Key getNew(){
        if(keys[2] == null){
            keys[2] = new Key(KeyMatrixGen.generateKeyMatrix());
        }
        return keys[2];
    }

    public void scoreNewKey(double score){
        keys[2].setScore(score);
        selectCandidate();
    }

    private void selectCandidate(){
        // First candidate
        if(keys[1] == null){
            keys[1] = new Key(keys[2].getMatrix(), keys[2].getScore());
            // transform new key
            return;
        }

        // check if new key is better than a candidate key
        if ((keys[2].getScore() - keys[1].getScore())>0){
            // set new candidate key
            keys[1] = new Key(keys[2].getMatrix(), keys[2].getScore());
            // transform new key
        } else {

            // cool down the key, i.e., set current candidate to be the
            // best so far and set candidate to be a new key used for decoding
            // in current iteration.
        }


    }
}
