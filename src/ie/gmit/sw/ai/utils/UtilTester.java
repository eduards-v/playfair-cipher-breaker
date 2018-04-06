package ie.gmit.sw.ai.utils;


import ie.gmit.sw.ai.keys.Key;

public class UtilTester {
    public static void main(String[] args) {

        String enc = TextFilesLoader.loadEncryption("enc_hobbit.txt");

        System.out.println(enc.length());


    }
}
