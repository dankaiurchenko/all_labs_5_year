package com.danarossa;

import com.danarossa.states.ClientState;

import java.util.Collection;

public interface Client {

    static String PARENT_WORKING_DIRECTORY = "/home/bohdanayurchecko/Projects/security_labs/working_directory";

    String getClientId();

    byte[] getOpenKey();

    String encryptAsymmetrical(String message) throws Exception;

    String getPassword();

    void addClient(Client client);

    Collection<Client> giveClients();

    // for test porpoise only
    boolean checkCorrectnessOfInformation();

    void setState(ClientState state);

    void removeState(String clientId);

    void transmitInfo(String receiver, String message) throws Exception;

    boolean receiveInformation(Package aPackage, Client sender) throws Exception;

    boolean sendInformation(Package aPackage, Client sender) throws Exception;
}
