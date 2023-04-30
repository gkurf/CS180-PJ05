import com.sun.java.accessibility.util.GUIInitializedListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class to store a collection of User objects and apply methods to them.
 *
 * @author Anish Agrawal, Jack Burns, Sanya Buti, Gabe Kurfman, Shubhangi Mishra
 * @version April 9, 2023
 */
public class Users {

    // private fields
    private String USER_DATA_COMMENT = "// Username; Password; User Type; Blocked User list (comma seperated); Invisible User list (comma seperated); Store list (comma seperated)";
    private static String BLOCK_PROMPT = "Enter Username to block: ";
    private static String INVIS_PROMPT = "Enter Username to become invisible to: ";
    private ArrayList<User> userList = new ArrayList <User>();
    private String dataFilename;
    private User currentUser;
    private GUI usersGUI;


    // constructor
    public Users(String dataFilename) {
        this.dataFilename = dataFilename;
        this.currentUser = null;
        loadData();
    }

    // setters and getters
    public ArrayList<User> getUserList() {
        return this.userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public String getDataFilename() {
        return this.dataFilename;
    }

    public void setDataFilename(String dataFilename) {
        this.dataFilename = dataFilename;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    // reading all the data from a file
    public void loadData() {
        try (BufferedReader dataFile = new BufferedReader(new FileReader(dataFilename))) {
            String line = "";
            String[] splitLine;
            String username;
            String password;
            String userType;

            line = dataFile.readLine(); // Ignore comment line

            while (line != null) {
                ArrayList<String> blockedUsers = new ArrayList<>();
                ArrayList<String> invisibleUsers = new ArrayList<>();
                ArrayList<String> storeList = new ArrayList<>();

                line = dataFile.readLine();
                if (line != null) {
                    try {
                        splitLine = line.split(";");
                        username = splitLine[0];
                        password = splitLine[1];
                        userType = splitLine[2];
                        try {
                            blockedUsers = new ArrayList<>(Arrays.asList(splitLine[3].split(",")));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            ;
                        }
                        try {
                            invisibleUsers = new ArrayList<>(Arrays.asList(splitLine[4].split(",")));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            ;
                        }
                        try {
                            storeList = new ArrayList<>(Arrays.asList(splitLine[5].split(",")));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            ;
                        }

                        GUI userGUI = null;
                        this.userList.add(new User(username, password, userType, blockedUsers, invisibleUsers, storeList, userGUI));
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("[ERROR] File format error. (Index out of bounds)");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            usersGUI.FileError();
            e.printStackTrace();
        } catch (IOException e) {
            usersGUI.FileError();
            e.printStackTrace();
        }
    }

    public void saveData() {
        PrintWriter dataFile;
        try {
            dataFile = new PrintWriter(new FileOutputStream(dataFilename, false));
            dataFile.println(USER_DATA_COMMENT);
            for (User user: userList) {
                dataFile.println(user.toCSV());
            }
            dataFile.close();
        } catch (FileNotFoundException e) {
            usersGUI.FileError();
            e.printStackTrace();
        }
    }

    // We could add a password attempt limit and stuff for security
    public User login() {
        String username;
        String password;
        do {
            username = usersGUI.logInUsername();
            for (User user: userList) {
                if (user.getUsername().equals(username)) {
                    currentUser = user;
                    break;
                }
            }
            if (currentUser == null) {
                usersGUI.invalidUsername();
            }
        } while (currentUser == null);

        do {
            password = usersGUI.logInPassword();
            if (!currentUser.getPassword().equals(password)) {
                usersGUI.invalidPassword();
            }
        } while (!currentUser.getPassword().equals(password));

        currentUser.welcomeUser();
        return currentUser;
    }

    public User newUser(Scanner scan) {
        String username = "";
        String password = "";
        String confirmPassword = "";
        String userType;
        ArrayList<String> blockedUsers = new ArrayList<String>();
        ArrayList<String> invisibleUsers = new ArrayList<String>();
        ArrayList<String> storeList = new ArrayList<String>();

        do {
            username = usersGUI.CreateAccountUsername();
            for (User user: userList) {
                if (user.getUsername().equals(username)) {
                    username = "";
                    break;
                }
            }
            if (username.equals("")) {
                usersGUI.userNameTaken();
            } else if (!validCharacters(username)) {
                usersGUI.userNameInvalid();
            }
        } while (username.equals("") || !validCharacters(username));

        do {
            password = usersGUI.CreateAccountPassword();
            confirmPassword = usersGUI.confirmPassword();
            if (!password.equals(confirmPassword)) {
               usersGUI.passwordMisMatch();
            } else if (!validCharacters(password)) {
               usersGUI.invalidPasswordCharacters();
            }
        } while (!password.equals(confirmPassword) || !validCharacters(password));

        int userCreates = usersGUI.typeofAccount();
        GUI userGUI = null;
        if (userCreates == 1) {
            currentUser = new User(username, password, "customer", blockedUsers, invisibleUsers, storeList, userGUI);
        } else {
            currentUser = new User(username, password, "seller", blockedUsers, invisibleUsers, storeList, userGUI);
            addStores(scan, currentUser);
        }
        currentUser.welcomeUser();
        userList.add(currentUser);
        saveData();
        return currentUser;
    }

    public User changeUsername(Scanner scan) {
        String username = "";

        System.out.println("\nCHANGE USERNAME");
        do {
            usersGUI.changeUserName();
            for (User user: userList) {
                if (user.getUsername().equals(username)) {
                    username = "";
                    break;
                }
            }
            if (username.equals("")) {
                usersGUI.userNameTaken();
            } else if (!validCharacters(username)) {
                usersGUI.userNameInvalid();
            }
        } while (username.equals("") || !validCharacters(username));
        userList.remove(currentUser);
        currentUser.setUsername(username);
        currentUser.welcomeUser();
        userList.add(currentUser);
        saveData();
        return currentUser;
    }

    public User changePassword(Scanner scan) {
        String password = "";
        String confirmPassword = "";
        ;
        do {
            password = usersGUI.changePassword();
            confirmPassword = usersGUI.confirmPassword();
            if (!password.equals(confirmPassword)) {
                usersGUI.passwordMisMatch();
            } else if (!validCharacters(password)) {
                usersGUI.invalidPasswordCharacters();
            }
        } while (!password.equals(confirmPassword) || !validCharacters(password));

        userList.remove(currentUser);
        currentUser.setPassword(password);
        userList.add(currentUser);
        saveData();
        return currentUser;
    }

    public boolean deleteAccount(Scanner scan) {
        String confirm;

        int delete = usersGUI.deleteAccount();
        if (delete == 1) {
            userList.remove(currentUser);
            saveData();
            return true;
        } else {
            usersGUI.deletionCancelled();
            return false;
        }
    }

    public boolean validCharacters(String inputStr) {
        String[] restrictedChars = {
                ",",
                ";",
                "\\"
        };
        for (int i = 0; i < restrictedChars.length; i++) {
            if (inputStr.contains(restrictedChars[i])) {
                return false;
            }
        }
        return true;
    }

    public void printUsers() {
        String totalOutput = " ";
        int count = 0;
        for (User user: userList) {
            String output = user.toString();
            count ++;
            totalOutput = "User:" + count + output;
        }
        usersGUI.usersOutput(totalOutput);
    }
    public void blockUser() {
        String input;
        boolean blocked = false;
        input = usersGUI.blockUser();

        for (User user: userList) {
            if (user.getUsername().equals(input)) {
                user.addBlockedUser(currentUser);
                saveData();
                blocked = true;
                break;
            }
        }
        if (!blocked) {
            usersGUI.errorBlocked();
        }
    }

    public void invisUser(Scanner scan) {
        String input;
        boolean invis = false;

        input = usersGUI.invisUsername();

        for (User user: userList) {
            if (user.getUsername().equals(input)) {
                user.addInvisUser(currentUser);
                saveData();
                invis = true;
                break;
            }
        }
        if (!invis) {
            usersGUI.errorInvis();
        }
    }

    public void addStores(Scanner scan, User user) {
        int storeCount = 0;
        String name;

        do {
            try {
                storeCount = usersGUI.storesAmount();
                if (storeCount < 0) {
                    usersGUI.errorStoreAmt();
                }
            } catch (InputMismatchException e) {
                usersGUI.errorStoreAmt();
            }

        } while (storeCount == 0);

        for (int i = 1; i <= storeCount; i++) {
            do {
                name = usersGUI.registerStore(i);
                if (!validCharacters(name)) {
                   usersGUI.invalidStoreName();
                }
            } while (!validCharacters(name));
            user.addStore(name);
        }
    }

    public boolean userExists(String username) {
        for (User user: userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> customerList() {
        ArrayList<User> customers = new ArrayList<User>();

        for (User user: userList) {
            if (user.isCustomer()) {
                customers.add(user);
            }
        }

        return customers;
    }

    public ArrayList<User> SellerList() {
        ArrayList<User> sellers = new ArrayList<User>();

        for (User user: userList) {
            if (!user.isCustomer()) {
                sellers.add(user);
            }
        }

        return sellers;
    }

    public ArrayList<Store> storeList() {
        ArrayList<Store> stores = new ArrayList<Store>();

        for (User user: userList) {
            if (!user.isCustomer()) {
                for (String name: user.getStoreList()) {
                    stores.add(new Store(user, name));
                }
            }
        }

        return stores;
    }

    public User getUserObj(String username) {
        for (User user: userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
