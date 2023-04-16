import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * This sets up the store object used in other classes
 *
 * @author Anish Agrawal, Jack Burns, Sanya Buti, Gabe Kurfman, Shubhangi Mishra
 * @version April 9, 2023
 */
public class Store {

    // private fields
    private User user;
    private String nameStore;
    private int messagesRec;
    private int messagesSent;

    // constructor
    public Store(User user, String nameStore) {

        this.user = user;
        this.nameStore = nameStore;
        try {
            String fileName = "sellerData.txt";
            FileOutputStream fos = new FileOutputStream(fileName, true);
            PrintWriter pw = new PrintWriter(fos);
            String sellerData = nameStore + ", " + user.getUsername();
            pw.println(sellerData);
            pw.close();
        } catch (FileNotFoundException e) {}
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