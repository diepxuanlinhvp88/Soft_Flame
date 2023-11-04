package Controller;

import Model.DatabaseOfDict;
import Model.Word;

public class lookUp {
    private String word;
    private static DatabaseOfDict conn = new DatabaseOfDict();

    public lookUp(String word){
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
        return conn.getInfoWord(querry);
    }
    public static  void main(String[] args){
        lookUp tmp = new lookUp("1-byte character");
        System.out.println(tmp.connectAndQuerry("av"));
    }
}
