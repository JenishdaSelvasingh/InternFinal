
package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Entity
@Component
public class ItemEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String ntId;
    private String itemType;

    public Long getId() {
        return this.id;
    }

    public String getNtId() {
        return this.ntId;
    }

    public String getItemType() {
        return this.itemType;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setNtId(final String ntId) {
        this.ntId = ntId;
    }

    public void setItemType(final String itemType) {
        this.itemType = itemType;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ItemEntity)) {
            return false;
        } else {
            ItemEntity other = (ItemEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label47;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label47;
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

                Object this$itemType = this.getItemType();
                Object other$itemType = other.getItemType();
                if (this$itemType == null) {
                    if (other$itemType != null) {
                        return false;
                    }
                } else if (!this$itemType.equals(other$itemType)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ItemEntity;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $ntId = this.getNtId();
        result = result * 59 + ($ntId == null ? 43 : $ntId.hashCode());
        Object $itemType = this.getItemType();
        result = result * 59 + ($itemType == null ? 43 : $itemType.hashCode());
        return result;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "ItemEntity(id=" + var10000 + ", ntId=" + this.getNtId() + ", itemType=" + this.getItemType() + ")";
    }

    public ItemEntity(final Long id, final String ntId, final String itemType) {
        this.id = id;
        this.ntId = ntId;
        this.itemType = itemType;
    }

    public ItemEntity() {
    }
}
