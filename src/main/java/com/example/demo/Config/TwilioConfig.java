

package com.example.demo.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(
        prefix = "twilio"
)
@Configuration
public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String trialNumber;

    public TwilioConfig() {
    }

    public String getAccountSid() {
        return this.accountSid;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public String getTrialNumber() {
        return this.trialNumber;
    }

    public void setAccountSid(final String accountSid) {
        this.accountSid = accountSid;
    }

    public void setAuthToken(final String authToken) {
        this.authToken = authToken;
    }

    public void setTrialNumber(final String trialNumber) {
        this.trialNumber = trialNumber;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TwilioConfig)) {
            return false;
        } else {
            TwilioConfig other = (TwilioConfig)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$accountSid = this.getAccountSid();
                    Object other$accountSid = other.getAccountSid();
                    if (this$accountSid == null) {
                        if (other$accountSid == null) {
                            break label47;
                        }
                    } else if (this$accountSid.equals(other$accountSid)) {
                        break label47;
                    }

                    return false;
                }

                Object this$authToken = this.getAuthToken();
                Object other$authToken = other.getAuthToken();
                if (this$authToken == null) {
                    if (other$authToken != null) {
                        return false;
                    }
                } else if (!this$authToken.equals(other$authToken)) {
                    return false;
                }

                Object this$trialNumber = this.getTrialNumber();
                Object other$trialNumber = other.getTrialNumber();
                if (this$trialNumber == null) {
                    if (other$trialNumber != null) {
                        return false;
                    }
                } else if (!this$trialNumber.equals(other$trialNumber)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TwilioConfig;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        Object $accountSid = this.getAccountSid();
        result = result * 59 + ($accountSid == null ? 43 : $accountSid.hashCode());
        Object $authToken = this.getAuthToken();
        result = result * 59 + ($authToken == null ? 43 : $authToken.hashCode());
        Object $trialNumber = this.getTrialNumber();
        result = result * 59 + ($trialNumber == null ? 43 : $trialNumber.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getAccountSid();
        return "TwilioConfig(accountSid=" + var10000 + ", authToken=" + this.getAuthToken() + ", trialNumber=" + this.getTrialNumber() + ")";
    }
}
