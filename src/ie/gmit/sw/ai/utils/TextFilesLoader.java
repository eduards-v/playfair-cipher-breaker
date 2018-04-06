package ie.gmit.sw.ai.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TextFilesLoader {

    private static final File source = new File("files");

    public static String loadEncryption(String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        File encryptions = new File(source, "encrypted");
        File encrypted = new File(encryptions, fileName);

        try(InputStream in = new BufferedInputStream(new FileInputStream(encrypted))){

            // BufferedReader to read underlying input stream
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            // get all lines from buffered reader as a Stream
            Stream<String> stream = br.lines();

            stream.forEach(stringBuilder::append);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }


    public static List<String[]> loadQuadGrams(){
        File resource = new File(source, "resources");
        File quadgrams = new File(resource, "4grams.txt");

        List<String[]> allQuadGrams = new ArrayList<>();

        try(InputStream in = new BufferedInputStream(new FileInputStream(quadgrams))){

            // BufferedReader to read underlying input stream
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            // get all lines from buffered reader as a Stream
            Stream<String> stream = br.lines();

            stream.forEach((String line) -> allQuadGrams.add(line.split(" ")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allQuadGrams;
    }

}
