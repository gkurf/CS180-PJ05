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
        panel.removeAll();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(0, 1));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Welcome!");
        
        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Welcome to Stride 180!", SwingConstants.CENTER);
        JButton button1 = new JButton("Login");
        JButton button2 = new JButton("Create a new account");
        JButton button3 = new JButton("Exit");

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

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

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

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

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

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }
        
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

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

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

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

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

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

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

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

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

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Choose a messaging option:");
        JButton button1 = new JButton("Search by Seller");
        JButton button2 = new JButton("View a list of Stores");
        JButton button3 = new JButton("Go to Main Customer Menu");

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

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Account Options:");
        JButton button1 = new JButton("View Statistics");
        JButton button2 = new JButton("Block a user");
        JButton button3 = new JButton("Become invisible");
        JButton button4 = new JButton("Change Username");
        JButton button5 = new JButton("Change Password");
        JButton button6 = new JButton("Delete Account");
        JButton button7 = new JButton("Go to Main Customer Menu");

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

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("What would you like to do?");
        JButton button1 = new JButton("Message a Customer");
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

    public int sellerMessageOptions() {
        panel.removeAll();
        frame.setTitle("Message Options");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Choose a messaging option:");
        JButton button1 = new JButton("Search by Customer");
        JButton button2 = new JButton("View a list of Customer ");
        JButton button3 = new JButton("Go to main seller menu");

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

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }
        
        label = new JLabel("Account Options:");
        JButton button1 = new JButton("View Store Statistics");
        JButton button2 = new JButton("Block a user");
        JButton button3 = new JButton("Become invisible");
        JButton button4 = new JButton("Change Username");
        JButton button5 = new JButton("Change Password");
        JButton button6 = new JButton("Delete Account");
        JButton button7 = new JButton("Go to Main Seller Menu");

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

    public String changeUserName() {
        frame.setTitle("Change Username");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        panel.removeAll();
        label = new JLabel("Enter your new username");
        panel.add(label);
        text = new JTextField();
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

    public String changePassword() {
        frame.setTitle("Change password");
        
        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        panel.removeAll();
        label = new JLabel("Enter your new password");
        panel.add(label);
        text = new JTextField();
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
    public int deleteAccount() {
        panel.removeAll();
        frame.setTitle("Delete account");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Are you sure you want to delete your account?");
        JButton button1 = new JButton("Yes");
        JButton button2 = new JButton("No");

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

    public void usersOutput(String output) {
        panel.removeAll();
        frame.setTitle("");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel(output);
        panel.add(label);
        frame.setVisible(true);
    }

    public String blockUser() {
        panel.removeAll();
        frame.setTitle("Block User");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Who do you want to block?");
        panel.add(label);
        text = new JTextField();
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

    public String invisUsername() {
        panel.removeAll();
        frame.setTitle("Invisible User");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Enter Username to become invisible to: ");
        panel.add(label);
        text = new JTextField();
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

    public int storesAmount() {
        panel.removeAll();
        frame.setTitle("Create Stores");
        
        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("How many stores do you want to add? ");
        panel.add(label);
        text = new JTextField();
        panel.add(text);
        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, //initial value
            1, //min
            100, //max
            1);//step
        JSpinner spinner = new JSpinner(spinnerModel);
        JButton enter = new JButton("Enter Integer");
        panel.add(spinner);
        panel.add(enter);
        frame.pack();
        frame.setVisible(true);

        count = 0;
        while (count == 0) {
            enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
        }

        return (int)spinner.getValue();
    }

    public String registerStore(int amount) {
        panel.removeAll();
        frame.setTitle("Search a store");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Enter the name of store number:" + " " + amount);
        panel.add(label);
        text = new JTextField();
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

    public String messageCustomer() {
        panel.removeAll();
        frame.setTitle("Message");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Enter the username of the customer you want to message");
        panel.add(label);
        text = new JTextField();
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

    public String messageSeller() {
        panel.removeAll();
        frame.setTitle("Message");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Enter the username of the seller you want to message");
        panel.add(label);
        text = new JTextField();
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

    public int numberCustomers(int customers, String dataCustomers) {
        panel.removeAll();
        frame.setTitle("Search Customers");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("There are " + customers + " customers");
        panel.add(label);
        label = new JLabel(dataCustomers);
        label = new JLabel("Which Customer Number would you like to message?");
        text = new JTextField();
        panel.add(text);
        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, //initial value
            1, //min
            100, //max
            1);//step
        JSpinner spinner = new JSpinner(spinnerModel);
        JButton enter = new JButton("Enter Integer");
        panel.add(spinner);
        panel.add(enter);
        frame.pack();
        frame.setVisible(true);

        count = 0;
        while (count == 0) {
            enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
        }

        return (int)spinner.getValue();
    }

    public int numberStore(int stores, String dataStores) {
        panel.removeAll();
        frame.setTitle("Search Stores");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("There are " + stores + " stores");
        panel.add(label);
        label = new JLabel(dataStores);
        panel.add(label);
        label = new JLabel("Which Store Number would you like to message?");
        panel.add(label);
        text = new JTextField();
        panel.add(text);

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, //initial value
            1, //min
            100, //max
            1);//step
        JSpinner spinner = new JSpinner(spinnerModel);
        JButton enter = new JButton("Enter Integer");
        panel.add(spinner);
        panel.add(enter);
        frame.pack();
        frame.setVisible(true);

        count = 0;
        while (count == 0) {
            enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = 1;
                }
            });
        }

        return (int)spinner.getValue();
    }

    public int sendMessageOption(String reciever, String history) {
        panel.removeAll();
        frame.setTitle("Send Message");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

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
        }
        return count;
    }
    
    public String optionOneMessage() {
        panel.removeAll();
        frame.setTitle("Send message");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Type a message to send: ");
        panel.add(label);
        text = new JTextField();
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

    public String optionTwoMessage() {
        panel.removeAll();
        frame.setTitle("Send text file");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("File Path: ");
        panel.add(label);
        text = new JTextField();
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

    public String optionThreeMessage() {
        panel.removeAll();
        frame.setTitle("Edit message");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Text to replace previous message: ");
        panel.add(label);
        text = new JTextField();
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

    public String optionFiveMessage() {
        panel.removeAll();
        frame.setTitle("Export to CSV");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("File Path: ");
        panel.add(label);
        text = new JTextField();
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

    public void commonWordsSeller(String commonOnes) {
        panel.removeAll();
        frame.setTitle("Statistics");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Most common words used by sellers:");
        panel.add(label);
        label = new JLabel("commonOnes");
        panel.add(label);
        frame.setVisible(true);
    }

    public void commonWordsCustomer(String commonOnes) {
        panel.removeAll();
        frame.setTitle("Statistics");

        if (!message.equals("")) {
            JLabel m = new JLabel(message);
            panel.add(m);
        }

        label = new JLabel("Most common words used by customers:");
        panel.add(label);
        label = new JLabel("commonOnes");
        panel.add(label);
        frame.setVisible(true);
    }
}