package view;

import data.Account.Account;
import data.Account.AccountManagement;
import data.api.ParaTransWithAPI;
import data.api.TextToSpeech;
import data.db.DatabaseManagement;
import data.db.DictionaryManagement;
import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public  class  Static_variable implements Initializable {
    public static DictionaryManagement dic;

    {
        try {
            dic = new DictionaryManagement();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseManagement data = new DatabaseManagement();
    public static ParaTransWithAPI tranapi = new ParaTransWithAPI();
    public static TextToSpeech tts = new TextToSpeech();
    public static final AccountManagement accountmanagement = new AccountManagement();
    public static Account account = null;
    public static String username = null;
    public static String password = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
