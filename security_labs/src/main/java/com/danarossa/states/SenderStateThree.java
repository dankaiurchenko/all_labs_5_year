package com.danarossa.states;

import com.danarossa.FilePackage;
import com.danarossa.Package;
import com.danarossa.SymmetricFileCypher;

public class SenderStateThree extends SenderStateTwo {
    public SenderStateThree(SenderStateTwo one) {
        super(one, true);
    }


    public void receivePackage(Package aPackage) {
        System.out.println("transmission finished successfully");
        client.removeState(this.receiverId);

    }

    public Package sendPackage() throws Exception {
        return new FilePackage(this.client.getClientId(), this.receiverId, new SymmetricFileCypher().cypher(message, client.getPassword()));
    }


}
