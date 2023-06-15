package JDoole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JDoodle {
    private static String clientId = "4a0706e32870d70965893de9f8aac11c";
    private static String clientSecret = "e4251e7f8692d63a68cc4542136828fbd0a317c606cf3858980373f3e75abf29";
    public static String apiCall(String stdin, String script, String language, String versionIndex){
        StringBuilder outputBuilder = new StringBuilder();
        String input = "{\"clientId\": \"" + clientId +
                "\",\"clientSecret\":\"" + clientSecret +
                "\",\"stdin\":\""+stdin+
                "\",\"script\":\"" + script +
                "\",\"language\":\"" + language +
                "\",\"versionIndex\":\"" + versionIndex + "\"} ";
        try {
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            while ((output = bufferedReader.readLine()) != null) {
                outputBuilder.append(output);
            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputBuilder.toString();
    }
}