import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
public class GUI implements ActionListener {
    private static int count = 0;
    private static JLabel label;
    private static JFrame frame;
    private static JPanel panel;
    private static JTextField text;

    public GUI() {
    }

  

    @Override

    public void actionPerformed(ActionEvent e) {
    }

    public static int mainEntry() {
        frame = new JFrame();
        label = new JLabel("Welcome!");
        panel = new JPanel();
        panel.add(label);
        JButton button1 = new JButton("Would you like to login?");
        JButton button2 = new JButton("Would you like to register a new Account?");
        JButton button3 = new JButton("Exit");
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panel.setLayout(new GridLayout(0, 1));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Welcome to Stride 180");
        frame.pack();
        frame.setVisible(true);
        count = 0;
        while (count == 0) {
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 2;
                }
            });
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 3;
                }
            });
        }
        return count;
    }

    public static int typeofAccount() {
        panel.removeAll();
        label = new JLabel("What type of account do you want to create?");
        JButton button1 = new JButton("Customer Account");
        JButton button2 = new JButton("Seller Account");
        panel.add(button1);
        panel.add(button2);
        frame.setVisible(true);
        count = 0;
        while (count == 0) {
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 2;
                }
            });
        }
        return count;
    }
    public static String CreateAccountUsername() {
        String Username = " ";
        panel.removeAll();
        label = new JLabel("Enter your account username");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        Username = text.getText();
        frame.setVisible(true);
        return Username;
    }

    public static String CreateAccountPassword() {
        String Password = " ";
        panel.removeAll();
        label = new JLabel("Enter your account password");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        Password = text.getText();
        frame.setVisible(true);
        return Password;
    }

    public static String confirmPassword() {
        String Password = " ";
        panel.removeAll();
        label = new JLabel("Confirm password");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        Password = text.getText();
        frame.setVisible(true);
        return Password;
    }

    public static String logInUsername() {
        // if type is true means user account otherwise means seller
        String Username = " ";
        panel.removeAll();
        label = new JLabel("Enter your account username");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        String responseUsername = text.getText();
        frame.setVisible(true);
        Username = responseUsername;
        return Username;
    }

    public static String logInPassword() {
        String Password = " ";
        panel.removeAll();
        label = new JLabel("Enter your account Password");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        String responsePassword = text.getText();
        frame.setVisible(true);
        Password = responsePassword;
        return Password;
    }

    public static int customerOptions() {
        panel.removeAll();
        label = new JLabel("What would you like to do?");
        JButton button1 = new JButton("Message a Seller");
        JButton button2 = new JButton("Account Settings");
        JButton button3 = new JButton("Log out");
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        frame.setVisible(true);
        count = 0;
        while (count == 0) {
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 2;
                }
            });
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 3;
                }
            });
        }
        return count;
    }

    public static int customerMessageOptions() {
        panel.removeAll();
        label = new JLabel("Messaging Options:");
        JButton button1 = new JButton("Search by Seller");
        JButton button2 = new JButton("View a list of Stores ");
        JButton button3 = new JButton("Go to Main Customer Menu");
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        frame.setVisible(true);
        count = 0;
        while (count == 0) {
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 2;
                }
            });
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 3;
                }
            });
        }
        return count;
    }
    public static int customerAccountOptions() {
        panel.removeAll();
        label = new JLabel("Account Options:");
        JButton button1 = new JButton("View Statistics");
        JButton button2 = new JButton("Block a user");
        JButton button3 = new JButton("Become invisible");
        JButton button4 = new JButton("Change Username");
        JButton button5 = new JButton("Change Password");
        JButton button6 = new JButton("Delete Account");
        JButton button7 = new JButton("Go to Main Customer Menu");
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        frame.setVisible(true);
        count = 0;
        while (count == 0) {
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 2;
                }
            });
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 3;
                }
            });
            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 4;
                }
            });
            button5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 5;
                }
            });
            button6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 6;
                }
            });
            button7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 7;
                }
            });
        }
        return count;
    }
    public static int SellerOptions() {
        panel.removeAll();
        label = new JLabel("What would you like to do?");
        JButton button1 = new JButton("Message a Customer");
        JButton button2 = new JButton("Account Settings");
        JButton button3 = new JButton("Log out");
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        frame.setVisible(true);
        count = 0;
        while (count == 0) {
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 2;
                }
            });
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 3;
                }
            });
        }
        return count;
    }

    public static int sellerMessageOptions() {
        panel.removeAll();
        label = new JLabel("Messaging Options:");
        JButton button1 = new JButton("Search by Customer");
        JButton button2 = new JButton("View a list of Customer ");
        JButton button3 = new JButton("Go to main seller menu");
        panel.add(button1);
        panel.add(button2);
        frame.setVisible(true);
        count = 0;
        while (count == 0) {
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 2;
                }
            });
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 3;
                }
            });
        }
        return count;
    }
    public static int sellerAccountOptions() {
        panel.removeAll();
        label = new JLabel("Account Options:");
        JButton button1 = new JButton("View Store Statistics");
        JButton button2 = new JButton("Block a user");
        JButton button3 = new JButton("Become invisible");
        JButton button4 = new JButton("Change Username");
        JButton button5 = new JButton("Change Password");
        JButton button6 = new JButton("Delete Account");
        JButton button7 = new JButton("Go to Main Seller Menu");
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        frame.setVisible(true);
        count = 0;
        while (count == 0) {
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 2;
                }
            });
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 3;
                }
            });
            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 4;
                }
            });
            button5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 5;
                }
            });
            button6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 6;
                }
            });
            button7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 7;
                }
            });
        }
        return count;
    }

    public static void BothSellerCustomer() {
        panel.removeAll();
        label = new JLabel("Error: Can't both be seller and customer");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void NotPartofConvo() {
        panel.removeAll();
        label = new JLabel("User is not part of this conversation!");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void NoTextToEdit() {
        panel.removeAll();
        label = new JLabel("No Text to Edit");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void NoMessageHistory() {
        panel.removeAll();
        label = new JLabel("No Message History");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void WelcomeUser(String cap, String Username) {
        panel.removeAll();
        label = new JLabel("Welcome " + cap + " " + Username + "!");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void FileError() {
        panel.removeAll();
        label = new JLabel(" [ERROR] File read error.");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void invalidUsername() {
        panel.removeAll();
        label = new JLabel("Invalid username! Please try again.");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void invalidPassword() {
        panel.removeAll();
        label = new JLabel("Invalid password! Please try again.");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void userNameTaken() {
        panel.removeAll();
        label = new JLabel("That username is taken");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void userNameInvalid() {
        panel.removeAll();
        label = new JLabel("Invalid characters in username! Please try again.");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void passwordMisMatch() {
        panel.removeAll();
        label = new JLabel(" Passwords do not match! Please try again.");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void invalidPasswordCharacters() {
        panel.removeAll();
        label = new JLabel("Invalid characters in password! Please try again.");
        panel.add(label);
        frame.setVisible(true);
    }
    public static String changeUserName() {
        String newUsername = " ";
        panel.removeAll();
        label = new JLabel("Enter your new username");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        newUsername = text.getText();
        frame.setVisible(true);
        return newUsername;
    }

    public static String changePassword() {
        String newUsername = " ";
        panel.removeAll();
        label = new JLabel("Enter your new password");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        newUsername = text.getText();
        frame.setVisible(true);
        return newUsername;
    }
    public static int deleteAccount() {
        panel.removeAll();
        label = new JLabel("Are you sure you want to delete your account?");
        JButton button1 = new JButton("Yes");
        JButton button2 = new JButton("No");
        panel.add(button1);
        panel.add(button2);
        frame.setVisible(true);
        count = 0;
        while (count == 0) {
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 2;
                }
            });
        }
        return count;
    }

    public static void deletionCancelled() {
        panel.removeAll();
        label = new JLabel("Okay! Deletion Cancelled");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void usersOutput(String output) {
        panel.removeAll();
        label = new JLabel(output);
        panel.add(label);
        frame.setVisible(true);
    }
    public static String blockUser() {
        String Username = " ";
        panel.removeAll();
        label = new JLabel("Who do you want to block?");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        Username = text.getText();
        frame.setVisible(true);
        return Username;
    }
    public static void errorBlocked() {
        panel.removeAll();
        label = new JLabel("That username does not exist and cannot be blocked!");
        panel.add(label);
        frame.setVisible(true);
    }
    public static String invisUsername() {
        String Username = " ";
        panel.removeAll();
        label = new JLabel("Enter Username to become invisible to: ");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        Username = text.getText();
        frame.setVisible(true);
        return Username;
    }

    public static void errorInvis() {
        panel.removeAll();
        label = new JLabel("That username does not exist");
        panel.add(label);
        frame.setVisible(true);
    }
    public static int storesAmount() {
        int stores = 0;
        panel.removeAll();
        label = new JLabel("How many stores do you want to add? ");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        stores = Integer.parseInt(text.getText());
        frame.setVisible(true);
        return stores;
    }
    public static void errorStoreAmt() {
        panel.removeAll();
        label = new JLabel("Error enter a number greater than 0 ");
        panel.add(label);
        frame.setVisible(true);
    }
    public static String registerStore(int amount) {
        String name = " ";
        panel.removeAll();
        label = new JLabel("Enter the name of store number:" + " " + amount);
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        name = text.getText();
        frame.setVisible(true);
        return name;
    }
    public static void invalidStoreName() {
        panel.removeAll();
        label = new JLabel(" Invalid characters in store name, please try again.");
        panel.add(label);
        frame.setVisible(true);
    }

    public static String messageCustomer() {
        String name = " ";
        panel.removeAll();
        label = new JLabel("Enter the username of the customer you want to message");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        name = text.getText();
        frame.setVisible(true);
        return name;
    }
    public static void invalidCustomer() {
        panel.removeAll();
        label = new JLabel("That customer does not exist");
        panel.add(label);
        frame.setVisible(true);
    }

    public static String messageSeller() {
        String name = " ";
        panel.removeAll();
        label = new JLabel("Enter the username of the seller you want to message");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        name = text.getText();
        frame.setVisible(true);
        return name;
    }
    public static void invalidSeller() {
        panel.removeAll();
        label = new JLabel("That seller does not exist");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void storeNotFound() {
        panel.removeAll();
        label = new JLabel("There are currently no registered stores");
        panel.add(label);
        frame.setVisible(true);
    }
    public static int numberCustomers(int customers, String dataCustomers) {
       int customerNumber = 0;
        panel.removeAll();
        label = new JLabel("There are " + customers + " customers");
        panel.add(label);
        label = new JLabel(dataCustomers);
        label = new JLabel("Which Customer Number would you like to message?");
        text = new JTextField();
        panel.add(text);
        customerNumber = Integer.parseInt(text.getText());
        frame.setVisible(true);
        return customerNumber;
    }
    public static void invalidChoice() {
        panel.removeAll();
        label = new JLabel("That is an invalid option. Please try again");
        panel.add(label);
        frame.setVisible(true);
    }
    public static int numberStore(int stores, String dataStores) {
        int storeNumber = 0;
        panel.removeAll();
        label = new JLabel("There are " + stores + " stores");
        panel.add(label);
        label = new JLabel(dataStores);
        panel.add(label);
        label = new JLabel("Which Store Number would you like to message?");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        storeNumber = Integer.parseInt(text.getText());
        frame.setVisible(true);
        return storeNumber;
    }
    public static int sendMessageOption(String reciever, String history) {
        panel.removeAll();
        label = new JLabel(reciever);
        panel.add(label);
        label = new JLabel(history);
        panel.add(label);
        JButton button1 = new JButton("Send New Message");
        JButton button2 = new JButton("Send Text File");
        JButton button3 = new JButton("Edit Previous Message");
        JButton button4 = new JButton("Delete Previous Message");
        JButton button5 = new JButton("Export conversation to CSV");
        JButton button6 = new JButton("Exit Conversation");
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        frame.setVisible(true);
        count = 0;
        while (count == 0) {
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 2;
                }
            });
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 3;
                }
            });
            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 4;
                }
            });
            button5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 5;
                }
            });
            button6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 6;
                }
            });
        }
        return count;
    }
    public static String optionOneMessage() {
        String output = " ";
        panel.removeAll();
        label = new JLabel("Type a message to send: ");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        output = text.getText();
        frame.setVisible(true);
        return output;
    }
    public static String optionTwoMessage() {
        String output = " ";
        panel.removeAll();
        label = new JLabel("File Path: ");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        output = text.getText();
        frame.setVisible(true);
        return output;
    }
    public static String optionThreeMessage() {
        String output = " ";
        panel.removeAll();
        label = new JLabel("Text to replace previous message: ");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        output = text.getText();
        frame.setVisible(true);
        return output;
    }
    public static String optionFiveMessage() {
        String output = " ";
        panel.removeAll();
        label = new JLabel("File Path: ");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        output = text.getText();
        frame.setVisible(true);
        return output;
    }
    public static void welcomeMessage() {
        String output = " ";
        panel.removeAll();
        label = new JLabel("Welcome to Stride180! We are a marketplace exclusively for the hottest kicks!");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void commonWordsSeller(String commonOnes) {
        String output = " ";
        panel.removeAll();
        label = new JLabel("Most common words used by sellers:");
        panel.add(label);
        label = new JLabel("commonOnes");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void noCommonWordsSeller() {
        String output = " ";
        panel.removeAll();
        label = new JLabel("The seller has no prior messages");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void commonWordsCustomer(String commonOnes) {
        String output = " ";
        panel.removeAll();
        label = new JLabel("Most common words used by customers:");
        panel.add(label);
        label = new JLabel("commonOnes");
        panel.add(label);
        frame.setVisible(true);
    }
    public static void noCommonWordsCustomer() {
        String output = " ";
        panel.removeAll();
        label = new JLabel("The customer has no prior messages");
        panel.add(label);
        frame.setVisible(true);
    }
    
}



