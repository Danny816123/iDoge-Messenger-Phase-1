package iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterSendMessagePage;

import iDoge.Other.Bark_Comment;
import iDoge.Other.ConsoleColors;
import iDoge.Other.User;
import iDoge.Profile.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.Send_Message_Page.loadSendMessagePage;
import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.Send_Message_Page.sendMessageToList;

public class Choose_Users_Page {
    private static Logger log = LogManager.getLogger(Choose_Users_Page.class.getName());
    public static void loadChooseUsersPage(Profile profile, Bark_Comment message) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "CHOOSE USERS PAGE");
        log.info("CHOOSE USERS PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter the number of users you want to choose:");
        log.info("Enter the number of users you want to choose:");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        HashSet<User> userSett = new HashSet<>();
        userSett.addAll(profile.getFollowersList());
        userSett.addAll(profile.getFollowingsList());
        ArrayList<User> userList = new ArrayList<>(userSett);
        for (int i = 1; i <= userList.size(); i++) {
            System.out.println(ConsoleColors.BLUE_BOLD + i + ") " + userList.get(i - 1).getUsername());
        }
        System.out.println();
        ArrayList<String> numbersList = new ArrayList<>();
        for (int i = 1; i <= userList.size(); i++) numbersList.add(String.valueOf(i));
        Scanner beholder = new Scanner(System.in);
        int n = 0;
        while (true) {
            String i = beholder.nextLine();
            System.out.println();
            if (numbersList.contains(i)) {
                n = Integer.parseInt(i);
                break;
            } else if (i.equals("0")) {
                loadSendMessagePage(profile, message);
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.info("Invalid command... :(");
                System.out.println();
            }
        }
        HashSet<User> userHashSet = new HashSet<>();
        while (true) {
            System.out.println(ConsoleColors.YELLOW_BOLD + "Select " + n + " users by writing their numbers with a space:");
            log.info("Select " + n + " users by writing their numbers with a space:");
            System.out.println();
            try {
                while (userHashSet.size() < n) {
                    int ii = beholder.nextInt();
                    userHashSet.add(userList.get(ii - 1));
                }
                sendMessageToList(profile, new ArrayList<>(userHashSet), message);
                loadSendMessagePage(profile, message);
                break;
            } catch (Exception e){
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.info("Invalid command... :(");
                System.out.println();
            }
        }
    }
}
