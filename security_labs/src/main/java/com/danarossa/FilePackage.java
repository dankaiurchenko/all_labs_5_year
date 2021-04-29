package com.danarossa;

public class FilePackage extends Package {

    final private byte[] file;
    final private byte[] fileName;


    public FilePackage(String sender, String receiver, byte[] file, byte[] name) {
        super(sender, receiver, null, true);
        this.file = file;
        this.fileName = name;
    }

    public byte[] getFile() {
        return file;
    }

    public byte[] getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "FilePackage";
    }
}
