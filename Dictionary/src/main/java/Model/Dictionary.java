package Model;

import java.util.ArrayList;

public class Dictionary {
    ArrayList<Word> dictionary = new ArrayList<Word>();
//    private int count = 0;

    public void addWord(String wordTarget, String wordExplain){
        Word word = new Word(wordTarget,wordExplain);
        this.dictionary.add(word);
    }

    public String getWord(String wordTarget){
        for(int i = 0; i < dictionary.size(); i++){
            if(dictionary.get(i).getWordTarget().equals(wordTarget)){
                return dictionary.get(i).getWordExplain();
            }
        }
        System.out.println("not found");
        throw new IllegalArgumentException("not Found");
    }



}