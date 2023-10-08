package org.example.loginserver;

import org.eclipse.jetty.client.HttpConnection;
import spark.Spark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static spark.Spark.*;

public class SecureSpark {

    public static void main(String[] args) {
        port(getPort());
        secure("keystores/ecikeystore.p12", "123456", null, null);
        get("/hello", (req, res) -> "Jelou");
        get("/", (req, res) ->{
            res.type("text/html");
            return Spark.class.getResourceAsStream("/static/index.html");
        });
        get("/login", (request, response) -> checkUsers(request.queryParams("user"), request.queryParams("passwd")));
    }

    private static int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }

    private static String checkUsers(String user, String passwd) throws IOException {
        if (user.equals("Isa") && passwd.equals("1234")){
            return showLogin();
        } else{
            return "Not correct";
        }
    }

    private static String showLogin() throws IOException {
        URL url = new URL("https://localhost:35001/hello");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        StringBuffer response = new StringBuffer();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else{
            System.out.println("GET connection not established");
        }
        return response.toString();
    }
}
