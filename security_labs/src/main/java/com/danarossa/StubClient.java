package com.danarossa;

import com.danarossa.states.ClientState;
import com.danarossa.states.IdleClientState;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class StubClient implements Client {

    private Map<String, ClientState> states;

    private final File workingFolder;
    private final File information;

    private final String clientId;
    private final String pathname;
    private Collection<Client> connectedClients;

    private String openKey;
    private String closedKey;
    private String password;


    public StubClient(String clientId) {

        openKey = "";
        closedKey = "";
        password = "";


        this.clientId = clientId;
        workingFolder = new File(Client.PARENT_WORKING_DIRECTORY + "/" + clientId);
        workingFolder.mkdirs();
        pathname = Client.PARENT_WORKING_DIRECTORY + "/" + clientId + "/" + clientId + "file.txt";
        information = new File(pathname);
        try {
            String filename = "MyFile.txt";
            FileWriter fw = new FileWriter(information);
            fw.write("test file for sending from " + clientId + "\n");
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    public void setState(ClientState state) {
        this.states.put(state.getReceiverId(), state);
    }

    public String getClientId() {
        return clientId;
    }

    public String getOpenKey() {
        return openKey;
    }

    public String getPassword() {
        return password;
    }

    public void addClient(Client client) {
        connectedClients.add(client);
    }

    public Collection<Client> giveClients() {
        return connectedClients;
    }


    public void transmitFile(String receiver) {
        if (!states.containsKey(receiver)) {
            ClientState state = new IdleClientState();
            state.setClient(this);
            state.setReceiver(receiver);
            states.put(receiver, state);
        }
        boolean b = sendInformation(states.get(receiver).sendPackage());

        System.out.println("key received = " + b);
    }

     public boolean sendInformation(Package aPackage) {
        for (Client connectedClient : this.connectedClients) {
            boolean b = connectedClient.receiveInformation(aPackage);
            if (b) {
                return true;
            }
        }
        return false;
    }

    public boolean receiveInformation(Package aPackage) {
        String senderId = aPackage.getSender();
        if (aPackage.getReceiver().equals(clientId)) {
            if (!states.containsKey(senderId)) {
                ClientState state = new IdleClientState();
                state.setClient(this);
                state.setReceiver(senderId);
                states.put(senderId, state);
            }
            this.states.get(senderId).receivePackage(aPackage);
            return true;
        }
        return sendInformation(aPackage);
    }

    public boolean checkCorrectnessOfInformation() {
        return true;
    }

}
