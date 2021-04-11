package com.danarossa.states;

import com.danarossa.ConfirmationPackage;
import com.danarossa.Package;

public class ReceiverStateOne extends AbstractClientState {

    byte[] openKey;


    public ReceiverStateOne(boolean file) {
        this.file = file;
    }

    public void receivePackage(Package aPackage) throws Exception {
        this.openKey = aPackage.getMessage();
        this.client.transmitInfo(aPackage.getSender(), null, file);
    }

    public Package sendPackage() {
        this.client.setState(new ReceiverStateTwo(this, this.file));
        return new ConfirmationPackage(this.client.getClientId(), this.receiverId);
    }

}
