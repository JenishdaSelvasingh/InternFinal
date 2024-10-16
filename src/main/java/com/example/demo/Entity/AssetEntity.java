package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
        name = "assets"
)
public class AssetEntity implements Serializable {
    @Id
    @GeneratedValue(
            generator = "uuid"
    )
    @GenericGenerator(
            name = "uuid",
            strategy = "uuid2"
    )
    private String id;
    @Column(
            name = "assetID"
    )
    private String assetID;
    @Column(
            name = "assetType"
    )
    private String assetType;
    @Column(
            name = "assetModel"
    )
    private String assetModel;
    @Column(
            name = "isActive"
    )
    private Boolean isActive;
    @Column(
            name = "notes"
    )
    private String notes;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "userID"
    )
    private UserEntity userEntity;
    @CreationTimestamp
    @Column(
            name = "dateCreated"
    )
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    @Column(
            name = "lastUpdated"
    )
    private LocalDateTime lastUpdated;

    public static AssetEntityBuilder builder() {
        return new AssetEntityBuilder();
    }

    public AssetEntity() {
    }

    public AssetEntity(final String id, final String assetID, final String assetType, final String assetModel, final Boolean isActive, final String notes, final UserEntity userEntity, final LocalDateTime dateCreated, final LocalDateTime lastUpdated) {
        this.id = id;
        this.assetID = assetID;
        this.assetType = assetType;
        this.assetModel = assetModel;
        this.isActive = isActive;
        this.notes = notes;
        this.userEntity = userEntity;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
    }

    public String toString() {
        String var10000 = this.getId();
        return "AssetEntity(id=" + var10000 + ", assetID=" + this.getAssetID() + ", assetType=" + this.getAssetType() + ", assetModel=" + this.getAssetModel() + ", isActive=" + this.getIsActive() + ", notes=" + this.getNotes() + ", userEntity=" + this.getUserEntity() + ", dateCreated=" + this.getDateCreated() + ", lastUpdated=" + this.getLastUpdated() + ")";
    }

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

    public UserEntity getUserEntity() {
        return this.userEntity;
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

    public void setUserEntity(final UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void setDateCreated(final LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public static class AssetEntityBuilder {
        private String id;
        private String assetID;
        private String assetType;
        private String assetModel;
        private Boolean isActive;
        private String notes;
        private UserEntity userEntity;
        private LocalDateTime dateCreated;
        private LocalDateTime lastUpdated;

        AssetEntityBuilder() {
        }

        public AssetEntityBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public AssetEntityBuilder assetID(final String assetID) {
            this.assetID = assetID;
            return this;
        }

        public AssetEntityBuilder assetType(final String assetType) {
            this.assetType = assetType;
            return this;
        }

        public AssetEntityBuilder assetModel(final String assetModel) {
            this.assetModel = assetModel;
            return this;
        }

        public AssetEntityBuilder isActive(final Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public AssetEntityBuilder notes(final String notes) {
            this.notes = notes;
            return this;
        }

        public AssetEntityBuilder userEntity(final UserEntity userEntity) {
            this.userEntity = userEntity;
            return this;
        }

        public AssetEntityBuilder dateCreated(final LocalDateTime dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public AssetEntityBuilder lastUpdated(final LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public AssetEntity build() {
            return new AssetEntity(this.id, this.assetID, this.assetType, this.assetModel, this.isActive, this.notes, this.userEntity, this.dateCreated, this.lastUpdated);
        }

        public String toString() {
            return "AssetEntity.AssetEntityBuilder(id=" + this.id + ", assetID=" + this.assetID + ", assetType=" + this.assetType + ", assetModel=" + this.assetModel + ", isActive=" + this.isActive + ", notes=" + this.notes + ", userEntity=" + this.userEntity + ", dateCreated=" + this.dateCreated + ", lastUpdated=" + this.lastUpdated + ")";
        }
    }
}
