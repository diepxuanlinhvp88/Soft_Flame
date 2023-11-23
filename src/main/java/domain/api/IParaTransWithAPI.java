package domain.api;

public interface IParaTransWithAPI {
    String lookUp(String text,String targetLanguage,String sourceLanguage);

    String imageToText(String path);
}
