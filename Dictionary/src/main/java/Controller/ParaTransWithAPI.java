package Controller;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

public class ParaTransWithAPI {

    private class paragraph{
        private String text;
        private String targetLanguage;
        private String sourceLanguage;

        public paragraph(String text, String targetLanguage, String sourceLanguage) {
            this.text = text;
            this.targetLanguage = targetLanguage;
            this.sourceLanguage = sourceLanguage;
        }
    }

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
//    public static  void main(String[] args){
//        ParaTransWithAPI tmp = new ParaTransWithAPI();
//        System.out.println(tmp.lookUp("tôi rất thích cô ấy","en","vi"));
//    }

//    public static String reFormatData(String data){
//
//    }

//    public static void main(String[] args) {
//        try {
//            String scriptUrl = "https://script.google.com/macros/s/AKfycbyW-nmxwX7Cch739uccKFTGpJQXzB-9R0jBzwoKO5lkBU5BkWSgR1t17oafUwb-pTSm/exec";
//
//            // Dữ liệu bạn muốn gửi
//            String requestData = "text=I Love her&target=vi&source=";  // Thay đổi dữ liệu theo nhu cầu
//
//            // Tạo URL
//            URL url = new URL(scriptUrl);
//
//            // Mở kết nối HTTP
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            // Thiết lập phương thức POST
//            connection.setRequestMethod("POST");
//
//            // Cho phép viết dữ liệu lên kết nối
//            connection.setDoOutput(true);
//
//            // Ghi dữ liệu vào luồng đầu ra của kết nối
//            try (OutputStream os = connection.getOutputStream();
//                 OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8")) {
//                osw.write(requestData);
//                osw.flush();
//            }
//
//            // Lấy mã trạng thái HTTP của phản hồi
//            int responseCode = connection.getResponseCode();
//
//            if (responseCode == 200) {
//                // Nếu mã trạng thái là 200 (OK), đọc dữ liệu từ phản hồi
//                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                StringBuilder response = new StringBuilder();
//                String line;
//
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
//                reader.close();
//
//                String responseData = response.toString();
//
//                // Xử lý dữ liệu từ phản hồi thành công (responseData)
//                System.out.println(responseData);
//            } else {
//                // Xử lý phản hồi lỗi
//            }
//
//            // Đóng kết nối
//            connection.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
