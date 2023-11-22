package domain.model;

public class Word {
    private final String wordTarget;
    private final String wordExplain;
    private final String pronounce;
    private final String html;
    private static int wordId;

    public Word(){
        wordExplain = "";
        wordTarget = "";
        pronounce = "";
        html = "";
    }


    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.pronounce = "";
        html = "";
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

    public Word(String wordTarget, String wordExplain, String html) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.pronounce = "";
        this.html = html;
    }

    public String getHtml() {
        return html;
    }

}