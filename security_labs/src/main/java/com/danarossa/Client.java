package com.danarossa;

import com.danarossa.states.ClientState;

import java.util.Collection;

public interface Client {

    static String PARENT_WORKING_DIRECTORY = "/home/bohdanayurchecko/Projects/security_labs/working_directory";

    String getClientId();

    String getOpenKey();

    String getPassword();

    void addClient(Client client);

    Collection<Client> giveClients();

    // for test porpoise only
    boolean checkCorrectnessOfInformation();

    void setState(ClientState state);

    boolean receiveInformation(Package aPackage);

    boolean sendInformation(Package aPackage);
}
