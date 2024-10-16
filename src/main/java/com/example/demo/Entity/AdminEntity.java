package com.example.demo.Entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.List;
import org.springframework.stereotype.Component;

@Entity
@Component
public class AdminEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "adminid",
            nullable = false
    )
    private String adminID;
    @Column(
            name = "password",
            nullable = false
    )
    private String password;
    @ElementCollection
    @CollectionTable(
            name = "access_tags",
            joinColumns = {@JoinColumn(
                    name = "admin_id"
            )}
    )
    @Column(
            name = "access_tag"
    )
    private List<String> accessTags;

    public Long getId() {
        return this.id;
    }

    public String getAdminID() {
        return this.adminID;
    }

    public String getPassword() {
        return this.password;
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

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setAccessTags(final List<String> accessTags) {
        this.accessTags = accessTags;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AdminEntity)) {
            return false;
        } else {
            AdminEntity other = (AdminEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label59;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label59;
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

                Object this$password = this.getPassword();
                Object other$password = other.getPassword();
                if (this$password == null) {
                    if (other$password != null) {
                        return false;
                    }
                } else if (!this$password.equals(other$password)) {
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
        return other instanceof AdminEntity;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $adminID = this.getAdminID();
        result = result * 59 + ($adminID == null ? 43 : $adminID.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $accessTags = this.getAccessTags();
        result = result * 59 + ($accessTags == null ? 43 : $accessTags.hashCode());
        return result;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "AdminEntity(id=" + var10000 + ", adminID=" + this.getAdminID() + ", password=" + this.getPassword() + ", accessTags=" + this.getAccessTags() + ")";
    }

    public AdminEntity(final Long id, final String adminID, final String password, final List<String> accessTags) {
        this.id = id;
        this.adminID = adminID;
        this.password = password;
        this.accessTags = accessTags;
    }

    public AdminEntity() {
    }
}
