package Ball.Client;

import java.util.Scanner;

public class ClientProgram {
    public static void main(String[] args) {


        try {
            Scanner in = new Scanner(System.in);

            try (Client client = new Client()) {
                int myid = client.FirstPlayer();
                System.out.println("My ID is:" + myid);
                System.out.println("-------------------------------");
                int[] listID = client.everyone();
                System.out.println("The player are:");
                for (int playID : listID) {
                    System.out.println("Player: " + playID);
                }
                System.out.println("-------------------------------");
                int possesion = client.who();
                System.out.println("The player " + possesion + " has the ball");
                System.out.println("-------------------------------");

                while (true) {

                    if (client.hasball() == true) {
                        System.out.println("Your ID is: " + myid);
                        System.out.println("-------------------------------");
                        System.out.println("You have the ball!");
                        int[] list = client.everyone();
                        System.out.println("The list of player is:");
                        for (int display : list) {
                            System.out.println("Player: " + display);
                        }
                        System.out.println("-------------------------------");
                        System.out.println("Who want you send the ball to?");
                        System.out.println("Or type 0 to refresh the list of player ");
                        String Psend = in.nextLine();
                        try {
                            int i = Integer.parseInt(Psend);
                            int PS = Integer.parseInt(Psend);
                            for (int playId : list) {
                                if (playId == PS) {
                                    client.send(String.valueOf(PS));
                                    System.out.println("Player " + Psend + " has now the ball");
                                    System.out.println("-------------------------------");
                                } else if (PS == 0) {
                                    int[] list3 = client.everyone();
                                    System.out.println("The list of player is:");
                                    for (int display : list3) {
                                        System.out.println("Player: " + display);

                                    }
                                    System.out.println("-------------------------------");
                                } else if (PS > list.length) {
                                    System.out.println("Player " + Psend + " does not exist, please retry");
                                    System.out.println("-------------------------------");

                                } else {
                                    continue;
                                }

                            }
                        } catch (NumberFormatException ex) {
                            System.out.println("Number input wrong, retry");
                        }
                    } else {
                        System.out.println("Your ID is: " + myid);
                        System.out.println("-------------------------------");
                        System.out.println("The list of player is:");
                        int[] list = client.everyone();
                        for (int display : list) {
                            System.out.println("Player " + display);
                        }
                        System.out.println("-------------------------------");
                        System.out.println("Player " + client.who() + " has the ball");
                        System.out.println("-------------------------------");
                        System.out.println("Wait for the player to play");
                        System.out.println("Press enter to refresh");
                        String wait = in.nextLine();
                        System.out.println("-------------------------------");


                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
}