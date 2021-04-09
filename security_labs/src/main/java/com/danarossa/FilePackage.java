package com.danarossa;

public class FilePackage extends Package {

    final private byte[] file;


    public FilePackage(String sender, String receiver, byte[] file) {
        super(sender, receiver, null, true);
        this.file = file;
    }

    public byte[] getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "FilePackage";
    }
}
