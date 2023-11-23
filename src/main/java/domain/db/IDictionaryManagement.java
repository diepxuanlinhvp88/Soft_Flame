package domain.db;

import domain.model.Word;

import java.io.FileNotFoundException;
import java.util.List;

public interface IDictionaryManagement {
    public String find(String target);
    public String getHtml(String targetWord);
    public String getWordEn(String target);
    public String getWordVi(String target);
    public String findWithWrong(String target);
    public List<String> wordListTarget(String target);
    public void add(Word word);
    public void remove(String target) throws FileNotFoundException;
    public void update(String oldWord, String newWord) throws FileNotFoundException;
    public boolean reLoadDictionaryFromFile(String filePath) throws FileNotFoundException;
    public String addedWords();
    public boolean reNewtxtFileFromDB();
}
