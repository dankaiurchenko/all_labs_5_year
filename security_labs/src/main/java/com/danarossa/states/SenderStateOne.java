package com.danarossa.states;

import com.danarossa.Package;

public class SenderStateOne extends AbstractClientState {

    public SenderStateOne(String fileNameToSend) {
        this.fileName = fileNameToSend;
    }

    public Package sendPackage() {
        return new Package(this.client.getClientId(), this.receiverId, client.getOpenKey(), null);
    }

    public void receivePackage(Package aPackage) throws Exception {
        this.client.setState(new SenderStateTwo(this));
        // save the key and send the approval
        client.transmitInfo(receiverId, fileName);
    }

}
