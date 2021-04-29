package com.danarossa.states;

import com.danarossa.FilePackage;
import com.danarossa.Package;
import com.danarossa.SymmetricFileCypher;

import java.io.File;

public class SenderStateThree extends SenderStateTwo {
    public SenderStateThree(SenderStateTwo one) {
        super(one, true);
    }


    public void receivePackage(Package aPackage) {
        System.out.println("transmission finished successfully");
        client.removeState(this.receiverId);

    }

    public Package sendPackage() throws Exception {
        byte[] file = new SymmetricFileCypher().cypherFile(message, client.getPassword());
        byte[] fileName = new SymmetricFileCypher().cypher(((File) message).getName().getBytes(), client.getPassword());
        return new FilePackage(this.client.getClientId(), this.receiverId, file, fileName);
    }


}
