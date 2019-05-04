import java.io.IOException;
import java.util.Scanner;

public class application {

    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        String senderName;
        int transactionAmount;

        System.out.println("Welcome to the TrustLine client.");
        System.out.println("Who is sending money? (Bob or Alice)");

        while (true) {
            senderName = in.nextLine();

            if (senderName.equals("Bob")) {

                System.out.println("How much are they sending?");
                transactionAmount = in.nextInt();
                System.out.println("Bob (Port 8888) is sending $" + transactionAmount + " to Alice (Port 8080)");

                try {
                    buildTransaction("Bob", "Alice", transactionAmount);
                } catch (IOException q) {
                    System.out.println("Bad connection or transaction parameters");
                }

            } else if (senderName.equals("Alice")) {

                System.out.println("How much are they sending?");
                transactionAmount = in.nextInt();
                System.out.println("Alice (Port 8080) is sending $" + transactionAmount + " to Bob (Port 8888)");

                try {
                    buildTransaction("Alice", "Bob", transactionAmount);
                } catch (IOException q) {
                    System.out.println("Bad connection or transaction parameters");
                }

            } else {
                System.out.println("Who is sending money? (Bob or Alice)");
            }
        }

    }

    public static void buildTransaction (String sender, String receiver, int value) throws IOException {
        int senderPort = 0;
        int receiverPort = 0;
        if (sender.equals("Bob") && receiver.equals("Alice")) {
            senderPort = 8888; receiverPort = 8080;
        } else if (sender.equals("Alice") && receiver.equals("Bob")) {
            senderPort = 8080; receiverPort = 8888;
        }

        String[] sendResponse = HttpURLConnectionTool.buildRequest("http://localhost:" + senderPort + "/sendRequest?amountSending=" + value, "GET");
        String[] receiveResponse = HttpURLConnectionTool.buildRequest("http://localhost:" + receiverPort + "/receiveRequest?amountReceiving=" + value, "GET");

        if (sendResponse[0].equals("200") && receiveResponse[0].equals("200")) {
            HttpURLConnectionTool.buildRequest("http://localhost:8080/confirmTransaction", "GET");
            HttpURLConnectionTool.buildRequest("http://localhost:8888/confirmTransaction", "GET");
        }

    }

}
