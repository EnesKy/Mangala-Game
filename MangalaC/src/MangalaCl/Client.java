package MangalaCl;

/**
 * Enes Kamil YILMAZ 1521221039
 */
import Mangala.Mangala;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Mangala.Message;
import static MangalaCl.Client.sInput;

// serverdan gelecek mesajları dinleyen thread
class Listen extends Thread {

    public void run() {
        while (Client.socket.isConnected()) {
            try {
                //Mesaj gelmesini beklediği için Blocking yapar.
                Message received = (Message) (sInput.readObject()); 
                //mesaj gelirse bu satıra geçer
                switch (received.type) {
                    case RivalConnected:
                        String nick = received.content.toString();
                        Mangala.mangala.getrivalNick().setText(nick);
                        Mangala.mangala.th.start();
                        System.out.println("RivalConnected");
                        break;
                    case Disconnect:
                        break;
                    case Text://mesaj yollama işlemi olduğunu belirtmek için kullanılır.
                        Mangala.mangala.sended = received.content.toString(); 
                        break;
                    case Pits:
                        Mangala.mangala.RivalsPit = (int[][]) received.content;
                        for (int i = 0; i < 2; i++){for (int j = 0; j < 7; j++) {
                         System.out.print(Mangala.mangala.RivalsPit[i][j]+" - ");
                         }System.out.println("");}
                        Mangala.mangala.pit = Mangala.mangala.RivalsPit; ////????????
                        System.out.println("mesaj geldi");
                        break;
                    case WhosTurn:
                        Mangala.mangala.turn = (int)received.content;
                        break;
                    case Bitis:
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                //Client.Stop();
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                //Client.Stop();
                break;
            }
        }
    }
}

public class Client {

    public static Socket socket;//her clienta bi socket
    public static ObjectInputStream sInput; //verileri almaya yarar
    public static ObjectOutputStream sOutput; //verileri göndermeye yarar
    public static Listen listenMe;

    public static void Start(String ip, int port) {
        try {
            Client.socket = new Socket(ip, port);
            Client.Display("Servera bağlandı");
            Client.sInput = new ObjectInputStream(Client.socket.getInputStream());
            Client.sOutput = new ObjectOutputStream(Client.socket.getOutputStream());
            Client.listenMe = new Listen();
            Client.listenMe.start();

            Message msg = new Message(Message.Message_Type.Name);//ilk mesaj olarak isim gönderiyorum
            msg.content = Mangala.mangala.getnickL().getText();
            Client.Send(msg);

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Stop() {
        try {
            if (Client.socket != null) {
                Client.listenMe.stop();
                Client.socket.close();
                Client.sOutput.flush();
                Client.sOutput.close();
                Client.sInput.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void Display(String msg) {
        System.out.println(msg);
    }

    public static void Send(Message msg) {//mesaj gönderme fonksiyonu
        try {
            Client.sOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
