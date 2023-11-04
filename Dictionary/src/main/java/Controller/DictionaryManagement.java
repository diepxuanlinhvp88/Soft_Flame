package Controller;

import Model.Dictionary;
import Model.Word;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;

public class DictionaryManagement {
    private static final Dictionary dictionary = new Dictionary();

    public DictionaryManagement() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("D:/UET/Soft_Flame/Dictionary/EngtoV.txt");
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

    public String find(String target) {
        return dictionary.getInfo(dictionary.find(target));
    }

    public String lookUp(String target) {
        return dictionary.getInfo(dictionary.find(target));
    }

    public List<String> wordListTarget(String target) {
        List<Word> a = dictionary.getWordList(target);

        List<String> res = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            res.add(a.get(i).getWordTarget());
        }

        return res;
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

    public static void main(String[] args) throws FileNotFoundException {
        DictionaryManagement d = new DictionaryManagement();
        List<Word> a = d.getDictionary().getWordList("he");
        List<String> b = new ArrayList<>();
//        for (int i = 0; i < a.size();i++) {
//            //System.out.println(a.get(i).getWordTarget());
//           // System.out.println(b.get(i));
//            b.set(i,a.get(i).getWordTarget());
//
//        }
        System.out.println(d.wordListTarget("he"));
    }
}
