
package data.db;

import domain.db.IDictionaryManagement;
import domain.model.Dictionary;
import domain.model.Word;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DictionaryManagement implements IDictionaryManagement {
    private static final Dictionary dictionary = new Dictionary();
    private static final DatabaseManagement db = new DatabaseManagement();
    private static final Word word = new Word();

    public DictionaryManagement() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(".\\/data/EngtoV.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream
                , StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        String[] words;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                words = line.split("<token>");
                if (words.length == 5) {
                    long id = Long.parseLong(words[0]);
                    Word word = new Word(id,words[1], words[3], words[4],words[2]);
                    if (dictionary.find(words[1]) == null)
                        dictionary.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * find the cost in order to transform str1 to str2.
     *
     * @param str1 : str 1
     * @param str2 : str 2
     * @return the cost.
     */
    public static int LevenshteinDistance(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= str2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }

        return dp[str1.length()][str2.length()];
    }

    public static Dictionary getDictionary() {
        return dictionary;
    }


    public String find(String target) {
        return dictionary.getInfo(dictionary.find(target));
    }


    public String getHtml(String targetWord) {
        return dictionary.getHtml(dictionary.find(targetWord));
    }

    public String getWordEn(String target) {
        return dictionary.getWordEn(dictionary.find(target));
    }

    public String getWordVi(String target) {
        return dictionary.getWordVi(dictionary.find(target));
    }


    public String findWithWrong(String target) {
        String tmp = target.substring(0, target.length() - 1);
        while (dictionary.getWordList(tmp).size() == 0) {
            tmp = tmp.substring(0, tmp.length() - 1);
        }
        int min = 100000;
        String s = "";
        for (Word i : dictionary.getWordList(tmp)) {
            if (LevenshteinDistance(target, i.getWordTarget()) < min) {
                min = LevenshteinDistance(target, i.getWordTarget());
                s = i.getWordTarget();
            }
        }
        return s;
    }

    /**
     * get the list of Word start with target.
     *
     * @param target the target Word
     * @return List.
     */
    public List<String> wordListTarget(String target) {
        List<Word> a = dictionary.getWordList(target);

        List<String> res = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            res.add(a.get(i).getWordTarget());
        }
        return res;
    }

    public void add(Word word) {
        dictionary.add(word);
        String data = String.valueOf(word.getWordId())+"<token>"+word.getWordTarget()+"<token>"
                +word.getHtml()+"<token>"+word.getWordExplain()+"<token>"+word.getPronounce()+"\n";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(".\\/data/EngtoV.txt", true))) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<String> getList() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(".\\/data/EngtoV.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream
                , StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        List<String> words = new ArrayList<>();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public void remove(String target) throws FileNotFoundException {
        dictionary.erase_from_tree(target);
        List<String> tmp = getList();
        for(int i = 0; i < tmp.size(); i++){
            String[] word = tmp.get(i).split("<token>");
            if(word.length>=2){
                if(word[1].equals(target)){
                    tmp.remove(i);
                    break;
                }
            }
            }
        reloadDicWhenExit(tmp);
    }


    public void update(String oldWord, String newWord) throws FileNotFoundException {
        Word tmp = dictionary.getWordFromtree(oldWord);
        String html = String.format("<h1>%s</h1><h3><i>//</i></h3><ul><li>%s</li></ul>",oldWord,newWord);
        Word tmp2 = new Word(tmp.getWordId(),tmp.getWordTarget(),newWord,tmp.getPronounce(),html);
        remove(oldWord);
        add(tmp2);
    }

    public boolean reloadDicWhenExit(List<String> tmp){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(".\\/data/EngtoV.txt"))) {
            for(String i: tmp) {
                writer.write(i + "\n");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * renew the dictionary if user want to update the changes.
     *
     * @param filePath path.
     * @return true if success.
     * @throws FileNotFoundException throw ex.
     */
    public boolean reLoadDictionaryFromFile(String filePath) throws FileNotFoundException {
        if (!reNewtxtFileFromDB()) return false;

        FileInputStream fileInputStream = new FileInputStream(".\\/data/EngtoV.txt");

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
                    Word word = new Word(words[1], words[3],words[2]);
                    if (dictionary.find(words[1]) == null)
                        dictionary.add(word);
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String addedWords() {

        String fileName = ".\\/data/infoEditWord.txt";

        List<String> tmp = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                tmp.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        for (String i : tmp) {
            long index = Long.parseLong(i);
            String data = db.connectAndQuerry("av", index);
            String[] words = data.split("\n");
            System.out.println(words.length);
            result += words[0] + "<token>" + words[1] + "<token>" + words[2] + "<token>" + words[3] + "<token>" + words[4] + "\n";
        }
        return result;
    }

    public boolean reNewtxtFileFromDB() {

        String tmp = addedWords();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(".\\/data/EngtoV.txt", true))) {
            writer.write(tmp);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        DictionaryManagement tmp = new DictionaryManagement();
        tmp.update("hello","hey bro");
//        tmp.reloadDicWhenExit();
        System.out.println(tmp.find("hello"));
    }
}
