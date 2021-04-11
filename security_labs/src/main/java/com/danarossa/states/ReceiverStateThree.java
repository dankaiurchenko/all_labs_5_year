package com.danarossa.states;

import com.danarossa.ConfirmationPackage;
import com.danarossa.FilePackage;
import com.danarossa.Package;
import com.danarossa.SymmetricFileCypher;

public class ReceiverStateThree extends ReceiverStateOne {

    private final byte[] password;

    public ReceiverStateThree(AbstractClientState state, byte[] password) {
        super(state, state.file);
        this.password = password;
    }

    public void receivePackage(Package aPackage) throws Exception {
        FilePackage filePackage = (FilePackage) aPackage;
        byte[] file = filePackage.getFile();
        byte[] decipherFile = new SymmetricFileCypher().decipher(file, password);
        byte[] decipherName = new SymmetricFileCypher().decipher(filePackage.getFileName(), password);
        this.client.saveFileInWorkingDirectory(decipherFile, new String(decipherName));
        this.client.transmitInfo(aPackage.getSender(), null, isFile());
    }

    public Package sendPackage() {
        client.removeState(this.receiverId);
        return new ConfirmationPackage(this.client.getClientId(), this.receiverId);
    }
}
