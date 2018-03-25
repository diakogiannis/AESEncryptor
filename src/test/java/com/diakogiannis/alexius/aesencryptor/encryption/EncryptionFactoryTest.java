/*
 * Copyright {2018} {Alexius Diakogiannis}
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.diakogiannis.alexius.aesencryptor.encryption;

import com.diakogiannis.alexius.aesencryptor.encryption.EncryptionFactoryImpl;
import com.diakogiannis.alexius.aesencryptor.encryption.EncryptionFactory;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alexius Diakogiannis alexius [at] jee.gr on 3/6/2018.
 */




/**
 * Main test class for project
 * @author Alexius Diakogiannis alexius at jee.gr
 */
public class EncryptionFactoryTest {

    /**
     * Test 
     */    
    @Test
    public void encryptDecrypt() {
        EncryptionFactory ef = new EncryptionFactoryImpl();
        
        String key = "Foo12345Foo12345";
        String superSecret = "Hello World";
        String encrypted = ef.encrypt(key, superSecret);

        Assert.assertEquals(superSecret, ef.decrypt(key, encrypted));
    }
/**
     * Test 
     */
    @Test
    public void encryptWithNull() {
        EncryptionFactory ef = new EncryptionFactoryImpl();
        String key = null;
        String superSecret = "Hello World";
        String encrypted = ef.encrypt(key, superSecret);
        Assert.assertEquals(null, encrypted);
    }
/**
     * Test 
     */
    @Test
    public void decryptWithNull() {
        EncryptionFactory ef = new EncryptionFactoryImpl();
        String decrypted = ef.decrypt("Foo12345Foo12345", null);
        Assert.assertEquals(null, decrypted);
    }
/**
     * Test 
     */
    @Test
    public void encryptDecryptBytes() {
        EncryptionFactory ef = new EncryptionFactoryImpl();
        String key = "Foo12345Foo12345";
        String superSecret = "Hello World";
        byte[] encrypted = ef.encryptBytes(key, superSecret.getBytes());

        Assert.assertEquals(superSecret, new String(ef.decryptBytes(key, encrypted)));
    }
/**
     * Test 
     */
    @Test
    public void encryptWithBytesNull() {
        EncryptionFactory ef = new EncryptionFactoryImpl();
        String key = null;
        String superSecret = "Hello World";
        byte[] encrypted = ef.encryptBytes(key, superSecret.getBytes());
        Assert.assertEquals(0, ef.decryptBytes(key, encrypted).length);
    }
/**
     * Test 
     */
    @Test
    public void decryptWithBytesNull() {
        EncryptionFactory ef = new EncryptionFactoryImpl();
        byte[] encrypted = ef.encryptBytes("foo", null);
        encrypted = ef.encryptBytes(null, new byte[0]);
        Assert.assertEquals(0, encrypted.length);
    }
    /**
     * Test 
     */
    @Test
    public void generateAESSecureRandom256KeyTest(){
        EncryptionFactory ef = new EncryptionFactoryImpl();
        String key = null;
        try {
            key = ef.generateAESSecureRandom256Key();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncryptionFactoryTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EncryptionFactoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNotNull(key);

    }
}

