# Laboratorio All Secure Spark

## Introducción
Construir una aplicación web segura usando certificados digitales.

## ¿Qué es necesario para usar este laboratorio?
- Git
- Java
- Maven
- IDE de tu elección

## ¿Cómo usar este laboratorio?
Primero, debes descargar el contenido de este repositorio y el repositiorio SecureOtherSpark:
    
    git clone https://github.com/isaeme23/SecureSpark.git
    git clone https://github.com/isaeme23/SecureOtherSpark.git

Después para compilar el contenido, usaremos la siguiente línea en ambos proyectos para compilar:

    mvn clean package

Y después podremos ejecutar los siguientes comandos en cada proyecto respectivo:
    
    java -cp "target/classes:target/dependency/*" org.example.loginserver.SecureSpark
    java -cp "target/classes:target/dependency/*" org.example.otherserver.SecureOtherSpark

Ahora, si vamos a nuestro navegador y colocamos en la barra de busqueda https://localhost:35000 se podrá
ver el contenido del login.

![img1.png](img%2Fimg1.png)

## ¿Cómo funciona?

Cuando un usuario si está registrado, tenemos el siguiente mensaje de exito:

![Captura de Pantalla 2023-10-15 a la(s) 8.59.29 p.m..png](img%2FCaptura de Pantalla 2023-10-15 a la(s) 8.59.29 p.m.png)

En caso contrario, tenemos un caso en el que se le muestra un mensaje de login fallido:

![img2.png](img%2Fimg2.png)


## ¿Cómo se logró que funcionara?
Primero, para ambos generamos un keystore

    SecureSpark: keytool -genkeypair -alias cliente -keyalg RSA -keysize 2048 -validity 365 -keystore cliente-keystore.jks
    SecureOtherSpark: keytool -genkeypair -alias servidor -keyalg RSA -keysize 2048 -validity 365 -keystore servidor-keystore.jks

**De forma local:** el nombre que nos pedía en cada uno era localhost.

**Para prueba en la nube:** el nombre que se nos pedía debia ser el de cada máquina virtual correspondiente.

Luego, generábamos el certificado para cada uno:

    SecureSpark: keytool -export -alias cliente -file cliente-cert.cer -keystore cliente-keystore.jks
    SecureOtherSpark: keytool -export -alias servidor -file servidor-cert.cer -keystore servidor-keystore.jks

Ahora, agregabamos ambos certificados al TrustStore de cada uno de los proyectos:

    SecureSpark: keytool -import -file ../SecureOtherSpark/servidor-cert.cer -alias servidor -keystore myTrustStoreClient
                 keytool -import -file ./cliente-cert.cer -alias cliente -keystore myTrustStoreClient
    SecureOtherSpark: keytool -import -file ../SecureSpark/cliente-cert.cer -alias cliente -keystore myTrustStoreServidor
                      keytool -import -file ./servidor-cert.cer -alias servidor -keystore myTrustStoreServidor

## Arquitectura a construir
![img4.png](img%2Fimg4.png)

## AWS
Se montaron 2 máquinas EC2 en AWS para cumplir con la arquitectura y tener los dos servicios corriendo para cumplir con
la arquitectura.

![img5.png](img%2Fimg5.png)


## ¿Funciona en AWS?
SI! Aqui el video:

https://github.com/isaeme23/SecureSpark/assets/77862058/f270c490-2e11-4675-afb9-15a486967001


## Autores
Isabella Manrique :)

## Agradecimientos
Profe Profe Luis Daniel Benavides Navarro
