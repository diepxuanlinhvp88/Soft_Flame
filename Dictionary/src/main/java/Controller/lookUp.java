package Controller;

import Model.DatabaseOfDict;
import Model.Word;

public class lookUp {
    private String word;
    private static DatabaseOfDict conn = new DatabaseOfDict();

    lookUp(String word){
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String connectAndQuerry(){
        String querry = String.format("SELECT * FROM av WHERE word  = '%s';",this.word);
        System.out.println(querry);
        return conn.getInfoWord(querry);
    }
    public static  void main(String[] args){
        lookUp tmp = new lookUp("hi");
        System.out.println(tmp.connectAndQuerry());
    }
}
