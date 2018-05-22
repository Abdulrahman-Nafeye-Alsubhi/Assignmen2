
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This class allows users to create relation between two profiles.
 *
 * @author Abdulrahman
 */
public class CreateRelationsUI extends JFrame {

    private JButton createRelationButton;
    private JComboBox<String> firstUserComboBox;
    private JComboBox<String> secondUserComboBox;
    private JComboBox<String> relationsComboBox;
    private JLabel profile1Label;
    private JLabel profile2Label;
    private JLabel relationsLabel;

    /**
     * Default constructor. Initializes all UI elements *
     */
    public CreateRelationsUI() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * Initializes all UI elements, add action listeners and set positions.
     *
     */
    private void initComponents() {

        firstUserComboBox = new JComboBox<>();
        secondUserComboBox = new JComboBox<>();
        relationsComboBox = new JComboBox<>();
        createRelationButton = new JButton();
        profile1Label = new JLabel();
        profile2Label = new JLabel();
        relationsLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Relation");
        setMinimumSize(new Dimension(425, 250));
        getContentPane().setLayout(null);

        firstUserComboBox.setModel(getUsersModel());
        getContentPane().add(firstUserComboBox);
        firstUserComboBox.setBounds(10, 32, 175, 20);

        secondUserComboBox.setModel(getUsersModel());
        getContentPane().add(secondUserComboBox);
        secondUserComboBox.setBounds(203, 32, 187, 20);

        relationsComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"friends", "couple", "parent", "classmates", "colleagues"}));
        getContentPane().add(relationsComboBox);
        relationsComboBox.setBounds(107, 82, 175, 20);

        createRelationButton.setText("Create Relation");
        createRelationButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createRelationsip(evt);
            }
        });
        getContentPane().add(createRelationButton);
        createRelationButton.setBounds(107, 132, 175, 23);

        profile1Label.setText("Profile 1:");
        getContentPane().add(profile1Label);
        profile1Label.setBounds(10, 11, 175, 14);

        profile2Label.setText("Profile 2");
        getContentPane().add(profile2Label);
        profile2Label.setBounds(203, 11, 187, 14);

        relationsLabel.setText("Relationship Type");
        getContentPane().add(relationsLabel);
        relationsLabel.setBounds(107, 58, 175, 14);

        pack();
    }

    /**
     * Check if selected profiles are different. If different create relation
     * through add relation method in Profile class. If any exceptions show
     * message in message dialog.
     *
     */
    private void createRelationsip(ActionEvent event) {
        if (firstUserComboBox.getSelectedIndex() == secondUserComboBox.getSelectedIndex()) {
            JOptionPane.showMessageDialog(null, "Cannot create relationship of a user with itself.");
        } else {
            Profile firstUserProfile = FileDataHandler.profiles.get(firstUserComboBox.getSelectedIndex());
            Profile secondUserProfile = FileDataHandler.profiles.get(secondUserComboBox.getSelectedIndex());
            String relationType = relationsComboBox.getSelectedItem().toString();
            try {
                firstUserProfile.addRelationShip(secondUserProfile, relationType);
                JOptionPane.showMessageDialog(null, "Relationship created successfully.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getClass() + " - " + e.getMessage());
            }
        }
    }

    private DefaultComboBoxModel getUsersModel() {
        String[] users = new String[FileDataHandler.profiles.size()];
        for (int i = 0; i < FileDataHandler.profiles.size(); i++) {
            users[i] = FileDataHandler.profiles.get(i).getName();
        }
        return new DefaultComboBoxModel(users);
    }

}
