package data.db;

import domain.db.IDatabaseManagement;
import domain.model.DatabaseOfDict;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class DatabaseManagement implements IDatabaseManagement {
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
    public String connectAndQuerry(String language,String word){
        String querry = String.format("SELECT * FROM %s WHERE word  = '%s';",language, word);
        //System.out.println(querry);
        //System.out.println(conn.getInfoWord(querry));
        return conn.getHtmlWord(querry);
    }
    public String connectAndQuerry(String language,long id){
        String querry = String.format("SELECT * FROM %s WHERE id = %d;",language, id);
        System.out.println(querry);
        System.out.println(conn.getInfoWord(querry));
        return conn.getInfoWord(querry);
    }

    public void addWordtoDatabase(String language,String wordTarget, String meaning, String pronpunce, String html,boolean reload) {
            if (pronpunce.isEmpty()) {
                pronpunce = " ";
            }
            if (html.isEmpty()) {
                html = String.format("<h1>%s</h1><h3><i>//</i></h3><ul><li>%s</li></ul>", wordTarget, meaning);
            }
            boolean tmp = conn.addWord(language, wordTarget, meaning, pronpunce, html);
            long id = DatabaseOfDict.getMaxId();
            if(reload) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(".\\/data/infoEditWord.txt"))) {
                    writer.write(String.valueOf(id) + "\n");
                    System.out.println("Ghi vào file thành công!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


    }
    public void removeWord(String word, String language){
        conn.remove(word,language);
    }

    public void bookMark(String word){
        String tmp = this.connectAndQuerry("av",word);
    }
    public static  void main(String[] args){

        DatabaseManagement tmp = new DatabaseManagement();
        System.out.println(tmp.connectAndQuerry("va","phiến"));
    }
}