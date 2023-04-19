import java.util.Scanner;
import java.util.*;

/**
 * This is the main class for the marketplace. The user interface and all
 * referencing to other class is
 * impedded in this program and will be the central run command.
 *
 * @author Anish Agrawal, Jack Burns, Sanya Buti, Gabe Kurfman, Shubhangi Mishra
 * @version April 19, 2023
 */
public class Stride180 {

    // messages that will be sent to users
    private static String WELCOME_PROMPT = "Welcome to Stride180! We are a marketplace exclusively for the hottest kicks!";
    private static String OPTION_PROMPT = "\nType an option to continue";
    private static String INVALID_OPTION = "\nInvalid option. Please try again!";
    private static String LOGIN_OPTIONS = "1. Login\n2. Create account\n3. Exit";
    private static String CUSTOMER_OPTIONS = "1. Message a Seller\n2. Account Settings\n3. Logout";
    private static String CUSTOMER_NEW_MESSAGE = "1. Search for a Seller\n2. List all Stores";
    private static String SELLER_OPTIONS = "1. Message a Customer\n2. Account Settings\n3. Logout";
    private static String SELLER_NEW_MESSAGE = "1. Search Customers\n2. List all Customers";
    private static String CUSTOMER_SETTINGS = "1. View Seller Statistics\n2. Block User\n3. Become Invisible to User\n4. Change Username\n5. Change Password\n6. Delete Account";
    private static String SELLER_SETTINGS = "1. View Store Statistics\n2. Block User\n3. Become Invisible to User\n4. Change Username\n5. Change Password\n6. Delete Account";
    private static String MESSAGE_OPTIONS = "1. Send New Message\n2. Send Text File\n3. Edit Previous Message\n4. Delete Previous Message\n5. Export Conversation to CSV\n6. Exit Conversation";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        User user;
        boolean userLoggedIn;
        System.out.println(WELCOME_PROMPT);
        Statistics statistics=new Statistics();
        

        do {
            Users users = new Users("userData.txt");
            user = login(scan, users);
            userLoggedIn = true;
            do {
                if (user == null) {

                } else if (user.isCustomer()) {
                    do {
                        System.out.println(OPTION_PROMPT);
                        System.out.println(CUSTOMER_OPTIONS);
                        input = scan.nextLine();

                        if (input.equals("1")) {
                            // Message seller
                            User seller;
                            do {
                                System.out.println(OPTION_PROMPT);
                                System.out.println(CUSTOMER_NEW_MESSAGE);
                                input = scan.nextLine();

                                if (input.equals("1")) {
                                    // Search seller
                                    seller = chooseSeller(scan, user, users);
                                    if (seller != null) {
                                        sendMessage(scan, user, seller);
                                    }
                                } else if (input.equals("2")) {
                                    // List all stores
                                    seller = displayStores(scan, users, user);
                                    if (seller != null) {
                                        sendMessage(scan, user, seller);
                                    }
                                } else {
                                    System.out.println(INVALID_OPTION);
                                }
                            } while (!"12".contains(input));
                        } else if (input.equals("2")) {
                            // Account Settings
                            do {
                                System.out.println(OPTION_PROMPT);
                                System.out.println(CUSTOMER_SETTINGS);
                                input = scan.nextLine();
                                if (input.equals("1")) {
                                    // View Customer Statiscs
                                     List<String> commonSellerWords = statistics.getSellerCommonWords();
                                    
                                      System.out.println("Most common words used by sellers:");
                                      for (String word : commonSellerWords) {
                                          if(word.equals(null))
                                       {
                                           System.out.println("The seller has no prior messages");
                                       }
                                            System.out.println("- " + word);
                                        }
                                       
                                
                                } else if (input.equals("2")) {
                                    // Block User
                                    users.blockUser(scan);
                                } else if (input.equals("3")) {
                                    // Invisible user
                                    users.invisUser(scan);
                                } else if (input.equals("4")) {
                                    // Change Username
                                    user = users.changeUsername(scan);
                                } else if (input.equals("5")) {
                                    // Change Password
                                    user = users.changePassword(scan);
                                } else if (input.equals("6")) {
                                    // Delete account
                                    if (users.deleteAccount(scan)) {
                                        userLoggedIn = false;
                                    }
                                } else {
                                    System.out.println(INVALID_OPTION);
                                }
                            } while (!"12345".contains(input) && userLoggedIn);
                        } else if (input.equals("3")) {
                            userLoggedIn = false; // Log out
                        } else {
                            System.out.println(INVALID_OPTION);
                        }
                    } while (!"123".contains(input) && userLoggedIn);

                } else {
                    do {
                        System.out.println(OPTION_PROMPT);
                        System.out.println(SELLER_OPTIONS);
                        input = scan.nextLine();

                        if (input.equals("1")) {
                            // Message customer
                            User customer;
                            do {
                                System.out.println(OPTION_PROMPT);
                                System.out.println(SELLER_NEW_MESSAGE);
                                input = scan.nextLine();

                                if (input.equals("1")) {
                                    // Search customers
                                    customer = chooseCustomer(user, users, scan);
                                    if (customer != null) {
                                        sendMessage(scan, user, customer);
                                    }
                                } else if (input.equals("2")) {
                                    // List all customers
                                    customer = displayCustomers(scan, users, user);
                                    if (customer != null) {
                                        sendMessage(scan, user, customer);
                                    }
                                } else {
                                    System.out.println(INVALID_OPTION);
                                }
                            } while (!"12".contains(input));
                        } else if (input.equals("2")) {
                            // Account Settings
                            do {
                                System.out.println(OPTION_PROMPT);
                                System.out.println(SELLER_SETTINGS);
                                input = scan.nextLine();
                                if (input.equals("1")) {
                                    List<String> commonCustomerWords = statistics.getCustomerCommonWords();
                                    
                                      System.out.println("Most common words used by customers:");
                                      for (String word : commonCustomerWords) {
                                          if(word.equals(null))
                                       {
                                           System.out.println("The customer has no prior messages");
                                       }
                                            System.out.println("- " + word);
                                        }
                                        
                                    List<String> commonSellerWords = statistics.getSellerCommonWords();
                                   
                                      System.out.println("Most common words used by sellers:");
                                      for (String word : commonSellerWords) {
                                            System.out.println("- " + word);
                                          if(word.equals(null))
                                       {
                                           System.out.println("The seller has no prior messages");
                                       }
                                        }
                                        
                                    
                                
                                    // View Store Statiscs
                                } else if (input.equals("2")) {
                                    // Block User
                                    users.blockUser(scan);
                                } else if (input.equals("3")) {
                                    // Invisible user
                                    users.invisUser(scan);
                                } else if (input.equals("4")) {
                                    // Change Username
                                    user = users.changeUsername(scan);
                                } else if (input.equals("5")) {
                                    // Change Password
                                    user = users.changePassword(scan);
                                } else if (input.equals("6")) {
                                    // Delete account
                                    if (users.deleteAccount(scan)) {
                                        userLoggedIn = false;
                                    }
                                } else {
                                    System.out.println(INVALID_OPTION);
                                }
                            } while (!"123456".contains(input) && userLoggedIn);
                        } else if (input.equals("3")) {
                            userLoggedIn = false; // Log out
                        } else {
                            System.out.println(INVALID_OPTION);
                        }
                    } while (!"123".contains(input) && userLoggedIn);
                }
            } while (userLoggedIn && user != null);
        } while (user != null);

        scan.close();
    }

    // helps the user log in or create an account
    public static User login(Scanner scan, Users users) {
        String input;
        User currentUser = null;

        System.out.println(OPTION_PROMPT);
        do {
            System.out.println(LOGIN_OPTIONS);
            input = scan.nextLine();

            if (input.equals("1")) {
                currentUser = users.login(scan);
            } else if (input.equals("2")) {
                currentUser = users.newUser(scan);
            } else if (input.equals("3")) {
                currentUser = null;
            } else {
                System.out.println(INVALID_OPTION);
            }
        } while (!"123".contains(input));

        return currentUser;
    }

    // sends message
    public static void sendMessage(Scanner scan, User sender, User reciever) {
        Message message = new Message(sender, reciever);

        String input;

        do {
            System.out.println("CONVERSATION WITH " + reciever.getUsername());
            System.out.println(message.messageString(sender));
            System.out.println(OPTION_PROMPT);
            System.out.println(MESSAGE_OPTIONS);
            input = scan.nextLine();

            if (input.equals("1")) {
                // Send new message
                System.out.println("Message to send: ");
                input = scan.nextLine();
                message.createMessage(sender, input);
            } else if (input.equals("2")) {
                // Send text file
                System.out.println("File Path: ");
                input = scan.nextLine();
                message.importFromTXT(sender, input);
            } else if (input.equals("3")) {
                // Edit previous message
                System.out.println("Text to replace previous message: ");
                input = scan.nextLine();
                message.editMessage(sender, input);
            } else if (input.equals("4")) {
                // Delete previous message
                message.deleteMessage(sender);
            } else if (input.equals("5")) {
                // Export to CSV
                System.out.println("File Path: ");
                input = scan.nextLine();
                message.exportToCSV(sender, input);
            } else if (input.equals("6")) {
                // Exit conversation
                break;
            } else {
                System.out.println(INVALID_OPTION);
            }
        } while (!"123456".contains(input));
    }

    // displays all the potential stores
    public static User displayStores(Scanner scan, Users users, User user) {
        User sellerChosen;
        ArrayList<Store> dataSeller = users.storeList();
        int size = dataSeller.size();
        int numberChosen = 0;

        if (size == 0) {
            System.out.println("There are currently no registered stores");
            return null;
        } else {
            String invisible = " ";
            for (int z = 0; z < user.getInvisibleUsers().size(); z++) {
                invisible = user.getInvisibleUsers().get(z) + " ";
            }
            for (int i = 0; i < dataSeller.size(); i++) {
                if (invisible.contains(dataSeller.get(i).getUser().getUsername())) {
                    dataSeller.remove(i);
                }
            }
            size = dataSeller.size();
            System.out.println("Number of stores: " + size);

            do {
                for (int j = 1; j <= dataSeller.size(); j++) {
                    System.out.println("Store #" + j + ": " + dataSeller.get(j - 1).getNameStore());
                }
                System.out.println("Which store number would you like to message?");
                try {
                    numberChosen = scan.nextInt();
                    if (numberChosen > size) {
                        System.out.println(INVALID_OPTION);
                    }
                } catch (InputMismatchException e) {
                    System.out.println(INVALID_OPTION);
                    numberChosen = size + 1;
                }
                scan.nextLine();
            } while (numberChosen > size);
            sellerChosen = dataSeller.get(numberChosen - 1).getUser();
            return sellerChosen;
        }
    }

    // displays list of all customers
    public static User displayCustomers(Scanner scan, Users users, User user) {
        User customerChosen;
        ArrayList <User> dataCustomer = users.customerList();
        int size = dataCustomer.size();
        int numberChosen = 0;

        if (size == 0) {
            System.out.println("There are currently no registered stores");
            return null;
        } else {
            String invisible = " ";
            for (int z = 0; z < user.getInvisibleUsers().size(); z++) {
                invisible = user.getInvisibleUsers().get(z) + " ";
            }
            for (int i = 0; i < dataCustomer.size(); i++) {
                if (invisible.contains(dataCustomer.get(i).getUsername())) {
                    dataCustomer.remove(i);
                }
            }
            size = dataCustomer.size();
            System.out.println("Number of customers: " + size);

            do {
                for (int j = 1; j <= dataCustomer.size(); j++) {
                    System.out.println("User #" + j + ": " + dataCustomer.get(j - 1).getUsername());
                }
                System.out.println("Which customer number would you like to message?");
                try {
                    numberChosen = scan.nextInt();
                    if (numberChosen > size) {
                        System.out.println(INVALID_OPTION);
                    }
                } catch (InputMismatchException e) {
                    System.out.println(INVALID_OPTION);
                    numberChosen = size + 1;
                }
                scan.nextLine();
            } while (numberChosen > size);
            customerChosen = dataCustomer.get(numberChosen - 1);
            return customerChosen;
        }
    }

    // for customer selection menu of sellers
    public static User chooseSeller(Scanner scan, User user, Users users) {
        String input;

        System.out.println("Enter the username of the seller you would like to message: ");
        input = scan.nextLine();
        for (User currentUser: users.SellerList()) {
            if (currentUser.getUsername().equals(input) &&
                !user.getInvisibleUsers().contains(currentUser.getUsername())) {
                return currentUser;
            }
        }

        System.out.println("That seller does not exist!");
        return null;
    }

    // creates the menu of customers for sellers
    public static User chooseCustomer(User user, Users users, Scanner scan) {
        String input;

        System.out.println("Enter the username of the customer you would like to message: ");
        input = scan.nextLine();
        for (User currentUser: users.customerList()) {
            if (currentUser.getUsername().equals(input) &&
                !user.getInvisibleUsers().contains(currentUser.getUsername())) {
                return currentUser;
            }
        }

        System.out.println("That customer does not exist!");
        return null;
    }
    
}
