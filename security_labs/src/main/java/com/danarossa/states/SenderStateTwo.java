package com.danarossa.states;

import com.danarossa.Package;

public class SenderStateTwo extends AbstractClientState {

    private String fileName;

    public SenderStateTwo(SenderStateOne one) {
        super(one);
    }

    public void receivePackage(Package aPackage) {
        System.out.println("transmission finished successfully");
        client.removeState(this.receiverId);
    }

    public Package sendPackage() throws Exception {
        // todo here cypher the message using the key

        fileName = client.encryptAsymmetrical(fileName);
        return new Package(this.client.getClientId(), this.receiverId, fileName, null);
    }

}
