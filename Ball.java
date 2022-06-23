package Ball.Server;

import java.net.Socket;
import java.util.*;

public class Ball {
    private static Socket socket;
    ArrayList<Integer> tPiD = new ArrayList();
    private static Map<Integer, Socket> currplayers = new HashMap<Integer, Socket>();
    public static Map<Integer, Boolean> hasball = new HashMap<>();
    static int playerID = 0;


    public int FirstPlayer() {
        if (tPiD.isEmpty()) {
            currplayers.put(1, socket);
            playerID = 1;
            tPiD.add(playerID);
            hasball.put(playerID, true);


        } else {
            if (!currplayers.isEmpty()) {
                int key = Collections.max(tPiD);
                currplayers.put(key + 1, socket);
                playerID = key + 1;
                tPiD.add(playerID);
                hasball.put(playerID, false);

            } else {
                int key = Collections.max(tPiD);
                currplayers.put(key + 1, socket);
                playerID = key + 1;
                tPiD.add(playerID);
                hasball.put(playerID, true);
            }
        }

        System.out.println("Player " + playerID + " has join the game");
        System.out.println("-------------------------");
        return playerID;

    }


    public int whohastheball() {
        int keep = 0;
        for (Integer key : hasball.keySet()) {
            if (hasball.get(key) == true) {
                keep = key;
            }
        }
        return keep;
    }


    public void sendtheball(int myID, int toPlayer) {
        if (myID == toPlayer) {
            hasball.replace(myID, true);
            System.out.println("Player " + myID + " still has the ball");
            System.out.println("-------------------------");
        } else {
            if (currplayers.containsKey(toPlayer)) {
                hasball.replace(myID, false);
                hasball.replace(toPlayer, true);
                System.out.println("Player " + myID + " sends the ball to Player " + toPlayer);
                System.out.println("-------------------------");


            }
        }
    }


    public boolean allowedball(int myID) {
        boolean status = true;

        if (hasball.get(myID) == true) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    public List<Integer> getallplayer() {
        List<Integer> fulllist = new ArrayList<>();
        fulllist.addAll(currplayers.keySet());
        System.out.println("Player list:");
        for (int i : fulllist) {
            System.out.println("Player " + i);
        }
        System.out.println("-------------------------");

        return fulllist;
    }


    public int playerleft(int playerID) {
        int check = 0;
        hasball.replace(playerID, false);
        hasball.remove(playerID);
        currplayers.remove(playerID, socket);
        if (!currplayers.isEmpty()) {
            if (whohastheball() == 0) {
                int nv = Collections.max(currplayers.keySet());
                hasball.replace(nv, true);
                check = nv;
                System.out.println("Player " + playerID + " has left the game and the ball is passed to player " + nv);
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Player " + playerID + " has left");
            System.out.println("No player in the game ");
            System.out.println("Waiting for new players");
            System.out.println("-------------------------");

        }

        return check;
    }


}
