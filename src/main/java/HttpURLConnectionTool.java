
//package HttpURLConnectionTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpURLConnectionTool {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "http://localhost:9090/SpringMVCExample";

    private static final String POST_URL = "http://localhost:9090/SpringMVCExample/home";

    private static final String POST_PARAMS = "userName=Pankaj";

    public static void main(String[] args) throws IOException {

//        sendGET();
//        System.out.println("GET DONE");
//        sendPOST();
//        System.out.println("POST DONE");






        buildRequest("http://localhost:8080/sendRequest?amountSending=1", "GET");
        buildRequest("http://localhost:8080/receiveRequest?amountReceiving=1", "GET");
        buildRequest("http://localhost:8080/confirmTransaction", "GET");
    }

    public static String[] buildRequest(String URL, String requestMethod) throws IOException {

        URL obj;
        try{
            obj = new URL(URL);
        } catch (MalformedURLException a) {
            System.out.println("URL " + URL + " was malformed, HTTP request aborted.");
            return null;
        }

        java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();
        con.setRequestMethod(requestMethod);
        int responseCode = con.getResponseCode();
        String convertedResponseCode = String.valueOf(responseCode);
        String responseOutput = new String();

        //Debug println
        //System.out.println("GET Response Code :: " + responseCode);

        // process successful response
        if (responseCode == java.net.HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result, store result to return
            // System.out.println(response.toString());
        } else {
            System.out.println("GET request didn't work: " + (responseCode));
        }

        String[] retVal = {convertedResponseCode, responseOutput};
        return retVal;
    }

//    private static void sendGET() throws IOException {
//        URL obj = new URL(GET_URL);
//        java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("User-Agent", USER_AGENT);
//        int responseCode = con.getResponseCode();
//        System.out.println("GET Response Code :: " + responseCode);
//        if (responseCode == java.net.HttpURLConnection.HTTP_OK) { // success
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            // print result
//            System.out.println(response.toString());
//        } else {
//            System.out.println("GET request not worked");
//        }
//
//    }

//    private static void sendPOST() throws IOException {
//        URL obj = new URL(POST_URL);
//        java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();
//        con.setRequestMethod("POST");
//        con.setRequestProperty("User-Agent", USER_AGENT);
//
//        // For POST only - START
//        con.setDoOutput(true);
//        OutputStream os = con.getOutputStream();
//        os.write(POST_PARAMS.getBytes());
//        os.flush();
//        os.close();
//        // For POST only - END
//
//        int responseCode = con.getResponseCode();
//        System.out.println("POST Response Code :: " + responseCode);
//
//        if (responseCode == java.net.HttpURLConnection.HTTP_OK) { //success
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            // print result
//            System.out.println(response.toString());
//        } else {
//            System.out.println("POST request not worked");
//        }
//    }

}
