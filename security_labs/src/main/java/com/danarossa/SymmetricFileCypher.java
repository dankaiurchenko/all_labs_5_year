package com.danarossa;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.nio.file.Files;
import java.security.Key;

public class SymmetricFileCypher {

    private static final String ALGORITHM = "AES";

    public byte[] cypherFile(Object object, byte[] password) {
        try {
            byte[] fileContent = Files.readAllBytes(((File) object).toPath());
            return cypher(fileContent, password);

        } catch (Exception ex) {
            System.out.println("The Exception is=" + ex);
            throw new RuntimeException(ex);
        }
    }

    public byte[] cypher(byte[] object, byte[] password) throws Exception {

        final Key key = new SecretKeySpec(password, ALGORITHM);
        final Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        return c.doFinal(object);
    }


    public byte[] decipher(byte[] object, byte[] password) {
        try {
            final Key key = new SecretKeySpec(password, ALGORITHM);
            final Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            return c.doFinal(object);
        } catch (Exception ex) {
            System.out.println("The Exception is=" + ex);
            throw new RuntimeException(ex);
        }

    }


}
