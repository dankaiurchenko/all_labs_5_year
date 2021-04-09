package com.danarossa;

import com.danarossa.states.AbstractClientState;
import com.danarossa.states.ClientState;
import com.danarossa.states.ReceiverStateOne;
import com.danarossa.states.SenderStateOne;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.util.*;

public class StubClient implements Client {

    private Map<String, ClientState> states = new HashMap<>();

    private final File workingFolder;
    private final File information;

    private final String clientId;
    private final Collection<Client> connectedClients = new HashSet<>();

    private final byte[] openKey;
    private final byte[] closedKey;
    private final byte[] password;
    private final AsymmetricCryptography asymmetricCryptography;


    public StubClient(String clientId) throws Exception {

        asymmetricCryptography = new AsymmetricCryptography();
        openKey = asymmetricCryptography.getPublic().getEncoded();
        closedKey = asymmetricCryptography.getPrivate().getEncoded();
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);
        SecretKey key = generator.generateKey();
        password = key.getEncoded();


        this.clientId = clientId;
        workingFolder = new File(Client.PARENT_WORKING_DIRECTORY + "/" + clientId);
        workingFolder.mkdirs();
        String pathname = Client.PARENT_WORKING_DIRECTORY + "/" + clientId + "/" + clientId + "file.txt";
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

    public void setState(AbstractClientState state) {
        this.states.put(state.getReceiverId(), state);
    }

    @Override
    public void removeState(String clientId) {
        this.states.remove(clientId);
    }

    public String getClientId() {
        return clientId;
    }

    public byte[] getOpenKey() {
        return openKey;
    }

    @Override
    public String encryptAsymmetrical(String message) throws Exception {
        return this.asymmetricCryptography.encryptText(message, asymmetricCryptography.getPrivate());
    }

    @Override
    public byte[] encryptAsymmetrical(byte[] message) throws Exception {
        return this.asymmetricCryptography.encryptByteArray(message, asymmetricCryptography.getPrivate());
    }

    public byte[] getPassword() {
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

    public void transmitMessage(String receiver, String message) throws Exception {
        this.transmitInfo(receiver, message, false);
    }

    public void transmitInfo(String receiver, Object message, boolean file) throws Exception {
        if (!states.containsKey(receiver)) {
            ClientState state = new SenderStateOne(message, file);
            state.setClient(this);
            state.setReceiver(receiver);
            states.put(receiver, state);
        }
        sendInformation(states.get(receiver).sendPackage(), this);
    }

    private void transmitFile(String receiver, Object file) throws Exception {
        this.transmitInfo(receiver, file, true);
    }

    public void transmitFile(String receiver) throws Exception {
        this.transmitInfo(receiver, this.information, true);
    }

    public boolean sendInformation(Package aPackage, Client sender) throws Exception {
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

    public boolean receiveInformation(Package aPackage, Client sender) throws Exception {
//        System.out.println("in " + clientId + " package : " + aPackage);
        String senderId = aPackage.getSender();
        if (aPackage.getReceiver().equals(clientId)) {
            if (!states.containsKey(senderId)) {
                ClientState state = new ReceiverStateOne(aPackage.isFile());
                state.setClient(this);
                state.setReceiver(senderId);
                states.put(senderId, state);
            }
            this.states.get(senderId).receivePackage(aPackage);
            return true;
        } else if (aPackage instanceof FilePackage) {
            saveFileInWorkingDirectory(((FilePackage) aPackage).getFile(), new Date().toString() + ".txt");
        }
        return sendInformation(aPackage, sender);
    }

    @Override
    public void saveFileInWorkingDirectory(byte[] bytes, String pathname) throws IOException {
        File file = new File(this.workingFolder.getAbsolutePath() + "/" + pathname);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }

        }
        System.out.println("saving file to my working directory ");
    }

    public boolean checkCorrectnessOfInformation() {
        return true;
    }

}
