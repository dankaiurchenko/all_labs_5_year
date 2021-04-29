package com.danarossa;

public class ConfirmationPackage extends Package {
    public ConfirmationPackage(String sender, String receiver) {
        super(sender, receiver, null, false);
    }

    @Override
    public String toString() {
        return "ConfirmationPackage";
    }
}
