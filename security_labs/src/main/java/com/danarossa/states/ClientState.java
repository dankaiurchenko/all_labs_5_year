package com.danarossa.states;

import com.danarossa.Client;
import com.danarossa.Package;

public interface ClientState {

    void receivePackage(Package aPackage) throws Exception;

    Package sendPackage() throws Exception;

    void setClient(Client client);

    void setReceiver(String client);

    String getReceiverId();


}


