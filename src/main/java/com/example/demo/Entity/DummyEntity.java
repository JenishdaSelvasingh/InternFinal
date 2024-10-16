package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import org.springframework.stereotype.Component;

@Entity
@Component
public class DummyEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String adminID;
    private List<String> accessTags;

    public DummyEntity(String adminID, List<String> accessTags) {
        this.adminID = adminID;
        this.accessTags = accessTags;
    }

    public Long getId() {
        return this.id;
    }

    public String getAdminID() {
        return this.adminID;
    }

    public List<String> getAccessTags() {
        return this.accessTags;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setAdminID(final String adminID) {
        this.adminID = adminID;
    }

    public void setAccessTags(final List<String> accessTags) {
        this.accessTags = accessTags;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof DummyEntity)) {
            return false;
        } else {
            DummyEntity other = (DummyEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label47;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label47;
                    }

                    return false;
                }

                Object this$adminID = this.getAdminID();
                Object other$adminID = other.getAdminID();
                if (this$adminID == null) {
                    if (other$adminID != null) {
                        return false;
                    }
                } else if (!this$adminID.equals(other$adminID)) {
                    return false;
                }

                Object this$accessTags = this.getAccessTags();
                Object other$accessTags = other.getAccessTags();
                if (this$accessTags == null) {
                    if (other$accessTags != null) {
                        return false;
                    }
                } else if (!this$accessTags.equals(other$accessTags)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DummyEntity;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $adminID = this.getAdminID();
        result = result * 59 + ($adminID == null ? 43 : $adminID.hashCode());
        Object $accessTags = this.getAccessTags();
        result = result * 59 + ($accessTags == null ? 43 : $accessTags.hashCode());
        return result;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "DummyEntity(id=" + var10000 + ", adminID=" + this.getAdminID() + ", accessTags=" + this.getAccessTags() + ")";
    }

    public DummyEntity(final Long id, final String adminID, final List<String> accessTags) {
        this.id = id;
        this.adminID = adminID;
        this.accessTags = accessTags;
    }

    public DummyEntity() {
    }
}
