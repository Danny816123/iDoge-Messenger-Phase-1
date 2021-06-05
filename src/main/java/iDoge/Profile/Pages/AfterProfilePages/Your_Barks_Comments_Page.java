package iDoge.Profile.Pages.AfterProfilePages;

import iDoge.ConsoleColors;
import iDoge.Profile.Bark_Comment;
import iDoge.Profile.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static iDoge.Profile.Pages.AfterProfilePages.AfterYourBarksPage.Bark_Comment_Page.loadBarkCommentPage;
import static iDoge.Profile.Pages.Profile_Page.loadProfile_Page;

public class Your_Barks_Page {
    public static void loadYourBarksPage(Profile profile) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "YOUR BARKS PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter Bark number to enter:");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        for (int i = 1; i <= profile.getBarksList().size(); i++){
            Bark_Comment bark = profile.getBarksList().get(i - 1);
            System.out.println(ConsoleColors.GREEN_BOLD + i + ") " + bark.getWriter().getUsername());
            System.out.println(ConsoleColors.BLUE_BOLD + bark.getText());
            System.out.println();
        }
        ArrayList<String> numbersList = new ArrayList<>();
        for (int i = 1; i <= profile.getBarksList().size(); i++) numbersList.add(String.valueOf(i));
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (numbersList.contains(i)){
                loadBarkCommentPage(profile.getBarksList() ,Integer.parseInt(i));
                break;
            }else if (i.equals("0")){
                loadProfile_Page(profile);
                break;
            } else System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
        }
    }
}
