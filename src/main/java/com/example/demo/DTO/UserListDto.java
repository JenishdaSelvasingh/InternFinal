package com.example.demo.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class UserListDto {
    private String name;
    private String ntId;
    private List<String> deviceId;
    private String email;
    private String manager;
    private LocalDateTime timeFormatted;

    public String getName() {

        return this.name;
    }

    public String getNtId() {

        return this.ntId;
    }

    public List<String> getDeviceId() {

        return this.deviceId;
    }

    public String getEmail() {

        return this.email;
    }

    public String getManager() {

        return this.manager;
    }

    public LocalDateTime getTimeFormatted() {

        return this.timeFormatted;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setNtId(final String ntId) {

        this.ntId = ntId;
    }

    public void setDeviceId(final List<String> deviceId) {

        this.deviceId = deviceId;
    }

    public void setEmail(final String email) {

        this.email = email;
    }

    public void setManager(final String manager) {

        this.manager = manager;
    }

    public void setTimeFormatted(final LocalDateTime timeFormatted) {

        this.timeFormatted = timeFormatted;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof UserListDto)) {
            return false;
        } else {
            UserListDto other = (UserListDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$ntId = this.getNtId();
                Object other$ntId = other.getNtId();
                if (this$ntId == null) {
                    if (other$ntId != null) {
                        return false;
                    }
                } else if (!this$ntId.equals(other$ntId)) {
                    return false;
                }

                Object this$deviceId = this.getDeviceId();
                Object other$deviceId = other.getDeviceId();
                if (this$deviceId == null) {
                    if (other$deviceId != null) {
                        return false;
                    }
                } else if (!this$deviceId.equals(other$deviceId)) {
                    return false;
                }

                label62: {
                    Object this$email = this.getEmail();
                    Object other$email = other.getEmail();
                    if (this$email == null) {
                        if (other$email == null) {
                            break label62;
                        }
                    } else if (this$email.equals(other$email)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$manager = this.getManager();
                    Object other$manager = other.getManager();
                    if (this$manager == null) {
                        if (other$manager == null) {
                            break label55;
                        }
                    } else if (this$manager.equals(other$manager)) {
                        break label55;
                    }

                    return false;
                }

                Object this$timeFormatted = this.getTimeFormatted();
                Object other$timeFormatted = other.getTimeFormatted();
                if (this$timeFormatted == null) {
                    if (other$timeFormatted != null) {
                        return false;
                    }
                } else if (!this$timeFormatted.equals(other$timeFormatted)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {

        return other instanceof UserListDto;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $ntId = this.getNtId();
        result = result * 59 + ($ntId == null ? 43 : $ntId.hashCode());
        Object $deviceId = this.getDeviceId();
        result = result * 59 + ($deviceId == null ? 43 : $deviceId.hashCode());
        Object $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        Object $manager = this.getManager();
        result = result * 59 + ($manager == null ? 43 : $manager.hashCode());
        Object $timeFormatted = this.getTimeFormatted();
        result = result * 59 + ($timeFormatted == null ? 43 : $timeFormatted.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getName();
        return "UserListDto(name=" + var10000 + ", ntId=" + this.getNtId() + ", deviceId=" + this.getDeviceId() + ", email=" + this.getEmail() + ", manager=" + this.getManager() + ", timeFormatted=" + this.getTimeFormatted() + ")";
    }

    public UserListDto() {
    }

    public UserListDto(final String name, final String ntId, final List<String> deviceId, final String email, final String manager, final LocalDateTime timeFormatted) {
        this.name = name;
        this.ntId = ntId;
        this.deviceId = deviceId;
        this.email = email;
        this.manager = manager;
        this.timeFormatted = timeFormatted;
    }
}
