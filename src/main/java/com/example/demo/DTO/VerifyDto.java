package com.example.demo.DTO;

public class VerifyDto {
    private String email;
    private String otp;
    private String password;

    public String getEmail() {
        return this.email;
    }

    public String getOtp() {
        return this.otp;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setOtp(final String otp) {
        this.otp = otp;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof VerifyDto)) {
            return false;
        } else {
            VerifyDto other = (VerifyDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$email = this.getEmail();
                    Object other$email = other.getEmail();
                    if (this$email == null) {
                        if (other$email == null) {
                            break label47;
                        }
                    } else if (this$email.equals(other$email)) {
                        break label47;
                    }

                    return false;
                }

                Object this$otp = this.getOtp();
                Object other$otp = other.getOtp();
                if (this$otp == null) {
                    if (other$otp != null) {
                        return false;
                    }
                } else if (!this$otp.equals(other$otp)) {
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
        return other instanceof VerifyDto;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        Object $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        Object $otp = this.getOtp();
        result = result * 59 + ($otp == null ? 43 : $otp.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getEmail();
        return "VerifyDto(email=" + var10000 + ", otp=" + this.getOtp() + ", password=" + this.getPassword() + ")";
    }

    public VerifyDto() {
    }

    public VerifyDto(final String email, final String otp, final String password) {
        this.email = email;
        this.otp = otp;
        this.password = password;
    }
}
