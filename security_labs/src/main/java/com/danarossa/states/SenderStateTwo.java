package com.danarossa.states;

import com.danarossa.Package;

public class SenderStateTwo extends AbstractClientState {

    public SenderStateTwo(AbstractClientState one, boolean file) {
        super(one, file);
    }

    public void receivePackage(Package aPackage) throws Exception {
        if (file) {
            this.client.setState(new SenderStateThree(this));
            // state three
            // and transmit info
            this.client.transmitFile(this.receiverId);
        } else {
            System.out.println("transmission finished successfully");
            client.removeState(this.receiverId);
        }
    }

    public Package sendPackage() throws Exception {
        System.out.println("sender state 2 is file " + file);
        final String message;
        if (file) {
            return new Package(this.client.getClientId(), this.receiverId, client.encryptAsymmetrical(client.getPassword()), this.file);
        } else {
            message = client.encryptAsymmetrical((String) this.message);
        }
        return new Package(this.client.getClientId(), this.receiverId, message.getBytes(), this.file);
    }

}
