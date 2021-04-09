package com.danarossa;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.security.Key;

public class SymmetricFileCypher {

    private static final String ALGORITHM = "AES";

    public byte[] cypher(Object object, byte[] password) {

        try {
            final Key key = new SecretKeySpec(password, ALGORITHM);
            final Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] fileContent = Files.readAllBytes(((File) object).toPath());
            return c.doFinal(fileContent);
        } catch (Exception ex) {
            System.out.println("The Exception is=" + ex);
            throw new RuntimeException(ex);
        }

    }


    public byte[] decipher(Object object, byte[] password) {
        try {
            final Key key = new SecretKeySpec(password, ALGORITHM);
            final Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            final byte[] decorVal = new BASE64Decoder().decodeBuffer(new ByteArrayInputStream((byte[]) object));
            return c.doFinal(decorVal);
        } catch (Exception ex) {
            System.out.println("The Exception is=" + ex);
            throw new RuntimeException(ex);
        }

    }


}
