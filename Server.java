import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// Server class
class Server {
	public static void main(String[] args) {
		ServerSocket server = null;

		try {

			// server is listening on port 1234
			server = new ServerSocket(1234);
			server.setReuseAddress(true);

			// running infinite loop for getting
			// client request
			while (true) {

				// socket object to receive incoming client
				// requests
				Socket client = server.accept();

				// Displaying that new client is connected
				// to server
				System.out.println("New client connected" + client.getInetAddress().getHostAddress());

				// create a new thread object
				ClientHandler clientSock = new ClientHandler(client);

				// This thread will handle the client
				// separately
				new Thread(clientSock).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ClientHandler class
	private static class ClientHandler implements Runnable {
		private final Socket clientSocket;

		// Constructor
		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		public void run() {
			while(true){
			PrintWriter out = null;
			BufferedReader in = null;
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				boolean stillGoing = false;
				String message = reader.readLine();
				if (message.startsWith("/write/")) {
					message = message.substring(7);
					String[] arr = message.split(" {-/} ");
					BufferedWriter fileWriter = new BufferedWriter(new FileWriter(arr[0], true));
					fileWriter.write(arr[1]);
				}
				if (message.startsWith("/read/")) {
					message = message.substring(6);
					BufferedReader bfr = new BufferedReader(new FileReader(message));

					ArrayList<String> messageList = new ArrayList<String>();
                    String line = bfr.readLine();
					while (line != null) {
						messageList.add(line);
						line = bfr.readLine();
					}
					String str = null;
					for (String part : messageList) {
						str += part;
					}
					writer.println(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
					if (in != null) {
						in.close();
						clientSocket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}
	}
}
