package iDoge;

import iDoge.Profile.Profile;

import java.io.IOException;
import java.util.Scanner;

import static iDoge.Explorer.Explorer_Page.loadExplorerPage;
import static iDoge.Messenger.Pages.Messenger_Page.loadMessengerPage;
import static iDoge.Profile.Pages.Profile_Page.loadProfile_Page;
import static iDoge.Settings.Settings_Page.loadSettingsPage;
import static iDoge.Timeline.Timeline_Page.loadTimelinePage;

public class Main_Menu {
    public static void loadMainMenu(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "MAIN MENU");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter section number to enter:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Profile");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Timeline");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Explorer");
        System.out.println(ConsoleColors.BLUE_BOLD + "4) Messenger");
        System.out.println(ConsoleColors.BLUE_BOLD + "5) Settings");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")){
                loadProfile_Page(profile);
                break;
            } else if (i.equals("2")){
                loadTimelinePage(profile);
                break;
            } else if (i.equals("3")){
                loadExplorerPage(profile);
                break;
            } else if (i.equals("4")){
                loadMessengerPage(profile);
                break;
            } else if (i.equals("5")){
                loadSettingsPage(profile);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
            }
        }
    }
}
