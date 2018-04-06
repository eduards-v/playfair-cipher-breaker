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

        // check if last candidate is better than best key so far
        // before returning best key
        if(keys[1].getScore() - keys[0].getScore() > 0){
            keys[0] = keys[1];
        }

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
            keys[2].transform();
            return;
        }

        double delta = keys[2].getScore() - keys[1].getScore();
        // check if new key is better than a candidate key
        if (delta > 0){
            // set new candidate key
            keys[1] = new Key(keys[2].getMatrix(), keys[2].getScore());
            // transform new key
            keys[2].transform();
        } else {

            // cool down the key, i.e., set current candidate to be the
            // best so far and set candidate to be a new key used for decoding
            // in current iteration.
            if(Math.random() < Math.exp(delta/algorithmTemp)){

                // first best
                if(keys[0] == null){
                    keys[0] = keys[1];
                }else{
                    // check if candidate is better than best so far
                    if(keys[1].getScore() - keys[0].getScore() > 0){
                        keys[0] = keys[1];
                    }
                }

                // make worst key to be candidate to cool down algorithm
                keys[1] = new Key(keys[2].getMatrix(), keys[2].getScore());
                // transform to try next key
                keys[2].transform();
                return;
            }
            // new key is not better
            // transform it
            keys[2].transform();

        }
    }
}
