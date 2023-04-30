import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * A class to store a collection of User objects and apply methods to them.
 *
 * @author Anish Agrawal, Jack Burns, Sanya Buti, Gabe Kurfman, Shubhangi Mishra
 * @version April 9, 2023
 */
public class Users {

    // private fields
    private String USER_DATA_COMMENT = "// Username; Password; User Type; Blocked User list (comma seperated); Invisible User list (comma seperated); Store list (comma seperated)";
    private ArrayList<User> userList = new ArrayList <User>();
    private String dataFilename;
    private User currentUser;
    //private static String BLOCK_PROMPT = "Enter Username to block: ";
    //private static String INVIS_PROMPT = "Enter Username to become invisible to: ";

    // constructor
    public Users(String dataFilename, GUI usersGUI) {
        this.dataFilename = dataFilename;
        this.currentUser = null;
        loadData(usersGUI);
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
    public void loadData(GUI usersGUI) {
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

    public void saveData(GUI usersGUI) {
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
    public User login(GUI usersGUI) {
        String username;
        String password;
        usersGUI.message = "";
        do {
            username = usersGUI.logInUsername();
            for (User user: userList) {
                if (user.getUsername().equals(username)) {
                    currentUser = user;
                    break;
                }
            }
            if (currentUser == null) {
                usersGUI.message = "Invalid username.";
            }
        } while (currentUser == null);

        usersGUI.message = "";
        do {
            password = usersGUI.logInPassword();
            if (!currentUser.getPassword().equals(password)) {
                usersGUI.message = "Invalid password.";
            }
        } while (!currentUser.getPassword().equals(password));

        currentUser.welcomeUser(usersGUI);
        return currentUser;
    }

    public User newUser(GUI usersGUI) {
        String username = "";
        String password = "";
        String confirmPassword = "";
        //String userType;
        ArrayList<String> blockedUsers = new ArrayList<String>();
        ArrayList<String> invisibleUsers = new ArrayList<String>();
        ArrayList<String> storeList = new ArrayList<String>();

        usersGUI.message = "";
        do {
            username = usersGUI.CreateAccountUsername();
            for (User user: userList) {
                if (user.getUsername().equals(username)) {
                    username = "";
                    break;
                }
            }
            if (username.equals("")) {
                usersGUI.message = "That username is taken! Please try again.";
            } else if (!validCharacters(username)) {
                usersGUI.message = "That username has invalid characters!";
            }
        } while (username.equals("") || !validCharacters(username));

        usersGUI.message = "";
        do {
            password = usersGUI.CreateAccountPassword();
            confirmPassword = usersGUI.confirmPassword();
            if (!password.equals(confirmPassword)) {
                usersGUI.message = "Passwords do not match!";
            } else if (!validCharacters(password)) {
                usersGUI.message = "That password contains invalid characters!";
            }
        } while (!password.equals(confirmPassword) || !validCharacters(password));

        int userCreates = usersGUI.typeofAccount();
        GUI userGUI = null;
        if (userCreates == 1) {
            currentUser = new User(username, password, "customer", blockedUsers, invisibleUsers, storeList, userGUI);
        } else {
            currentUser = new User(username, password, "seller", blockedUsers, invisibleUsers, storeList, userGUI);
            addStores(usersGUI, currentUser);
        }
        currentUser.welcomeUser(usersGUI);
        userList.add(currentUser);
        saveData(usersGUI);
        return currentUser;
    }

    public User changeUsername(GUI usersGUI) {
        String username = "";

        usersGUI.message = "";
        do {
            usersGUI.changeUserName();
            for (User user: userList) {
                if (user.getUsername().equals(username)) {
                    username = "";
                    break;
                }
            }
            if (username.equals("")) {
                usersGUI.message = "That username is taken!";
            } else if (!validCharacters(username)) {
                usersGUI.message = "That username is invalid!";
            }
        } while (username.equals("") || !validCharacters(username));
        userList.remove(currentUser);
        currentUser.setUsername(username);
        currentUser.welcomeUser(usersGUI);
        userList.add(currentUser);
        saveData(usersGUI);
        return currentUser;
    }

    public User changePassword(GUI usersGUI) {
        String password = "";
        String confirmPassword = "";
        
        usersGUI.message = "";
        do {
            password = usersGUI.changePassword();
            confirmPassword = usersGUI.confirmPassword();
            if (!password.equals(confirmPassword)) {
                usersGUI.message = "Passwords do not match!";
            } else if (!validCharacters(password)) {
                usersGUI.message = "That password contains invalid characters!";
            }
        } while (!password.equals(confirmPassword) || !validCharacters(password));

        userList.remove(currentUser);
        currentUser.setPassword(password);
        userList.add(currentUser);
        saveData(usersGUI);
        return currentUser;
    }

    public boolean deleteAccount(GUI usersGUI) {
        //String confirm;

        usersGUI.message = "";
        int delete = usersGUI.deleteAccount();
        if (delete == 1) {
            userList.remove(currentUser);
            saveData(usersGUI);
            return true;
        } else {
            usersGUI.message = "Account deletion cancelled.";
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

    public void printUsers(GUI usersGUI) {
        String totalOutput = " ";
        int count = 0;
        for (User user: userList) {
            String output = user.toString();
            count ++;
            totalOutput = "User:" + count + output;
        }
        usersGUI.usersOutput(totalOutput);
    }
    public void blockUser(GUI usersGUI) {
        String input;
        boolean blocked = false;
        input = usersGUI.blockUser();

        for (User user: userList) {
            if (user.getUsername().equals(input)) {
                user.addBlockedUser(currentUser);
                saveData(usersGUI);
                blocked = true;
                break;
            }
        }
        if (!blocked) {
            usersGUI.errorBlocked();
        }
    }

    public void invisUser(GUI usersGUI) {
        String input;
        boolean invis = false;

        input = usersGUI.invisUsername();
        usersGUI.message = "";
        for (User user: userList) {
            if (user.getUsername().equals(input)) {
                user.addInvisUser(currentUser);
                saveData(usersGUI);
                invis = true;
                break;
            }
        }
        if (!invis) {
            usersGUI.errorInvis();
        }
    }

    public void addStores(GUI usersGUI, User user) {
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
