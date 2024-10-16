package com.example.demo.DTO;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class LoggerDto {
    private Long id;
    private String ntId;
    private List<String> deviceId;
    private LocalDateTime timeFormatted;

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

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof LoggerDto)) {
            return false;
        } else {
            LoggerDto other = (LoggerDto)o;
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
        return other instanceof LoggerDto;
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
        return result;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "LoggerDto(id=" + var10000 + ", ntId=" + this.getNtId() + ", deviceId=" + this.getDeviceId() + ", timeFormatted=" + this.getTimeFormatted() + ")";
    }

    public LoggerDto(final Long id, final String ntId, final List<String> deviceId, final LocalDateTime timeFormatted) {
        this.id = id;
        this.ntId = ntId;
        this.deviceId = deviceId;
        this.timeFormatted = timeFormatted;
    }

    public LoggerDto() {
    }
}
