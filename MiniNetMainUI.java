
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

/**
 * GUI Dashboard for all operations.
 *
 * @author Abdulrahman
 */
public class MiniNetMainUI extends JFrame {

    private JButton viewProfileButton;
    private JButton deleteProfileButton;
    private JButton namesOfChildParents;
    private JButton friendsOfFriendsButton;
    private JButton createProfileButton;
    private JButton createRelationButton;
    private JButton findRelationButton;
    private JLabel profileActionsLabel;
    private JLabel networkActionsLabel;
    private JLabel userProfilesLabel;
    private JList<String> profilesList;
    private JMenu guiManue;
    private JMenuBar guiMenuBar;
    private JMenuItem itemClose;
    private JScrollPane listScrollPane;
    private JSeparator lineSeparator;

    /**
     * Default constructor. Initialized all UI elements.
     */
    public MiniNetMainUI() {
        initComponents();
    }

    /**
     * Initialize all UI elements. Add action listeners. Set position.
     */
    private void initComponents() {

        lineSeparator = new JSeparator();
        profileActionsLabel = new JLabel();
        networkActionsLabel = new JLabel();
        listScrollPane = new JScrollPane();
        profilesList = new JList<>();
        userProfilesLabel = new JLabel();
        viewProfileButton = new JButton();
        deleteProfileButton = new JButton();
        namesOfChildParents = new JButton();
        friendsOfFriendsButton = new JButton();
        createProfileButton = new JButton();
        createRelationButton = new JButton();
        findRelationButton = new JButton();
        guiMenuBar = new JMenuBar();
        guiManue = new JMenu();
        itemClose = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("MinitNet");
        setMinimumSize(new java.awt.Dimension(700, 450));
        setResizable(false);
        getContentPane().setLayout(null);

        lineSeparator.setOrientation(SwingConstants.VERTICAL);
        lineSeparator.setVerifyInputWhenFocusTarget(false);
        getContentPane().add(lineSeparator);
        lineSeparator.setBounds(430, 0, 2, 380);

        profileActionsLabel.setText("Network Actions");
        getContentPane().add(profileActionsLabel);
        profileActionsLabel.setBounds(510, 0, 100, 40);

        networkActionsLabel.setText("Profile Actions");
        getContentPane().add(networkActionsLabel);
        networkActionsLabel.setBounds(130, 0, 100, 40);

        profilesList.setModel(getUsersList());
        profilesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listScrollPane.setViewportView(profilesList);

        getContentPane().add(listScrollPane);
        listScrollPane.setBounds(0, 60, 130, 320);

        userProfilesLabel.setText("Profiles in Network");
        getContentPane().add(userProfilesLabel);
        userProfilesLabel.setBounds(4, 40, 120, 14);

        viewProfileButton.setText("View Profile");
        viewProfileButton.setMaximumSize(new java.awt.Dimension(80, 20));
        viewProfileButton.setMinimumSize(new java.awt.Dimension(80, 20));
        viewProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                viewProfile(evt);
            }
        });
        getContentPane().add(viewProfileButton);
        viewProfileButton.setBounds(180, 80, 210, 50);

        deleteProfileButton.setText("Delete Profile");
        deleteProfileButton.setMaximumSize(new java.awt.Dimension(80, 20));
        deleteProfileButton.setMinimumSize(new java.awt.Dimension(80, 20));
        deleteProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteProfile(evt);
            }
        });
        getContentPane().add(deleteProfileButton);
        deleteProfileButton.setBounds(180, 150, 210, 50);

        namesOfChildParents.setText("Names of Children/Parents");
        namesOfChildParents.setMaximumSize(new java.awt.Dimension(80, 20));
        namesOfChildParents.setMinimumSize(new java.awt.Dimension(80, 20));
        namesOfChildParents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                findNamesOfChildParents(evt);
            }
        });
        getContentPane().add(namesOfChildParents);
        namesOfChildParents.setBounds(180, 220, 210, 50);

        friendsOfFriendsButton.setText("Friends of Friends");
        friendsOfFriendsButton.setMaximumSize(new java.awt.Dimension(80, 20));
        friendsOfFriendsButton.setMinimumSize(new java.awt.Dimension(80, 20));
        friendsOfFriendsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                findFriendsOfFriends(evt);
            }
        });
        getContentPane().add(friendsOfFriendsButton);
        friendsOfFriendsButton.setBounds(180, 290, 210, 50);

        createProfileButton.setText("Create Profile");
        createProfileButton.setMaximumSize(new java.awt.Dimension(80, 20));
        createProfileButton.setMinimumSize(new java.awt.Dimension(80, 20));
        createProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                createProfile(evt);
            }
        });
        getContentPane().add(createProfileButton);
        createProfileButton.setBounds(470, 90, 210, 50);

        createRelationButton.setText("Create Relationship");
        createRelationButton.setMaximumSize(new java.awt.Dimension(80, 20));
        createRelationButton.setMinimumSize(new java.awt.Dimension(80, 20));
        createRelationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                createRelation(evt);
            }
        });
        getContentPane().add(createRelationButton);
        createRelationButton.setBounds(470, 180, 210, 50);

        findRelationButton.setText("Find Relationship");
        findRelationButton.setMaximumSize(new java.awt.Dimension(80, 20));
        findRelationButton.setMinimumSize(new java.awt.Dimension(80, 20));
        findRelationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                findRelations(evt);
            }
        });
        getContentPane().add(findRelationButton);
        findRelationButton.setBounds(470, 270, 210, 50);

        guiManue.setText("File");

        itemClose.setText("Exit");
        itemClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                closeUI(evt);
            }
        });
        guiManue.add(itemClose);
        guiMenuBar.add(guiManue);
        setJMenuBar(guiMenuBar);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Close the application on menu item exit.
     */
    private void closeUI(ActionEvent evt) {
        System.exit(0);
    }

    /**
     * Handles the click on View Profile button. Check if any profile selected.
     * If selected pass the index to ProfileViewUI.
     */
    private void viewProfile(ActionEvent evt) {
        if (this.profilesList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "No user selected, please select a profile.");
        } else {
            int selectedIndex = profilesList.getSelectedIndex();
            new ProfileViewUI(selectedIndex).setVisible(true);
        }
    }

    /**
     * Handles the click on Delete Profile button. Check if any profile
     * selected. If selected pass the index to deleteProfile method.
     */
    private void deleteProfile(ActionEvent evt) {
        if (this.profilesList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "No user selected, please select a profile.");
        } else {
            int selectedIndex = profilesList.getSelectedIndex();
            FileDataHandler.deleteProfile(selectedIndex);
            this.profilesList.setModel(getUsersList());
        }
    }

    /**
     * Handles the click on View Find Children/Parent button. Check if any
     * profile selected. If selected find all the user connected filter
     * relationships such that relationship type is parent.
     */
    private void findNamesOfChildParents(ActionEvent evt) {
        if (this.profilesList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "No user selected, please select a profile.");
        } else {
            int selectedIndex = profilesList.getSelectedIndex();
            String parentsOrChildren = "";
            for (int i = 0; i < FileDataHandler.profiles.size(); i++) {
                for (int j = 0; j < FileDataHandler.profiles.get(i).getRelationships().size(); j++) {
                    Relationship relation = FileDataHandler.profiles.get(i).getRelationships().get(j);
                    if (relation.getRelatedUser().equals(FileDataHandler.profiles.get(selectedIndex)) && relation.getRelationshipType().equalsIgnoreCase("parent")) {
                        parentsOrChildren += FileDataHandler.profiles.get(i).getName() + "\n";
                    }
                }
            }
            JOptionPane.showMessageDialog(null, parentsOrChildren);
        }
    }

    /**
     * Handles the click on View Find Friends of Friends. Check if any profile
     * selected. If selected find all the user connected and their connections
     * also. Put all the connections in arraylist. Remove duplicates and itself.
     */
    private void findFriendsOfFriends(ActionEvent evt) {
        if (this.profilesList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "No user selected, please select a profile.");
        } else {
            int selectedIndex = profilesList.getSelectedIndex();
            ArrayList<String> friendsOfFriends = new ArrayList<>();
            for (int i = 0; i < FileDataHandler.profiles.get(selectedIndex).getRelationships().size(); i++) {
                Profile connectedUser = FileDataHandler.profiles.get(selectedIndex).getRelationships().get(i).getRelatedUser();
                for (int j = 0; j < connectedUser.getRelationships().size(); j++) {
                    friendsOfFriends.add(connectedUser.getRelationships().get(j).getRelatedUser().getName());
                }
            }

            Set<String> hs = new HashSet<>();
            hs.addAll(friendsOfFriends);
            friendsOfFriends.clear();
            friendsOfFriends.addAll(hs);
            friendsOfFriends.remove(FileDataHandler.profiles.get(selectedIndex).getName());
            String output = "";
            for (String user : friendsOfFriends) {
                output += user + "\n";
            }
            JOptionPane.showMessageDialog(null, output);
        }
    }

    /**
     * Handles the click on Create Profile button.
     */
    private void createProfile(ActionEvent evt) {
        new CreateProfileUI(this).setVisible(true);
    }

    /**
     * Handles the click on Create Relation button.
     */
    private void createRelation(ActionEvent evt) {
        new CreateRelationsUI().setVisible(true);
    }

    /**
     * Handles the click on Find Profile button.
     */
    private void findRelations(ActionEvent evt) {
        new FindRelationsUI().setVisible(true);
    }

    /**
     * Creates updated list of users in network.
     */
    public void updateList() {
        this.profilesList.setModel(getUsersList());
    }

    /**
     * Create ListModel from profiles arraylist in data storage class.
     */
    public static AbstractListModel<String> getUsersList() {
        return new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return FileDataHandler.profiles.size();
            }

            @Override
            public String getElementAt(int index) {
                return FileDataHandler.profiles.get(index).getName();
            }

        };
    }

}
