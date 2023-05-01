import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.io.*;
import javax.swing.*;

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
    private Socket socket = null;
    private OutputStream outputStream = null;
    private PrintWriter writer = null;
    private BufferedReader reader = null;

    // Constructor
    public User(String username, String password, String userType, ArrayList<String> blockedUsers,
                ArrayList<String> invisibleUsers, ArrayList<String> storeList, GUI userGUI) {
        try {
            socket = new Socket("localhost", 1234);
            outputStream = socket.getOutputStream();
            writer = new PrintWriter(outputStream, true);
            //InputStream inputStream = socket.getInputStream();
            //BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            this.username = username;
            this.password = password;
            this.userType = userType;
            this.customer = userType.equals("customer");
            this.blockedUsers = blockedUsers;
            this.invisibleUsers = invisibleUsers;
            this.storeList = storeList;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not establish connection to server." +
                            "\nConsult the Stride180 Readme for more details.",
                    "Server Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
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
        String fileContent = null;
        String send = "/read/" + "messageHistory.txt";
        writer.println(send);
        try {
            while (fileContent != null) {
                fileContent = reader.readLine();
            }
        } catch (IOException e) {
            fileContent = "\n\n";
        }
        ;
        ArrayList<Message> messagesWithUser = new ArrayList<Message>();
        Users users = new Users("Userdata.txt", null);
        ArrayList<User> arrUser = users.getUserList();

        ArrayList<String> fileText = new ArrayList<String>();
        String[] fileContentArray = fileContent.split("\n");
        Collections.addAll(fileText, fileContentArray);
        for (String conversation : fileText) {
            if (conversation.contains(user.getUsername())) {
                conversation.replace(user.getUsername(), "");
                conversation.replace(",", "");
                for (User match : arrUser) {
                    if (match.getUsername().equals(conversation)) {
                        messagesWithUser.add(new Message(user, match));
                    }
                }
            }
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
    public void welcomeUser(GUI userGUI) {
        String cap = getUserType().substring(0, 1).toUpperCase() + getUserType().substring(1);
        String username = getUsername();
        userGUI.message = "Welcome " + cap + " " + username + "!";
    }
}
