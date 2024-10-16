

package com.example.demo.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Entity
@Component
public class UserEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "name"
    )
    private String name;
    @Column(
            name = "ntId",
            unique = true
    )
    private String ntId;
    @Column(
            name = "deviceId"
    )
    private List<String> deviceId;
    @Column(
            name = "emailId",
            unique = true
    )
    private String email;
    @Column(
            name = "manager"
    )
    private String manager;
    @Column(
            name = "time"
    )
    private LocalDateTime timeFormatted;
    @Column(
            name = "otp"
    )
    private Integer otp;
    @Column(
            name = "otpExpiry"
    )
    private LocalDateTime otpExpiry;
    @OneToMany(
            mappedBy = "userEntity",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private Set<AssetEntity> assetEntity;

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

    public LocalDateTime getTimeFormatted() {
        return this.timeFormatted;
    }

    public Integer getOtp() {
        return this.otp;
    }

    public LocalDateTime getOtpExpiry() {
        return this.otpExpiry;
    }

    public Set<AssetEntity> getAssetEntity() {
        return this.assetEntity;
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

    public void setTimeFormatted(final LocalDateTime timeFormatted) {
        this.timeFormatted = timeFormatted;
    }

    public void setOtp(final Integer otp) {
        this.otp = otp;
    }

    public void setOtpExpiry(final LocalDateTime otpExpiry) {
        this.otpExpiry = otpExpiry;
    }

    public void setAssetEntity(final Set<AssetEntity> assetEntity) {
        this.assetEntity = assetEntity;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof UserEntity)) {
            return false;
        } else {
            UserEntity other = (UserEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
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

                label110: {
                    Object this$ntId = this.getNtId();
                    Object other$ntId = other.getNtId();
                    if (this$ntId == null) {
                        if (other$ntId == null) {
                            break label110;
                        }
                    } else if (this$ntId.equals(other$ntId)) {
                        break label110;
                    }

                    return false;
                }

                label103: {
                    Object this$deviceId = this.getDeviceId();
                    Object other$deviceId = other.getDeviceId();
                    if (this$deviceId == null) {
                        if (other$deviceId == null) {
                            break label103;
                        }
                    } else if (this$deviceId.equals(other$deviceId)) {
                        break label103;
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

                label89: {
                    Object this$manager = this.getManager();
                    Object other$manager = other.getManager();
                    if (this$manager == null) {
                        if (other$manager == null) {
                            break label89;
                        }
                    } else if (this$manager.equals(other$manager)) {
                        break label89;
                    }

                    return false;
                }

                label82: {
                    Object this$timeFormatted = this.getTimeFormatted();
                    Object other$timeFormatted = other.getTimeFormatted();
                    if (this$timeFormatted == null) {
                        if (other$timeFormatted == null) {
                            break label82;
                        }
                    } else if (this$timeFormatted.equals(other$timeFormatted)) {
                        break label82;
                    }

                    return false;
                }

                Object this$otpExpiry = this.getOtpExpiry();
                Object other$otpExpiry = other.getOtpExpiry();
                if (this$otpExpiry == null) {
                    if (other$otpExpiry != null) {
                        return false;
                    }
                } else if (!this$otpExpiry.equals(other$otpExpiry)) {
                    return false;
                }

                Object this$assetEntity = this.getAssetEntity();
                Object other$assetEntity = other.getAssetEntity();
                if (this$assetEntity == null) {
                    if (other$assetEntity != null) {
                        return false;
                    }
                } else if (!this$assetEntity.equals(other$assetEntity)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserEntity;
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
        Object $timeFormatted = this.getTimeFormatted();
        result = result * 59 + ($timeFormatted == null ? 43 : $timeFormatted.hashCode());
        Object $otpExpiry = this.getOtpExpiry();
        result = result * 59 + ($otpExpiry == null ? 43 : $otpExpiry.hashCode());
        Object $assetEntity = this.getAssetEntity();
        result = result * 59 + ($assetEntity == null ? 43 : $assetEntity.hashCode());
        return result;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "UserEntity(id=" + var10000 + ", name=" + this.getName() + ", ntId=" + this.getNtId() + ", deviceId=" + this.getDeviceId() + ", email=" + this.getEmail() + ", manager=" + this.getManager() + ", timeFormatted=" + this.getTimeFormatted() + ", otp=" + this.getOtp() + ", otpExpiry=" + this.getOtpExpiry() + ", assetEntity=" + this.getAssetEntity() + ")";
    }

    public UserEntity(final Long id, final String name, final String ntId, final List<String> deviceId, final String email, final String manager, final LocalDateTime timeFormatted, final Integer otp, final LocalDateTime otpExpiry, final Set<AssetEntity> assetEntity) {
        this.id = id;
        this.name = name;
        this.ntId = ntId;
        this.deviceId = deviceId;
        this.email = email;
        this.manager = manager;
        this.timeFormatted = timeFormatted;
        this.otp = otp;
        this.otpExpiry = otpExpiry;
        this.assetEntity = assetEntity;
    }

    public UserEntity() {
    }
}
