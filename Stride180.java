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
   
    public static void main(String[] args) {
        int input = 0;
        GUI usersGUI = new GUI();
        User user;
        boolean userLoggedIn;
        Statistics statistics = new Statistics();

        do {
            Users users = new Users("userData.txt", usersGUI);
            user = login(usersGUI, users);
            userLoggedIn = true;
        
            do {
                if (user != null && user.isCustomer()) {
                    user.welcomeUser(usersGUI);
                    usersGUI.message = "";
                    do {
                        input = usersGUI.customerOptions();
                        usersGUI.message = "";

                        if (input == 1) {
                            // Message seller
                            User seller;
                           
                            do {
                                input = usersGUI.customerMessageOptions();
                                usersGUI.message = "";
                                if (input == 1) {
                                    // Search seller
                                    seller = chooseSeller(usersGUI,user, users);
                                    if (seller != null) {
                                        sendMessage(usersGUI, user, seller);
                                    }
                                } else if (input == 2) {
                                    // List all stores
                                    seller = displayStores(usersGUI, users, user);
                                    if (seller != null) {
                                        sendMessage(usersGUI, user, seller);
                                    }
                                } else {
                                    usersGUI.message = "Invalid Option Selected!";
                                }
                            } while (input < 1 || input > 2);
                        } else if (input == 2) {
                            // Account Settings

                            do {
                                input = usersGUI.customerAccountOptions();
                                usersGUI.message = "";
                                if (input == 1) {
                                    // View Customer Statiscs
                                    List<String> commonSellerWords = statistics.getSellerCommonWords();
                                    String commonWords = " ";
                                    for (String word : commonSellerWords) {
                                        if (word.equals(null)) {
                                            usersGUI.message = "No common words found!";
                                        }
                                        commonWords += word + "";
                                    }
                                    usersGUI.commonWordsSeller(commonWords);

                                } else if (input == 2) {
                                    // Block User
                                    users.blockUser(usersGUI);
                                } else if (input == 3) {
                                    // Invisible user
                                    users.invisUser(usersGUI);
                                } else if (input == 4) {
                                    // Change Username
                                    user = users.changeUsername(usersGUI);
                                } else if (input == 5) {
                                    // Change Password
                                    user = users.changePassword(usersGUI);
                                } else if (input == 6) {
                                    // Delete account
                                    if (users.deleteAccount(usersGUI)) {
                                        userLoggedIn = false;
                                    }
                                } else {
                                    usersGUI.message = "Invalid Option Selected!";
                                }
                            } while ((input < 1 || input > 5) && userLoggedIn);
                        } else if (input == 3) {
                            userLoggedIn = false; // Log out
                        } else {
                            usersGUI.message = "Invalid Option Selected!";
                        }
                    } while ((input < 1 || input > 3) && userLoggedIn);

                } else if (user != null) {
                    do {
                        input = usersGUI.SellerOptions();
                        usersGUI.message = "";
                        if (input == 1) {
                            // Message customer
                            User customer;
                            
                            do {
                                input = usersGUI.sellerMessageOptions();
                                usersGUI.message = "";
                                if (input == 1) {
                                    // Search customers
                                    customer = chooseCustomer(user, users, usersGUI);
                                    if (customer != null) {
                                        sendMessage(usersGUI, user, customer);
                                    }
                                } else if (input == 2) {
                                    // List all customers
                                    customer = displayCustomers(usersGUI, users, user);
                                    if (customer != null) {
                                        sendMessage(usersGUI, user, customer);
                                    }
                                } else {
                                    usersGUI.message = "Invalid Option Selected!";
                                }
                            } while (input < 1 || input > 2);
                        } else if (input == 2) {
                            // Account Settings
                            
                            do {
                                input = usersGUI.sellerAccountOptions();
                                usersGUI.message = "";
                                if (input == 1) {
                                    List<String> commonCustomerWords = statistics.getCustomerCommonWords();
                                    String commonWords = "";
                                    String commonWordss = " ";
                                    for (String word : commonCustomerWords) {
                                        if(word.equals(null))
                                        {
                                            usersGUI.message = "No common words found.";
                                        }
                                        commonWords += word + " ";
                                    }
                                    usersGUI.commonWordsCustomer(commonWords);

                                    List<String> commonSellerWords = statistics.getSellerCommonWords();


                                    for (String word : commonSellerWords) {
                                        if(word.equals(null))
                                        {
                                            usersGUI.message = "No common words found.";
                                        }
                                        commonWordss = word + "";
                                    }
                                    usersGUI.commonWordsSeller(commonWordss);


                                    // View Store Statiscs
                                } else if (input == 2) {
                                    // Block User
                                    users.blockUser(usersGUI);
                                } else if (input == 3) {
                                    // Invisible user
                                    users.invisUser(usersGUI);
                                } else if (input == 4) {
                                    // Change Username
                                    user = users.changeUsername(usersGUI);
                                } else if (input == 5) {
                                    // Change Password
                                    user = users.changePassword(usersGUI);
                                } else if (input == 6) {
                                    // Delete account
                                    if (users.deleteAccount(usersGUI)) {
                                        userLoggedIn = false;
                                    }
                                } else {
                                    usersGUI.message = "Invalid Option Selected!";
                                }
                            } while ((input < 1 || input > 6) && userLoggedIn);
                        } else if (input == 3) {
                            userLoggedIn = false; // Log out
                        } else {
                            usersGUI.message = "Invalid Option Selected!";
                        }
                    } while ((input < 1 || input > 3) && userLoggedIn);
                }
            } while (userLoggedIn && user != null);
            
        } while (user != null);

        usersGUI.closeWindow();
        System.exit(0);
    }

    // helps the user log in or create an account
    public static User login(GUI usersGUI, Users users) {
        int input;
        User currentUser = null;

        do {
            input = usersGUI.mainEntry();
            usersGUI.message = "";

            if (input == 1) {
                currentUser = users.login(usersGUI);
            } else if (input == 2) {
                currentUser = users.newUser(usersGUI);
            } else if (input == 3) {
                currentUser = null;
            } else {
                usersGUI.message = "Invalid Option Selected!";
            }
        } while (input < 0 || input > 3);

        return currentUser;
    }

    // sends message
    public static void sendMessage(GUI usersGUI, User sender, User reciever) {
        Message message = new Message(sender, reciever);

        int input;
        String secondInput = " ";
        do {
            String recieverUser = "Conversation with " + reciever.getUsername();
            String history = message.messageString(sender);
            input = usersGUI.sendMessageOption(recieverUser, history);
            usersGUI.message = "";

            if (input == 1) {
                // Send new message
                secondInput = usersGUI.optionOneMessage();
                message.createMessage(sender, secondInput);
            } else if (input == 2) {
                // Send text file
                secondInput = usersGUI.optionTwoMessage();
                message.importFromTXT(sender, secondInput);
            } else if (input == 3) {
                // Edit previous message
                secondInput = usersGUI.optionThreeMessage();
                message.editMessage(sender, secondInput);
            } else if (input == 4) {
                // Delete previous message
                message.deleteMessage(sender);
            } else if (input == 5) {
                // Export to CSV
                secondInput = usersGUI.optionFiveMessage();
                message.exportToCSV(sender, secondInput);
            } else if (input == 6) {
                // Exit conversation
                break;
            } else {
                usersGUI.message = "Invalid Option Selected!";
            }
        } while (input < 0 || input > 7);
    }

    // displays all the potential stores
    public static User displayStores(GUI usersGUI, Users users, User user) {
        User sellerChosen;
        ArrayList<Store> dataSeller = users.storeList();
        int size = dataSeller.size();
        int numberChosen = 0;
        String dataStores = " ";

        usersGUI.message = "";
        if (size == 0) {
            usersGUI.message = "Store not found";
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

            do {
                for (int j = 1; j <= dataSeller.size(); j++) {
                    dataStores += "Store #" + j + ": " + dataSeller.get(j - 1).getNameStore();
                }
                try {
                    numberChosen = usersGUI.numberStore(size, dataStores);
                    if (numberChosen > size) {
                        usersGUI.message = "Invalid Option Selected!";
                    }
                } catch (InputMismatchException e) {
                    usersGUI.message = "Invalid Option Selected!";
                    numberChosen = size + 1;
                }
            } while (numberChosen > size);
            sellerChosen = dataSeller.get(numberChosen - 1).getUser();
            return sellerChosen;
        }
    }

    // displays list of all customers
    public static User displayCustomers(GUI usersGUI, Users users, User user) {
        User customerChosen;
        ArrayList <User> dataCustomer = users.customerList();
        int size = dataCustomer.size();
        int numberChosen = 0;
        String dataCustomers = " ";

        usersGUI.message = "";
        if (size == 0) {
            usersGUI.message = "Customer not found";
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

            do {
                for (int j = 1; j <= dataCustomer.size(); j++) {
                    dataCustomers += "User #" + j + ": " + dataCustomer.get(j - 1).getUsername();
                }
                try {
                    numberChosen = usersGUI.numberCustomers(size, dataCustomers);
                    if (numberChosen > size) {
                        usersGUI.message = "Invalid Option Selected!";
                    }
                } catch (InputMismatchException e) {
                    usersGUI.message = "Invalid Option Selected!";
                    numberChosen = size + 1;
                }
            } while (numberChosen > size);
            customerChosen = dataCustomer.get(numberChosen - 1);
            return customerChosen;
        }
    }
    // for customer selection menu of sellers
    public static User chooseSeller(GUI usersGUI, User user, Users users) {
        String input;

        input = usersGUI.messageSeller();
        for (User currentUser: users.SellerList()) {
            if (currentUser.getUsername().equals(input) &&
                    !user.getInvisibleUsers().contains(currentUser.getUsername())) {
                return currentUser;
            }
        }

        return null;
    }

    // creates the menu of customers for sellers
    public static User chooseCustomer(User user, Users users, GUI usersGUI) {
        String input;

        input = usersGUI.messageCustomer();
        for (User currentUser: users.customerList()) {
            if (currentUser.getUsername().equals(input) &&
                    !user.getInvisibleUsers().contains(currentUser.getUsername())) {
                return currentUser;
            }
        }

        return null;
    }
}
