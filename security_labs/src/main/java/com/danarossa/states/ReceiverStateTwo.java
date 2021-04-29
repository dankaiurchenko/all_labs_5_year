package com.danarossa.states;

import com.danarossa.AsymmetricCryptography;
import com.danarossa.ConfirmationPackage;
import com.danarossa.Package;
import sun.security.rsa.RSAPublicKeyImpl;

public class ReceiverStateTwo extends AbstractClientState {

    byte[] key;
    byte[] password;

    public ReceiverStateTwo(ReceiverStateOne one, boolean file) {
        super(one, file);
        this.key = one.openKey;
    }

    public void receivePackage(Package aPackage) throws Exception {

        if (isFile()) {
            this.password = new AsymmetricCryptography().decryptByteArray(aPackage.getMessage(), RSAPublicKeyImpl.newKey(key));
        } else {
            String s = new AsymmetricCryptography().decryptText(new String(aPackage.getMessage()), RSAPublicKeyImpl.newKey(key));

            System.out.println();
            System.out.println("received package + " + s);
            System.out.println();
        }

        this.client.transmitInfo(receiverId, null, file);
    }

    public Package sendPackage() {
        if (file) {
            client.setState(new ReceiverStateThree(this, password));

        } else {
            client.removeState(this.receiverId);
        }
        return new ConfirmationPackage(this.client.getClientId(), this.receiverId);
    }

}
