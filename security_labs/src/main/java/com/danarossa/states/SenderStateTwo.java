package com.danarossa.states;

import com.danarossa.Package;

public class SenderStateTwo extends AbstractClientState {

    public SenderStateTwo(SenderStateOne one) {
        super(one);
    }

    public void receivePackage(Package aPackage) {
        System.out.println("transmission finished successfully");
        client.removeState(this.receiverId);
    }

    public Package sendPackage() {
        // todo here cypher the message using the key

        return new Package(this.client.getClientId(), this.receiverId, fileName, null);
    }

}
