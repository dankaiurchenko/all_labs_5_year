package com.danarossa.states;

import com.danarossa.ConfirmationPackage;
import com.danarossa.Package;

import java.nio.charset.StandardCharsets;

public class ReceiverStateOne extends AbstractClientState {

    byte[] openKey;

    public void receivePackage(Package aPackage) throws Exception {
        this.openKey = aPackage.getMessage().getBytes(StandardCharsets.UTF_8);
        System.out.println(aPackage.getMessage().length());
        System.out.println(openKey.length);
        System.out.println(new String(openKey, StandardCharsets.UTF_8).length());
        this.client.transmitInfo(aPackage.getSender(), null);
    }

    public Package sendPackage() {
        this.client.setState(new ReceiverStateTwo(this));
        return new ConfirmationPackage(this.client.getClientId(), this.receiverId);
    }

}
