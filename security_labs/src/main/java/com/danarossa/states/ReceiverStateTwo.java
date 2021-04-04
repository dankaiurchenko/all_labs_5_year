package com.danarossa.states;

import com.danarossa.ConfirmationPackage;
import com.danarossa.Package;

public class ReceiverStateTwo extends AbstractClientState {

    String key;

    public ReceiverStateTwo(ReceiverStateOne one) {
        super(one);
        this.key = one.openKey;
    }

    public void receivePackage(Package aPackage) {
        System.out.println();
        System.out.println("received package + " + aPackage);
        System.out.println();

        // todo here uncypher the message

        this.client.transmitInfo(receiverId, null);
    }

    public Package sendPackage() {
        client.removeState(this.receiverId);
        return new ConfirmationPackage(this.client.getClientId(), this.receiverId);
    }

}
