package org.example.otherserver;

import static spark.Spark.*;
public class SecureOtherSpark {

    public static void main(String[] args) {
        port(getPort());
        secure("keystores/ecikeystore.p12", "123456", "keystores/myTrustStore", "123456");
        get("/hello", ((request, response) -> "Hi, login successful, you're a pro"));
    }

    public static int getPort(){
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35001;
    }
}
