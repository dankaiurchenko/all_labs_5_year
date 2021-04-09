package com.danarossa;

import com.danarossa.states.AbstractClientState;

import java.io.IOException;
import java.util.Collection;

public interface Client {

    static String PARENT_WORKING_DIRECTORY = "/home/bohdanayurchecko/Projects/security_labs/working_directory";

    String getClientId();

    byte[] getOpenKey();

    String encryptAsymmetrical(String message) throws Exception;

    byte[] encryptAsymmetrical(byte[] message) throws Exception;

    byte[] getPassword();

    void addClient(Client client);

    Collection<Client> giveClients();

    void saveFileInWorkingDirectory(byte[] bytes, String pathname) throws IOException;

    // for test porpoise only
    boolean checkCorrectnessOfInformation();

    void setState(AbstractClientState state);

    void removeState(String clientId);

    void transmitMessage(String receiver, String message) throws Exception;

    void transmitInfo(String receiver, Object message, boolean file) throws Exception;

    void transmitFile(String receiver) throws Exception;

    boolean receiveInformation(Package aPackage, Client sender) throws Exception;

    boolean sendInformation(Package aPackage, Client sender) throws Exception;
}
