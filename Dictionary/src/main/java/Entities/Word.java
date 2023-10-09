package Entities;

public class Word {
    private String wordTarget;
    private String wordExplain;
    private static int wordId;

    Word(){
        wordExplain = "";
        wordTarget = "";
    }

    Word(String wordTarget, String wordExplain){
        this.wordTarget = wordTarget.trim().toLowerCase();
        this.wordExplain = wordExplain.trim().toLowerCase();
        wordId++;
    }

    public String getWordTarget(){
        return this.wordTarget;
    }
    public String getWordExplain(){
        return this.wordExplain;
    }
}