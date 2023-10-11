package org.example.loginserver;

import spark.Spark;

import static org.example.loginserver.SecureURLReader.verification;

import static spark.Spark.*;

public class SecureSpark {

    public static void main(String[] args) {
        port(getPort());
        secure("cliente-keystore.jks", "123456", "myTrustStoreClient", "123456");
        get("/hello", (req, res) -> "Jelou");
        get("/", (req, res) ->{
            res.type("text/html");
            return Spark.class.getResourceAsStream("/static/index.html");
        });
        get("/login", (request, response) -> checkUsers(request.queryParams("user"), request.queryParams("passwd")));
    }

    public static int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }

    public static String checkUsers(String user, String passwd) throws Exception {
        if (user.equals("Isa") && passwd.equals("1234")){
            return showLogin();
        } else{
            return "Not correct";
        }
    }

    public static String showLogin() throws Exception {
//        URL url = new URL("https://localhost:35001/hello");
//        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
//        StringBuffer response = new StringBuffer();
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK){
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//        } else{
//            System.out.println("GET connection not established");
//        }
//        return response.toString();
        return verification("https://localhost:35001/hello");
    }
}
