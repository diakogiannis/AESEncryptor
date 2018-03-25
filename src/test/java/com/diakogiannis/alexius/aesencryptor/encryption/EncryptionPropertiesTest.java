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

import com.diakogiannis.alexius.aesencryptor.encryption.enums.EncodingProperties;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alexius Diakogiannis alexius [at] jee.gr on 3/25/2018.
 */




/**
 * Main test class for enum
 * @author Alexius Diakogiannis alexius at jee.gr
 */
public class EncryptionPropertiesTest {

    /**
     * Main test
     */    
    @Test
    public void enumTest() {
        Assert.assertEquals("AES/CBC/PKCS5PADDING",EncodingProperties.PADDING.value());
        Assert.assertEquals("UTF-8",EncodingProperties.ENCODING.value());
        Assert.assertEquals("AES",EncodingProperties.KEYSPEC.value() );
        Assert.assertEquals(EncodingProperties.KEY_LENGTH.intValue() , new Integer(256));
    }

    
}

