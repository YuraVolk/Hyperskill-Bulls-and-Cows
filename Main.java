package bullscows;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static int indexOf(String[] arr, String val) {
        return IntStream.range(0, arr.length).filter(i -> arr[i] == val).findFirst().orElse(-1);
    }

    static class timerSetInit {
        volatile int timeUnit = 60*1000*20;
    }


    public static void main(String[] args) {

        String[] a = new String[]{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        String[] at = new String[]{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        Scanner scanner = new Scanner(System.in);
        int t = 0;
        int lim = 0;

        int mn = Integer.MIN_VALUE;

        boolean timeCode = false;
        boolean inv = false;
        boolean cl = false;
        boolean hrd = false;

       System.out.println("Please, enter the secret code's length:");

     //   System.out.println("What challenge to take, 1011010 - TimeCode, 100110 - Invisible, 011001 - Classic, Hardcore - 0101101");
        String tTest = scanner.next();

        if (!tTest.equals("1011010")&&!tTest.equals("100110")&&!tTest.equals("011001")&&!tTest.equals("0101101")&&!tTest.equals("101011")) {

            t = 0;

            if (tTest.matches("\\d+")) {
                t = Integer.parseInt(tTest);
            } else {
                System.out.println("Error: \""+tTest+"\" isn't a valid number.");
                System.exit(0);
            }

            System.out.println("Input the number of possible symbols in the code:");

            String lTest = scanner.next();

            lim = 0;

            if (lTest.matches("\\d+")) {
                lim = Integer.parseInt(lTest);
            } else {
                System.out.println("Error: \""+lTest+"\" isn't a valid number.");
                System.exit(0);
            }

            if (t==0 && lim==1) {
                System.out.println("Error: Length cannot be zero.");
                System.exit(0);
            }

            if (lim>36) {
                System.out.println("Error: Maximum number of possible symbols in the code is 36 (0-9, a-z).");
                System.exit(0);
            }

            if (t>lim) {
                System.out.println("Error: It's not possible to generate a code with a length of "+t+" with "+lim+" unique symbols.");
                System.exit(0);
            }

        } else {
            if (tTest.equals("1011010")) {
                System.out.println("Time Bomb challenge activated. Guess a number within 50 turns, or you automatically lose.");
                t = 10;
                lim = 36;
                timeCode = true;
            }
            if (tTest.equals("100110")) {
                System.out.println("Invisible challenge activated. Guess a number within 25 turns, or you automatically lose. You cannot see bulls. Instead of them goes cow.");
                t = 4;
                lim = 36;
                inv = true;
            }
            if (tTest.equals("011001")) {
                System.out.println("Classic activated. Guess a number within 10 turns, or you automatically lose.");
                t = 5;
                lim = 10;
                cl = true;
            }
            if (tTest.equals("0101101")) {
                System.out.println("Hardcore activated. Guess a number within 30 turns, or you automatically lose. Every time you make a guess with nothing, you lose 10 points.");
                t = 6;
                lim = 36;
                hrd = true;
            }
            if (tTest.equals("0101101")) {
                System.out.println("Hardcore activated. Guess a number within 30 turns, or you automatically lose. Every time you make a guess with nothing, you lose 10 points.");
                t = 7;
                lim = 36;
                hrd = true;
            }

        }

        String n = "";

        while (true) {
            if (t<=a.length) {
                while (n.length()<t) {
                    String rnd = (a[new Random().nextInt(lim)]);
                    n+=rnd;
                    if (rnd=="") {
                        continue;
                    }
                    a[indexOf(a,rnd)] = "";

                }
                break;

            }
        }

       System.out.print("The secret is prepared: ");
        for (int i = 0;i<n.length();i++) {
            System.out.print("*");
        }

        if (lim<11) {
            System.out.println(" (0-"+at[lim-1]+")");
        } else {
            System.out.println(" (0-9, a-"+at[lim-1]+")");
        }

        System.out.println("Okay, let's start a game!");


        int cow = 0;
        int bull = 0;

        int c = 1;
        while (true) {
            if (c>50&&timeCode==true) {
                System.out.println("You lost the challenge.");
                timeCode = false;
                break;
            }

            if (c>25&inv==true) {
                System.out.println("You lost the challenge.");
                inv = false;
                break;
            }

            if (c>10&cl==true) {
                System.out.println("You lost the challenge.");
                cl = false;
                break;
            }

            if (c>30&hrd==true) {
                System.out.println("You lost the challenge.");
                hrd = false;
                break;
            }

            cow = 0;
            bull = 0;

            System.out.println("Turn "+c+":");


            String m = scanner.next();

            if (m.length()!=n.length()) {
                System.out.println("\""+m+"\" isn't a valid number.");
                continue;
            }

            for (int i = 0;i<n.length();i++) {
                if (m.charAt(i)==n.charAt(i)) {
                    bull++;
                } else {
                    if (n.contains(Character.toString(m.charAt(i)))) {
                        cow++;
                    }
                }
            }

            if (bull==n.length()) {
                System.out.println("Grade: "+ bull  + " bull(s)");
                break;
            }

            if (inv==true) {
                cow+=bull;
                bull=0;
            }

            if (bull==0&&cow!=0) {
                System.out.println("Grade: "+ cow  + " cow(s)");
            } else if (cow==0&bull!=0) {
                System.out.println("Grade: "+ bull  + " bull(s)");
            } else if (bull!=0&&cow!=0) {
                System.out.println("Grade: "+ bull  + " bull(s) and " + cow  + " cow(s)");
            } else {
                System.out.println("Grade: None.");

                if (hrd==true) {
                    c+=10;
                }
            }



            c++;


        }

        System.out.println("The secret code is " + n + ".");


        if (timeCode == true) {
            System.out.print("Congratulations! Time Code challenge completed.");
        }

        if (inv == true) {
            System.out.print("Congratulations! Invisible challenge completed.");
        }

        if (cl == true) {
            System.out.print("Congratulations! Classic challenge completed.");
        }

        if (hrd == true) {
            System.out.print("Congratulations! Hardcore challenge completed.");
        }

    }
}
//2*1*3

