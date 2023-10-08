package org.example;

import static spark.Spark.*;

public class SecureSpark {

    public static void main(String[] args) {
        port(getPort());
        secure("keystores/ecikeystore.p12", "123456", null, null);
        get("/hello", (req, res) -> "Jelou");
    }

    private static int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }
}
