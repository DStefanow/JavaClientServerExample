import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
	public static void main(String[] args) throws IOException {
		// Hardcoded data
		HashMap<String, String> userCred = new HashMap<String, String>();
		userCred.put("kriso", "password1234");
		userCred.put("milen", "testest");
		userCred.put("pesho", "pesho123");
		userCred.put("katya", "newpasswd");

		ServerSocket server = new ServerSocket(1211); // Open new connection on port 1211
		Socket socket = server.accept(); // Accept input from client side

		Scanner scan = new Scanner(socket.getInputStream()); // Read from the pipe
		String userData = scan.nextLine();
		String[] splitedInput = userData.split(" ");
		String usernameInput = splitedInput[0];
		String passwordInput = splitedInput[1];

		String result;
		if (userCred.containsKey(usernameInput)) { // Username exists in our data
			if (passwordInput.equals(userCred.get(usernameInput))) {
				result = "Valid username and password";
			} else {
				result = "Invalid password for user: " + usernameInput;
			}
		} else {
			result = "User with that username is not present in our data";
		}


		// Enable sending response to client
		PrintStream printout = new PrintStream(socket.getOutputStream());
		printout.println(result);

		// Close connection and stream after work
		server.close();
		scan.close();
	}
}
