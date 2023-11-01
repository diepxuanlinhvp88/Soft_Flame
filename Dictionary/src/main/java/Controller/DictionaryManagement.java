package Controller;

import Model.Dictionary;
import Model.Word;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.WatchEvent;
import java.util.List;

public class DictionaryManagement {
    private static final Dictionary dictionary = new Dictionary();

    public DictionaryManagement() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("D:/UET/hk3/oop/Soft_Flame/Dictionary/EngtoV.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream
                , StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        String[] words;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                words = line.split("<token>");
//                if(words.length!=5) {
//                    System.out.println(words[0]);
//                    System.out.println(words.length);
//                }
                if (words.length >= 4) {
                    Word word = new Word(words[1], words[3]);
                    if (dictionary.find(words[1]) == null)
                        dictionary.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Dictionary getDictionary() {
        return dictionary;
    }

    public String find(String target){
        return dictionary.getInfo(dictionary.find(target));
    }

    public String lookUp(String target){
        return dictionary.getInfo(dictionary.find(target));
    }

//    public void readDataFromLocalFile(String filePath) throws FileNotFoundException {
//        FileInputStream fileInputStream = new FileInputStream("D:/java_code/Soft_Flame/Dictionary/EngtoV.txt");
//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream
//                , StandardCharsets.UTF_8);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String line;
//        String[] words;
//        try {
//            while ((line = bufferedReader.readLine()) != null) {
//                words = line.split("<token>");
////                if(words.length!=5) {
////                    System.out.println(words[0]);
////                    System.out.println(words.length);
////                }
//                if (words.length >= 4) {
//                    Word word = new Word(words[1], words[3]);
//                    if (dictionary.find(words[1]) == null)
//                        dictionary.add(word);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
