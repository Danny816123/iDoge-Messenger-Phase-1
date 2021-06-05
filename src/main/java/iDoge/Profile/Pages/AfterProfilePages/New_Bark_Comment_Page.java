package iDoge.Profile.Pages.AfterProfilePages;

import iDoge.ConsoleColors;
import iDoge.Profile.Bark_Comment;
import iDoge.Profile.Profile;

import java.io.IOException;
import java.util.Scanner;

import static iDoge.Profile.Pages.Profile_Page.loadProfile_Page;

public class New_Bark_Page {
    public static void loadNew_Bark_Page(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "NEW BARK/COMMENT PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new Bark or press Enter to go back:");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        String i = beholder.nextLine();
        if (!i.equals("")) {
            System.out.println();
            System.out.println(ConsoleColors.YELLOW_BOLD + "New Bark posted!!!");
            System.out.println();
            profile.getBarksList().add(new Bark_Comment(profile.getUser(), i));
            profile.saveProfile();
        }
        loadProfile_Page(profile);
    }
}
