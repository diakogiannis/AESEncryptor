# Read me [![Javadocs](https://www.javadoc.io/badge/com.diakogiannis.alexius.security/AESEncryptor.svg)](https://www.javadoc.io/doc/com.diakogiannis.alexius.security/AESEncryptor)

# Prequisites

## Maven
You need to add the following Maven depentency

[![N|MaverRepository](https://mvnrepository.com/assets/images/392dffac024b9632664e6f2c0cac6fe5-logo.png)](https://mvnrepository.com/artifact/com.diakogiannis.alexius.security/AESEncryptor)

```xml
<dependency>
    <groupId>com.diakogiannis.alexius.security</groupId>
    <artifactId>AESEncryptor</artifactId>
    <version>1.2.7</version>
</dependency>
```
## Installation of policy files
You need to download and install [Java Cryptography Extension JCE Unlimited Strength Jurisdiction Policy Files 8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)  

Extract the jar files from the zip and save them in 
```sh 
${java.home}/jre/lib/security/ 
```
# Encryption

## Generation of a secret key

You can use your own secret key (it needs to be 256bits long) for example `Foo12345Foo12345` but there is a method to do this for you called `generateAESSecureRandom256Key()` 
Simply use
```java
String mySecretKey = generateAESSecureRandom256Key();
```
for a key generation!

## For Strings
### Encrypt
#### Example
```java
//Use you own key :)
String key = "Foo12345Foo12345";
String superSecret = "Hello World"; //This is to be encrypted

EncryptionFactory ef = new EncryptionFactoryImpl();
String encrypted = ef.encrypt(key, superSecret); //This holds the encrypted String
```
### Decrypt
#### Example
```java
//Use the same key you used for encryption :)
String key = "Foo12345Foo12345";

EncryptionFactory ef = new EncryptionFactoryImpl();
String encrypted = ef.decrypt(key, superSecret); //The "superSecret" String here is the one generated from the previous example
```
## For Byte Arrays
This is usefull when encrypting files
### Encrypt
#### Example
```java
//Use you own key :)
String key = "Foo12345Foo12345";
String superSecret = "Hello World"; //This is to be encrypted

EncryptionFactory ef = new EncryptionFactoryImpl();
 byte[] encrypted = ef.encryptBytes(key, superSecret.getBytes()); //This holds the encrypted bytes
```
### Decrypt
#### Example
```java
//Use the same key you used for encryption :)
String key = "Foo12345Foo12345";

EncryptionFactory ef = new EncryptionFactoryImpl();
byte[] encrypted = ef.decryptBytes(key, superSecret); //The "superSecret" byte array here is the one generated from the previous example
```
