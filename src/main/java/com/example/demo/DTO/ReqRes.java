package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.example.demo.Entity.AssetEntity;
import java.util.List;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(Include.NON_NULL)
public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String email;
    private String role;
    private String password;
    private String assetID;
    private String assetType;
    private String assetModel;
    private Boolean isActive;
    private String notes;
    private List<AssetEntity> assets;

    public ReqRes() {
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getError() {
        return this.error;
    }

    public String getMessage() {
        return this.message;
    }

    public String getToken() {
        return this.token;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public String getExpirationTime() {
        return this.expirationTime;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getRole() {
        return this.role;
    }

    public String getPassword() {
        return this.password;
    }

    public String getAssetID() {
        return this.assetID;
    }

    public String getAssetType() {
        return this.assetType;
    }

    public String getAssetModel() {
        return this.assetModel;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public String getNotes() {
        return this.notes;
    }

    public List<AssetEntity> getAssets() {
        return this.assets;
    }

    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setExpirationTime(final String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setAssetID(final String assetID) {
        this.assetID = assetID;
    }

    public void setAssetType(final String assetType) {
        this.assetType = assetType;
    }

    public void setAssetModel(final String assetModel) {
        this.assetModel = assetModel;
    }

    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }

    public void setAssets(final List<AssetEntity> assets) {
        this.assets = assets;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ReqRes)) {
            return false;
        } else {
            ReqRes other = (ReqRes)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getStatusCode() != other.getStatusCode()) {
                return false;
            } else {
                label193: {
                    Object this$isActive = this.getIsActive();
                    Object other$isActive = other.getIsActive();
                    if (this$isActive == null) {
                        if (other$isActive == null) {
                            break label193;
                        }
                    } else if (this$isActive.equals(other$isActive)) {
                        break label193;
                    }

                    return false;
                }

                Object this$error = this.getError();
                Object other$error = other.getError();
                if (this$error == null) {
                    if (other$error != null) {
                        return false;
                    }
                } else if (!this$error.equals(other$error)) {
                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                label172: {
                    Object this$token = this.getToken();
                    Object other$token = other.getToken();
                    if (this$token == null) {
                        if (other$token == null) {
                            break label172;
                        }
                    } else if (this$token.equals(other$token)) {
                        break label172;
                    }

                    return false;
                }

                Object this$refreshToken = this.getRefreshToken();
                Object other$refreshToken = other.getRefreshToken();
                if (this$refreshToken == null) {
                    if (other$refreshToken != null) {
                        return false;
                    }
                } else if (!this$refreshToken.equals(other$refreshToken)) {
                    return false;
                }

                Object this$expirationTime = this.getExpirationTime();
                Object other$expirationTime = other.getExpirationTime();
                if (this$expirationTime == null) {
                    if (other$expirationTime != null) {
                        return false;
                    }
                } else if (!this$expirationTime.equals(other$expirationTime)) {
                    return false;
                }

                label151: {
                    Object this$name = this.getName();
                    Object other$name = other.getName();
                    if (this$name == null) {
                        if (other$name == null) {
                            break label151;
                        }
                    } else if (this$name.equals(other$name)) {
                        break label151;
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

                label137: {
                    Object this$role = this.getRole();
                    Object other$role = other.getRole();
                    if (this$role == null) {
                        if (other$role == null) {
                            break label137;
                        }
                    } else if (this$role.equals(other$role)) {
                        break label137;
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

                label123: {
                    Object this$assetID = this.getAssetID();
                    Object other$assetID = other.getAssetID();
                    if (this$assetID == null) {
                        if (other$assetID == null) {
                            break label123;
                        }
                    } else if (this$assetID.equals(other$assetID)) {
                        break label123;
                    }

                    return false;
                }

                Object this$assetType = this.getAssetType();
                Object other$assetType = other.getAssetType();
                if (this$assetType == null) {
                    if (other$assetType != null) {
                        return false;
                    }
                } else if (!this$assetType.equals(other$assetType)) {
                    return false;
                }

                label109: {
                    Object this$assetModel = this.getAssetModel();
                    Object other$assetModel = other.getAssetModel();
                    if (this$assetModel == null) {
                        if (other$assetModel == null) {
                            break label109;
                        }
                    } else if (this$assetModel.equals(other$assetModel)) {
                        break label109;
                    }

                    return false;
                }

                label102: {
                    Object this$notes = this.getNotes();
                    Object other$notes = other.getNotes();
                    if (this$notes == null) {
                        if (other$notes == null) {
                            break label102;
                        }
                    } else if (this$notes.equals(other$notes)) {
                        break label102;
                    }

                    return false;
                }

                Object this$assets = this.getAssets();
                Object other$assets = other.getAssets();
                if (this$assets == null) {
                    if (other$assets != null) {
                        return false;
                    }
                } else if (!this$assets.equals(other$assets)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ReqRes;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        result = result * 59 + this.getStatusCode();
        Object $isActive = this.getIsActive();
        result = result * 59 + ($isActive == null ? 43 : $isActive.hashCode());
        Object $error = this.getError();
        result = result * 59 + ($error == null ? 43 : $error.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $token = this.getToken();
        result = result * 59 + ($token == null ? 43 : $token.hashCode());
        Object $refreshToken = this.getRefreshToken();
        result = result * 59 + ($refreshToken == null ? 43 : $refreshToken.hashCode());
        Object $expirationTime = this.getExpirationTime();
        result = result * 59 + ($expirationTime == null ? 43 : $expirationTime.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        Object $role = this.getRole();
        result = result * 59 + ($role == null ? 43 : $role.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $assetID = this.getAssetID();
        result = result * 59 + ($assetID == null ? 43 : $assetID.hashCode());
        Object $assetType = this.getAssetType();
        result = result * 59 + ($assetType == null ? 43 : $assetType.hashCode());
        Object $assetModel = this.getAssetModel();
        result = result * 59 + ($assetModel == null ? 43 : $assetModel.hashCode());
        Object $notes = this.getNotes();
        result = result * 59 + ($notes == null ? 43 : $notes.hashCode());
        Object $assets = this.getAssets();
        result = result * 59 + ($assets == null ? 43 : $assets.hashCode());
        return result;
    }

    public String toString() {
        int var10000 = this.getStatusCode();
        return "ReqRes(statusCode=" + var10000 + ", error=" + this.getError() + ", message=" + this.getMessage() + ", token=" + this.getToken() + ", refreshToken=" + this.getRefreshToken() + ", expirationTime=" + this.getExpirationTime() + ", name=" + this.getName() + ", email=" + this.getEmail() + ", role=" + this.getRole() + ", password=" + this.getPassword() + ", assetID=" + this.getAssetID() + ", assetType=" + this.getAssetType() + ", assetModel=" + this.getAssetModel() + ", isActive=" + this.getIsActive() + ", notes=" + this.getNotes() + ", assets=" + this.getAssets() + ")";
    }
}
