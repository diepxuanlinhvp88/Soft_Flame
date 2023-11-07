package Controller;

import Model.DatabaseOfDict;
import Model.Word;

public class DatabaseManagement {
    private String word;
    private static DatabaseOfDict conn = new DatabaseOfDict();

    public DatabaseManagement(String word){
        this.word = word;
    }
    public DatabaseManagement(){

    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    /**
     * connect to database and querry the data.
     * @param language has two values: av(English to Vietnamese) and va(Vietnamese to English)
     * @return the info of that word.
     */
    public String connectAndQuerry(String language){
        try {
            String querry = String.format("SELECT * FROM %s WHERE word  = '%s';", language, this.word);
            System.out.println(querry);
            return conn.getInfoWord(querry);
        } catch (RuntimeException e){
            e.printStackTrace();
            return "Not found";
        }
    }

    /**
     *
     * @param language va(Vietnamese to English) av(English to Vietnamese)
     * @param wordTarget
     * @param meaning
     * @param pronounce
     * @param html
     * @return
     */
    public boolean addWordtoDatabase(String language,String wordTarget, String meaning, String pronounce, String html){
        return conn.addWord(language,wordTarget,html,pronounce,meaning);
    }

    public String removeWordFromDb(String word,String language){
        return conn.remove(word,language);
    }

    public boolean updateDb(Word oldWord, Word newWord,String language){
        String tmp1 = this.removeWordFromDb(oldWord.getWordTarget(),language);
        if(!tmp1.isEmpty()) {
           return this.addWordtoDatabase(language, newWord.getWordTarget(), newWord.getWordExplain(), "", "");
        }
        return false;
    }
    public static  void main(String[] args){
        DatabaseManagement tmp = new DatabaseManagement();
        tmp.setWord("ads");
        System.out.println(tmp.connectAndQuerry("av"));
        Word tmp1 = new Word("ads","a d s");
        Word tmp2 = new Word("ads","abc");
        tmp.updateDb(tmp1,tmp2,"av");
        System.out.println(tmp.connectAndQuerry("av"));
    }
}
