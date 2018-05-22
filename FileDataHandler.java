
import Exceptions.NoSuchAgeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * This class loads data from text files, create profiles and relationships.
 * Data structures in this class are shared throughout the application.
 *
 * @author Abdulrahman
 */
public class FileDataHandler {

    // All the profiles first loaded from text files and changes made through application.
    public static ArrayList<Profile> profiles = new ArrayList<>();
    // Any error during parsing is store in this string.
    public static String errorlog = "";

    /**
     * Open people.txt files Parse all the data and create profiles.
     */
    public static void loadPeoplesFile() {
        File file = new File("people.txt");
        if (file.exists()) {
            try {
                Scanner kb = new Scanner(file);
                while (kb.hasNext()) {
                    String line = kb.nextLine();
                    String[] profileData = line.split(",");
                    String name = profileData[0].replace("\"", "").trim();
                    String profilePicture = profileData[1].replace("\"", "").trim();
                    String status = profileData[2].replace("\"", "").trim();
                    String gender = profileData[3].replace("\"", "").trim();
                    Integer age = Integer.parseInt(profileData[4].replace("\"", "").trim());
                    if (age < 0 || age > 150) {
                        try {
                            throw new NoSuchAgeException();
                        } catch (NoSuchAgeException ex) {
                            errorlog += ex.getMessage() + "\n";
                        }
                    }
                    String state = profileData[5].replace("\"", "").trim();
                    Profile profile = new Profile(name, profilePicture, status, gender, age, state);
                    if (profiles.contains(profile)) {
                        errorlog += "Cannot add " + name + "'s profile as it already exists.\n";
                    } else {
                        profiles.add(profile);
                    }
                }
                for (Profile p : profiles) {
                    System.out.println(p);
                }
                System.out.println(errorlog);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Unable to read people.txt");
            }
        } else {
            JOptionPane.showMessageDialog(null, "people.txt file missing. Aborting");
        }
    }

    /**
     * Open relations.txt file Parse the data. Create relationships as specified
     * in file.
     */
    public static void loadRelations() {
        File file = new File("relations.txt");
        if (file.exists()) {
            try {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNext()) {
                    String relationRow = fileScanner.nextLine();
                    String[] relationDetails = relationRow.split(",");
                    String firstUser = relationDetails[0].trim();
                    String secondUser = relationDetails[1].trim();
                    String relation = relationDetails[2].trim();
                    if (profiles.contains(new Profile(firstUser)) && profiles.contains(new Profile(secondUser))) {
                        Profile firstUserProfile = profiles.get(profiles.indexOf(new Profile(firstUser)));
                        Profile secondUserProfile = profiles.get(profiles.indexOf(new Profile(secondUser)));
                        firstUserProfile.getRelationships().add(new Relationship(secondUserProfile, relation));
                        secondUserProfile.getRelationships().add(new Relationship(firstUserProfile, relation));
                        System.out.println("Created connection");
                    } else {
                        errorlog += relationDetails[0] + " or " + relationDetails[1] + " doesn't exist in database. Unable to create connection.";
                    }
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Unable to read relations.txt");
            }
        } else {
            JOptionPane.showMessageDialog(null, "relations.txt file missing. Aborting");
        }
    }

    /**
     * Remove all profile instance from the relations of all other profiles in
     * the network Delete the profile itself.
     *
     * @param selectedIndex - Index of selected profile.
     */
    public static void deleteProfile(int selectedIndex) {
        // Remove profile relationships with all other users
        for (int i = 0; i < profiles.size(); i++) {
            profiles.get(i).getRelationships().remove(profiles.get(selectedIndex));
        }
        // Remove user.
        profiles.remove(selectedIndex);
    }

    public static String[] getUserNames() {
        String[] names = new String[profiles.size()];
        for (int i = 0; i < profiles.size(); i++) {
            names[i] = profiles.get(i).getName();
        }
        return names;
    }

    /**
     * Load data from files. Run GUI Class.
     */
    public static void main(String[] args) {
        loadPeoplesFile();
        loadRelations();
        new MiniNetMainUI().setVisible(true);
    }
}
