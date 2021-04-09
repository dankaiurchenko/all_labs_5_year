package com.danarossa.states;

import com.danarossa.ConfirmationPackage;
import com.danarossa.FilePackage;
import com.danarossa.Package;
import com.danarossa.SymmetricFileCypher;

import java.util.Date;

public class ReceiverStateThree extends ReceiverStateOne {

    private final byte[] password;

    public ReceiverStateThree(AbstractClientState state, byte[] password) {
        super(state, state.file);
        this.password = password;
    }

    public void receivePackage(FilePackage aPackage) throws Exception {
        System.out.println("received a file ! finally !!!");
        Object file = aPackage.getFile();
        byte[] decipher = new SymmetricFileCypher().decipher(file, password);
        this.client.saveFileInWorkingDirectory(decipher, "received from " + this.receiverId + "_" + new Date().toString() + ".txt");

        // todo here save to the file
        this.client.transmitInfo(aPackage.getSender(), null, isFile());
    }

    public Package sendPackage() {
        client.removeState(this.receiverId);
        return new ConfirmationPackage(this.client.getClientId(), this.receiverId);
    }
}
