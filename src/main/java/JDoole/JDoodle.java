package JDoole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JDoodle {
    private static String clientId = "106ecb6fd86bae734a18f6ae09c7e27b";
    private static String clientSecret = "6dcd21de8f8b43d82a4119a10e96b8462032b0aa77fb6254bbcd20a887e9d029";
    public static String apiCall(String stdin, String script, String language, String versionIndex){
        System.out.println("APICALL1");
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
                System.out.println("ERROR");
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

        System.out.println(outputBuilder.toString());
        return outputBuilder.toString();
    }
}