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

    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.pronounce = "";
    }

    public String getPronounce() {
        return pronounce;
    }

    public String getWordTarget(){
        return this.wordTarget;
    }
    public String getWordExplain(){
        return this.wordExplain;
    }
}