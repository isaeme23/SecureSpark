package org.example.loginserver;

import spark.Spark;
import static org.example.loginserver.SecureURLReader.verification;
import static spark.Spark.*;


/**
 * Clase principal que mostrará el login
 */
public class SecureSpark {

    /**
     * Metodo principal que maneja las peticiones get y verifica la seguridad contrastando los
     * certificados
     * @param args
     */

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

    /**
     * Metodo que retorna el número del puerto por el que correra el servicio
     * @return numero de puerto
     */

    public static int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }

    /**
     * Verifica que el usuario ingresado es correcto
     * @param user Usuario
     * @param passwd Contraseña
     * @return String que corresponde a la respuesta correcta o incorrecta del login
     * @throws Exception
     */

    public static String checkUsers(String user, String passwd) throws Exception {
        if (user.equals("Isa") && passwd.equals("1234")){
            return showLogin();
        } else{
            return "Not correct";
        }
    }

    /**
     * Muestra el mensaje de login exitoso conectandose a la otra máquina virtual
     * @return Mensaje de exito
     * @throws Exception
     */

    public static String showLogin() throws Exception {
        return verification("https://ec2-54-87-169-94.compute-1.amazonaws.com:35001/hello");
    }
}