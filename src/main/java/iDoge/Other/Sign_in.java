package iDoge;

import com.fasterxml.jackson.databind.ObjectMapper;
import iDoge.Profile.Profile;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static iDoge.Main_Menu.loadMainMenu;
import static iDoge.Profile.Profile.loadProfile;

public class Sign_in {
    public static void userSaver(String name, String username, String password, String email) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User(name, username, password, email);
        objectMapper.writeValue(new File("Database/Users/" + user.getUsername() + ".json"), user);
    }

    public static User userLoader(String username) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("Database/Users/" + username + ".json"), User.class);
    }

    public static User signIn() throws IOException {
        Scanner beholder = new Scanner(System.in);
        a : while (true){
            System.out.println(ConsoleColors.PURPLE_BOLD + "WELCOME TO iDOGE!!!");
            System.out.println(ConsoleColors.YELLOW_BOLD + "Do you have an account? (yes/no)");
            if (beholder.nextLine().equals("yes")){
                while (true){
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your username:");
                    String username = beholder.nextLine();
                    try{
                        User user = userLoader(username);
                        if (user.isDeleted()){
                            System.out.println(ConsoleColors.RED_BOLD + "User doesn't exist... :(((");
                            continue;
                        }
                    }catch (IOException e){
                        System.out.println(ConsoleColors.RED_BOLD + "User doesn't exist... :(((");
                        continue;
                    }
                    User user = userLoader(username);
                    while (true) {
                        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your password:");
                        String password = beholder.nextLine();
                        if (password.equals(user.getPassword())){
                            System.out.println(ConsoleColors.PURPLE_BOLD + "Signed in successfully!!!");
                            return user;
                        }else {
                            System.out.println(ConsoleColors.RED_BOLD + "Password is incorrect");
                        }
                    }
                }
            } else{
                while (true){
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your name");
                    String name = beholder.nextLine();
                    while (true){
                        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your desired username:");
                        String username = beholder.nextLine();
                        boolean deleted = false;
                        try{
                            User user = userLoader(username);
                            if (!user.isDeleted()) {
                                System.out.println(ConsoleColors.RED_BOLD + "Username is already taken... :(((");
                                continue;
                            }
                            deleted = true;
                            System.out.println(ConsoleColors.YELLOW_BOLD + "Username has been used before...");
                        }catch (IOException e){
                            deleted = false;
                        }
                        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your password:");
                        String password = beholder.nextLine();
                        f : while (true){
                            System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your email:");
                            String email = beholder.nextLine();
                            try{
                                File dir = new File("Database/Users/");
                                File[] directoryListing = dir.listFiles();
                                if (directoryListing != null) {
                                    for (File child : directoryListing) {
                                        ObjectMapper objectMapper = new ObjectMapper();
                                        User user2 = objectMapper.readValue(child, User.class);
                                        if (user2.getEmail().equals(email)){
                                            System.out.println(ConsoleColors.RED_BOLD + "There's an account with this email... :(((");
                                            continue f;
                                        }
                                    }
                                }
                            }catch (Exception e){
                                System.out.println(ConsoleColors.RED_BOLD + "An error occurred... :(((");
                                continue;
                            }
                            try{
                                if (!deleted) {
                                    userSaver(name, username, password, email);
                                    Profile profile = new Profile(userLoader(username));
                                    profile.saveProfile();
                                } else {
                                    User user = userLoader(username);
                                    user.resetUser(name, username, password, email);
                                }
                            }catch (IOException e){
                                System.out.println(ConsoleColors.RED_BOLD + "An error occurred... :(((");
                                continue;
                            }
                            System.out.println(ConsoleColors.PURPLE_BOLD + "Account created successfully!!!");
                            continue a;
                        }
                    }
                }
            }
        }
    }

    public static void start() throws IOException {
        User myUser = signIn();
        Profile myProfile = loadProfile(myUser.getUsername());
        loadMainMenu(myProfile);
    }
}
