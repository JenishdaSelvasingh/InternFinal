package com.example.demo.Config;

import com.example.demo.enums.AccessTag;
import org.springframework.stereotype.Component;
import java.util.List;



@Component
public class LoginReqMeta {
    private String adminID;
    private String password;
    private List<AccessTag> accessTags;

    public String getAdminID() {
        return this.adminID;
    }

    public String getPassword() {
        return this.password;
    }

    public List<AccessTag> getAccessTags() {
        return this.accessTags;
    }

    public void setAdminID(final String adminID) {
        this.adminID = adminID;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setAccessTags(final List<AccessTag> accessTags) {
        this.accessTags = accessTags;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof LoginReqMeta)) {
            return false;
        } else {
            LoginReqMeta other = (LoginReqMeta)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$adminID = this.getAdminID();
                    Object other$adminID = other.getAdminID();
                    if (this$adminID == null) {
                        if (other$adminID == null) {
                            break label47;
                        }
                    } else if (this$adminID.equals(other$adminID)) {
                        break label47;
                    }

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
        return other instanceof LoginReqMeta;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        Object $adminID = this.getAdminID();
        result = result * 59 + ($adminID == null ? 43 : $adminID.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $accessTags = this.getAccessTags();
        result = result * 59 + ($accessTags == null ? 43 : $accessTags.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getAdminID();
        return "LoginReqMeta(adminID=" + var10000 + ", password=" + this.getPassword() + ", accessTags=" + this.getAccessTags() + ")";
    }

    public LoginReqMeta(final String adminID, final String password, final List<AccessTag> accessTags) {
        this.adminID = adminID;
        this.password = password;
        this.accessTags = accessTags;
    }

    public LoginReqMeta() {
    }
}
