import java.io.*;
import java.text.*;
import java.util.*;

public class MessageSorter {
    static String START_DATE = "(sent at ";
    static String END_DATE = ")";
    static String FILEPATH = "agraw messages to thisIsATest";
    public static void main(String[] args) throws IOException {
        // Open the file for reading
        BufferedReader reader = new BufferedReader(new FileReader(FILEPATH));

        // Create a SimpleDateFormat object to parse the timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        // Create a list to hold the lines from the file
        List<String> lines = new ArrayList<String>();

        // Read the lines from the file and add them to the list
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        // Close the file
        reader.close();

        // Sort the lines by date using a Comparator
        Collections.sort(lines, new Comparator<String>() {
            public int compare(String line1, String line2) {
                try {
                    // Parse the timestamps from the lines
                    Date date1 = dateFormat.parse(line1.substring(line1.lastIndexOf(START_DATE) + START_DATE.length(), line1.lastIndexOf(END_DATE)));
                    Date date2 = dateFormat.parse(line2.substring(line2.lastIndexOf(START_DATE) + START_DATE.length(), line2.lastIndexOf(END_DATE)));

                    // Compare the timestamps and return the result
                    return date1.compareTo(date2);
                } catch (ParseException e) {
                    System.err.println("Error parsing timestamp in line: " + line1 + "\n");
                    throw new IllegalArgumentException(e);
                }
            }
        });

        // Open the file for writing
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH));

        // Write the sorted lines to the file
        for (String sortedLine : lines) {
            writer.write(sortedLine);
            writer.newLine();
        }

        // Close the file
        writer.close();
    }
}