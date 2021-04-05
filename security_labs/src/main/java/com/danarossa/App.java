package com.danarossa;

public class App {
    public static void main(String[] args) throws Exception {

        Client client1 = new StubClient("client1");
        Client client2 = new StubClient("client2");
        Client client3 = new StubClient("client3");
        Client client4 = new StubClient("client4");
        Client client5 = new StubClient("client5");
        Client client6 = new StubClient("client6");
        Client client7 = new StubClient("client7");
        Client client8 = new StubClient("client8");
        Client client9 = new StubClient("client9");


        client1.addClient(client2);
        client1.addClient(client3);
        client2.addClient(client7);
        client2.addClient(client8);
        client8.addClient(client9);
        client3.addClient(client6);
        client3.addClient(client4);
        client4.addClient(client5);


        client5.transmitInfo("client9", "confidential information: KPI is the best university in Ukraine");
        client3.transmitInfo("client7", "super confidential information: Gnome 40 is awesome!");

    }
}
