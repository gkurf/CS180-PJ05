import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
public class GUI implements ActionListener {
    private static int count;
    private static String textInput;
    private static JLabel label;
    private JFrame frame;
    private JPanel panel;
    private static JTextField text;
    public String message;

    public GUI() {
        this.frame = new JFrame();
        this.panel = new JPanel();
        this.message = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public int mainEntry() {
        frame = new JFrame();
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(0, 1));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Welcome!");
        
        JLabel m = new JLabel(message);
        label = new JLabel("Welcome to Stride 180!", SwingConstants.CENTER);
        JButton button1 = new JButton("Login");
        JButton button2 = new JButton("Create a new account");
        JButton button3 = new JButton("Exit");

        panel.add(m);
        panel.add(label);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        frame.add(panel);
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

    public int typeofAccount() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("What type of account do you want to create?", SwingConstants.CENTER);
        JButton button1 = new JButton("Customer Account");
        JButton button2 = new JButton("Seller Account");

        panel.add(label);
        panel.add(button1);
        panel.add(button2);
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
        }
        return count;
    }
    
    public String CreateAccountUsername() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Enter your account username", SwingConstants.CENTER);
        text = new JTextField();

        panel.add(label);
        panel.add(text);
        frame.pack();
        frame.setVisible(true);

        textInput = "";
        while (textInput.equals("")) {
            text.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textInput = text.getText();
                }
            });
        }
        
        return textInput;
    }

    public String CreateAccountPassword() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Enter your account password", SwingConstants.CENTER);
        text = new JTextField();

        panel.add(label);
        panel.add(text);
        frame.pack();
        frame.setVisible(true);

        textInput = "";
        while (textInput.equals("")) {
            text.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textInput = text.getText();
                }
            });
        }
        
        return textInput;
    }

    public String confirmPassword() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Re-type your password", SwingConstants.CENTER);
        text = new JTextField();

        panel.add(label);
        panel.add(text);
        frame.pack();
        frame.setVisible(true);

        textInput = "";
        while (textInput.equals("")) {
            text.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textInput = text.getText();
                }
            });
        }
        
        return textInput;
    }

    public String logInUsername() {
        panel.removeAll();
        frame.setTitle("Login to Account");

        label = new JLabel("Enter your account username", SwingConstants.CENTER);
        text = new JTextField();

        panel.add(label);
        panel.add(text);
        frame.pack();
        frame.setVisible(true);

        textInput = "";
        while (textInput.equals("")) {
            text.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textInput = text.getText();
                }
            });
        }
        
        return textInput;
    }

    public String logInPassword() {
        panel.removeAll();
        frame.setTitle("Login to Account");

        label = new JLabel("Enter your account password", SwingConstants.CENTER);
        text = new JTextField();

        panel.add(label);
        panel.add(text);
        frame.pack();
        frame.setVisible(true);

        textInput = "";
        while (textInput.equals("")) {
            text.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textInput = text.getText();
                }
            });
        }
        
        return textInput;
    }

    public int customerOptions() {
        panel.removeAll();
        frame.setTitle("Customer Options");

        label = new JLabel("What would you like to do?");
        JButton button1 = new JButton("Message a Seller");
        JButton button2 = new JButton("Account Settings");
        JButton button3 = new JButton("Log out");

        panel.add(label);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        
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

    public int customerMessageOptions() {
        panel.removeAll();
        frame.setTitle("Message Options");

        JLabel m = new JLabel(message);
        label = new JLabel("Choose a messaging option:");
        JButton button1 = new JButton("Search by Seller");
        JButton button2 = new JButton("View a list of Stores");
        JButton button3 = new JButton("Go to Main Customer Menu");

        panel.add(m);
        panel.add(label);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

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
    
    public int customerAccountOptions() {
        panel.removeAll();
        frame.setTitle("Account Options");

        JLabel m = new JLabel(message);
        label = new JLabel("Account Options:");
        JButton button1 = new JButton("View Statistics");
        JButton button2 = new JButton("Block a user");
        JButton button3 = new JButton("Become invisible");
        JButton button4 = new JButton("Change Username");
        JButton button5 = new JButton("Change Password");
        JButton button6 = new JButton("Delete Account");
        JButton button7 = new JButton("Go to Main Customer Menu");

        panel.add(m);
        panel.add(label);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);

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
    
    public int SellerOptions() {
        panel.removeAll();
        frame.setTitle("Seller Options");

        JLabel m = new JLabel(message);
        label = new JLabel("What would you like to do?");
        JButton button1 = new JButton("Message a Customer");
        JButton button2 = new JButton("Account Settings");
        JButton button3 = new JButton("Log out");

        panel.add(m);
        panel.add(label);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

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

    public int sellerMessageOptions() {
        panel.removeAll();
        frame.setTitle("Message Options");

        JLabel m = new JLabel(message);
        label = new JLabel("Choose a messaging option:");
        JButton button1 = new JButton("Search by Customer");
        JButton button2 = new JButton("View a list of Customer ");
        JButton button3 = new JButton("Go to main seller menu");

        panel.add(m);
        panel.add(label);
        panel.add(button1);
        panel.add(button2);

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
    public int sellerAccountOptions() {
        panel.removeAll();
        frame.setTitle("Account Options");

        JLabel m = new JLabel(message);
        label = new JLabel("Account Options:");
        JButton button1 = new JButton("View Store Statistics");
        JButton button2 = new JButton("Block a user");
        JButton button3 = new JButton("Become invisible");
        JButton button4 = new JButton("Change Username");
        JButton button5 = new JButton("Change Password");
        JButton button6 = new JButton("Delete Account");
        JButton button7 = new JButton("Go to Main Seller Menu");

        panel.add(m);
        panel.add(label);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);

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

    public void NotPartofConvo() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("User is not part of this conversation!");
        panel.add(label);
        frame.setVisible(true);
    }
    public void NoTextToEdit() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("No Text to Edit");
        panel.add(label);
        frame.setVisible(true);
    }
    public void NoMessageHistory() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("No Message History");
        panel.add(label);
        frame.setVisible(true);
    }
    public void WelcomeUser(String cap, String Username) {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Welcome " + cap + " " + Username + "!");
        panel.add(label);
        frame.setVisible(true);
    }
    public void FileError() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel(" [ERROR] File read error.");
        panel.add(label);
        frame.setVisible(true);
    }
    public void invalidUsername() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Invalid username! Please try again.");
        panel.add(label);
        frame.setVisible(true);
    }
    public void invalidPassword() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Invalid password! Please try again.");
        panel.add(label);
        frame.setVisible(true);
    }
    public void userNameTaken() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("That username is taken");
        panel.add(label);
        frame.setVisible(true);
    }
    public void userNameInvalid() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Invalid characters in username! Please try again.");
        panel.add(label);
        frame.setVisible(true);
    }
    public void passwordMisMatch() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel(" Passwords do not match! Please try again.");
        panel.add(label);
        frame.setVisible(true);
    }
    public void invalidPasswordCharacters() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Invalid characters in password! Please try again.");
        panel.add(label);
        frame.setVisible(true);
    }
    public String changeUserName() {
        frame.setTitle("Create an Account");

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

    public String changePassword() {
        frame.setTitle("Create an Account");

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
    public int deleteAccount() {
        panel.removeAll();
        frame.setTitle("Create an Account");

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

    public void deletionCancelled() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Okay! Deletion Cancelled");
        panel.add(label);
        frame.setVisible(true);
    }

    public void usersOutput(String output) {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel(output);
        panel.add(label);
        frame.setVisible(true);
    }

    public String blockUser() {
        String Username = " ";
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Who do you want to block?");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        Username = text.getText();
        frame.setVisible(true);
        return Username;
    }
    public void errorBlocked() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("That username does not exist and cannot be blocked!");
        panel.add(label);
        frame.setVisible(true);
    }
    public String invisUsername() {
        String Username = " ";
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Enter Username to become invisible to: ");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        Username = text.getText();
        frame.setVisible(true);
        return Username;
    }

    public void errorInvis() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("That username does not exist");
        panel.add(label);
        frame.setVisible(true);
    }
    public int storesAmount() {
        int stores = 0;
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("How many stores do you want to add? ");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        stores = Integer.parseInt(text.getText());
        frame.setVisible(true);
        return stores;
    }
    public void errorStoreAmt() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Error enter a number greater than 0 ");
        panel.add(label);
        frame.setVisible(true);
    }
    public String registerStore(int amount) {
        String name = " ";
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Enter the name of store number:" + " " + amount);
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        name = text.getText();
        frame.setVisible(true);
        return name;
    }
    public void invalidStoreName() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel(" Invalid characters in store name, please try again.");
        panel.add(label);
        frame.setVisible(true);
    }

    public String messageCustomer() {
        String name = " ";
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Enter the username of the customer you want to message");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        name = text.getText();
        frame.setVisible(true);
        return name;
    }
    public void invalidCustomer() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("That customer does not exist");
        panel.add(label);
        frame.setVisible(true);
    }

    public String messageSeller() {
        String name = " ";
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Enter the username of the seller you want to message");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        name = text.getText();
        frame.setVisible(true);
        return name;
    }
    public void invalidSeller() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("That seller does not exist");
        panel.add(label);
        frame.setVisible(true);
    }
    public void storeNotFound() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("There are currently no registered stores");
        panel.add(label);
        frame.setVisible(true);
    }
    public int numberCustomers(int customers, String dataCustomers, String message) {
       int customerNumber = 0;
        panel.removeAll();
        frame.setTitle("Search Customers");

        JLabel m = new JLabel(message);
        panel.add(m);
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
    public void invalidChoice() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("That is an invalid option. Please try again");
        panel.add(label);
        frame.setVisible(true);
    }
    public int numberStore(int stores, String dataStores, String message) {
        int storeNumber = 0;
        panel.removeAll();
        frame.setTitle("Search Stores");

        JLabel m = new JLabel(message);
        panel.add(m);
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
    public int sendMessageOption(String reciever, String history) {
        panel.removeAll();
        frame.setTitle("Send Message");

        JLabel m = new JLabel(message);
        panel.add(m);
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
    public String optionOneMessage() {
        String output = " ";
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Type a message to send: ");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        output = text.getText();
        frame.setVisible(true);
        return output;
    }
    public String optionTwoMessage() {
        String output = " ";
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("File Path: ");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        output = text.getText();
        frame.setVisible(true);
        return output;
    }
    public String optionThreeMessage() {
        String output = " ";
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Text to replace previous message: ");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        output = text.getText();
        frame.setVisible(true);
        return output;
    }
    public String optionFiveMessage() {
        String output = " ";
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("File Path: ");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        output = text.getText();
        frame.setVisible(true);
        return output;
    }
    /*
    public void welcomeMessage() {
        panel.removeAll();
        label = new JLabel("Welcome to Stride180! We are a marketplace exclusively for the hottest kicks!");
        panel.add(label);
        frame.setVisible(true);
    }  */
    public void commonWordsSeller(String commonOnes) {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Most common words used by sellers:");
        panel.add(label);
        label = new JLabel("commonOnes");
        panel.add(label);
        frame.setVisible(true);
    }
    public void noCommonWordsSeller() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("The seller has no prior messages");
        panel.add(label);
        frame.setVisible(true);
    }
    public void commonWordsCustomer(String commonOnes) {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("Most common words used by customers:");
        panel.add(label);
        label = new JLabel("commonOnes");
        panel.add(label);
        frame.setVisible(true);
    }
    public void noCommonWordsCustomer() {
        panel.removeAll();
        frame.setTitle("Create an Account");

        label = new JLabel("The customer has no prior messages");
        panel.add(label);
        frame.setVisible(true);
    }
    
}