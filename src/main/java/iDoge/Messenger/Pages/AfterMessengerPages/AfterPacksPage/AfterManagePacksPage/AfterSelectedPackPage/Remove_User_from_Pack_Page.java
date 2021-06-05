package iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterManagePacksPage.AfterSelectedPackPage;

import iDoge.Other.ConsoleColors;
import iDoge.Other.Sign_in;
import iDoge.Profile.Profile;
import iDoge.Other.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterManagePacksPage.Selected_Pack_Page.loadSelectedPackPage;

public class Remove_User_from_Pack_Page {
    private static Logger log = LogManager.getLogger(Remove_User_from_Pack_Page.class.getName());
    public static void loadRemoveUserFromPackPage(Profile profile, String key) throws IOException {
        ArrayList<User> userList = profile.getPacks().get(key);
        System.out.println(ConsoleColors.PURPLE_BOLD + "REMOVE USER FROM PACK PAGE");
        log.info("REMOVE USER FROM PACK PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter user number to remove:");
        log.info("Enter user number to remove:");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        for (int i = 1; i <= userList.size(); i++){
            System.out.println(ConsoleColors.BLUE_BOLD + i + ") " + userList.get(i - 1).getUsername());
        }
        System.out.println();
        ArrayList<String> numbersList = new ArrayList<>();
        for (int i = 1; i <= userList.size(); i++) numbersList.add(String.valueOf(i));
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            System.out.println();
            if (numbersList.contains(i)){
                User user = userList.get(Integer.parseInt(i) - 1);
                profile.getPacks().get(key).remove(user);
                profile.saveProfile();
                System.out.println(ConsoleColors.YELLOW_BOLD + "User removed!!!");
                log.info("User removed!!!");
                System.out.println();
                loadSelectedPackPage(profile, key);
                break;
            } else if (i.equals("0")){
                loadSelectedPackPage(profile, key);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
                System.out.println();
            }
        }
    }
}
