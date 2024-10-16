

package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(
        shape = Shape.OBJECT
)
public enum AccessTag {
    USER,
    ADMIN,
    ADMINUSER;

    private AccessTag() {
    }

    public static AccessTag fromValue(String v) {
        return valueOf(v);
    }

    public String toString() {
        return this.name();
    }
}
