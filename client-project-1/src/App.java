import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do {
                System.out.println("Enter string to be send:");
                echoString = scanner.nextLine();
                output.println(echoString);

                if(!echoString.equals("exit")) {
                    response = input.readLine();
                    System.out.println(response);
                }
            } while (!echoString.equals("exit"));

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
