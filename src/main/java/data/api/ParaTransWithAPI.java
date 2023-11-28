package data.api;

import domain.api.IParaTransWithAPI;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import view.LoginController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;



public class ParaTransWithAPI implements IParaTransWithAPI {


    /**
     * request a post to server and get the meaning of text.
     * @param text text that need to be translated.
     * @param targetLanguage leave empty("") for auto recognize language.
     * @param sourceLanguage the language of text need to be translated.
     * @return meaning of text.
     */
    public String lookUp(String text,String targetLanguage,String sourceLanguage){

        try {
            String newtext = URLEncoder.encode(text, "UTF-8");
            String scriptUrl = "https://script.google.com/macros/s/AKfycbwMl_1m-3vXEZkkLncFxzQJWipWgPWmPUqmm_uu7SJWGL7gIn3NnA__rfacyALkbJdI/exec";
            String requestData = "text="+newtext+"&target="+targetLanguage+"&source="+sourceLanguage;
            URL url = new URL(scriptUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            //write the request data to post request
            try (OutputStream os = connection.getOutputStream();
                 OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8")) {
                osw.write(requestData);
                osw.flush();
            }

            // Get the information of response.
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String responseData = response.toString();
                System.out.println(requestData);
                connection.disconnect();
                return responseData;
            } else {
                connection.disconnect();
                return "Something went wrong went you try to connect to server";
            }
            // Đóng kết nối

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    public String imageToText(String path) {
        try {
            String pythonFile = ".\\/callAPIOCR.py";

            ProcessBuilder pb = new ProcessBuilder("python", pythonFile,path);

            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder target = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                target.append(line).append(" ");
            }
            int exitCode = process.waitFor();
            System.out.println("Tiến trình đã kết thúc với mã thoát: " + exitCode);
            return String.valueOf(target);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "cannot resolve python file";
        }
    }

    public void textToSpeechAPI(String language, String text) throws UnsupportedEncodingException {
        String Context = URLEncoder.encode(text,"UTF-8");

        try {
            String url = "https://translate.google.com/translate_tts?ie=UTF-8&client=tw-ob&tl="
                    + language + "&q=" + Context;
            System.out.println(url);

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            // Đọc dữ liệu phản hồi từ API
            InputStream inputStream = con.getInputStream();

            // Ghi dữ liệu vào file MP3
            String filePath = ".\\/data/output.mp3";
            FileOutputStream outputStream = new FileOutputStream(filePath);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Đóng luồng và hoàn thành quá trình ghi file
            inputStream.close();
            outputStream.close();
            System.out.println("File saved: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        ParaTransWithAPI tmp = new ParaTransWithAPI();
       tmp.textToSpeechAPI("en","My best friend is called Tony. We are classmates. We sit in the class on   ");




    }


}
