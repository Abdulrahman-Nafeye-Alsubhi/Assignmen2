
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

/**
 * This class allows users to find relations of a users.
 *
 * @author Abdullah
 */
public class FindRelationsUI extends JFrame {

    private JButton findRelationsButton;
    private JComboBox<String> userComboBox;
    private JComboBox<String> relationTypeCombobox;
    private JLabel userLabel;
    private JLabel relationLabel;
    private JLabel outputLabel;
    private JList<String> relationsList;
    private JScrollPane listScrollPane;

    /**
     * Default constructor. Initializes all UI elements *
     */
    public FindRelationsUI() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * Initializes all UI elements, add action listeners and set positions.
     *
     */
    private void initComponents() {

        userComboBox = new JComboBox<>();
        relationTypeCombobox = new JComboBox<>();
        userLabel = new JLabel();
        relationLabel = new JLabel();
        outputLabel = new JLabel();
        findRelationsButton = new JButton();
        listScrollPane = new JScrollPane();
        relationsList = new JList<>();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(500, 350));
        getContentPane().setLayout(null);

        userComboBox.setModel(getUsersModel());
        getContentPane().add(userComboBox);
        userComboBox.setBounds(10, 50, 184, 20);

        relationTypeCombobox.setModel(new DefaultComboBoxModel<>(new String[]{"friends", "couple", "parent", "classmates", "colleagues"}));

        getContentPane().add(relationTypeCombobox);
        relationTypeCombobox.setBounds(240, 50, 192, 20);

        userLabel.setText("Profile");
        getContentPane().add(userLabel);
        userLabel.setBounds(10, 30, 116, 14);

        relationLabel.setText("Relationship");
        getContentPane().add(relationLabel);
        relationLabel.setBounds(240, 30, 150, 14);

        outputLabel.setText("Users Connected");
        getContentPane().add(outputLabel);
        outputLabel.setBounds(180, 120, 140, 14);

        findRelationsButton.setText("Find Relationships");
        findRelationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                findRelations(evt);
            }
        });
        getContentPane().add(findRelationsButton);
        findRelationsButton.setBounds(160, 90, 150, 23);

        listScrollPane.setViewportView(relationsList);

        getContentPane().add(listScrollPane);
        listScrollPane.setBounds(130, 140, 210, 160);

        pack();
    }

    /**
     * Pass through the relations of the selected user, filter only selected by
     * the user. Sort the profiles in Ascending order and show result in a list.
     *
     * @param evt - Click action event on Find Relationships button.
     */
    private void findRelations(ActionEvent evt) {
        int selectedIndex = userComboBox.getSelectedIndex();
        String selectedRelation = relationTypeCombobox.getSelectedItem().toString();
        if (selectedIndex != -1) {
            Profile userProfile = FileDataHandler.profiles.get(selectedIndex);
            ArrayList<String> users = new ArrayList<>();
            for (int i = 0; i < userProfile.getRelationships().size(); i++) {
                if (userProfile.getRelationships().get(i).getRelationshipType().equalsIgnoreCase(selectedRelation)) {
                    users.add(userProfile.getRelationships().get(i).getRelatedUser().getName());
                }
            }
            Collections.sort(users);
            relationsList.setModel(new AbstractListModel<String>() {
                @Override
                public int getSize() {
                    return users.size();
                }

                @Override
                public String getElementAt(int index) {
                    return users.get(index);
                }
            });
        }
    }

    /**
     * Create ListModel from profiles arraylist in data storage class.
     */
    private DefaultComboBoxModel getUsersModel() {
        String[] users = new String[FileDataHandler.profiles.size()];
        for (int i = 0; i < FileDataHandler.profiles.size(); i++) {
            users[i] = FileDataHandler.profiles.get(i).getName();
        }
        return new DefaultComboBoxModel(users);
    }

}
