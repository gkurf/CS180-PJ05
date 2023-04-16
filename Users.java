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

                        this.userList
                            .add(new User(username, password, userType, blockedUsers, invisibleUsers, storeList));
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("[ERROR] File format error. (Index out of bounds)");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] Data file not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("[ERROR] File read error.");
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
            System.out.println("[ERROR] Data file not found.");
            e.printStackTrace();
        }
    }

    // We could add a password attempt limit and stuff for security
    public User login(Scanner scan) {
        String username;
        String password;

        System.out.println("\nLOGIN");

        do {
            System.out.print("Username: ");
            username = scan.nextLine();
            for (User user: userList) {
                if (user.getUsername().equals(username)) {
                    currentUser = user;
                    break;
                }
            }
            if (currentUser == null) {
                System.out.println("Invalid username! Please try again.");
            }
        } while (currentUser == null);

        do {
            System.out.print("Password: ");
            password = scan.nextLine();
            if (!currentUser.getPassword().equals(password)) {
                System.out.println("Incorrect password! Please try again.");
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

        System.out.println("\nNEW USER");
        do {
            System.out.print("Enter a Username: ");
            username = scan.nextLine();
            for (User user: userList) {
                if (user.getUsername().equals(username)) {
                    username = "";
                    break;
                }
            }
            if (username.equals("")) {
                System.out.println("That username is taken! Please try again.");
            } else if (!validCharacters(username)) {
                System.out.println("Invalid characters in username! Please try again.");
            }
        } while (username.equals("") || !validCharacters(username));

        do {
            System.out.print("Enter a Password: ");
            password = scan.nextLine();
            System.out.print("Confirm Password: ");
            confirmPassword = scan.nextLine();
            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match! Please try again.");
            } else if (!validCharacters(password)) {
                System.out.println("Invalid characters in password! Please try again.");
            }
        } while (!password.equals(confirmPassword) || !validCharacters(password));

        System.out.print("Enter a user type (\"seller\"/\"customer\"): ");
        userType = scan.nextLine();
        if (userType.equals("customer")) {
            currentUser = new User(username, password, "customer", blockedUsers, invisibleUsers, storeList);
        } else {
            currentUser = new User(username, password, "seller", blockedUsers, invisibleUsers, storeList);
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
            System.out.print("Enter a Username: ");
            username = scan.nextLine();
            for (User user: userList) {
                if (user.getUsername().equals(username)) {
                    username = "";
                    break;
                }
            }
            if (username.equals("")) {
                System.out.println("That username is taken! Please try again.");
            } else if (!validCharacters(username)) {
                System.out.println("Invalid characters in username! Please try again.");
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

        System.out.println("\nCHANGE PASSSWORD");
        do {
            System.out.print("Enter a Password: ");
            password = scan.nextLine();
            System.out.print("Confirm password: ");
            confirmPassword = scan.nextLine();
            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match! Please try again.");
            } else if (!validCharacters(password)) {
                System.out.println("Invalid characters in password! Please try again.");
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

        System.out.println("\nDELETE ACCOUNT");
        System.out.print("Are you sure you want to delete your account? (Y/N)");
        confirm = scan.nextLine();
        if (confirm.toLowerCase().equals("y")) {
            userList.remove(currentUser);
            saveData();
            return true;
        } else {
            System.out.println("Deletion cancelled.");
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
        for (User user: userList) {
            System.out.println(user.toString());
        }
    }

    public void blockUser(Scanner scan) {
        String input;
        boolean blocked = false;
        System.out.println(BLOCK_PROMPT);
        input = scan.nextLine();

        for (User user: userList) {
            if (user.getUsername().equals(input)) {
                user.addBlockedUser(currentUser);
                saveData();
                blocked = true;
                break;
            }
        }
        if (!blocked) {
            System.out.println("That username does not exist and cannot be blocked!");
        }
    }

    public void invisUser(Scanner scan) {
        String input;
        boolean invis = false;

        System.out.println(INVIS_PROMPT);
        input = scan.nextLine();

        for (User user: userList) {
            if (user.getUsername().equals(input)) {
                user.addInvisUser(currentUser);
                saveData();
                invis = true;
                break;
            }
        }

        if (!invis) {
            System.out.println("That username does not exist!");
        }
    }

    public void addStores(Scanner scan, User user) {
        int storeCount = 0;
        String name;

        do {
            System.out.println("How many stores would you like to own?");
            try {
                storeCount = scan.nextInt();
                if (storeCount < 0) {
                    System.out.println("Invalid input, please enter a positive number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a positive number.");
            }
            scan.nextLine();
        } while (storeCount == 0);

        for (int i = 1; i <= storeCount; i++) {
            do {
                System.out.println("What is the name of store #" + i + "?");
                name = scan.nextLine();
                if (!validCharacters(name)) {
                    System.out.println("Invalid characters in store name, please try again.");
                }
            } while (!validCharacters(name));
            user.addStore(name);
        }
    }

    public boolean userExists(Scanner scan, String username) {
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