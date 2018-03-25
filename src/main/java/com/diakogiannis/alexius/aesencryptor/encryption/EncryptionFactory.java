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

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Alexius Diakogiannis 
 *
 */
public interface EncryptionFactory {

   
    /**
     * Decrypts a String using AES/CBC/PKCS5PADDING. The IV must be prepended of the hash, separated by ':'.
     * @param encodedKey
     * @param hash
     * @return Decrypted String
     *  
     */
    public String decrypt(String encodedKey, String hash);
    /**
     * Encrypts content in bytes
     * @param key
     * @param value
     * @return encrypted byte 
     */
    public byte[] encryptBytes(final String key,final byte[] value);
    
    /**
     * Decrypts content from bytes
     * @param key
     * @param encryptedWithIv
     * @return decrypted bytes
     */
    public byte[] decryptBytes(final String key,final byte[] encryptedWithIv);
    
    /**
     *
     * @param encodedKey
     * @param value
     * @return AES/CBC/PKCS5PADDING encrypted string with a random IV. The IV and the hash are separated by ':'. The left part is the IV
     *      */
    public String encrypt(String encodedKey, String value);

   
    /**
     * Generates an AES secure random key of 256 bytes length
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException 
     */
    public String generateAESSecureRandom256Key() throws NoSuchAlgorithmException, UnsupportedEncodingException;
    
}
