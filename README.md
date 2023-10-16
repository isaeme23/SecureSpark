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

