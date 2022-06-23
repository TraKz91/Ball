package Ball.Server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private Ball ball;


    public ClientHandler(Socket socket, Ball ball) {
        this.socket = socket;
        this.ball = ball;

    }

    @Override
    public void run() {
        Player player = new Player(ball.playerID);
        int playerID = 0;
        try (
                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            try {
                while (true) {

                    String line = scanner.nextLine();
                    String[] substrings = line.split(" ");
                    switch (substrings[0].toLowerCase()) {
                        case "first":
                            playerID = ball.FirstPlayer();
                            String a = String.valueOf(playerID);
                            writer.println(playerID);
                            break;

                        case "who":
                            int who = ball.whohastheball();
                            writer.println(who);
                            System.out.println("Player " + who + " has the ball");
                            break;


                        case "everyone":
                            List<Integer> thelist = ball.getallplayer();
                            writer.println(thelist.size());
                            for (Integer id : thelist) {
                                writer.println((id));
                            }
                            break;


                        case "send":
                            int p2 = Integer.parseInt(substrings[1]);
                            ball.sendtheball(playerID, p2);

                            break;

                        case "have":
                            boolean a1 = ball.allowedball(playerID);
                            writer.println(a1);
                            break;

                        default:
                            throw new Exception("Unknown command: " + substrings[0]);

                    }

                }

            } catch (Exception e) {
                writer.println("ERROR " + e.getMessage());
                socket.close();
            }
            ball.playerleft(playerID);
            List<Integer> lastlistOfId = ball.getallplayer();
            System.out.println("Player left:");
            for (Integer id : lastlistOfId) {
                writer.println((id));
                System.out.println("Player " + id);
            }


        } catch (Exception e) {
        } finally {

        }
    }
}

