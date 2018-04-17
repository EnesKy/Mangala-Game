package MangalaS;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import Mangala.Message;

class ServerThread extends Thread {

    public void run() {
        //works until server closes
        while (!Server.serverSocket.isClosed()) {
            try {
                Server.Display("Client Bekleniyor...");
                Socket clientSocket = Server.serverSocket.accept();//Waits for any client to be accepted. Its blocking code.
                Server.Display("Client Geldi...");
                SClient nclient = new SClient(clientSocket, Server.IdClient); //Making a SClient object with incoming clients socket number and given a id number
                Server.IdClient++;// changing id number
                Server.Clients.add(nclient);//Adding client to the list
                nclient.listenThread.start();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

public class Server {

    public static ServerSocket serverSocket;
    public static int IdClient = 0;
    public static int port = 0; //The port number that listens by server
    public static ServerThread runThread;//Servers listening thread
    public static ArrayList<SClient> Clients = new ArrayList<>();
    public static Semaphore pairTwo = new Semaphore(1, true);//Semaphore object that used for pairing two Clients

    //Starting Server thread with port number
    public static void Start(int openport) {
        try {
            Server.port = openport;
            Server.serverSocket = new ServerSocket(Server.port);
            Server.runThread = new ServerThread();
            Server.runThread.start();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Display(String msg) {
        System.out.println(msg);
    }

    /*
     This method used at sending message to Clients
     */
    public static void Send(SClient cl, Message msg) {
        try {
            cl.sOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
