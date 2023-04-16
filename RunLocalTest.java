import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.*;
import static org.junit.Assert.*;

/**
 * A framework to run public test cases.
 *
 * @author Anish Agrawal, Jack Burns, Sanya Buti, Gabe Kurfman, Shubhangi Mishra
 * @version April 9, 2023
 */
public class RunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        System.out.printf("Test Count: %d.\n", result.getRunCount());
        if (result.wasSuccessful()) {
            System.out.println("Excellent - all local tests ran successfully.");
        } else {
            System.out.printf("Tests failed: %d.\n", result.getFailureCount());
            for (Failure failure: result.getFailures()) {
                System.out.println(failure.getMessage());
                System.out.println(failure.getTestHeader());
                System.out.println(failure.getDescription());
                System.out.println(failure);
            }
        }
    }

    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

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
        private static String MESSAGE_OPTIONS = "1. Send New Message\n2. Send Text File\n3. Edit Previous Message\n4. Export Conversation to CSV";

        @Test(timeout = 1000)
        public void testExit() {
            String input = "3" + System.lineSeparator();

            // Pair the input with the expected result
            String expected = WELCOME_PROMPT + System.lineSeparator() +
                OPTION_PROMPT + System.lineSeparator() +
                LOGIN_OPTIONS;

            // Runs the program with the input values
            receiveInput(input);
            Stride180.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Error with exiting program!",
                expected.trim(), output.trim());
        }

        @Test(timeout = 1000)
        public void testCustomerCreation() throws FileNotFoundException {
            String input = "2" + System.lineSeparator() +
                "Customer1" + System.lineSeparator() +
                "Password123" + System.lineSeparator() +
                "Password123" + System.lineSeparator() +
                "customer" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "6" + System.lineSeparator() +
                "y" + System.lineSeparator() +
                "3" + System.lineSeparator();

            // Pair the input with the expected result
            String expected = WELCOME_PROMPT + System.lineSeparator() +
                OPTION_PROMPT + System.lineSeparator() +
                LOGIN_OPTIONS + System.lineSeparator() + System.lineSeparator() +
                "NEW USER" + System.lineSeparator() +
                "Enter a Username: " +
                "Enter a Password: " +
                "Confirm Password: " +
                "Enter a user type (\"seller\"/\"customer\"): " +
                "Welcome Customer Customer1!" + System.lineSeparator() +
                OPTION_PROMPT + System.lineSeparator() +
                CUSTOMER_OPTIONS + System.lineSeparator() +
                OPTION_PROMPT + System.lineSeparator() +
                CUSTOMER_SETTINGS + System.lineSeparator() +
                System.lineSeparator() +
                "DELETE ACCOUNT" + System.lineSeparator() +
                "Are you sure you want to delete your account? (Y/N)" +
                OPTION_PROMPT + System.lineSeparator() +
                LOGIN_OPTIONS + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            Stride180.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Error with creating a customer account!",
                expected.trim(), output.trim());
        }

        @Test(timeout = 1000)
        public void testSellerCreation() throws FileNotFoundException {
            String input = "2" + System.lineSeparator() +
                "Seller1" + System.lineSeparator() +
                "Password123" + System.lineSeparator() +
                "Password123" + System.lineSeparator() +
                "seller" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "Store 1" + System.lineSeparator() +
                "Store 2" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "6" + System.lineSeparator() +
                "y" + System.lineSeparator() +
                "3" + System.lineSeparator();

            // Pair the input with the expected result
            String expected = WELCOME_PROMPT + System.lineSeparator() +
                OPTION_PROMPT + System.lineSeparator() +
                LOGIN_OPTIONS + System.lineSeparator() + System.lineSeparator() +
                "NEW USER" + System.lineSeparator() +
                "Enter a Username: " +
                "Enter a Password: " +
                "Confirm Password: " +
                "Enter a user type (\"seller\"/\"customer\"): " +
                "How many stores would you like to own?" + System.lineSeparator() +
                "What is the name of store #1?" + System.lineSeparator() +
                "What is the name of store #2?" + System.lineSeparator() +
                "Welcome Seller Seller1!" + System.lineSeparator() +
                OPTION_PROMPT + System.lineSeparator() +
                SELLER_OPTIONS + System.lineSeparator() +
                OPTION_PROMPT + System.lineSeparator() +
                SELLER_SETTINGS + System.lineSeparator() +
                System.lineSeparator() +
                "DELETE ACCOUNT" + System.lineSeparator() +
                "Are you sure you want to delete your account? (Y/N)" +
                OPTION_PROMPT + System.lineSeparator() +
                LOGIN_OPTIONS + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            Stride180.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            PrintWriter out = new PrintWriter("filename.txt");
            out.println(expected);
            out.println(output);
            out.close();
            assertEquals("Error with creating a customer account!",
                expected.trim(), output.trim());
        }

        // @Test(timeout = 1000)
        public void testOtherStuff() throws FileNotFoundException {
            String input = "2" + System.lineSeparator() +
                "Seller1" + System.lineSeparator() +
                "Password123" + System.lineSeparator() +
                "Password123" + System.lineSeparator() +
                "seller" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "Store 1" + System.lineSeparator() +
                "Store 2" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "6" + System.lineSeparator() +
                "y" + System.lineSeparator() +
                "3" + System.lineSeparator();

            // Pair the input with the expected result
            String expected = WELCOME_PROMPT + System.lineSeparator() +
                OPTION_PROMPT + System.lineSeparator() +
                LOGIN_OPTIONS + System.lineSeparator() + System.lineSeparator() +
                "NEW USER" + System.lineSeparator() +
                "Enter a Username: " +
                "Enter a Password: " +
                "Confirm Password: " +
                "Enter a user type (\"seller\"/\"customer\"): " +
                "How many stores would you like to own?" + System.lineSeparator() +
                "What is the name of the store?" + System.lineSeparator() +
                "What is the name of the store?" + System.lineSeparator() +
                "Welcome Seller Seller1!" + System.lineSeparator() +
                OPTION_PROMPT + System.lineSeparator() +
                SELLER_OPTIONS + System.lineSeparator() +
                OPTION_PROMPT + System.lineSeparator() +
                SELLER_SETTINGS + System.lineSeparator() +
                System.lineSeparator() +
                "DELETE ACCOUNT" + System.lineSeparator() +
                "Are you sure you want to delete your account? (Y/N)" +
                OPTION_PROMPT + System.lineSeparator() +
                LOGIN_OPTIONS + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            Stride180.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Error with creating a customer account!",
                expected.trim(), output.trim());
        }
    }
}