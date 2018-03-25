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

/**
 * Created by Alexius Diakogiannis alexius [at] jee.gr on 3/6/2018.
 */


import com.diakogiannis.alexius.aesencryptor.encryption.enums.EncodingProperties;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class EncryptionFactoryImpl implements EncryptionFactory {

    private static final Logger LOG = Logger.getLogger(EncryptionFactoryImpl.class.getSimpleName());

    private static final String AES_PADDING = EncodingProperties.PADDING.value();
    private static final int AES_KEY_LENGTH = 256;
    
    /**
     * encrypts a Base64 string
     * @param key
     * @param value
     * @return
     */
    @Override
    public String encrypt(final String key,final String value) {
        String encryptedString = null;

        try {
            String randomIV = randomIV();
            IvParameterSpec iv = new IvParameterSpec(Base64.decodeBase64(randomIV));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(EncodingProperties.ENCODING.value()), EncodingProperties.KEYSPEC.value());

            Cipher cipher = Cipher.getInstance(AES_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            LOG.log(Level.FINEST, "encrypted string: {0}", Base64.encodeBase64String(encrypted));
            encryptedString = Base64.encodeBase64String(encrypted).concat("-").concat(randomIV);
        } catch (NullPointerException | UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
            LOG.log(Level.SEVERE,ex.getMessage(), ex);
        }

        return encryptedString;
    }

    /**
     * Encrypts a byte[]
     * @param key
     * @param value
     * @return
     */
    @Override
    public byte[] encryptBytes(final String key,final byte[] value) {
        try {
            byte[] randomIVBytes = Base64.decodeBase64(randomIV());
            IvParameterSpec iv = new IvParameterSpec(randomIVBytes);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(EncodingProperties.ENCODING.value()), EncodingProperties.KEYSPEC.value());

            Cipher cipher = Cipher.getInstance(EncodingProperties.PADDING.value());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value);
            LOG.log(Level.FINEST, "encrypted string: {0}", Base64.encodeBase64String(encrypted));

            byte[] encryptedWithIv = new byte[encrypted.length + randomIVBytes.length];

            //copy randomIVBytes into encryptedWithIv
            System.arraycopy(randomIVBytes, 0, encryptedWithIv, 0, randomIVBytes.length);

            //copy encrypted into encryptedWithIv after randomIVBytes
            System.arraycopy(encrypted, 0, encryptedWithIv, encrypted.length, randomIVBytes.length);

            return encryptedWithIv;

        } catch (NullPointerException | UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
            LOG.log(Level.SEVERE,ex.getMessage(),ex);
            return new byte[0];
        }

    }

    /**
     * Decrypts a byte[]
     * @param key
     * @param encryptedWithIv
     * @return
     */
    @Override
    public byte[] decryptBytes(final String key,final byte[] encryptedWithIv) {

        try {
            byte[] iv = new byte[16];
            byte[] encrypted = new byte[encryptedWithIv.length - 16];

            System.arraycopy(encryptedWithIv, 0, iv, 0, 16);
            System.arraycopy(encryptedWithIv, 16, encrypted, 0, encryptedWithIv.length - 16);


            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(EncodingProperties.ENCODING.value()), EncodingProperties.KEYSPEC.value());

            Cipher cipher = Cipher.getInstance(EncodingProperties.PADDING.value());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] encryptedBytes = cipher.doFinal(encrypted);

            return encryptedBytes;

        } catch (NullPointerException | NegativeArraySizeException | UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
            LOG.log(Level.SEVERE,ex.getMessage(), ex);
            return new byte[0];
        }

    }

    /**
     * Decrypts a Base64 string
     * @param key
     * @param encrypted
     * @return dercrypted String
     */
    @Override
    public String decrypt(final String key,final  String encrypted) {
        String decryptedString = null;
        try {
            byte[] iv = Base64.decodeBase64(encrypted.split("-")[1]);
            byte[] encryptedBytesSplited = Base64.decodeBase64(encrypted.split("-")[0]);

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(EncodingProperties.ENCODING.value()), EncodingProperties.KEYSPEC.value());

            Cipher cipher = Cipher.getInstance(EncodingProperties.PADDING.value());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] encryptedBytes = cipher.doFinal(encryptedBytesSplited);

            decryptedString = new String(encryptedBytes);

            LOG.log(Level.FINEST, "decrypted string: {0}", decryptedString);

        } catch (NullPointerException | UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
            LOG.log(Level.SEVERE,ex.getMessage(), ex);
        }

        return decryptedString;
    }

    /**
     * Generates a random IV
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String randomIV() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance(EncodingProperties.SECURE_RANDOM_KEY_SPEC.value());
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        return Base64.encodeBase64String(iv);
    }

    /**
     * Generates an AES secure random key of 256 bytes length
     * @return
     * @throws NoSuchAlgorithmException 
     * @throws UnsupportedEncodingException 
     */
    @Override
    public String generateAESSecureRandom256Key() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        KeyGenerator keyGen = KeyGenerator.getInstance(EncodingProperties.KEYSPEC.value());
	keyGen.init(AES_KEY_LENGTH);
        SecretKey secretKey = keyGen.generateKey();
        return Base64.encodeBase64String(secretKey.getEncoded());
    }

    
}

