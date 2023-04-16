import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class simulates a User in the marketplace and helps create, interact, or
 * delete other users
 *
 * @author Anish Agrawal, Jack Burns, Sanya Buti, Gabe Kurfman, Shubhangi Mishra
 * @version April 9, 2023
 */
public class User {

    // defining all fields
    private String username;
    private String password;
    private boolean customer;
    private String userType;
    private ArrayList<String> blockedUsers;
    private ArrayList<String> invisibleUsers;
    private ArrayList<String> storeList;

    // Constructor
    public User(String username, String password, String userType, ArrayList<String> blockedUsers,
        ArrayList<String> invisibleUsers, ArrayList<String> storeList) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.customer = userType.equals("customer");
        this.blockedUsers = blockedUsers;
        this.invisibleUsers = invisibleUsers;
        this.storeList = storeList;
    }

    // setters and getters
    public ArrayList<String> getStoreList() {
        return this.storeList;
    }

    public void setStoreList(ArrayList<String> storeList) {
        this.storeList = storeList;
    }

    public boolean getCustomer() {
        return this.customer;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public ArrayList<String> getBlockedUsers() {
        return this.blockedUsers;
    }

    public void setBlockedUsers(ArrayList<String> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    public ArrayList<String> getInvisibleUsers() {
        return this.invisibleUsers;
    }

    public void setInvisibleUsers(ArrayList<String> invisibleUsers) {
        this.invisibleUsers = invisibleUsers;
    }

    // get messages between the current user and the inputted user
    public ArrayList<Message> getMessages(User user) {
        File history = new File("messageHistory.txt");
        ArrayList<Message> messagesWithUser = new ArrayList<Message>();
        Users users = new Users("Userdata.txt");
        ArrayList<User> arrUser = users.getUserList();
        try {
            if (history.createNewFile()) { // if the file doesn't exist
                history.delete();
                System.out.println("No message history");
            } else {
                BufferedReader bfr = null;
                ArrayList<String> fileText = null;
                bfr = new BufferedReader(new FileReader(history));

                fileText = new ArrayList<String>();
                String line = bfr.readLine();
                while (line != null) {
                    fileText.add(line);
                    line = bfr.readLine();
                }
                bfr.close();

                for (String conversation: fileText) {
                    if (conversation.contains(user.getUsername())) {
                        conversation.replace(user.getUsername(), "");
                        conversation.replace(",", "");
                        for (User match: arrUser) {
                            if (match.getUsername().equals(conversation)) {
                                messagesWithUser.add(new Message(user, match));
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messagesWithUser;
    }

    public void addStore(String store) {
        storeList.add(store);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }

    public void addBlockedUser(User user) {
        this.blockedUsers.add(user.getUsername());
    }

    public void addInvisUser(User user) {
        this.invisibleUsers.add(user.getUsername());
    }

    // Converting everything for a CSV format
    public String toCSV() {
        String str = getUsername() + ";" + getPassword() + ";" + getUserType();
        str += ";" + String.join(",", getBlockedUsers());
        str += ";" + String.join(",", getInvisibleUsers());
        str += ";" + String.join(",", getStoreList());

        return str;
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", customer='" + isCustomer() + "'" +
            ", userType='" + getUserType() + "'" +
            ", blockedUsers='" + getBlockedUsers() + "'" +
            ", invisibleUsers='" + getInvisibleUsers() + "'" +
            "}";
    }

    // Welcomes the user
    public void welcomeUser() {
        String cap = getUserType().substring(0, 1).toUpperCase() + getUserType().substring(1);
        System.out.println("Welcome " + cap + " " + getUsername() + "!");
    }
}