package iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterManagePacksPage;

import iDoge.Other.ConsoleColors;
import iDoge.Other.Sign_in;
import iDoge.Profile.Profile;
import iDoge.Other.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterManagePacksPage.AfterSelectedPackPage.Add_User_to_Pack_Page.loadAddUserToPackPage;
import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterManagePacksPage.AfterSelectedPackPage.Remove_User_from_Pack_Page.loadRemoveUserFromPackPage;
import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.Manage_Packs_Page.loadManagePacksPage;

public class Selected_Pack_Page {
    private static Logger log = LogManager.getLogger(Selected_Pack_Page.class.getName());
    public static void loadSelectedPackPage(Profile profile, String key) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "PACK PAGE");
        log.info("PACK PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter command number to execute:");
        log.info("Enter command number to execute:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Add user");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Remove user");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Remove pack");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        ArrayList<User> users = new ArrayList<>(profile.getPacks().get(key));
        for (int i = 1; i <= users.size(); i++){
            System.out.println(ConsoleColors.BLUE_BOLD + users.get(i - 1).getUsername());
        }
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")){
                loadAddUserToPackPage(profile, key);
                break;
            } else if (i.equals("2")){
                loadRemoveUserFromPackPage(profile, key);
                break;
            } else if (i.equals("3")){
                profile.getPacks().remove(key);
                profile.saveProfile();
                System.out.println(ConsoleColors.YELLOW_BOLD + "Pack removed!!!");
                log.info("Pack removed!!!");
                System.out.println();
                loadManagePacksPage(profile);
                break;
            } else if (i.equals("0")){
                loadManagePacksPage(profile);
                break;
            }else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                System.out.println();
                log.error("Invalid command... :(");
            }
        }
    }
}
