package com.example.demo.DTO;

import org.springframework.stereotype.Component;

@Component
public class ResetDto {
    private String email;
    private Integer otp;

    public String getEmail() {
        return this.email;
    }

    public Integer getOtp() {
        return this.otp;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setOtp(final Integer otp) {
        this.otp = otp;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResetDto)) {
            return false;
        } else {
            ResetDto other = (ResetDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$otp = this.getOtp();
                Object other$otp = other.getOtp();
                if (this$otp == null) {
                    if (other$otp != null) {
                        return false;
                    }
                } else if (!this$otp.equals(other$otp)) {
                    return false;
                }

                Object this$email = this.getEmail();
                Object other$email = other.getEmail();
                if (this$email == null) {
                    if (other$email != null) {
                        return false;
                    }
                } else if (!this$email.equals(other$email)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ResetDto;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        Object $otp = this.getOtp();
        result = result * 59 + ($otp == null ? 43 : $otp.hashCode());
        Object $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getEmail();
        return "ResetDto(email=" + var10000 + ", otp=" + this.getOtp() + ")";
    }

    public ResetDto(final String email, final Integer otp) {
        this.email = email;
        this.otp = otp;
    }

    public ResetDto() {
    }
}
