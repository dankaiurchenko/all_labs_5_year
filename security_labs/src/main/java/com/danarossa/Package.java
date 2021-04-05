package com.danarossa;

public class Package {
    final private String sender;
    final private String receiver;
    final private byte[] message;
    final private Object file;

    public Package(String sender, String receiver, byte[] message, Object file) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.file = file;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public byte[] getMessage() {
        return message;
    }

    public Object getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "message='" + new String(message);
    }
}
