package domain.model;

public class Word {
    private final String wordTarget;
    private final String wordExplain;
    private final String pronounce;
    private final String html;
    private  long wordId;

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

    public long getWordId() {
        return wordId;
    }

    public Word(long id, String wordTarget, String wordExplain, String pronounce, String html) {
        this.wordId = id;
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.pronounce = pronounce;
        this.html = html;
    }

    public String getHtml() {
        return html;
    }

}