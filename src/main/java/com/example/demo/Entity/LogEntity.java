

package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "logs"
)
public class LogEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "ntId",
            unique = false
    )
    private String ntId;
    @Column(
            name = "deviceId"
    )
    private List<String> deviceId;
    @Column(
            name = "time"
    )
    private LocalDateTime timeFormatted;
    @Column(
            name = "emailId"
    )
    private String email;

    public LogEntity(final Long id, final String ntId, final List<String> deviceId, final LocalDateTime timeFormatted, final String email) {
        this.id = id;
        this.ntId = ntId;
        this.deviceId = deviceId;
        this.timeFormatted = timeFormatted;
        this.email = email;
    }

    public LogEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNtId() {
        return this.ntId;
    }

    public List<String> getDeviceId() {
        return this.deviceId;
    }

    public LocalDateTime getTimeFormatted() {
        return this.timeFormatted;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setNtId(final String ntId) {
        this.ntId = ntId;
    }

    public void setDeviceId(final List<String> deviceId) {
        this.deviceId = deviceId;
    }

    public void setTimeFormatted(final LocalDateTime timeFormatted) {
        this.timeFormatted = timeFormatted;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof LogEntity)) {
            return false;
        } else {
            LogEntity other = (LogEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label71;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label71;
                    }

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

                label57: {
                    Object this$deviceId = this.getDeviceId();
                    Object other$deviceId = other.getDeviceId();
                    if (this$deviceId == null) {
                        if (other$deviceId == null) {
                            break label57;
                        }
                    } else if (this$deviceId.equals(other$deviceId)) {
                        break label57;
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

                Object this$email = this.getEmail();
                Object other$email = other.getEmail();
                if (this$email == null) {
                    if (other$email == null) {
                        return true;
                    }
                } else if (this$email.equals(other$email)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LogEntity;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $ntId = this.getNtId();
        result = result * 59 + ($ntId == null ? 43 : $ntId.hashCode());
        Object $deviceId = this.getDeviceId();
        result = result * 59 + ($deviceId == null ? 43 : $deviceId.hashCode());
        Object $timeFormatted = this.getTimeFormatted();
        result = result * 59 + ($timeFormatted == null ? 43 : $timeFormatted.hashCode());
        Object $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "LogEntity(id=" + var10000 + ", ntId=" + this.getNtId() + ", deviceId=" + this.getDeviceId() + ", timeFormatted=" + this.getTimeFormatted() + ", email=" + this.getEmail() + ")";
    }
}
