//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.io.*;
import javax.swing.*;

/**
 * This sets up the store object used in other classes
 *
 * @author Anish Agrawal, Jack Burns, Sanya Buti, Gabe Kurfman, Shubhangi Mishra
 * @version April 19, 2023
 */
public class Store {

    // private fields
    private User user;
    private String nameStore;
    private int messagesRec;
    private int messagesSent;
    private Socket socket = null;
    private OutputStream outputStream = null;
    private PrintWriter writer = null;
    //private InputStream inputStream = null;
    //private BufferedReader reader = null;

    // constructor
    public Store(User user, String nameStore) {
        try {
            socket = new Socket("localhost", 1234);
            outputStream = socket.getOutputStream();
            writer = new PrintWriter(outputStream, true);
            //InputStream inputStream = socket.getInputStream();
            //BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            this.user = user;
            this.nameStore = nameStore;
            String fileName = "sellerData.txt";
            String sellerData = nameStore + ", " + user.getUsername();
            String send = "/write/"+fileName + " {-/} "+ sellerData;
            writer.println(send);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Could not establish connection to server.");
    }
    }

    // setters and getters
    public User getUser() {
        return user;
    }

    public int getMessagesRec() {
        return messagesRec;
    }

    public void setMessagesRec(int messages) {
        this.messagesRec = messages;
    }

    public int getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(int messages) {
        this.messagesSent = messages;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    @Override
    public String toString() {
        return "Store{" +
            "user=" + user +
            ", nameStore='" + nameStore + '\'' +
            '}';
    }
}