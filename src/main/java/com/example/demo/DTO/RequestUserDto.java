package com.example.demo.DTO;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RequestUserDto {
    private Long id;
    private String name;
    private String ntId;
    private List<String> deviceId;
    private String email;
    private String manager;
    private Integer otp;

    public RequestUserDto(List<String> deviceId, String name, String email, String manager, String ntId) {
        this.deviceId = deviceId;
        this.name = name;
        this.email = email;
        this.ntId = ntId;
        this.manager = manager;
    }

    public Long getId() {
        return this.id;
    }

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

    public Integer getOtp() {
        return this.otp;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public void setOtp(final Integer otp) {
        this.otp = otp;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RequestUserDto)) {
            return false;
        } else {
            RequestUserDto other = (RequestUserDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label95: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label95;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label95;
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

                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                label74: {
                    Object this$ntId = this.getNtId();
                    Object other$ntId = other.getNtId();
                    if (this$ntId == null) {
                        if (other$ntId == null) {
                            break label74;
                        }
                    } else if (this$ntId.equals(other$ntId)) {
                        break label74;
                    }

                    return false;
                }

                label67: {
                    Object this$deviceId = this.getDeviceId();
                    Object other$deviceId = other.getDeviceId();
                    if (this$deviceId == null) {
                        if (other$deviceId == null) {
                            break label67;
                        }
                    } else if (this$deviceId.equals(other$deviceId)) {
                        break label67;
                    }

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

                Object this$manager = this.getManager();
                Object other$manager = other.getManager();
                if (this$manager == null) {
                    if (other$manager != null) {
                        return false;
                    }
                } else if (!this$manager.equals(other$manager)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RequestUserDto;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $otp = this.getOtp();
        result = result * 59 + ($otp == null ? 43 : $otp.hashCode());
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
        return result;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "RequestUserDto(id=" + var10000 + ", name=" + this.getName() + ", ntId=" + this.getNtId() + ", deviceId=" + this.getDeviceId() + ", email=" + this.getEmail() + ", manager=" + this.getManager() + ", otp=" + this.getOtp() + ")";
    }

    public RequestUserDto(final Long id, final String name, final String ntId, final List<String> deviceId, final String email, final String manager, final Integer otp) {
        this.id = id;
        this.name = name;
        this.ntId = ntId;
        this.deviceId = deviceId;
        this.email = email;
        this.manager = manager;
        this.otp = otp;
    }

    public RequestUserDto() {
    }
}
