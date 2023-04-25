import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		JOptionPane.showMessageDialog(null, "Welcome to the Mini Project 5 Workspace!");
		String host = JOptionPane.showInputDialog("Enter the host name:");

		// Use port number 1111
		String portStr = JOptionPane.showInputDialog("Enter the port number:");
		int port = 12345;
		try {
			port = Integer.parseInt(portStr);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid port number.");
		}
		try (Socket socket = new Socket(host, port)) {

			boolean searching = true;

			JOptionPane.showMessageDialog(null, "Connection established.");
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(outputStream, true);
			InputStream inputStream = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			do {
				String userText = JOptionPane.showInputDialog("Enter your text you want to append:");

				writer.println(userText);
				writer.flush();
				String completed = reader.readLine();
				JOptionPane.showMessageDialog(null, completed);

				int response = JOptionPane.showConfirmDialog(null, "Do you want to continue to use this workspace?", "YA?",
						JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.NO_OPTION) {
					searching = false;
					writer.println("no");
					writer.flush();

				} else {
					writer.println("yes");
					writer.flush();
				}

			} while (searching);
			// Farewell message
			JOptionPane.showMessageDialog(null, "Thank you for using the search engine!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not establish connection to server.");
		}

	}
}
