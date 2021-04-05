package com.danarossa.states;

import com.danarossa.AsymmetricCryptography;
import com.danarossa.ConfirmationPackage;
import com.danarossa.Package;
import sun.security.rsa.RSAPublicKeyImpl;

public class ReceiverStateTwo extends AbstractClientState {

    byte[] key;

    public ReceiverStateTwo(ReceiverStateOne one) {
        super(one);
        this.key = one.openKey;
    }

    public void receivePackage(Package aPackage) throws Exception {

        String s = new AsymmetricCryptography().decryptText(aPackage.getMessage(), RSAPublicKeyImpl.newKey(key));

        System.out.println();
        System.out.println("received package + " + s);
        System.out.println();

        this.client.transmitInfo(receiverId, null);
    }

    public Package sendPackage() {
        client.removeState(this.receiverId);
        return new ConfirmationPackage(this.client.getClientId(), this.receiverId);
    }

}
