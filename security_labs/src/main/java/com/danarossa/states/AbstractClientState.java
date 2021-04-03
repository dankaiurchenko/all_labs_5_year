package com.danarossa.states;

import com.danarossa.Client;

public abstract class AbstractClientState implements ClientState {
    protected Client client;
    protected String receiverId;

    public void setClient(Client client) {
        this.client = client;
    }

    public void setReceiver(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverId() {
        return receiverId;
    }
}
