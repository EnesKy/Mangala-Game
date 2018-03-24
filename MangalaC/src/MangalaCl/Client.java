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
                Message received = (Message) (sInput.readObject());//Mesaj gelmesini beklediği için Blocking yapar.
                //mesaj gelirse bu satıra geçer
                switch (received.type) {
                    case Dizi:
                        break;
                    case RivalConnected:
                        String nick = received.content.toString();
                        Mangala.mangala.getrivalNick().setText(nick);
                        // Mangala.mangala.btn_pick.setEnabled(true);
                        // Mangala.mangala.btn_send_message.setEnabled(true);
                        //Mangala.mangala.th.start();
                        break;
                    case Disconnect:
                        break;
                    case Text:
                        //Mangala.mangala.txt_receive.setText(received.content.toString());
                        break;
                    case Selected:
                        Mangala.mangala.RivalSelection = (int) received.content;
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
