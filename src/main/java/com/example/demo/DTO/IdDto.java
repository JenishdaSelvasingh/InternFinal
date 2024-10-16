package com.example.demo.DTO;

import org.springframework.stereotype.Component;

@Component
public class IdDto {
    private String idValue;
    private String idType;

    public String getIdValue() {
        return this.idValue;
    }

    public String getIdType() {
        return this.idType;
    }

    public void setIdValue(final String idValue) {
        this.idValue = idValue;
    }

    public void setIdType(final String idType) {
        this.idType = idType;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof IdDto)) {
            return false;
        } else {
            IdDto other = (IdDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$idValue = this.getIdValue();
                Object other$idValue = other.getIdValue();
                if (this$idValue == null) {
                    if (other$idValue != null) {
                        return false;
                    }
                } else if (!this$idValue.equals(other$idValue)) {
                    return false;
                }

                Object this$idType = this.getIdType();
                Object other$idType = other.getIdType();
                if (this$idType == null) {
                    if (other$idType != null) {
                        return false;
                    }
                } else if (!this$idType.equals(other$idType)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof IdDto;
    }

    public int hashCode() {
        int PRIME = 1; //true
        int result = 1;
        Object $idValue = this.getIdValue();
        result = result * 59 + ($idValue == null ? 43 : $idValue.hashCode());
        Object $idType = this.getIdType();
        result = result * 59 + ($idType == null ? 43 : $idType.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getIdValue();
        return "IdDto(idValue=" + var10000 + ", idType=" + this.getIdType() + ")";
    }

    public IdDto(final String idValue, final String idType) {
        this.idValue = idValue;
        this.idType = idType;
    }

    public IdDto() {
    }
}
