package Model;

public class Word {
    private final String wordTarget;
    private final String wordExplain;

    private final String pronounce;


    private static int wordId;

    Word(){
        wordExplain = "";
        wordTarget = "";
        pronounce = "";
    }

    Word(String wordTarget, String wordExplain, String pronounce){
        this.wordTarget = wordTarget.trim().toLowerCase();
        this.wordExplain = wordExplain.trim().toLowerCase();
        this.pronounce = pronounce;
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