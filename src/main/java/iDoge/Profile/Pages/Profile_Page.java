package iDoge.Profile;

import iDoge.ConsoleColors;

import java.util.Scanner;

public class Profile_Page {
    public static void loadProfile_Page(){
        System.out.println(ConsoleColors.PURPLE_BOLD + "PROFILE PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter section number to enter:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) New Bark");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Your Barks");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Info");
        System.out.println(ConsoleColors.BLUE_BOLD + "4) Edit info");
        System.out.println(ConsoleColors.BLUE_BOLD + "5) Lists");
        System.out.println(ConsoleColors.BLUE_BOLD + "6) Notifications");
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")){
                break;
            } else if (i.equals("2")){
                break;
            } else if (i.equals("3")){
                break;
            } else if (i.equals("4")){
                break;
            } else if (i.equals("5")){
                break;
            } else if (i.equals("6")){
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
            }
        }
    }

    public static void main(String[] args) {
        loadProfile_Page();
    }
}
