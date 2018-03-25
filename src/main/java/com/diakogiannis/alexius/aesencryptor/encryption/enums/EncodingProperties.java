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
package com.diakogiannis.alexius.aesencryptor.encryption.enums;

/**
 *
 * @author Alexius Diakogiannis [alexius[at]jee.gr]
 */
public enum EncodingProperties {
    PADDING("AES/CBC/PKCS5PADDING"),
    KEY_LENGTH(256),
    ENCODING("UTF-8"),
    KEYSPEC("AES"),
    SECURE_RANDOM_KEY_SPEC("SHA1PRNG");

    private String property;
    private Integer intProperty;
    
    /**
     * Default constructor
     * @param property 
     */
    EncodingProperties(String property) {
        this.property = property;
    }
    
    /**
     * Default constructor for int values
     * @param property 
     */
    EncodingProperties(Integer intProperty) {
        this.intProperty = intProperty;
    }
    
    /**
     * return String property
     * @return 
     */
    public String value(){
        return property;
    }
    
    /**
     * return integer property
     * @return 
     */
    public Integer intValue(){
        return intProperty;
    }
    
}
