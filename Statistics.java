import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * This class allows for Statistics of relevent data to be displayed on request by users
 *
 * @author Anish Agrawal, Jack Burns, Sanya Buti, Gabe Kurfman, Shubhangi Mishra
 * @version April 9, 2023
 */
public class Statistics {

    private List<Message> customers;
    private List<Message> sellers;
    private List<String> commonWords;

    public Statistics() {
        customers = new ArrayList<>();
        sellers=new ArrayList<>();
        commonWords = new ArrayList<>();
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
                    if (word.equalsIgnoreCase("the seller") || word.equalsIgnoreCase("the customer")) {
                    continue;
                }
                    allWords.add(word.toLowerCase());
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
                    if (word.equalsIgnoreCase("seller") || word.equalsIgnoreCase("customer")) {
                    continue;
                }
                    allWords.add(word.toLowerCase());
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
