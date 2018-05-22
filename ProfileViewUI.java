
import java.awt.Dimension;
import java.io.File;
import javax.swing.*;

/**
 * This class allows users to view a profile.
 *
 * @author Abdulrahaman */
public class ProfileViewUI extends JFrame {

    private JComboBox<String> relationsComboBox;
    private JLabel profilePhoto;
    private JLabel nameLabel;
    private JLabel statusLabel;
    private JLabel ageLabel;
    private JLabel genderLabel;
    private JLabel typeLabel;
    private JLabel stateLabel;
    private JLabel relationshipsLabel;
    private JTextField nameField;
    private JTextField statusField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField typeField;
    private JTextField stateVidel;

    /**
     * Default constructor. Initializes all UI elements. Load data of
     * selectedIndex from profiles arraylist.
     */
    public ProfileViewUI(int selectedIndex) {
        initComponents();
        Profile userProfile = FileDataHandler.profiles.get(selectedIndex);
        this.nameField.setText(userProfile.getName());
        this.statusField.setText(userProfile.getStatus());
        this.ageField.setText(userProfile.getAge() + "");
        this.genderField.setText(userProfile.getGender());
        this.typeField.setText(userProfile.getType() + "");
        this.stateVidel.setText(userProfile.getState());
        File f = new File(userProfile.getProfilePicture());
        if (f.exists()) {
            profilePhoto.setIcon(new ImageIcon(userProfile.getProfilePicture()));
        }
        String[] relationships = new String[userProfile.getRelationships().size()];
        for (int i = 0; i < userProfile.getRelationships().size(); i++) {
            relationships[i] = userProfile.getRelationships().get(i).toString();
            System.out.println(relationships[i]);
        }
        getContentPane().remove(relationsComboBox);
        this.relationsComboBox = new JComboBox<>(relationships);
        getContentPane().add(relationsComboBox);
        relationsComboBox.setBounds(260, 220, 180, 20);
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        profilePhoto = new JLabel();
        nameLabel = new JLabel();
        statusLabel = new JLabel();
        ageLabel = new JLabel();
        genderLabel = new JLabel();
        typeLabel = new JLabel();
        stateLabel = new JLabel();
        nameField = new JTextField();
        statusField = new JTextField();
        ageField = new JTextField();
        genderField = new JTextField();
        typeField = new JTextField();
        stateVidel = new JTextField();
        relationshipsLabel = new JLabel();
        relationsComboBox = new JComboBox<>();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("User Profile");
        setMinimumSize(new Dimension(500, 300));
        getContentPane().setLayout(null);

        profilePhoto.setIcon(new ImageIcon(getClass().getResource("/notfound.jpg")));
        getContentPane().add(profilePhoto);
        profilePhoto.setBounds(0, 30, 140, 210);

        nameLabel.setText("Name");
        getContentPane().add(nameLabel);
        nameLabel.setBounds(160, 40, 80, 14);

        statusLabel.setText("Status");
        getContentPane().add(statusLabel);
        statusLabel.setBounds(160, 70, 80, 14);

        ageLabel.setText("Age");
        getContentPane().add(ageLabel);
        ageLabel.setBounds(160, 100, 90, 14);

        genderLabel.setText("Gender");
        getContentPane().add(genderLabel);
        genderLabel.setBounds(160, 130, 90, 14);

        typeLabel.setText("Type");
        getContentPane().add(typeLabel);
        typeLabel.setBounds(160, 160, 90, 14);

        stateLabel.setText("State");
        getContentPane().add(stateLabel);
        stateLabel.setBounds(160, 190, 90, 14);

        nameField.setEditable(false);

        getContentPane().add(nameField);
        nameField.setBounds(260, 40, 180, 20);

        statusField.setEditable(false);
        getContentPane().add(statusField);
        statusField.setBounds(260, 70, 180, 20);

        ageField.setEditable(false);
        getContentPane().add(ageField);
        ageField.setBounds(260, 100, 180, 20);

        genderField.setEditable(false);
        getContentPane().add(genderField);
        genderField.setBounds(260, 130, 180, 20);

        typeField.setEditable(false);
        getContentPane().add(typeField);
        typeField.setBounds(260, 160, 180, 20);

        stateVidel.setEditable(false);
        getContentPane().add(stateVidel);
        stateVidel.setBounds(260, 190, 180, 20);

        relationshipsLabel.setText("Relationsips");
        getContentPane().add(relationshipsLabel);
        relationshipsLabel.setBounds(160, 220, 90, 14);

        getContentPane().add(relationsComboBox);
        relationsComboBox.setBounds(260, 220, 180, 20);

        pack();
    }

}
