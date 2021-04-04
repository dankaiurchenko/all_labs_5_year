package com.danarossa;

import com.danarossa.states.ClientState;
import com.danarossa.states.ReceiverStateOne;
import com.danarossa.states.SenderStateOne;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class StubClient implements Client {

    private Map<String, ClientState> states = new HashMap<>();

    private final File workingFolder;
    private final File information;

    private final String clientId;
    private final String pathname;
    private Collection<Client> connectedClients = new HashSet<>();

    private String openKey;
    private String closedKey;
    private String password;


    public StubClient(String clientId) {

        openKey = clientId + "open key";
        closedKey = clientId + "closed key";
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

    @Override
    public void removeState(String clientId) {
        this.states.remove(clientId);
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
        if (!connectedClients.contains(client)) {
            connectedClients.add(client);
            client.addClient(this);
        }
    }

    public Collection<Client> giveClients() {
        return connectedClients;
    }


    public void transmitInfo(String receiver, String message) {
        if (!states.containsKey(receiver)) {
            ClientState state = new SenderStateOne(message);
            state.setClient(this);
            state.setReceiver(receiver);
            states.put(receiver, state);
        }
        sendInformation(states.get(receiver).sendPackage(), this);
    }

    public boolean sendInformation(Package aPackage, Client sender) {
        for (Client connectedClient : this.connectedClients) {
            if (sender != connectedClient) {
                boolean b = connectedClient.receiveInformation(aPackage, this);
                if (b) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean receiveInformation(Package aPackage, Client sender) {
        System.out.println("in " + clientId + " package : " + aPackage);
        String senderId = aPackage.getSender();
        if (aPackage.getReceiver().equals(clientId)) {
            if (!states.containsKey(senderId)) {
                ClientState state = new ReceiverStateOne();
                state.setClient(this);
                state.setReceiver(senderId);
                states.put(senderId, state);
            }
            this.states.get(senderId).receivePackage(aPackage);
            return true;
        }
        return sendInformation(aPackage, sender);
    }

    public boolean checkCorrectnessOfInformation() {
        return true;
    }

}
