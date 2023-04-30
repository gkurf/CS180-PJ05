import java.io.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.net.*;
import javax.swing.JOptionPane;

/**
 * This class handles the bulk end of the messaging capabilities between users
 *
 * @author Anish Agrawal, Jack Burns, Sanya Buti, Gabe Kurfman, Shubhangi Mishra
 * @version April 19, 2023
 */
public class Message {

    // defining private fields
    private User customer;
    private User seller;
    private String customerFileName;
    private String sellerFileName;
    private Socket socket = null;
    private OutputStream outputStream = null;
    private PrintWriter writer = null;
    private InputStream inputStream = null;
    private BufferedReader reader = null;

    // setting constructor
    public Message(User user1, User user2) {
        if (user1.getUserType().equals(user2.getUserType())) { // if they are both customers or both sellers
            System.out.println("The sender and receiver cannot be both buyers or both sellers!");
        } else {
            try {
                socket = new Socket("localhost", 1234);
                outputStream = socket.getOutputStream();
                writer = new PrintWriter(outputStream, true);
                InputStream inputStream = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                if (user1.getUserType().equals("customer")) {
                    this.customer = user1;
                    this.seller = user2;
                } else {
                    this.customer = user2;
                    this.seller = user1;
                }

                String sendCustomer = "/write/" + customer.getUsername() + " messages to " + seller.getUsername()
                        + " {-/} ";
                writer.println(sendCustomer);
                String sendSeller = "/write/" + seller.getUsername() + " messages to " + customer.getUsername()
                        + " {-/} ";
                writer.println(sendSeller);
                String sendHistory = "/write/ messageHistory {-/} " + user1.getUsername() + "," + user2.getUsername();
                writer.println(sendHistory);

                this.customerFileName = customer.getUsername() + " messages to " + seller.getUsername();
                this.sellerFileName = seller.getUsername() + " messages to " + customer.getUsername();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not establish connection to server.");
            }
        }
    }

    // this creates a message between the sender and a customer
    public void createMessage(User sender, String message) {
        if (sender != this.customer && sender != this.seller) {
            System.out.println("User is not part of this conversation!");
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String sendCustomerMessage = "/write/" + customerFileName + " {-/} " + sender.getUsername() + ": " + message
                    + " (sent at " + timestamp + ")";
            writer.println(sendCustomerMessage);
            String sendSellerMessage = "/write/" + sellerFileName + " {-/} " + sender.getUsername() + ": " + message
                    + " (sent at " + timestamp + ")";
            writer.println(sendSellerMessage);
        }
    }

    // This method is used for documentation and finds out the timestamp of latest
    // message
    public String getTimeStamp(int messageNumber, User user) {
        String message = "";
        if (user != this.customer && user != this.seller) {
            JOptionPane.showMessageDialog(null, "User is not part of this conversation!");
        } else {
            if (user.getUserType().equals("customers")) {
                try {
                    String[] arrFileText = null;
                    writer.println("/read/" + customerFileName);
                    try {
                        String fileText = reader.readLine();
                        arrFileText = fileText.split("..pkjn..");
                    } catch (NullPointerException e) {
                    } catch (IOException e) {
                    }

                    message = arrFileText[messageNumber + 1];
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    String[] arrFileText = null;
                    writer.println("/read/" + sellerFileName);
                    try {
                        String fileText = reader.readLine();
                        arrFileText = fileText.split("..pkjn..");
                    } catch (NullPointerException e) {
                    } catch (IOException e) {
                    }

                    message = arrFileText[messageNumber + 1];
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return message.substring(message.lastIndexOf(' ') + 1, message.lastIndexOf(')'));
    }

    // this edits a message in the conversation
    public void editMessage(User Editor, String newMessage) {
        File customerFile = new File(customerFileName);
        File sellerFile = new File(sellerFileName);
        if (Editor != this.customer && Editor != this.seller) {
            System.out.println("User is not part of this conversation!");
        } else {
            BufferedReader reader = null;
            String lastMessage = "";
            ArrayList<String> customerMessageInfo = null;
            ArrayList<String> sellerMessageInfo = null;
            String line;
            try {
                if (Editor.getUserType().equals("customer")) {
                    reader = new BufferedReader(new FileReader(customerFile));

                    customerMessageInfo = new ArrayList<String>();
                    line = reader.readLine();
                    while (line != null) {
                        if (line.startsWith(Editor.getUsername())) {
                            lastMessage = line;
                        }
                        customerMessageInfo.add(line);
                        line = reader.readLine();
                    }
                    reader.close();

                    reader = new BufferedReader(new FileReader(sellerFile));

                    sellerMessageInfo = new ArrayList<String>();
                    line = reader.readLine();
                    while (line != null) {
                        sellerMessageInfo.add(line);
                        line = reader.readLine();
                    }
                    reader.close();
                } else {
                    reader = new BufferedReader(new FileReader(sellerFile));

                    sellerMessageInfo = new ArrayList<String>();
                    line = reader.readLine();
                    while (line != null) {
                        if (line.startsWith(Editor.getUsername())) {
                            lastMessage = line;
                        }
                        sellerMessageInfo.add(line);
                        line = reader.readLine();
                    }
                    reader.close();

                    reader = new BufferedReader(new FileReader(customerFile));

                    customerMessageInfo = new ArrayList<String>();
                    line = reader.readLine();
                    while (line != null) {
                        customerMessageInfo.add(line);
                        line = reader.readLine();
                    }
                    reader.close();
                }

                if (lastMessage.equals("")) {
                    System.out.println("No text to edit");
                } else {
                    customerFile.delete();
                    File editedCustomerMessages = new File(customerFileName);
                    editedCustomerMessages.createNewFile();
                    PrintWriter customerWriter = new PrintWriter(new FileOutputStream(editedCustomerMessages, true));

                    for (int i = 0; i < customerMessageInfo.size(); i++) {
                        if (customerMessageInfo.get(i).substring(0, customerMessageInfo.get(i).lastIndexOf('(') -
                                1)
                                .equals(lastMessage.substring(0, customerMessageInfo.get(i).lastIndexOf('(') - 1))) {
                            customerWriter.println(customer.getUsername() + ": " + newMessage +
                                    customerMessageInfo.get(i)
                                            .substring(customerMessageInfo.get(i).lastIndexOf('(') - 1));
                        } else {
                            customerWriter.println(customerMessageInfo.get(i));
                        }
                    }
                    customerWriter.close();

                    sellerFile.delete();
                    File editedSellerMessages = new File(sellerFileName);
                    editedSellerMessages.createNewFile();
                    PrintWriter sellerWriter = new PrintWriter(new FileOutputStream(editedSellerMessages, true));

                    for (int i = 0; i < sellerMessageInfo.size(); i++) {
                        if (sellerMessageInfo.get(i).substring(0, sellerMessageInfo.get(i).lastIndexOf('(') -
                                1).equals(lastMessage.substring(0, sellerMessageInfo.get(i).lastIndexOf('(') - 1))) {
                            sellerWriter.println(customer.getUsername() + ": " + newMessage +
                                    sellerMessageInfo.get(i).substring(sellerMessageInfo.get(i).lastIndexOf('(') - 1));
                        } else {
                            sellerWriter.println(sellerMessageInfo.get(i));
                        }
                    }
                    sellerWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // This method deletes message in the conversation
    public void deleteMessage(User deleter) {
        if (deleter != this.customer && deleter != this.seller) {
            System.out.println("User is not part of this conversation!");
        } else {
            BufferedReader reader = null;
            ArrayList<String> messageList = null;
            String lastMessage = "";
            if (deleter.getUserType().equals("customer")) {

                String send = "/read/" + customerFileName;
                writer.println(send);
                String fileContent = "";
                try {
                    fileContent = reader.readLine();
                } catch (NullPointerException e) {
                } catch (IOException e) {
                }
                // fileContent.replace("null", "");
                String[] fileContentArray = fileContent.split("..pkjm..");
                messageList = new ArrayList<String>();
                for (String x : fileContentArray) {
                    if (x.startsWith(deleter.getUsername())) {
                        lastMessage = x;
                    }
                    messageList.add(x);
                }
                messageList.remove(lastMessage);
                String total = "";
                for (String u : messageList) {
                    total += u + "..pkjm..";
                }
                String newWrite = "/write/" + customerFileName + " ,mv.. " + total;
                writer.println(newWrite);

            } else {
                File sellerFile = new File(sellerFileName);
                try {
                    reader = new BufferedReader(new FileReader(sellerFileName));

                    messageList = new ArrayList<String>();
                    String line = reader.readLine();
                    while (line != null) {
                        if (line.startsWith(deleter.getUsername())) {
                            lastMessage = line;
                        }
                        messageList.add(line);
                        line = reader.readLine();
                    }
                    for (int i = 0; i < messageList.size(); i++) {
                        if (lastMessage.equals(messageList.get(i))) {
                            messageList.remove(i);
                        }
                    }
                    reader.close();

                    sellerFile.delete();
                    File newSellerFile = new File(sellerFileName);
                    newSellerFile.createNewFile();
                    PrintWriter pw = new PrintWriter(new FileOutputStream(sellerFileName, true));
                    for (String message : messageList) {
                        pw.println(message);
                    }
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // exports the given message history as a csv file
    public void exportToCSV(User user, String filepath) {
        try {
            FileWriter csvWriter = new FileWriter(filepath);

            // Write the header row
            csvWriter.append("Timestamp,Recipient,Sender,Content\n");

            // Write each message as a row in the CSV
            for (Message message : user.getMessages(user)) {
                csvWriter.append(message.getParticipants() + ",");
                csvWriter.append(message.getCustomer() + ",");
                csvWriter.append(message.getSeller() + ",");
            }

            csvWriter.flush();
            csvWriter.close();
            System.out.println("CSV file successfully exported!");
        } catch (IOException e) {
            System.out.println("Error exporting CSV file.");
            e.printStackTrace();
        }
    }

    // converts the inputted text file into a message history
    public void importFromTXT(User sender, String filepath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;

            while ((line = reader.readLine()) != null) {
                createMessage(sender, line);
            }

            reader.close();
            System.out.println("Text file successfully imported!");
        } catch (IOException e) {
            System.out.println("Error importing text file.");
            e.printStackTrace();
        }
    }

    // finds participants in the conversation
    public String getParticipants() {
        return "Customer " + this.customer.getUsername() + " and Seller " + this.seller.getUsername();
    }

    // gettings customers
    public ArrayList<String> getCustomer() {
        ArrayList<String> customerMessages = new ArrayList<String>();
        try {
            BufferedReader customerReader = new BufferedReader(new FileReader(customerFileName));
            String line = customerReader.readLine();
            customerMessages.add(customer.getUsername());
            while (line != null) {
                customerMessages.add(line);
                line = customerReader.readLine();
            }
            customerReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("This conversation does not exist.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerMessages;
    }

    // getting sellers
    public ArrayList<String> getSeller() {
        ArrayList<String> sellerMessages = new ArrayList<String>();
        try {
            BufferedReader sellerReader = new BufferedReader(new FileReader(sellerFileName));
            String line = sellerReader.readLine();
            sellerMessages.add(seller.getUsername());
            while (line != null) {
                sellerMessages.add(line);
                line = sellerReader.readLine();
            }
            sellerReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("This conversation does not exist.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sellerMessages;
    }

    @Override
    public String toString() {
        ArrayList<String> customerMessages;
        ArrayList<String> sellerMessages;
        String conversation;
        try {
            BufferedReader customerReader = new BufferedReader(new FileReader(customerFileName));
            customerMessages = new ArrayList<String>();
            String line = customerReader.readLine();
            while (line != null) {
                customerMessages.add(line);
                line = customerReader.readLine();
            }
            customerReader.close();

            BufferedReader sellerReader = new BufferedReader(new FileReader(sellerFileName));
            sellerMessages = new ArrayList<String>();
            line = sellerReader.readLine();
            while (line != null) {
                sellerMessages.add(line);
                line = sellerReader.readLine();
            }
            sellerReader.close();

            conversation = "Customer:";
            for (String customerMessage : customerMessages) {
                conversation += "\n" + customerMessage;
            }
            conversation += "\nSeller:";
            for (String sellerMessage : sellerMessages) {
                conversation += "\n" + sellerMessage;
            }

            return conversation;
        } catch (FileNotFoundException e) {
            System.out.println("This conversation does not exist.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "An error occurred";
    }

    public String messageString(User user) {
        String conversation = "";
        String line;

        try {
            if (user.equals(customer)) {
                BufferedReader customerReader = new BufferedReader(new FileReader(customerFileName));
                line = customerReader.readLine();
                while (line != null) {
                    conversation += line + "\n";
                    line = customerReader.readLine();
                }
                customerReader.close();
            } else if (user.equals(seller)) {
                BufferedReader sellerReader = new BufferedReader(new FileReader(customerFileName));
                line = sellerReader.readLine();
                while (line != null) {
                    conversation += line + "\n";
                    line = sellerReader.readLine();
                }
                sellerReader.close();
            } else {
                System.out.println("Well this is awkward, the code should never get here.");
            }

            return conversation;
        } catch (FileNotFoundException e) {
            System.out.println("This conversation does not exist.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "An error occurred";
    }
}