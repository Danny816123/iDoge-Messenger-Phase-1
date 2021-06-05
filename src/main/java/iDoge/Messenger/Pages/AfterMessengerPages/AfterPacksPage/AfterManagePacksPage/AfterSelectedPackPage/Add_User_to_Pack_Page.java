package iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterManagePacksPage.AfterSelectedPackPage;

import iDoge.Other.ConsoleColors;
import iDoge.Other.Sign_in;
import iDoge.Profile.Profile;
import iDoge.Other.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterManagePacksPage.Selected_Pack_Page.loadSelectedPackPage;

public class Add_User_to_Pack_Page {
    private static Logger log = LogManager.getLogger(Add_User_to_Pack_Page.class.getName());
    public static void loadAddUserToPackPage(Profile profile, String key) throws IOException {
        HashSet<User> userSett = new HashSet<>();
        userSett.addAll(profile.getFollowersList());
        userSett.addAll(profile.getFollowingsList());
        ArrayList<User> userList = new ArrayList<>(userSett);
        System.out.println(ConsoleColors.PURPLE_BOLD + "ADD USER TO PACK PAGE");
        log.info("ADD USER TO PACK PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter user number to add:");
        log.info("Enter user number to add:");
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
                HashSet<User> userSet = new HashSet<>(profile.getPacks().get(key));
                userSet.add(user);
                profile.getPacks().get(key).clear();
                profile.getPacks().get(key).addAll(userSet);
                profile.saveProfile();
                System.out.println(ConsoleColors.YELLOW_BOLD + "New user added!!!");
                log.info("New user added!!!");
                System.out.println();
                loadSelectedPackPage(profile, key);
                break;
            } else if (i.equals("0")){
                loadSelectedPackPage(profile, key);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.info("Invalid command... :(");
                System.out.println();
            }
        }
    }
}
