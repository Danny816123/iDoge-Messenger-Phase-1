package iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage;

import iDoge.Other.ConsoleColors;
import iDoge.Other.Sign_in;
import iDoge.Profile.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterManagePacksPage.Add_Packs_Page.loadAddPacksPage;
import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterManagePacksPage.Selected_Pack_Page.loadSelectedPackPage;
import static iDoge.Messenger.Pages.AfterMessengerPages.Packs_Page.loadPacksPage;

public class Manage_Packs_Page {
    private static Logger log = LogManager.getLogger(Manage_Packs_Page.class.getName());
    public static void loadManagePacksPage(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "MANAGE PACKS PAGE");
        log.info("MANAGE PACKS PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter pack number to enter or press Enter to add a pack:");
        log.info("Enter pack number to enter or press Enter to add a pack:");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        ArrayList<String> keys = new ArrayList<>(profile.getPacks().keySet());
        for (int i = 1; i <= profile.getPacks().keySet().size(); i++){
            System.out.println(ConsoleColors.BLUE_BOLD + i + ") " + keys.get(i - 1));
        }
        ArrayList<String> numbersList = new ArrayList<>();
        for (int i = 1; i <= profile.getPacks().keySet().size(); i++) numbersList.add(String.valueOf(i));
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (numbersList.contains(i)){
                loadSelectedPackPage(profile, keys.get(Integer.parseInt(i) -  1));
                break;
            } else if (i.equals("")) {
                loadAddPacksPage(profile);
                break;
            } else if (i.equals("0")){
                loadPacksPage(profile);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
                System.out.println();
            }
        }
    }
}
