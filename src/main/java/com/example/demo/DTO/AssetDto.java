package com.example.demo.DTO;

import java.time.LocalDateTime;

public class AssetDto {
    private String id;
    private String assetID;
    private String assetType;
    private String assetModel;
    private Boolean isActive;
    private String notes;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

    public String getId() {
        return this.id;
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

    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public void setId(final String id) {
        this.id = id;
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

    public void setDateCreated(final LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AssetDto)) {
            return false;
        } else {
            AssetDto other = (AssetDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label107: {
                    Object this$isActive = this.getIsActive();
                    Object other$isActive = other.getIsActive();
                    if (this$isActive == null) {
                        if (other$isActive == null) {
                            break label107;
                        }
                    } else if (this$isActive.equals(other$isActive)) {
                        break label107;
                    }

                    return false;
                }

                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$assetID = this.getAssetID();
                Object other$assetID = other.getAssetID();
                if (this$assetID == null) {
                    if (other$assetID != null) {
                        return false;
                    }
                } else if (!this$assetID.equals(other$assetID)) {
                    return false;
                }

                label86: {
                    Object this$assetType = this.getAssetType();
                    Object other$assetType = other.getAssetType();
                    if (this$assetType == null) {
                        if (other$assetType == null) {
                            break label86;
                        }
                    } else if (this$assetType.equals(other$assetType)) {
                        break label86;
                    }

                    return false;
                }

                label79: {
                    Object this$assetModel = this.getAssetModel();
                    Object other$assetModel = other.getAssetModel();
                    if (this$assetModel == null) {
                        if (other$assetModel == null) {
                            break label79;
                        }
                    } else if (this$assetModel.equals(other$assetModel)) {
                        break label79;
                    }

                    return false;
                }

                label72: {
                    Object this$notes = this.getNotes();
                    Object other$notes = other.getNotes();
                    if (this$notes == null) {
                        if (other$notes == null) {
                            break label72;
                        }
                    } else if (this$notes.equals(other$notes)) {
                        break label72;
                    }

                    return false;
                }

                Object this$dateCreated = this.getDateCreated();
                Object other$dateCreated = other.getDateCreated();
                if (this$dateCreated == null) {
                    if (other$dateCreated != null) {
                        return false;
                    }
                } else if (!this$dateCreated.equals(other$dateCreated)) {
                    return false;
                }

                Object this$lastUpdated = this.getLastUpdated();
                Object other$lastUpdated = other.getLastUpdated();
                if (this$lastUpdated == null) {
                    if (other$lastUpdated != null) {
                        return false;
                    }
                } else if (!this$lastUpdated.equals(other$lastUpdated)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AssetDto;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        Object $isActive = this.getIsActive();
        result = result * 59 + ($isActive == null ? 43 : $isActive.hashCode());
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $assetID = this.getAssetID();
        result = result * 59 + ($assetID == null ? 43 : $assetID.hashCode());
        Object $assetType = this.getAssetType();
        result = result * 59 + ($assetType == null ? 43 : $assetType.hashCode());
        Object $assetModel = this.getAssetModel();
        result = result * 59 + ($assetModel == null ? 43 : $assetModel.hashCode());
        Object $notes = this.getNotes();
        result = result * 59 + ($notes == null ? 43 : $notes.hashCode());
        Object $dateCreated = this.getDateCreated();
        result = result * 59 + ($dateCreated == null ? 43 : $dateCreated.hashCode());
        Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : $lastUpdated.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getId();
        return "AssetDto(id=" + var10000 + ", assetID=" + this.getAssetID() + ", assetType=" + this.getAssetType() + ", assetModel=" + this.getAssetModel() + ", isActive=" + this.getIsActive() + ", notes=" + this.getNotes() + ", dateCreated=" + this.getDateCreated() + ", lastUpdated=" + this.getLastUpdated() + ")";
    }

    public AssetDto() {
    }

    public AssetDto(final String id, final String assetID, final String assetType, final String assetModel, final Boolean isActive, final String notes, final LocalDateTime dateCreated, final LocalDateTime lastUpdated) {
        this.id = id;
        this.assetID = assetID;
        this.assetType = assetType;
        this.assetModel = assetModel;
        this.isActive = isActive;
        this.notes = notes;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }
}
