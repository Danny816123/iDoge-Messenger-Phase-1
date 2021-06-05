package iDoge.Messenger.Pages.AfterMessengerPages;

import iDoge.Other.Bark_Comment;
import iDoge.Other.ConsoleColors;
import iDoge.Other.Sign_in;
import iDoge.Profile.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.Manage_Packs_Page.loadManagePacksPage;
import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.Send_Message_Page.loadSendMessagePage;
import static iDoge.Messenger.Pages.Messenger_Page.loadMessengerPage;

public class Packs_Page {
    private static Logger log = LogManager.getLogger(Packs_Page.class.getName());
    public static void loadPacksPage(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "PACKS PAGE");
        log.info("PACKS PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter section number to enter:");
        log.info("Enter section number to enter:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Manage packs");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Send message");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")){
                loadManagePacksPage(profile);
                break;
            } else if (i.equals("2")){
                System.out.println(ConsoleColors.PURPLE_BOLD + "WRITE NEW MESSAGE PAGE");
                log.info("WRITE NEW MESSAGE PAGE");
                System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new message or press Enter to go back:");
                log.info("Write your new message or press Enter to go back:");
                System.out.println();
                String ii = beholder.nextLine();
                if (!ii.equals("")) {
                    System.out.println();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "New message written!!!");
                    log.info("New message written!!!");
                    System.out.println();
                    Bark_Comment message = new Bark_Comment(profile.getUser().getUsername(), ii);
                    loadSendMessagePage(profile, message);
                }else {
                    loadPacksPage(profile);
                }
                break;
            } else if (i.equals("0")){
                loadMessengerPage(profile);
                break;
            }else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
            }
        }
    }
}
