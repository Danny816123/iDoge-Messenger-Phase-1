package iDoge.Profile.Pages;

import iDoge.Bark_Comment;
import iDoge.ConsoleColors;
import iDoge.Follow_Request;
import iDoge.Profile.Profile;
import iDoge.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.Send_Message_Page.loadSendMessagePage;
import static iDoge.Messenger.Pages.Messenger_Page.isNotBlaclisted;
import static iDoge.Profile.Pages.AfterProfilePages.AfterYourBarksCommentsPage.Bark_Comment_Page.loadBarkCommentPage;
import static iDoge.Profile.Profile.loadProfile;

public class Personal_Profile_Page {
    public static void loadPersonalProfilePage(Profile profile, ArrayList<Integer> address, ArrayList<Bark_Comment> list, int w) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "PERSONAL PROFILE PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter command number to execute:");
        System.out.println();
        Bark_Comment barkComment = list.get(w - 1);
        Profile theirProfile = loadProfile(barkComment.getWriter().getUsername());
        System.out.println(ConsoleColors.GREEN_BOLD + barkComment.getWriter().getName());
        System.out.println(ConsoleColors.GREEN_BOLD + barkComment.getWriter().getUsername());
        if (theirProfile.getFollowersList().contains(profile.getUser())){
            if (barkComment.getWriter().getLastSeen$Online().equals("Everyone")){
                System.out.println(ConsoleColors.GREEN_BOLD + barkComment.getWriter().getLastseen());
            } else if (barkComment.getWriter().getLastSeen$Online().equals("Followings")){
                if (theirProfile.getFollowingsList().contains(profile.getUser())){
                    System.out.println(ConsoleColors.GREEN_BOLD + barkComment.getWriter().getLastseen());
                } else {
                    System.out.println(ConsoleColors.GREEN_BOLD + "Last seen recently...");
                }
            } else {
                System.out.println(ConsoleColors.GREEN_BOLD + "Last seen recently...");
            }
            System.out.println(ConsoleColors.GREEN_BOLD + "Following...");
        } else {
            System.out.println(ConsoleColors.GREEN_BOLD + "Last seen recently...");
            System.out.println(ConsoleColors.GREEN_BOLD + "Not following...");
        }
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Follow/send follow request or unfollow user");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Send message");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Block user");
        System.out.println(ConsoleColors.BLUE_BOLD + "4) Report user");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")) {
                if (!theirProfile.getFollowersList().contains(profile.getUser())) {
                    if (isNotBlaclisted(barkComment.getWriter().getUsername(), profile.getUser().getUsername())) {
                        if (barkComment.getWriter().isPublic()) {
                            theirProfile.getFollowersList().add(profile.getUser());
                            profile.getFollowingsList().add(barkComment.getWriter());
                            theirProfile.saveProfile();
                            profile.saveProfile();
                            System.out.println(ConsoleColors.YELLOW_BOLD + "User followed!!!");
                        } else {
                            Follow_Request req = new Follow_Request(profile.getUser(), barkComment.getWriter());
                            theirProfile.getRecievedFollowRequests().add(req);
                            profile.getSentFollowRequests().add(req);
                            theirProfile.saveProfile();
                            profile.saveProfile();
                            System.out.println(ConsoleColors.YELLOW_BOLD + "Follow request sent!!!");
                        }
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD + "Blacklist error... :(");
                    }
                } else {
                    theirProfile.getFollowersList().remove(profile.getUser());
                    profile.getFollowingsList().remove(barkComment.getWriter());
                    theirProfile.saveProfile();
                    profile.saveProfile();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "User unfollowed!!!");
                }
                System.out.println();
            } else if (i.equals("2")){
                if (theirProfile.getFollowersList().contains(profile.getUser())) {
                    if (isNotBlaclisted(barkComment.getWriter().getUsername(), profile.getUser().getUsername())) {
                        System.out.println(ConsoleColors.PURPLE_BOLD + "WRITE NEW MESSAGE PAGE");
                        System.out.println(ConsoleColors.YELLOW_BOLD + "Write your new message or press Enter to go back:");
                        System.out.println();
                        String ii = beholder.nextLine();
                        if (!ii.equals("")) {
                            System.out.println();
                            System.out.println(ConsoleColors.YELLOW_BOLD + "New message written!!!");
                            System.out.println();
                            Bark_Comment message = new Bark_Comment(profile.getUser(), ii);
                            loadSendMessagePage(profile, message);
                            break;
                        }else {
                            loadPersonalProfilePage(profile, address, list, w);
                        }
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD + "Blacklist error... :(");
                    }
                } else {
                    System.out.println(ConsoleColors.RED_BOLD + "Messenger rules error... :(");
                }
            } else if (i.equals("3")) {

                break;
            } else if (i.equals("4")) {

                break;
            } else if (i.equals("0")){
                loadBarkCommentPage(profile, address, list, w);
                break;
            }else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                System.out.println();
            }
        }
    }
}
