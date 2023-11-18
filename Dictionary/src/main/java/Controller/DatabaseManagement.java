package Controller;

import Model.DatabaseOfDict;
import Model.Word;

public class DatabaseManagement {
    private String word;
    private static DatabaseOfDict conn = new DatabaseOfDict();
    public DatabaseManagement(){}

    public DatabaseManagement(String word){
        this.word = word;
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
        String querry = String.format("SELECT * FROM %s WHERE word  = '%s';",language, this.word);
        System.out.println(querry);
        System.out.println(conn.getInfoWord(querry));
        return conn.getInfoWord(querry);
    }

    public boolean addWordtoDatabase(String language,String wordTarget, String meaning, String pronpunce, String html){
        return conn.addWord(language,wordTarget,html,pronpunce,meaning);
    }
    public void removeWord(String word, String language){
        conn.remove(word,language);
    }
    public static  void main(String[] args){
        DatabaseManagement tmp = new DatabaseManagement("acc");
        System.out.println(tmp.connectAndQuerry("av"));
    }
}
