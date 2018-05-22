
import Exceptions.NoSuchAgeException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class allows users to create a new profile through UI
 *
 * @author Abdullah
 */
public class CreateProfileUI extends JFrame {

    // Instance of Main UI dashboard (MiniNetMainUI class) to update list when new element added.
    MiniNetMainUI instance;
    private JButton createProfileButton;
    private JLabel nameLabel;
    private JLabel profileLabel;
    private JLabel ageLabel;
    private JLabel genderLabel;
    private JLabel statusLabel;
    private JLabel stateLabel;
    private JTextField nameField;
    private JTextField profilePicture;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField statusField;
    private JTextField stateField;

    /**
     * Default constructor. Initializes all UI elements and saves instance of
     * MiniNetMainUI class.
     *
     * @param instance
     */
    public CreateProfileUI(MiniNetMainUI instance) {
        initComponents();
        this.instance = instance;
    }

    /**
     * Initializes all UI elements, add action listeners and set positions.
     *
     */
    private void initComponents() {
        setTitle("Create Profile");
        nameLabel = new JLabel();
        profileLabel = new JLabel();
        ageLabel = new JLabel();
        genderLabel = new JLabel();
        statusLabel = new JLabel();
        stateLabel = new JLabel();
        nameField = new JTextField();
        profilePicture = new JTextField();
        ageField = new JTextField();
        genderField = new JTextField();
        statusField = new JTextField();
        stateField = new JTextField();
        createProfileButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(400, 300));
        getContentPane().setLayout(null);

        nameLabel.setText("Name");
        getContentPane().add(nameLabel);
        nameLabel.setBounds(40, 30, 80, 14);

        profileLabel.setText("Profile Picture Name");
        getContentPane().add(profileLabel);
        profileLabel.setBounds(40, 60, 110, 14);

        ageLabel.setText("Age");
        getContentPane().add(ageLabel);
        ageLabel.setBounds(40, 90, 90, 14);

        genderLabel.setText("Gender (M/F)");
        getContentPane().add(genderLabel);
        genderLabel.setBounds(40, 120, 90, 14);

        statusLabel.setText("Status");
        getContentPane().add(statusLabel);
        statusLabel.setBounds(40, 150, 90, 14);

        stateLabel.setText("State");
        getContentPane().add(stateLabel);
        stateLabel.setBounds(40, 180, 90, 14);

        getContentPane().add(nameField);
        nameField.setBounds(160, 30, 180, 20);
        getContentPane().add(profilePicture);
        profilePicture.setBounds(160, 60, 180, 20);
        getContentPane().add(ageField);
        ageField.setBounds(160, 90, 180, 20);
        getContentPane().add(genderField);
        genderField.setBounds(160, 120, 180, 20);
        getContentPane().add(statusField);
        statusField.setBounds(160, 150, 180, 20);
        getContentPane().add(stateField);
        stateField.setBounds(160, 180, 180, 20);

        createProfileButton.setText("Create Profile");
        createProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                createProfile(event);
            }
        });
        getContentPane().add(createProfileButton);
        createProfileButton.setBounds(90, 220, 210, 23);
        setLocationRelativeTo(null);
        pack();
    }

    /**
     * Get data from all text fields, validate and create user profile. If
     * profile created add it to profiles arraylist FileDataHandler
     *
     */
    private void createProfile(ActionEvent event) {
        String name = this.nameField.getText();
        String profilePicture = this.profilePicture.getText();
        String ageString = this.ageField.getText();
        String gender = this.genderField.getText();
        String status = this.statusField.getText();
        String state = this.stateField.getText();
        int age = 0;
        try {
            age = Integer.parseInt(ageString);
            if (age < 0 || age > 150) {
                throw new NoSuchAgeException();
            }
            Profile profile = new Profile(name, profilePicture, status, gender, age, state);
            if (FileDataHandler.profiles.contains(profile)) {
                JOptionPane.showMessageDialog(null, "Profile already exists.");
            } else {
                FileDataHandler.profiles.add(profile);
                JOptionPane.showMessageDialog(null, "Profile added successfully.");
                instance.updateList();
                dispose();
            }
        } catch (NumberFormatException | NoSuchAgeException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

}
