package org.example;

import spark.Spark;

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
        get("/login", (request, response) -> checkusers(request.queryParams("user"), request.queryParams("passwd")));
    }

    private static int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }

    private static String checkusers(String user, String passwd){
        if (user.equals("Isa") && passwd.equals("1234")){
            return "Success";
        } else{
            return "Not correct";
        }
    }
}
