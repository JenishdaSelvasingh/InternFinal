package com.example.demo.DTO;

public class LoginReqDto {
    private String adminID;
    private String password;

    public String getAdminID() {
        return this.adminID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setAdminID(final String adminID) {
        this.adminID = adminID;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof LoginReqDto)) {
            return false;
        } else {
            LoginReqDto other = (LoginReqDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
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

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LoginReqDto;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        Object $adminID = this.getAdminID();
        result = result * 59 + ($adminID == null ? 43 : $adminID.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getAdminID();
        return "LoginReqDto(adminID=" + var10000 + ", password=" + this.getPassword() + ")";
    }

    public LoginReqDto(final String adminID, final String password) {
        this.adminID = adminID;
        this.password = password;
    }

    public LoginReqDto() {
    }
}
