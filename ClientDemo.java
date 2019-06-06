import java.util.Scanner;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.InputMismatchException;

public class ClientDemo {
	public static void main(String[] args) throws IOException {
		String inputData;

		Socket socket = new Socket("127.0.0.1", 1211); // try to SEND on localhost - port 1211

		Scanner scan = new Scanner(System.in);
		Scanner socketScan = new Scanner(socket.getInputStream()); // Enable sending data
		PrintStream printout = new PrintStream(socket.getOutputStream()); // Response from server

		try {
			System.out.print("Enter username and password separated by single empty space: ");
			inputData = scan.nextLine(); // Read user input

			printout.println(inputData); // Send data
			String input = socketScan.nextLine(); // read the response from the server

			System.out.println(input);
		} catch (InputMismatchException e) {
			System.out.println("Wrong input format");
		} finally { // Close connection
			if (socket != null) {
				socket.close();
			}

			if (scan != null) {
				scan.close();
			}

			if (socketScan != null) {
				socketScan.close();
			}
		}
	}
}
