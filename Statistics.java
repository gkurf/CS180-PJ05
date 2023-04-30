import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.IOException;
import java.net.*;
import java.io.*;
import javax.swing.*;
/**
 * This class allows for Statistics of relevent data to be displayed on request by users
 *
 * @author Anish Agrawal, Jack Burns, Sanya Buti, Gabe Kurfman, Shubhangi Mishra
 * @version April 19, 2023
 */
public class Statistics {

    private List<Message> customers;
    private List<Message> sellers;
    private List<String> commonWords;
    private Socket socket = null;
    private OutputStream outputStream = null;
    private PrintWriter writer = null;
    private BufferedReader reader = null;
    
    public Statistics() {
         try{
            socket = new Socket("localhost", 1234);
            outputStream = socket.getOutputStream();
            writer = new PrintWriter(outputStream, true);
            customers = new ArrayList<>();
            sellers=new ArrayList<>();
            commonWords = new ArrayList<>();
         } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not establish connection to server." +
                                        "\nConsult the Stride180 Readme for more details.");
            System.exit(0);
        }
    }
    private String sendMessage(String message) {
        String response = "";
        try {
            writer.println(message);
            response = reader.readLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error sending message to server.");
        }
        return response;
    }

    public void addCustomer(Message customer) {
        customers.add(customer);
    }
    public void addseller(Message seller){
        sellers.add(seller);
    }

    public void sortCustomers(Comparator<Message> comparator) {
        Collections.sort(customers, comparator);
    }
    public void sortSellers(Comparator<Message> comparator){
        Collections.sort(sellers, comparator);
    }

    public List<Message> getCustomers() {
        return customers;
    }
    public List<Message> getSellers(){
        return sellers;
    }
    // get most common words in messages used by the Customer
    public List<String> getCustomerCommonWords() {
        List<String> allWords = new ArrayList<>();
        for (Message customer : customers) {

            for (String message : customer.getCustomer()) {
                String[] words = message.split("\\s+");
                for (String word : words) {
                    if (!word.equalsIgnoreCase("the seller") || !word.equalsIgnoreCase("the customer") || !word.contains("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                    allWords.add(word.toLowerCase());
                }
                }
            }
        }

        commonWords.clear();
        while (commonWords.size() < 10 && !allWords.isEmpty()) {
            String mostCommonWord = null;
            int mostCommonCount = 0;
            for (String word : allWords) {
                int count = Collections.frequency(allWords, word);
                if (count > mostCommonCount) {
                    mostCommonWord = word;
                    mostCommonCount = count;
                }
            }
            if (mostCommonWord != null) {
                commonWords.add(mostCommonWord);
                allWords.removeAll(Collections.singleton(mostCommonWord));
            }
        }

        return commonWords;
    }
    // get most common words in messages used by the seller
    public List<String> getSellerCommonWords() {
        List<String> allWords = new ArrayList<>();
        for (Message seller : sellers) {

            for (String message : seller.getSeller()) {
                String[] words = message.split("\\s+");
                for (String word : words) {
                    if (!word.equalsIgnoreCase("seller") || !word.equalsIgnoreCase("customer") ||!word.equalsIgnoreCase("the")|| !word.contains("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))  {
                    allWords.add(word.toLowerCase());
                }
                    
                }
            }
        }

        commonWords.clear();
        while (commonWords.size() < 10 && !allWords.isEmpty()) {
            String mostCommonWord = null;
            int mostCommonCount = 0;
            for (String word : allWords) {
                int count = Collections.frequency(allWords, word);
                if (count > mostCommonCount) {
                    mostCommonWord = word;
                    mostCommonCount = count;
                }
            }
            if (mostCommonWord != null) {
                commonWords.add(mostCommonWord);
                allWords.removeAll(Collections.singleton(mostCommonWord));
            }
        }

        return commonWords;
    }

}
