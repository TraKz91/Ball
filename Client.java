package Ball.Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements AutoCloseable {
    final int port = 8888;

    private final Scanner reader;
    private final PrintWriter writer;

    public Client() throws Exception {
        Socket socket = new Socket("localhost", port);
        reader = new Scanner(socket.getInputStream());

        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public int FirstPlayer() {
        writer.println("FIRST");
        String line = reader.nextLine();
        //System.out.println(line);
        int ID = Integer.parseInt((line));

        return ID;
    }


    public int who() {
        writer.println("WHO");
        String line = reader.nextLine();
        return Integer.parseInt(line);
    }

    public int[] everyone() {
        writer.println("EVERYONE");
        String line = reader.nextLine();
        int sizeoflist = Integer.parseInt(line);

        int[] playerlist = new int[sizeoflist];
        for (int i = 0; i < sizeoflist; i++) {
            line = reader.nextLine();
            playerlist[i] = Integer.parseInt(line);
        }
        return playerlist;
    }

    public boolean hasball() {
        writer.println("have");
        String line = reader.nextLine();
        boolean ID = Boolean.parseBoolean(line);

        return ID;
    }

    public void send(String send) {
        writer.println("send " + send);

    }

    @Override
    public void close() throws Exception {
        reader.close();
        writer.close();
    }
}