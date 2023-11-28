package data.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CallGPTApi {
    public String getFeedback(String text) {
        try {
            String pythonFile = ".\\/CallGPT.py";

            ProcessBuilder pb = new ProcessBuilder("python", pythonFile,text);

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
    public static void main(String[] args){
        CallGPTApi tp = new CallGPTApi();
        String text = "Give me some feedback about my writing ielts here:  The pie charts compare home ownership and renting for 1991 and 2007 in percentage terms. In 1991, homeowners were the most popular type of housing, accounting for 60%, or more than over half of all homes. The next largest sector was social rented homes, amounting to 23% or nearly one-third of homes. The remaining homes were mostly privately rented (11%) with a tiny fraction being social housing (6%).Sixteen years later, in 2007, the number of home owners had risen to 70%, or almost three quarters of all homes. This was an increase of 10% compared with 1991. Much of the increase in home ownership can be explained by the decrease in social rented homes, which had dropped from 23% to 17%.The percentage of privately rented homes had remained unchanged at 11%. However, there were 5 million more homes in 2007 compared with 1991 so the number of rented homes had increased despite the same percentage. Social housing has decreased three-fold from 6% in 1991 to 2% in 2007, and it remains the least popular type of housing.";
        System.out.println(tp.getFeedback(text));
    }
}

