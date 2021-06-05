package iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterSendMessagePage;

import iDoge.Other.Bark_Comment;
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

import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.Send_Message_Page.loadSendMessagePage;
import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.Send_Message_Page.sendMessageToList;

public class Choose_Packs_Page {
    private static Logger log = LogManager.getLogger(Choose_Packs_Page.class.getName());
    public static void loadChoosePacksPage(Profile profile, Bark_Comment message) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "CHOOSE PACKS PAGE");
        log.info("CHOOSE PACKS PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter the number of packs you want to choose:");
        log.info("Enter the number of packs you want to choose:");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        ArrayList<String> keys = new ArrayList<>(profile.getPacks().keySet());
        for (int i = 1; i <= profile.getPacks().keySet().size(); i++){
            System.out.println(ConsoleColors.BLUE_BOLD + i + ") " + keys.get(i - 1));
        }
        System.out.println();
        ArrayList<String> numbersList = new ArrayList<>();
        for (int i = 1; i <= keys.size(); i++) numbersList.add(String.valueOf(i));
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
                log.error("Invalid command... :(");
                System.out.println();
            }
        }
        HashSet<String> keysSet = new HashSet<>();
        while (true) {
            System.out.println(ConsoleColors.YELLOW_BOLD + "Select " + n + " packs by writing their numbers with a space:");
            log.info("Select " + n + " packs by writing their numbers with a space:");
            System.out.println();
            try {
                while (keysSet.size() < n) {
                    int ii = beholder.nextInt();
                    keysSet.add(keys.get(ii - 1));
                }
                HashSet<User> userSet = new HashSet<>();
                for (String string : keysSet) {
                    userSet.addAll(profile.getPacks().get(string));
                }
                sendMessageToList(profile, new ArrayList<>(userSet), message);
                loadSendMessagePage(profile, message);
                break;
            } catch (Exception e){
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
                System.out.println();
            }
        }
    }
}
