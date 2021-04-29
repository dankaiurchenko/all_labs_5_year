package com.danarossa.states;

import com.danarossa.Client;

public abstract class AbstractClientState implements ClientState {
    protected Client client;
    protected String receiverId;
    protected Object message;
    protected boolean file;

    public AbstractClientState() {
    }

    public AbstractClientState(Object message, boolean file) {
        this.message = message;
        this.file = file;
    }

    public AbstractClientState(AbstractClientState one, boolean file) {
        this.client = one.client;
        this.receiverId = one.receiverId;
        this.message = one.message;
        this.file = file;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setReceiver(String receiverId) {
        this.receiverId = receiverId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiverId() {
        return receiverId;
    }


    public boolean isFile() {
        return file;
    }
}
