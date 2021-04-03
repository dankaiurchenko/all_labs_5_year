package com.danarossa.states;

import com.danarossa.Package;

public class IdleClientState extends AbstractClientState {


    public void receivePackage(Package aPackage) {
        ClientState state = new KeyReceivedClientState();
        state.setClient(this.client);
        state.setReceiver(this.receiverId);
        this.client.setState(state);
        // save the key and send the approval
        this.client.sendInformation(new Package(this.client.getClientId(), this.receiverId, "key received", null));
    }

    public Package sendPackage() {
        ClientState state = new KeySentClientState();
        state.setClient(this.client);
        state.setReceiver(this.receiverId);
        client.setState(state);
        return new Package(this.client.getClientId(), this.receiverId, client.getOpenKey(), null);
    }

}
