package com.danarossa;

public class Package {
    final private String sender;
    final private String receiver;
    final private String message;
    final private Object file;

    public Package(String sender, String receiver, String message, Object file) {
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

    public String getMessage() {
        return message;
    }

    public Object getFile() {
        return file;
    }
}
