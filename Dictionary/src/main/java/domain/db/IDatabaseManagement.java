package domain.db;

public interface IDatabaseManagement {
    public void setWord(String word);
    public String connectAndQuerry(String language);
    public String connectAndQuerry(String language,String word);
    public String connectAndQuerry(String language,long id);
    public boolean addWordtoDatabase(String language,String wordTarget, String meaning, String pronpunce, String html);
    public void removeWord(String word, String language);
    public void bookMark(String word);
}
