package com.danarossa.states;

import com.danarossa.Package;

public class SenderStateOne extends AbstractClientState {

    public SenderStateOne(Object fileNameToSend, boolean file) {
        super(fileNameToSend, file);
    }

    public Package sendPackage() {
        return new Package(this.client.getClientId(), this.receiverId, client.getOpenKey(), this.file);
    }

    public void receivePackage(Package aPackage) throws Exception {
        this.client.setState(new SenderStateTwo(this, file));
        // save the key and send the approval
        client.transmitInfo(receiverId, message, file);
    }

}
