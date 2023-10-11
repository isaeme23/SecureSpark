package org.example.loginserver;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecureURLReader {

    public static String verification(String url) throws Exception {
        // Create a file and a password representation
        File trustStoreFile = new File("myTrustStoreClient");
        char[] trustStorePassword = "123456".toCharArray();
        // Load the trust store, the default type is "pkcs12", the alternative is "jks"
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);
        // Get the singleton instance of the TrustManagerFactory
        TrustManagerFactory tmf = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());

        // Itit the TrustManagerFactory using the truststore object
        tmf.init(trustStore);
        //Set the default global SSLContext so all the connections will use it
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);
        SSLContext.setDefault(sslContext);
        // We can now read this URL
        return readURL(url);
        // This one can't be read because the Java default truststore has been
        // changed.
        //readURL("https://www.google.com");
    }
    public static String readURL(String url) throws Exception {
        String site = url;
        // Crea el objeto que representa una URL
        URL siteURL = new URL(site);
        // Crea el objeto que URLConnection
        URLConnection urlConnection = siteURL.openConnection();
        //urlConnection.("GET");
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
        StringBuffer response = new StringBuffer();
//        int responseCode = urlConnection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
//        } else{
//            System.out.println("GET connection not established");
//        }
        return response.toString();
    }


}
