package iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage;

import iDoge.Other.Bark_Comment;
import iDoge.Other.ConsoleColors;
import iDoge.Messenger.Chat;
import iDoge.Other.Sign_in;
import iDoge.Profile.Profile;
import iDoge.Other.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static iDoge.Messenger.Chat.loadChat;
import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterSendMessagePage.Choose_Packs_Page.loadChoosePacksPage;
import static iDoge.Messenger.Pages.AfterMessengerPages.AfterPacksPage.AfterSendMessagePage.Choose_Users_Page.loadChooseUsersPage;
import static iDoge.Messenger.Pages.AfterMessengerPages.Packs_Page.loadPacksPage;
import static iDoge.Messenger.Pages.Messenger_Page.canCommunicate;
import static iDoge.Messenger.Pages.Messenger_Page.isNotBlaclisted;

public class Send_Message_Page {
    private static Logger log = LogManager.getLogger(Send_Message_Page.class.getName());
    public static void loadSendMessagePage(Profile profile, Bark_Comment message) throws IOException {
        System.out.println(ConsoleColors.PURPLE_BOLD + "SEND MESSAGE PAGE");
        log.info("SEND MESSAGE PAGE");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter command number to execute:");
        log.info("Enter command number to execute:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "1) Choose users to send");
        System.out.println(ConsoleColors.BLUE_BOLD + "2) Choose packs to send");
        System.out.println(ConsoleColors.BLUE_BOLD + "3) Send to everyone");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
        Scanner beholder = new Scanner(System.in);
        while (true) {
            String i = beholder.nextLine();
            if (i.equals("1")){
                loadChooseUsersPage(profile, message);
                break;
            } else if (i.equals("2")){
                loadChoosePacksPage(profile, message);
                break;
            } else if (i.equals("3")){
                HashSet<User> userSett = new HashSet<>();
                userSett.addAll(profile.getFollowersList());
                userSett.addAll(profile.getFollowingsList());
                ArrayList<User> userList = new ArrayList<>(userSett);
                sendMessageToList(profile, userList, message);
                System.out.println(ConsoleColors.YELLOW_BOLD + "Message sent to everyone!!!");
                log.info("Message sent to everyone!!!");
            } else if (i.equals("0")){
                loadPacksPage(profile);
                break;
            }else {
                System.out.println(ConsoleColors.RED_BOLD + "Invalid command... :(");
                log.error("Invalid command... :(");
            }
        }
    }

    public static void sendMessageToList(Profile profile, ArrayList<User> list, Bark_Comment message) throws IOException {
        for (User user: list) {
            if (isNotBlaclisted(profile.getUser().getUsername(), user.getUsername())) {
                if (canCommunicate(profile.getUser().getUsername(), user.getUsername())) {
                    message.setPublishedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    try {
                        Chat chat = loadChat(profile.getUser().getUsername(), user.getUsername());
                        chat.getSentMessages().add(message);
                        chat.saveChat();
                    } catch (Exception e) {
                        Chat chat = new Chat(profile.getUser().getUsername(), user.getUsername());
                        chat.getSentMessages().add(message);
                        chat.saveChat();
                    }
                    try {
                        Chat chat2 = loadChat(user.getUsername(), profile.getUser().getUsername());
                        chat2.getRecievedMessages().add(message);
                        int r = chat2.getUnseenMessages();
                        chat2.setUnseenMessages(r + 1);
                        chat2.saveChat();
                    } catch (Exception e) {
                        Chat chat2 = new Chat(user.getUsername(), profile.getUser().getUsername());
                        chat2.getRecievedMessages().add(message);
                        int r = chat2.getUnseenMessages();
                        chat2.setUnseenMessages(r + 1);
                        chat2.saveChat();
                    }
                    System.out.println(ConsoleColors.YELLOW_BOLD + user.getUsername() + ": " + "Message sent!!!");
                    log.info(user.getUsername() + ": " + "Message sent!!!");
                } else {
                    System.out.println(ConsoleColors.RED_BOLD + user.getUsername() + ": " + "Messenger rules error... :(");
                    log.error(user.getUsername() + ": " + "Messenger rules error... :(");
                }
            } else {
                System.out.println(ConsoleColors.RED_BOLD + user.getUsername() + ": " + "Blacklist error... :(");
                log.error(user.getUsername() + ": " + "Messenger rules error... :(");
            }
            System.out.println();
        }
    }
}
