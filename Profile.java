
import Exceptions.NoAvailableException;
import Exceptions.NotToBeClassmatesException;
import Exceptions.NotToBeColleaguesException;
import Exceptions.NotToBeCoupledException;
import Exceptions.NotToBeFriendsException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Profile class holds data related to profile.
 *
 * @author Abdullah
 */
public class Profile {

    private String name;
    private String profilePicture;
    private String status;
    private Integer age;
    private String gender;
    private ArrayList<Relationship> relationships;
    private PersonType type;
    private String state;

    /**
     * Six parameters constructor.
     *
     * @param name - Name of the user.
     * @param profilePicture - Profile picture path of the user.
     * @param status - Status of the user.
     * @param gender - Gender of the user.
     * @param age - Age of the user.
     * @param state - Name of the state where user lives in.
     */
    public Profile(String name, String profilePicture, String status, String gender, Integer age, String state) {
        this.name = name;
        this.profilePicture = profilePicture;
        this.status = status;
        this.age = age;
        this.state = state;
        if (gender.trim().equalsIgnoreCase("M")) {
            this.gender = "Male";
        } else {
            this.gender = "Female";
        }
        this.gender = gender;
        if (age > 16) {
            this.type = PersonType.ADULT;
        } else if (age >= 3 && age <= 16) {
            this.type = PersonType.CHILD;
        } else if (age <= 2) {
            this.type = PersonType.YOUNGCHILD;
        }
        this.relationships = new ArrayList<>();
    }

    /**
     * Single parameter constructor mostly used for finding object in arraylists
     * as equals method compare name only.
     *
     * @param name
     */
    public Profile(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
        if (age > 16) {
            this.type = PersonType.ADULT;
        } else if (age >= 3 && age <= 16) {
            this.type = PersonType.CHILD;
        } else if (age < 2) {
            this.type = PersonType.YOUNGCHILD;
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(ArrayList<Relationship> relationships) {
        this.relationships = relationships;
    }

    public PersonType getType() {
        return type;
    }

    /**
     * Try to create relationship between two profiles, extensive exceptional
     * handling as per spec sheet.
     *
     * @param otherUser - Other user's profile.
     * @param relationshipType - Relationship type.
     * @throws Exception
     */
    public void addRelationShip(Profile otherUser, String relationshipType) throws Exception {
        if (relationshipType.equalsIgnoreCase("friends")) {
            if (this.getType() == type.CHILD && otherUser.getType() == type.CHILD) {
                if ((this.getAge() - otherUser.getAge()) <= 3 && (this.getAge() - otherUser.getAge()) >= -3) {
                    Relationship relation = new Relationship(otherUser, relationshipType);
                    Relationship relationCopy = new Relationship(this, relationshipType);
                    this.getRelationships().add(relation);
                    otherUser.getRelationships().add(relationCopy);
                } else {
                    throw new NotToBeFriendsException();
                }
            } else if (this.getType() == type.ADULT && otherUser.getType() == type.ADULT) {
                Relationship relation = new Relationship(otherUser, relationshipType);
                Relationship relationCopy = new Relationship(this, relationshipType);
                this.getRelationships().add(relation);
                otherUser.getRelationships().add(relationCopy);
            } else if (this.getType() != otherUser.getType()) {
                throw new NotToBeFriendsException();
            }
        } else if (relationshipType.equalsIgnoreCase("colleagues")) {
            if (this.getType() == type.CHILD || otherUser.getType() == type.CHILD) {
                throw new NotToBeColleaguesException();
            } else {
                Relationship relation = new Relationship(otherUser, relationshipType);
                Relationship relationCopy = new Relationship(this, relationshipType);
                this.getRelationships().add(relation);
                otherUser.getRelationships().add(relationCopy);
            }
        } else if (relationshipType.equalsIgnoreCase("classmates")) {
            if (this.getType() == type.YOUNGCHILD || otherUser.getType() == type.YOUNGCHILD) {
                throw new NotToBeClassmatesException();
            } else {
                Relationship relation = new Relationship(otherUser, relationshipType);
                Relationship relationCopy = new Relationship(this, relationshipType);
                this.getRelationships().add(relation);
                otherUser.getRelationships().add(relationCopy);
            }
        } else if (relationshipType.equalsIgnoreCase("couple")) {
            if (this.getType() != type.ADULT || otherUser.getType() != type.ADULT) {
                throw new NotToBeCoupledException();
            }
            int coupleRelationsThis = 0;
            int coupleRelationsOther = 0;
            for (int i = 0; i < this.getRelationships().size(); i++) {
                if (this.getRelationships().get(i).getRelationshipType().equalsIgnoreCase("couple")) {
                    coupleRelationsThis += 1;
                }
            }
            for (int i = 0; i < otherUser.getRelationships().size(); i++) {
                if (otherUser.getRelationships().get(i).getRelationshipType().equalsIgnoreCase("couple")) {
                    coupleRelationsOther += 1;
                }
            }
            if (coupleRelationsThis > 0 || coupleRelationsOther > 0) {
                throw new NoAvailableException();
            }
            Relationship relation = new Relationship(otherUser, relationshipType);
            Relationship relationCopy = new Relationship(this, relationshipType);
            this.getRelationships().add(relation);
            otherUser.getRelationships().add(relationCopy);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * Compares profile object with other profile class object such that names
     * are equal.
     *
     * @param obj - Other profile object.
     * @return
     */
    @Override
    public boolean equals(Object obj
    ) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profile other = (Profile) obj;
        return this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return "name=" + name + ", profilePicture=" + profilePicture + ", status=" + status + ", age=" + age + ", gender=" + gender + ", relationships=" + relationships + ", type=" + type;
    }

}
