
import java.util.Objects;

/**
 * This class holds data of a relationship between two profiles.
 *
 * @author Abdurahman
 */
public class Relationship {

    private Profile relatedUser;
    private String relationshipType;

    public Relationship(Profile relatedUser, String relationshipType) {
        this.relatedUser = relatedUser;
        this.relationshipType = relationshipType;
    }

    public Profile getRelatedUser() {
        return relatedUser;
    }

    public void setRelatedUser(Profile relatedUser) {
        this.relatedUser = relatedUser;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    @Override
    public String toString() {
        return relatedUser.getName() + ", " + relationshipType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.relatedUser);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Relationship other = (Relationship) obj;
        if (!Objects.equals(this.relatedUser, other.relatedUser)) {
            return false;
        }
        return true;
    }

}
