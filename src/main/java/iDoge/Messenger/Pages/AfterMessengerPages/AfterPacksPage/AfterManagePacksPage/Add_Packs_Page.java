package iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterManagePacksPage;

import iDoge.Other.ConsoleColors;
import iDoge.Other.Sign_in;
import iDoge.Profile.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.Manage_Packs_Page.loadManagePacksPage;

public class Add_Packs_Page {
    private static Logger log = LogManager.getLogger(Add_Packs_Page.class.getName());
    public static void loadAddPacksPage(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "ADD PACKS PAGE");
        log.info("ADD PACKS PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new pack name or press Enter to go back:");
        log.info("Write your new pack name or press Enter to go back:");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (!i.equals("")) {
                if (!profile.getPacks().containsKey(i)) {
                    profile.getPacks().put(i, new ArrayList<>());
                    profile.saveProfile();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "New pack added!!!");
                    log.info("New pack added!!!");
                    System.out.println();
                    loadManagePacksPage(profile);
                    break;
                } else {
                    System.out.println(ConsoleColors.RED_BOLD + "Pack exists... :(");
                    log.error("Pack exists... :(");
                    System.out.println();
                }
            } else {
                loadManagePacksPage(profile);
                break;
            }
        }
    }
}
