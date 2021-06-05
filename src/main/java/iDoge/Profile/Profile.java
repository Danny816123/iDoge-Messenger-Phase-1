package iDoge.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import iDoge.User;
import iDoge.ConsoleColors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import static iDoge.Profile.Pages.AfterProfilePages.AfterYourBarksCommentsPage.Bark_Comment_Page.loadBarkCommentPage;
import static iDoge.Profile.Pages.AfterProfilePages.Your_Barks_Comments_Page.loadYourBarksCommentsPage;

public class Profile {
    private User user;
    private ArrayList<Bark_Comment> barksList;
    private ArrayList<User> followersList;
    private ArrayList<User> followingsList;
    private ArrayList<User> blackList;
    private ArrayList<User> muteList;
    private ArrayList<Follow_Request> sentFollowRequests;
    private ArrayList<Follow_Request> recievedFollowRequests;
    private ArrayList<String> notifications;
    private ArrayList<Bark_Comment> savedMessages;
    private ArrayList<Bark_Comment> likedPosts;
    private boolean isOnline;
    private HashSet<User> spamList;

    public Profile() {
    }

    public Profile(User user) {
        this.user = user;
        this.barksList = new ArrayList<>();
        this.followersList = new ArrayList<>();
        this.followingsList = new ArrayList<>();
        this.blackList = new ArrayList<>();
        this.muteList = new ArrayList<>();
        this.sentFollowRequests = new ArrayList<>();
        this.recievedFollowRequests = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.savedMessages = new ArrayList<>();
        this.likedPosts = new ArrayList<>();
        this.isOnline = true;
        this.spamList = new HashSet<>();
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Bark_Comment> getBarksList() {
        return barksList;
    }

    public ArrayList<User> getFollowersList() {
        return followersList;
    }

    public ArrayList<User> getFollowingsList() {
        return followingsList;
    }

    public ArrayList<User> getBlackList() {
        return blackList;
    }

    public ArrayList<User> getMuteList() {
        return muteList;
    }

    public ArrayList<Follow_Request> getSentFollowRequests() {
        return sentFollowRequests;
    }

    public ArrayList<Follow_Request> getRecievedFollowRequests() {
        return recievedFollowRequests;
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    public ArrayList<Bark_Comment> getSavedMessages() {
        return savedMessages;
    }

    public ArrayList<Bark_Comment> getLikedPosts() {
        return likedPosts;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public void saveProfile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("Database/Profiles/" + this.user.getUsername() + ".json"), this);
    }

    public HashSet<User> getSpamList() {
        return spamList;
    }

    public static Profile loadProfile(String username) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("Database/Profiles/" + username + ".json"), Profile.class);
    }

    public void newBark(String text){
        barksList.add(new Bark_Comment(this.user, text));
    }

    public void showBarks(){
        System.out.println(ConsoleColors.PURPLE_BOLD + "Your Barks:");
        System.out.println();
        for (int i = 0; i < barksList.size(); i++) {
            System.out.println(ConsoleColors.BLUE_BOLD + (i + 1) + ") " + barksList.get(i).getText());
            System.out.println();
        }
    }

    public void showInfo(){
        System.out.println(ConsoleColors.PURPLE_BOLD + "Your info:");
        System.out.println();
        System.out.println(ConsoleColors.BLUE_BOLD + "Name: " + this.user.getName());
        System.out.println(ConsoleColors.BLUE_BOLD + "Username: " + this.user.getUsername());
        System.out.println(ConsoleColors.BLUE_BOLD + "Password: " + this.user.getPassword());
        System.out.println(ConsoleColors.BLUE_BOLD + "Birthday: " + this.user.getBirthday());
        System.out.println(ConsoleColors.BLUE_BOLD + "Email: " + this.user.getEmail());
        System.out.println(ConsoleColors.BLUE_BOLD + "Phone number: " + this.user.getPhone());
        System.out.println(ConsoleColors.BLUE_BOLD + "Bio: " + this.user.getBio());
        if (this.user.isActivated()) System.out.println(ConsoleColors.BLUE_BOLD + "Activation: " + "Activated");
        else System.out.println(ConsoleColors.BLUE_BOLD + "Activation: " + "Disabled");
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD + "0) <<Back");
        System.out.println();
    }

    public static Bark_Comment loadYourBarksCommentsPageFromAddress(Profile profile, ArrayList<Integer> address, int s, Bark_Comment b) throws IOException {
        if (0 == address.size()){
            loadYourBarksCommentsPage(profile, address, profile.getBarksList(), 0);
            return null;
        } else if (s == address.size()){
            loadYourBarksCommentsPage(profile, address, b.getCommentsList(), 1);
            return null;
        } else if (s == 0){
            return loadYourBarksCommentsPageFromAddress(profile, address, s + 1, profile.getBarksList().get(address.get(0)));
        } else {
            return loadYourBarksCommentsPageFromAddress(profile, address, s + 1, b.getCommentsList().get(address.get(s)));
        }
    }

    public static Bark_Comment loadBarkCommentPageFromAddress(Profile profile, ArrayList<Integer> address, int s, Bark_Comment b) throws IOException {
        if (address.size() == 1){
            loadBarkCommentPage(profile, address, profile.getBarksList(), address.get(0) + 1);
            return null;
        } else {
            if (s == address.size() - 1) {
                loadBarkCommentPage(profile, address, b.getCommentsList(), address.get(s) + 1);
                return null;
            } else if (s == 0) {
                return loadBarkCommentPageFromAddress(profile, address, s + 1, profile.getBarksList().get(address.get(0)));
            } else {
                return loadBarkCommentPageFromAddress(profile, address, s + 1, b.getCommentsList().get(address.get(s)));
            }
        }
    }




    public static void main(String[] args) throws IOException {
        Profile prof = new Profile(new User("1","2","3","4"));
        Bark_Comment bark = new Bark_Comment(new User("11","22","33","44"), "shdfgh");
        bark.getCommentsList().add(new Bark_Comment(new User("11","22","33","44"), "suiyituytyutuh"));
        prof.getNotifications().add("udshfgkjldshfgjk");
        prof.getBarksList().add(bark);
        prof.saveProfile();
        System.out.println(loadProfile(prof.getUser().getUsername()).getNotifications().get(0));
        System.out.println(loadProfile(prof.getUser().getUsername()).getBarksList().get(0).getText());
        System.out.println(loadProfile(prof.getUser().getUsername()).getBarksList().get(0).getCommentsList().get(0).getText());

    }
}
