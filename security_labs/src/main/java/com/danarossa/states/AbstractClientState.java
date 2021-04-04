package com.danarossa.states;

import com.danarossa.Client;

public abstract class AbstractClientState implements ClientState {
    protected Client client;
    protected String receiverId;
    protected String fileName;

    public AbstractClientState() {
    }

    public AbstractClientState(AbstractClientState one) {
        this.client = one.client;
        this.receiverId = one.receiverId;
        this.fileName = one.fileName;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setReceiver(String receiverId) {
        this.receiverId = receiverId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getReceiverId() {
        return receiverId;
    }
}
